package Bank_task;

import Bank_task.enums.TypeLevel;

import java.util.ArrayList;
import java.util.Arrays;

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

    public boolean haveInRegistry() {
        for(String accountNumber1 : list) {
            if (accountNumber1.equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPrintAccountNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < accountNumber.length(); ++i) {
            sb.append(accountNumber.charAt(i));
            if (i == 3 || i == 7 || i == 11 || i == 15) sb.append(" ");
        }
        return sb.toString();
    }
}