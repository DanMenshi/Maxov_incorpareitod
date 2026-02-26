import Bank_task.Account;
import Bank_task.DatabaseManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BankWindow extends JFrame {
    private DatabaseManager dbManager;
    private JTable table;
    private DefaultTableModel tableModel;

    public BankWindow() {
        dbManager = new DatabaseManager(); // Подключаемся к БД

        setTitle("Система управления Банком");
        setSize(800, 500);
        setLocationRelativeTo(null);

        // --- 1. ТАБЛИЦА (Центр) ---
        // Заголовки столбцов
        String[] columns = {"Номер счета", "Имя", "Фамилия", "Баланс", "Статус"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        // Загружаем данные из БД при запуске
        refreshTableData();

        // Добавляем таблицу в панель с прокруткой
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        // --- 2. ПАНЕЛЬ КНОПОК (Низ) ---
        JPanel buttonPanel = new JPanel();

        JButton refreshBtn = new JButton("Обновить");
        JButton transferBtn = new JButton("Перевести средства");
        JButton addClientBtn = new JButton("Добавить клиента");

        buttonPanel.add(refreshBtn);
        buttonPanel.add(transferBtn);
        buttonPanel.add(addClientBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        // --- 3. ЛОГИКА КНОПОК ---

        // Кнопка ОБНОВИТЬ
        refreshBtn.addActionListener(e -> refreshTableData());

        // Кнопка ПЕРЕВЕСТИ (Транзакция из Задания 4)
        transferBtn.addActionListener(e -> {
            showTransferDialog();
        });

        // Кнопка ДОБАВИТЬ (просто заглушка, можно реализовать позже)
        addClientBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Функцию добавления нужно реализовать (Create)");
        });
    }

    // Метод для загрузки данных из БД в таблицу
    private void refreshTableData() {
        // Очищаем таблицу
        tableModel.setRowCount(0);

        // Берем список из БД
        List<Account> accounts = dbManager.getAllAccountsFromDB();

        // Заполняем таблицу
        for (Account acc : accounts) {
            Object[] row = {
                    acc.getAccount().getAccountNumber(),
                    acc.getClient().getFirstName(),
                    acc.getClient().getSecondName(),
                    acc.getSum(),
                    acc.getClient().getLevel()
            };
            tableModel.addRow(row);
        }
    }

    // Диалоговое окно для перевода денег
    private void showTransferDialog() {
        // Создаем поля ввода
        JTextField fromField = new JTextField();
        JTextField toField = new JTextField();
        JTextField amountField = new JTextField();

        Object[] message = {
                "Счет отправителя:", fromField,
                "Счет получателя:", toField,
                "Сумма:", amountField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Перевод средств", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String from = fromField.getText();
                String to = toField.getText();
                int amount = Integer.parseInt(amountField.getText());

                // ВЫЗЫВАЕМ ВАШ МЕТОД ТРАНЗАКЦИИ
                boolean success = dbManager.transferMoneyDB(from, to, amount);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Успешно переведено!");
                    refreshTableData(); // Обновляем таблицу, чтобы увидеть новый баланс
                } else {
                    JOptionPane.showMessageDialog(this, "Ошибка перевода (проверьте баланс или номера)", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Введите корректную сумму!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}