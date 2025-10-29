package Bank_task;

import Bank_task.enums.TypeLevel;

public class Client {
    private String firstName;
    private String secondName;
    private TypeLevel level;


    public Client(String firstName, String secondName, TypeLevel level) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.level = level;
    }

    public String getFirstName() {
        return firstName;
    }

    public TypeLevel getLevel() {
        return level;
    }
}
