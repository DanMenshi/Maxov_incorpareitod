package Bank_task;

import Bank_task.enums.TypeNameTransfer;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private ArrayList<Account> accounts;

    public Bank(String name) {
        this.name = name;
        accounts = new ArrayList<Account>();
    }

    public void addAccount(Account account) {
        if (inAccount(account)) return;
        accounts.add(account);
    }

    public void putMoney(Account acc, int _sum) {
        if (0 >= _sum) return;
        acc.sum(_sum);
        Operation operation = new Operation(TypeNameTransfer.ADDITION, _sum);
        acc.getOperations().add(operation);
    }

    public void sendMoney(Account acc, int _sum) {
        if (acc.getSum() < _sum) return;
        else {
            acc.minus(_sum);
            Operation operationPayment = new Operation(TypeNameTransfer.PAYMENT, _sum * -1);
            acc.getOperations().add(operationPayment);
        }
        if (acc.willCashBack(_sum)) {
            int cashBack = acc.calculationCashBack(_sum);
            acc.sum(cashBack);
            Operation operationCashBack = new Operation(TypeNameTransfer.CASH_BACK, cashBack);
            acc.getOperations().add(operationCashBack);
        }
        if (!acc.haveBonus()) {
            acc.sum(1000);
            Operation operationWelcome = new Operation(TypeNameTransfer.WELCOME_BONUS, 1000);
            acc.getOperations().add(operationWelcome);
        }
    }

    public void transferMoney(Account cl1, Account cl2, int sum_) {
        if (sum_ < 100) return;
        if (!cl1.enoughMoney(sum_)) return;
        if (cl1 == cl2) return;
        if (!inAccount(cl1) || !inAccount(cl2)) return;
        else {
            cl1.minus(sum_);
            Operation operationSend = new Operation(TypeNameTransfer.SEND_TRANSFER, sum_, true, cl2.getAccount());
            cl1.getOperations().add(operationSend);
            cl2.sum(sum_);
            Operation operationAccept = new Operation(TypeNameTransfer.ACCEPT_TRANSFER, sum_, true, cl1.getAccount());
            cl2.getOperations().add(operationAccept);
        }
    }

    public boolean inAccount(Account account) {
        for(Account acc : accounts) {
            if (acc.getAccount().getAccountNumber().equals(account.getAccount().getAccountNumber())) {
                return true;
            }
        }
        return false;
    }
    public boolean inAccount(String accountNumber) {
        for(Account acc : accounts) {
            if (accountNumber.equals(acc.getAccount().getAccountNumber())) {
                return true;
            }
        }
        return false;
    }
    public Account getInAccount(String accountNumber) {
        for(Account acc : accounts) {
            if (accountNumber.equals(acc.getAccount().getAccountNumber())) {
                return acc;
            }
        }
        return null;
    }

    public void migrateAccounts(List<Account> accountsFromDb) {
        int count = 0;
        for (Account i : accountsFromDb) {
            addAccount(i);
            ++count;
        }
        System.out.println("Успешно добавлено " + count + " аккаунтов из БД.");
    }

    public String getName() {
        return name;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
