import fitness.Client;
import fitness.DatabaseManager;

import fitness.Subscription;
import fitness.enums.Gender;
import fitness.enums.TypeSubscription;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FitnessWindow extends JFrame {
    private final DatabaseManager db = new DatabaseManager();
    private final DefaultTableModel model;

    public FitnessWindow() {
        setTitle("Фитнес Клуб");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"Имя", "Пол", "Возраст", "Абонемент", "Дата регистрации", "Дата окончания"};
        model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        refreshData();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton addBtn = new JButton("Зарегистрировать");
        JButton refreshBtn = new JButton("Обновить");

        panel.add(addBtn);
        panel.add(refreshBtn);
        add(panel, BorderLayout.SOUTH);

        refreshBtn.addActionListener(e -> refreshData());
        addBtn.addActionListener(e -> showRegisterDialog());
    }

    private void refreshData() {
        model.setRowCount(0);
        List<Client> clients = db.loadAllClients();
        for (Client c : clients) {
            model.addRow(new Object[]{
                    c.getName(),
                    c.getGender(),
                    c.getAge(),
                    c.getSubs().getAbon(),
                    c.getSubs().getBeginTime(),
                    "См. в базе"
            });
        }
    }

    private void showRegisterDialog() {
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JComboBox<Gender> genderBox = new JComboBox<>(Gender.values());
        JComboBox<TypeSubscription> subBox = new JComboBox<>(TypeSubscription.values());

        Object[] message = {
                "Имя:", nameField,
                "Возраст:", ageField,
                "Пол:", genderBox,
                "Тип абонемента:", subBox
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Регистрация", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                Gender gender = (Gender) genderBox.getSelectedItem();
                TypeSubscription subType = (TypeSubscription) subBox.getSelectedItem();

                Subscription sub = new Subscription(subType);
                Client client = new Client(name, gender, age, sub);

                db.registerClient(client);
                refreshData();
                JOptionPane.showMessageDialog(this, "Клиент зарегистрирован!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ошибка ввода: " + e.getMessage());
            }
        }
    }
}