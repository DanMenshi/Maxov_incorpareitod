package Bank_task;

import Bank_task.enums.TypeLevel;

import java.util.ArrayList;

public class AccountNumber {
    private final String accountNumber;
    private static final ArrayList<String> list = new ArrayList<String>();

    public AccountNumber(Client clientName) {
        this.accountNumber = generateAccNumber(clientName);
    }

    private String generateAccNumber(Client clientName) {
        StringBuilder sb = new StringBuilder();
        sb.append((int)clientName.getFirstName().charAt(0));
        while (sb.length() != 20) {
            sb.append((int) (0 + Math.random() * 10) );
        }
        boolean condition = false;

        if (list.isEmpty()) {
            list.addLast(sb.toString());
            return sb.toString();
        } else {
            for (String el :  list) {
                if (el.contentEquals(sb)) {
                    condition = true;
                }
            }
        }
        if (condition) {
            System.out.println("вероятность что вы видите это сообщение 10 в 16 степени");
            return generateAccNumber(clientName);
        }
        else {
            list.addLast(sb.toString());
            return sb.toString();
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}