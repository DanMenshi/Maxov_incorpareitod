package Bank_task;

import Bank_task.enums.TypeLevel;

import java.util.ArrayList;

public class Account {
    private String accountNumber;
    private int sum;
    private ArrayList<Integer> operations;

    private String generateAccNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; ++i) {
            sb.append((int) (Math.random() * (9999 - 1000 + 1)) + 1000);
        }
        return sb.toString();
    }

    public Account() {
        this.accountNumber = generateAccNumber();
        this.sum = 0;
        this.operations = new ArrayList<Integer>();
    }

    public void plus(int sum_) {
        if (sum_ <= 0) {
            System.out.println("Нельзя положить на счет сумм равную или меньше нуля");
        } else {
            operations.add(sum_);
            sum += sum_;
        }
    }

    public void minus(int sum_) {
        if (sum_ <= 0) {
            System.out.println("Нельзя снять с счёта сумму равную или меньше нуля");
        }
        else if ((sum - sum_) < 0) {
            System.out.println("На балансе недостаточно средств");
        }
        else {
            if (HaveBonus()) {
                plus(1000);
            }
            sum -= sum_;
            operations.add(sum_ * -1);
        }
    }
    public boolean HaveBonus() {
        boolean a = false;
        for (int el : operations) {
            if (el < 0) {
                a = true;
            }
        }
        return !a;
    }

    public void discount(int sum_, TypeLevel level) {
        if (level == TypeLevel.BASE && sum_ >= 10000) plus(sum_ / 100);
        if (level == TypeLevel.PREMIUM && sum_ >= 10000) plus(sum_ / 100 * 5);
        if (level == TypeLevel.VIP && sum_ < 10000) plus(sum_ / 100);
        if (level == TypeLevel.VIP && sum_ >= 10000) plus(sum_ / 100 * 5);
        if (level == TypeLevel.VIP && sum_ >= 100000) plus(sum_ / 100 * 10);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getSum() {
        return sum;
    }

    public ArrayList<Integer> getOperations() {
        return operations;
    }

}

