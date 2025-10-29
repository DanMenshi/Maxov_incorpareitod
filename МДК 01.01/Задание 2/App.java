package Bank_task;

import Bank_task.enums.TypeLevel;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank("TBank");
        for (int i = 1; i < 6; i++) bank.add(new Client("Base" + i, "User", TypeLevel.BASE, new Account()));
        for (int i = 1; i < 6; i++) bank.add(new Client("Premium" + i, "User", TypeLevel.PREMIUM, new Account()));
        for (int i = 1; i < 6; i++) bank.add(new Client("Vip" + i, "User", TypeLevel.VIP, new Account()));
        Client danya = new Client("Даня", "Меньшиков", TypeLevel.PREMIUM, new Account());
        bank.add(danya);

        System.out.println("=== Добро пожаловать в " + bank.getName() + " ===");
        System.out.println("1. Продолжить как клиент");
        System.out.println("2. Продолжить как банк (режим тестирования)");
        System.out.print("Выберите режим: ");

        int mode = sc.nextInt();
        sc.nextLine(); // очистка буфера

        if (mode == 1) {
            runClientCreation(bank, sc);
        } else if (mode == 2) {
            runBankTestMode(bank, sc);
        } else {
            System.out.println("Неверный выбор.");
        }

        sc.close();
    }

    private static void runClientCreation(Bank bank, Scanner sc) {
        System.out.println("\n=== Создание нового клиента ===");
        System.out.print("Имя: ");
        String first = sc.nextLine();
        System.out.print("Фамилия: ");
        String last = sc.nextLine();
        System.out.print("Уровень (BASE / PREMIUM / VIP): ");
        String lvlStr = sc.nextLine().toUpperCase();

        TypeLevel level;
        try {
            level = TypeLevel.valueOf(lvlStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Неверный уровень. Установлен BASE.");
            level = TypeLevel.BASE;
        }

        Client client = new Client(first, last, level, new Account());
        bank.add(client);

        System.out.println("\n✅ Клиент создан!");
        System.out.println("Имя: " + first + " " + last);
        System.out.println("Уровень: " + level);
        System.out.println("Номер счёта: " + client.getNumberAccount());
        System.out.println();

        runClientMode(bank, sc, client.getNumberAccount());
    }

    private static void runClientMode(Bank bank, Scanner sc, String acc) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Меню клиента ===");
            System.out.println("1. Пополнить счёт");
            System.out.println("2. Снять со счёта");
            System.out.println("3. Перевести другому клиенту");
            System.out.println("4. Показать информацию по счёту");
            System.out.println("0. Выйти");
            System.out.print("Ваш выбор: ");

            int action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 1 -> {
                    System.out.print("Введите сумму для пополнения: ");
                    int sum = sc.nextInt();
                    bank.plusSum(sum, acc);
                }
                case 2 -> {
                    System.out.print("Введите сумму для снятия: ");
                    int sum = sc.nextInt();
                    bank.minusSum(sum, acc);
                }
                case 3 -> {
                    System.out.print("Введите сумму перевода: ");
                    int sum = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Введите номер счёта получателя: ");
                    String acc2 = sc.nextLine();
                    bank.transferP2P(acc, sum, acc2);
                }
                case 4 -> bank.printClient(acc);
                case 0 -> {
                    System.out.println("Выход...");
                    exit = true;
                }
                default -> System.out.println("Некорректный выбор.");
            }
        }
    }

    private static void runBankTestMode(Bank bank, Scanner sc) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Меню банка ===");
            System.out.println("1. Добавить клиента");
            System.out.println("2. Показать всех клиентов");
            System.out.println("3. Найти клиента по счёту");
            System.out.println("0. Выйти");
            System.out.print("Ваш выбор: ");

            int action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 1 -> {
                    System.out.print("Имя: ");
                    String first = sc.nextLine();
                    System.out.print("Фамилия: ");
                    String last = sc.nextLine();
                    System.out.print("Уровень (BASE / PREMIUM / VIP): ");
                    String lvlStr = sc.nextLine().toUpperCase();
                    TypeLevel level;
                    try {
                        level = TypeLevel.valueOf(lvlStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Неверный уровень. Установлен BASE.");
                        level = TypeLevel.BASE;
                    }

                    Client newClient = new Client(first, last, level, new Account());
                    bank.add(newClient);
                    System.out.println("✅ Клиент добавлен. Номер счёта: " + newClient.getNumberAccount());
                }
                case 2 -> {
                    System.out.println("=== Все клиенты ===");
                    bank.printAllClients();
                }
                case 3 -> {
                    System.out.print("Введите номер счёта: ");
                    String acc = sc.nextLine();
                    bank.printClient(acc);
                }
                case 0 -> {
                    System.out.println("Выход...");
                    exit = true;
                }
                default -> System.out.println("Некорректный выбор.");
            }
        }
    }
}
