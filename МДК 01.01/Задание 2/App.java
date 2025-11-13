package Bank_task;

import Bank_task.enums.TypeLevel;
import java.util.Scanner;

public class App {



    public void start() {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank("СберБанк");

        for (int i = 1; i < 6; i++) bank.addAccount(new Account(new Client("Base" + i, "User", TypeLevel.BASE)));
        for (int i = 1; i < 6; i++) bank.addAccount(new Account(new Client("Premium" + i, "User", TypeLevel.PREMIUM)));
        for (int i = 1; i < 6; i++) bank.addAccount(new Account(new Client("Vip" + i, "User", TypeLevel.VIP)));
        Account danya = new Account(new Client("Даня", "Меньшиков", TypeLevel.BASE));
        Account igor = new Account(new Client("Игорь", "Бабич", TypeLevel.BASE));

        bank.addAccount(danya);
        bank.addAccount(igor);
        bank.putMoney(danya, 100000);
        bank.sendMoney(danya, 20000);
        bank.transferMoney(danya, igor, 10000);

        while (true) {
            System.out.print("=== " + "Добро пожаловать в " + bank.getName() + " ===" +
                    "\n1. Режим банка (тесты клиентов)." +
                    "\n2. Режим клиента (обычная работа)." +
                    "\nВыберете режим работы: ");

            int choice = sc.nextInt();
            if (choice == 1) {
                System.out.print("\033[H\033[2J"); System.out.flush();
                bankMod(bank, sc);
                break;
            }
            if (choice == 2) {
                System.out.print("\033[H\033[2J"); System.out.flush();
                clientMod(bank, sc);
                break;
            }
            else {
                System.out.print("\033[H\033[2J"); System.out.flush();
            }

            sc.close();
        }

    }
    public void bankMod(Bank bank, Scanner sc) {
        boolean exit = false;
        while (!exit) {
            System.out.print("\n\n=== Меню " + bank.getName() + " ===" +
                    "\n1. Добавить клиента." +
                    "\n2. Показать всех клиентов." +
                    "\n3. Найти клиента по счёту." +
                    "\n0. Выйти." +
                    "\nВаш выбор: ");
            int action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 1: {
                    Account account;
                    System.out.print("Имя: ");
                    String firstName = sc.nextLine();
                    System.out.print("Фамилия: ");
                    String secondName = sc.nextLine();
                    System.out.print("Уровень (1.BASE/ 2.PREMIUM/ 3.VIP/ ): ");
                    String level = sc.nextLine();
                    if (level.equals("1")) {
                        account = new Account(new Client(firstName, secondName, TypeLevel.BASE));
                    }
                    if (level.equals("2")) {
                        account = new Account(new Client(firstName, secondName, TypeLevel.PREMIUM));
                    }
                    if (level.equals("3")) {
                        account = new Account(new Client(firstName, secondName, TypeLevel.VIP));
                    }
                    else {
                        System.out.println("Не верно указан уровень. Установлен BASE.");
                        account = new Account(new Client(firstName, secondName, TypeLevel.BASE));
                    }
                    bank.addAccount(account);
                    System.out.println("Клиент добавлен. Его номер счета: [ " + account.getAccount().getPrintAccountNumber() + " ].");
                    break;
                }
                case 2: {
                    int index = 1;
                    for (Account acc : bank.getAccounts()) {
                        System.out.println(index + ". " + acc.toString());
                        ++index;
                    }
                    System.out.print("\nХотите узнать подробно о отдельном пользователе? (1.да/2.нет)\nУкажите: ");
                    String choice = sc.nextLine();
                    if (choice.equals("1")) {
                        System.out.print("Укажите индекс клиента: ");
                        int choiceIndex = sc.nextInt();
                        if (0 < choiceIndex && choiceIndex < index) {
                            index = 1;
                            for(Account acc : bank.getAccounts()) {
                                if (index == choiceIndex) {
                                    acc.printClient();
                                }
                                ++index;
                            }
                        }
                        sc.nextLine();
                    }
                    break;
                }
                case 3: {
                    System.out.print("Введите счёт клиента (без пробелов): ");
                    String accountNumber = sc.nextLine();
                    if(bank.inAccount(accountNumber)) {
                        System.out.println();
                        bank.getInAccount(accountNumber).printClient();
                    } else {
                        System.out.println("Такого счёта не найдена в нашем банке!");
                    }
                    break;
                }
                case 0: {
                    System.out.println("Выход...");
                    exit = true;
                }

            }
        }
    }
    public void clientMod(Bank bank, Scanner sc) {

    }
}
