package Bank_task;

import Bank_task.enums.TypeLevel;

import java.util.ArrayList;

public class Account {
    private Client client;
    private String accountNumber;
    private int sum;

    private ArrayList<Integer> operation;

    public Account(Client client_, String accountNumber_) {
        this.client = client_;
        this.accountNumber = accountNumber_;
        this.sum = 0;
        this.operation = new ArrayList<Integer>();
    }

    void plusSum(int sum_) {
        if (sum_ <= 0) {
            System.out.println("Нельзя положить на счет сумм равную или меньше нуля");
        } else {
            operation.add(sum_);
            if (operation.size() == 1) {
                System.out.println("Вам начислен приветственный бонус в размере 1000р.");
                sum += 1000;
            }
            sum += sum_;
        }
    }
    void minusSum(int sum_) {
        if (sum_ <= 0) {
            System.out.println("Нельзя положить на счет сумм равную или меньше нуля");
        }
        else if ((sum - sum_) < 0) {
            System.out.println("На балансе недостаточно средств");
        }
        else {
            if (sum_ >= 10000) {
                if (client.getLevel() == TypeLevel.BASE) plusSum(sum_ / 100);
                if (client.getLevel() == TypeLevel.PREMIUM) plusSum(sum_ / 100 * 5);
                if (client.getLevel() == TypeLevel.VIP && sum_ < 10000) {
                    plusSum(sum_ / 100);
                }
                else {
                    if (sum_ >= 100000) plusSum(sum_ / 100 * 10);
                    else {
                        plusSum(sum_ / 100 * 5);
                    }
                }
            }
            sum_ *= -1;
            sum += sum_;
            operation.add(sum_);
        }

    }


}

