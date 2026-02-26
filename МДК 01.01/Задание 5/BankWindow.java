import Bank_task.Account;
import Bank_task.Client;
import Bank_task.DatabaseManager;
import Bank_task.enums.TypeLevel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BankWindow extends JFrame {
    private final DatabaseManager db = new DatabaseManager();
    private final DefaultTableModel model;
    private final JTable table;

    public BankWindow() {
        setTitle("Банковская система");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Закрыть только это окно

        String[] columns = {"Номер счета", "Имя", "Фамилия", "Баланс", "Уровень", "Бонус"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        refreshData(); // Загрузка из БД
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel();

        JButton addBtn = new JButton("Новый клиент");
        JButton transferBtn = new JButton("Перевод средств");
        JButton refreshBtn = new JButton("Обновить");

        panel.add(addBtn);
        panel.add(transferBtn);
        panel.add(refreshBtn);
        add(panel, BorderLayout.SOUTH);


        refreshBtn.addActionListener(e -> refreshData());
        addBtn.addActionListener(e -> showAddClientDialog());
        transferBtn.addActionListener(e -> showTransferDialog());
    }

    private void refreshData() {
        model.setRowCount(0); // Очистка
        List<Account> accounts = db.getAllAccountsFromDB();
        for (Account a : accounts) {
            model.addRow(new Object[]{
                    a.getAccount().getAccountNumber(),
                    a.getClient().getFirstName(),
                    a.getClient().getSecondName(),
                    a.getSum(),
                    a.getClient().getLevel(),
                    a.haveBonus() ? "Да" : "Нет"
            });
        }
    }

    private void showAddClientDialog() {
        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JComboBox<TypeLevel> levelBox = new JComboBox<>(TypeLevel.values());

        Object[] message = {
                "Имя:", nameField,
                "Фамилия:", surnameField,
                "Уровень:", levelBox
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Новый клиент", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                Client client = new Client(nameField.getText(), surnameField.getText(), (TypeLevel) levelBox.getSelectedItem());
                Account account = new Account(client); // Создаем объект
                db.saveAccountToDB(account);
                refreshData();
                JOptionPane.showMessageDialog(this, "Клиент добавлен!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ошибка: " + e.getMessage());
            }
        }
    }

    private void showTransferDialog() {
        JTextField fromField = new JTextField();
        JTextField toField = new JTextField();
        JTextField amountField = new JTextField();

        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            fromField.setText(model.getValueAt(selectedRow, 0).toString());
        }

        Object[] message = {
                "Счет отправителя:", fromField,
                "Счет получателя:", toField,
                "Сумма:", amountField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Перевод", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String from = fromField.getText();
                String to = toField.getText();
                int amount = Integer.parseInt(amountField.getText());

                boolean success = db.transferMoneyDB(from, to, amount);
                if (success) {
                    refreshData();
                    JOptionPane.showMessageDialog(this, "Перевод выполнен успешно!");
                } else {
                    JOptionPane.showMessageDialog(this, "Ошибка перевода (проверьте баланс)", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Введите корректное число!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}