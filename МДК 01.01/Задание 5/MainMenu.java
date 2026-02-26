import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public MainMenu() {
        // Настройка окна
        setTitle("Главное меню");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // По центру экрана
        setLayout(new GridLayout(2, 1, 10, 10)); // Сетка: 2 строки, 1 столбец

        // Кнопки
        JButton bankButton = new JButton("Открыть БАНК");
        JButton fitnessButton = new JButton("Открыть ФИТНЕС");

        // Добавляем кнопки в окно
        add(bankButton);
        add(fitnessButton);

        // Логика нажатия на "Банк"
        bankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BankWindow().setVisible(true); // Открываем окно банка
            }
        });

        // Логика нажатия на "Фитнес" (сделайте по аналогии позже)
        fitnessButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Фитнес пока в разработке!");
        });
    }

    public static void main(String[] args) {
        // Запуск приложения
        SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
    }
}