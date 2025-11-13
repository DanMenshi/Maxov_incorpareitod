package Bank_task;

import Bank_task.enums.TypeLevel;
import Bank_task.enums.TypeNameTransfer;

import java.util.ArrayList;

public class Account {
    private final Client client;
    private final AccountNumber account;
    private int sum;
    private final ArrayList<Operation> operations;

    public Account(Client client) {
        this.client = client;
        this.sum = 0;
        account = new AccountNumber(this.client);
        operations = new ArrayList<Operation>();
    }

    public void sum(int _sum) {
        sum += _sum;
    }
    public void minus(int _sum) {
        sum -= _sum;
    }
    public boolean haveBonus() {
        for(Operation op : operations) {
           if (op.getName() == TypeNameTransfer.WELCOME_BONUS) {
               return true;
           }
        }
        return false;
    }
    public boolean willCashBack(int sum_) {
        return sum_ >= 10000 || client.getLevel() == TypeLevel.VIP;
    }
    public int calculationCashBack(int sum_) {
        if (sum_ >= 10000 && getClient().getLevel() == TypeLevel.BASE) return sum_/100;
        if (sum_ >= 10000 && getClient().getLevel() == TypeLevel.PREMIUM) return  sum_/100*5;
        if (getClient().getLevel() == TypeLevel.VIP) {
            if (sum_ < 10000) return sum_/100;
            else if (sum_ >= 100000) return sum_/100*10;
            else return  sum_/100*5;
        }
        return -1;
    }
    public boolean enoughMoney(int sum_) {
        return sum - sum_ >= 0;
    }
    public AccountNumber getAccount() {
        return account;
    }

    public ArrayList<Operation> getOperations() {
        return operations;
    }

    public Client getClient() {
        return client;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return "Клиент: " + getClient().getFirstName() + " " + getClient().getSecondName() + ", баланс = " + sum + ", номер счета = [ " + account.getPrintAccountNumber() + " ].";
    }

    public void printClient() {
        System.out.println("=== " + client.getFirstName() + " " + client.getSecondName() + " ===" +
                "\nНомер счёта: [ " + account.getPrintAccountNumber() + " ]." +
                "\nДенег на счету: " + sum +
                "\n=== История счёта ===");
        if (operations.isEmpty()) System.out.println("\tПусто...");
        else {
            int index = operations.size();
            for (;index != 0;) {
                Operation op = operations.get(index - 1);
                System.out.print("\n" + index + ". " + "\tтип операции: " + op.getName().getS() +
                        "   \n\tдата операции: " + op.getDateTime() +
                        "   \n\t\tсумма: " + op.getSum());
                if (op.isThisTransfer()) {
                    System.out.print("   \n\tотправитель: [ " + op.getSender().getPrintAccountNumber() + " ].\n");
                } else System.out.print(".\n");
                --index;
            }
        }
    }
}

