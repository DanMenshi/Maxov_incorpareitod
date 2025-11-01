package Bank_task;

import Bank_task.enums.TypeLevel;
import Bank_task.enums.TypeNameTransfer;

import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<Account> accounts;

    public Bank(String name) {
        this.name = name;
        accounts = new ArrayList<Account>();
    }

    public void addAccount(Account account) {
        if (inAccount(account)) return;
        accounts.addLast(account);
    }

    public void putMoney(Account acc, int _sum) {
        if (0 >= _sum) return;
        acc.sum(_sum);
        Operation operation = new Operation(TypeNameTransfer.ADDITION, _sum, false);
        acc.getOperations().addLast(operation);
    }


    public boolean inAccount(Account account) {
        for(Account acc : accounts) {
            if (acc.getAccount().equals(account.getAccount())) {
                return true;
            }
        }
        return false;
    }
}
