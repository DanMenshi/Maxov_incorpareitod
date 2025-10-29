package Bank_task;

import Bank_task.enums.TypeLevel;

public class Client {
    private String firstName;
    private String secondName;
    private TypeLevel level;
    private Account account;


    public Client(String firstName, String secondName, TypeLevel level, Account account) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.level = level;
        this.account = account;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public TypeLevel getLevel() {
        return level;
    }

    public Account getAccount() {
        return account;
    }

    public String getNumberAccount() { return account.getAccountNumber(); }
}
