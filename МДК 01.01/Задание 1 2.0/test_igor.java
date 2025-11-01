package Bank_task;

import Bank_task.enums.TypeLevel;

public class test_igor {

/*    public AccountNumber(Client clientName, String s) {
        this.accountNumber = detgenerateAccNumber(clientName);
    }

    private String detgenerateAccNumber(Client clientName) {
        StringBuilder sb = new StringBuilder();
        sb.append(list.getLast());

        boolean condition = false;

        if (list.isEmpty()) {
            list.addLast(sb.toString());
            return sb.toString();
        } else {
            for (String el : list) {
                if (el.contentEquals(sb)) {
                    condition = true;
                }
            }
        }

        if (condition) {
            System.out.println("Рекурсия!");
            return generateAccNumber(clientName);
        } else {
            list.addLast(sb.toString());
            return sb.toString();
        }
    }

    private void determinateAddBankScore(String bankScore) {
        list.addLast(bankScore);
    }

    private String generateScore(Client clientName) {
        StringBuilder sb = new StringBuilder();
        sb.append((int) clientName.getFirstName().charAt(0));
        while (sb.length() != 20) {
            sb.append((int) (0 + Math.random() * 10));
        }

        return sb.toString();
    }

    private String determinateGenerateAccNumber(String bankScore, Client clientName) {

        boolean condition = false;

        if (list.isEmpty()) {
            list.addLast(bankScore);
            return bankScore;
        } else {
            for (String el : list) {
                if (el.contentEquals(bankScore)) {
                    condition = true;
                    break;
                }
            }
        }
        if (condition) {
            System.out.println("Рекурсия!");
            return generateScore(clientName);
        } else {
            list.addLast(bankScore);
            return bankScore;
        }
    }

    public boolean TestGenerateScore() {
        determinateAddBankScore("1040101");
        String secondArg = determinateGenerateAccNumber("1040101", new Client("А", "B", TypeLevel.BASE));
        if (list.getLast().contentEquals(secondArg)) {
            System.out.println("List: " + list.getLast() +
                    "second arg: " + secondArg);
            return false;
        } else {
            System.out.println("List: " + list.getLast() +
                    "second arg: " + secondArg);
            return true;
        }
    }*/
}

