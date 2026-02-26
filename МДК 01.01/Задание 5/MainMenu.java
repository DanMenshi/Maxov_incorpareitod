import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Корпорация Maxov Inc.");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // По центру экрана
        setLayout(new GridLayout(3, 1, 10, 10));

        JLabel title = new JLabel("Система управления", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title);

        JButton bankBtn = new JButton("Открыть Банк");
        bankBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        bankBtn.addActionListener(e -> {
            new BankWindow().setVisible(true);
        });
        add(bankBtn);

        JButton fitnessBtn = new JButton("Открыть Фитнес");
        fitnessBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        fitnessBtn.addActionListener(e -> {
            new FitnessWindow().setVisible(true);
        });
        add(fitnessBtn);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
    }
}