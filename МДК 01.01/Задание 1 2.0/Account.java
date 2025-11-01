package Bank_task;

import Bank_task.enums.TypeLevel;

import java.util.ArrayList;

public class Account {
    private Client client;
    private AccountNumber account;
    private int sum;
    private ArrayList<Operation> operations;

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

    public String getAccount() {
        return account.getAccountNumber();
    }

    public ArrayList<Operation> getOperations() {
        return operations;
    }
}

