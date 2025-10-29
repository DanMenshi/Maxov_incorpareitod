package Bank_task;

import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<Client> clients;

    public Bank(String name_) {
        this.name = name_;
        this.clients = new ArrayList<Client>();
    }
    public void add(Client a) {
        clients.addLast(a);
    }

    public void plusSum(int sum_, String accountNumber) {
        boolean have = false;
        for (Client client : clients) {
            if (client.getAccount().getAccountNumber().equals(accountNumber)) {
                have = true;
                client.getAccount().plus(sum_);
                break;
            }
        }
        if (!have) {
            System.out.println("Счет [" + accountNumber + "] не найден в системе.");
        }
    }
    public void minusSum(int sum_, String accountNumber) {
        boolean have = false;
        for (Client client : clients) {
            if (client.getAccount().getAccountNumber().equals(accountNumber)) {
                have = true;
                client.getAccount().minus(sum_);
                client.getAccount().discount(sum_, client.getLevel());
                break;
            }
        }
        if (!have) {
            System.out.println("Счет [" + accountNumber + "] не найден в системе.");
        }
    }
    public void printClient(String accountNumber) {
        boolean have = false;
        for (Client client : clients) {
            if (client.getAccount().getAccountNumber().equals(accountNumber)) {
                have = true;
                System.out.println("Клиент: " + client.getFirstName() + " " + client.getSecondName());
                System.out.println("Счет: " + client.getAccount().getAccountNumber() + "  Сумма: " + client.getAccount().getSum());
                System.out.println("История по счету: "); int i = 1;
                for (int operation : client.getAccount().getOperations()) {
                    System.out.println(i + ". '" + operation + "'. ");
                    ++i;
                }
                break;
            }
        }
        if (!have) {
            System.out.println("Счет [" + accountNumber + "] не найден в системе.");
        }
    }
    public void transferP2P(String accountNumber1, int sum_, String accountNumber2) {
        if (sum_ < 100) {
            System.out.println("Нельзя перевести малую сумму(>100).");
        } else {
            boolean have = false;
            Client client1 = null, client2 = null;
            for (Client client : clients) {
                if (client.getAccount().getAccountNumber().equals(accountNumber1)) {
                    client1 = client;
                    have = true;
                    break;
                }
            }
            if(!have) {
                System.out.println("Счет отправителя [" + accountNumber1 + "] не найден в системе.");
                return;
            }

            boolean var = false;
            for (Client client : clients) {
                if (client.getAccount().getAccountNumber().equals(accountNumber2)) {
                    client2 = client;
                    var = true;
                    break;
                }
            }
            if(!var) {
                System.out.println("Счет получателя [" + accountNumber2 + "] не найден в системе.");
                return;
            }
            client1.getAccount().minus(sum_);
            client2.getAccount().plus(sum_);
            if (client1.getAccount().getOperations().getLast().equals(sum_)) {
                System.out.println("Успешная транзакция от [" + accountNumber1 + "] к [" + accountNumber2 + "].");
            }
        }
    }
    public String getName() {
        return name;
    }

    public void printAllClients() {
        if (clients.isEmpty()) {
            System.out.println("В банке нет клиентов.");
            return;
        }
        for (Client c : clients) {
            System.out.println(c.getFirstName() + " " + c.getSecondName() +
                    " | Уровень: " + c.getLevel() +
                    " | Счёт: " + c.getNumberAccount() +
                    " | Баланс: " + c.getAccount().getSum());
        }
    }

}
