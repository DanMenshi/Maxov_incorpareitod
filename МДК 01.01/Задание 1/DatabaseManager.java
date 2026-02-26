package fitness;

import fitness.enums.Gender;
import fitness.enums.TypeSubscription;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {


    public Connection getConnection() throws SQLException {
        String URL = "jdbc:postgresql://localhost:5432/task_db";
        String USER = "postgres";
        String PASSWORD = "1234";
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void registerClient(Client client) {
        String clientSql = "INSERT INTO fit_clients (full_name, gender, age) VALUES (?, ?, ?) RETURNING id";
        String subSql = "INSERT INTO fit_subscriptions (client_id, sub_type, reg_date, end_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false); // Транзакция

            int clientId = -1;

            try (PreparedStatement ps = conn.prepareStatement(clientSql)) {
                ps.setString(1, client.getName());
                ps.setString(2, client.getGender().name());
                ps.setInt(3, client.getAge());

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    clientId = rs.getInt("id");
                }
            }

            if (clientId != -1) {
                try (PreparedStatement ps = conn.prepareStatement(subSql)) {
                    ps.setInt(1, clientId);
                    ps.setString(2, client.getSubs().getAbon().name());
                    LocalDate regDate = LocalDate.now();
                    LocalDate endDate;

                    if (client.getSubs().getAbon() == TypeSubscription.ONETIME) {
                        endDate = regDate;
                    } else {
                        endDate = regDate.plusYears(1);
                    }

                    ps.setDate(3, Date.valueOf(regDate));
                    ps.setDate(4, Date.valueOf(endDate));

                    ps.executeUpdate();
                }
            }

            conn.commit();
            System.out.println("Клиент " + client.getName() + " успешно зарегистрирован в БД.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> loadAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT c.full_name, c.gender, c.age, s.sub_type, s.reg_date, s.end_date " +
                "FROM fit_clients c " +
                "JOIN fit_subscriptions s ON c.id = s.client_id";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("full_name");
                Gender gender = Gender.valueOf(rs.getString("gender"));
                int age = rs.getInt("age");

                TypeSubscription subType = TypeSubscription.valueOf(rs.getString("sub_type"));
                LocalDate regDate = rs.getDate("reg_date").toLocalDate();
                LocalDate endDate = rs.getDate("end_date").toLocalDate();


                Subscription sub = new Subscription(subType, regDate, endDate);
                Client client = new Client(name, gender, age, sub);

                clients.add(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}