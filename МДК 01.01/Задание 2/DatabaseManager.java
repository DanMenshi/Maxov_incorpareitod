package Bank_task;

import Bank_task.enums.TypeLevel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    public Connection getConnection() throws SQLException {
        String URL = "jdbc:postgresql://localhost:5432/task_db";
        String USER = "postgres";
        String PASSWORD = "1234"; // Ваш пароль великий учитель
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void saveAccountToDB(Account account) {
        String insertClientSQL = "INSERT INTO clients (first_name, second_name, client_level) VALUES (?, ?, ?) RETURNING id";
        String insertAccountSQL = "INSERT INTO accounts (account_number, client_id, balance, has_welcome_bonus) VALUES (?, ?, ?, ?)";
        try (Connection conn = this.getConnection()) {
            int clientId = -1;
            try (PreparedStatement clientStmt = conn.prepareStatement(insertClientSQL)) {
                clientStmt.setString(1, account.getClient().getFirstName());
                clientStmt.setString(2, account.getClient().getSecondName());
                clientStmt.setString(3, account.getClient().getLevel().name());

                ResultSet rs = clientStmt.executeQuery();
                if (rs.next()) {
                    clientId = rs.getInt("id");
                }
            }

            if (clientId != -1) {
                try (PreparedStatement accountStmt = conn.prepareStatement(insertAccountSQL)) {
                    accountStmt.setString(1, account.getAccount().getAccountNumber());
                    accountStmt.setInt(2, clientId);
                    accountStmt.setInt(3, account.getSum());
                    accountStmt.setBoolean(4, account.haveBonus());
                    accountStmt.executeUpdate();
                }
            }
            System.out.println("Аккаунт успешно сохранен в БД!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void migrateAccounts(Bank bank) {
        ArrayList<Account> accounts = bank.getAccounts();
        int count = 0;
        for (Account i : accounts) {
            saveAccountToDB(i);
            ++count;
        }
        System.out.println("Успешно добавлено " + count + " аккаунтов в БД.");
    }

    public List<Account> getAllAccountsFromDB() {
        List<Account> accountsList = new ArrayList<>();

        String sql = "SELECT a.account_number, a.balance, " +
                "c.first_name, c.second_name, c.client_level " +
                "FROM accounts a " +
                "JOIN clients c ON a.client_id = c.id";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String accNum = rs.getString("account_number");
                int balance = rs.getInt("balance");
                String firstName = rs.getString("first_name");
                String secondName = rs.getString("second_name");
                String levelStr = rs.getString("client_level");

                TypeLevel level;
                try {
                    level = TypeLevel.valueOf(levelStr);
                } catch (IllegalArgumentException e) {
                    level = TypeLevel.BASE;
                }

                Client client = new Client(firstName, secondName, level);
                Account account = new Account(client, balance, accNum, false);
                accountsList.add(account);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при чтении из БД:");
            e.printStackTrace();
        }

        return accountsList;
    }

    public boolean transferMoneyDB(String senderAccountNumber, String receiverAccountNumber, int amount) {
        if (amount <= 0) {
            System.out.println("Сумма должна быть больше нуля.");
            return false;
        }

        String withdrawSQL = "UPDATE accounts SET balance = balance - ? WHERE account_number = ? AND balance >= ?";
        String depositSQL = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";

        try (Connection conn = this.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement withdrawStmt = conn.prepareStatement(withdrawSQL);
                 PreparedStatement depositStmt = conn.prepareStatement(depositSQL)) {

                withdrawStmt.setInt(1, amount);
                withdrawStmt.setString(2, senderAccountNumber);
                withdrawStmt.setInt(3, amount);

                int rowsUpdated = withdrawStmt.executeUpdate();
                if (rowsUpdated == 0) {
                    conn.rollback();
                    System.out.println("Ошибка списания. Недостаточно средств или счет не найден.");
                    return false;
                }

                depositStmt.setInt(1, amount);
                depositStmt.setString(2, receiverAccountNumber);
                int rowsDeposited = depositStmt.executeUpdate();

                if (rowsDeposited == 0) {
                    conn.rollback();
                    System.out.println("Ошибка зачисления. Счет получателя не найден.");
                    return false;
                }

                conn.commit();
                System.out.println("Перевод успешно выполнен!");
                return true;

            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Произошла ошибка базы данных. Транзакция отменена.");
                e.printStackTrace();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Account getAccountByNumber(String accountNumber) {
        String sql = "SELECT a.account_number, a.balance, a.has_welcome_bonus, " +
                "c.first_name, c.second_name, c.client_level " +
                "FROM accounts a " +
                "JOIN clients c ON a.client_id = c.id " +
                "WHERE a.account_number = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, accountNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToAccount(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateBalance(String accountNumber, int newBalance) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newBalance);
            ps.setString(2, accountNumber);
            ps.executeUpdate();
            System.out.println("Баланс обновлен.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(String accountNumber) {
        // Из-за ON DELETE CASCADE в SQL, удаление клиента удалит и счет.
        // Но здесь мы удалим счет. Если нужно удалить и клиента, запрос будет сложнее.
        String sql = "DELETE FROM accounts WHERE account_number = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, accountNumber);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Аккаунт " + accountNumber + " удален.");
            } else {
                System.out.println("Аккаунт не найден.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Account mapRowToAccount(ResultSet rs) throws SQLException {
        String accNum = rs.getString("account_number");
        int balance = rs.getInt("balance");
        boolean bonus = rs.getBoolean("has_welcome_bonus");
        String fName = rs.getString("first_name");
        String sName = rs.getString("second_name");

        TypeLevel level;
        try {
            level = TypeLevel.valueOf(rs.getString("client_level"));
        } catch (Exception e) {
            level = TypeLevel.BASE;
        }

        Client client = new Client(fName, sName, level);
        return new Account(client, balance, accNum, bonus);
    }
}
