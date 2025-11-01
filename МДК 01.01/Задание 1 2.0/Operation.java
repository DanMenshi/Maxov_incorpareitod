package Bank_task;

import Bank_task.enums.TypeNameTransfer;

public class Operation {
    private TypeNameTransfer name;

    private int sum;

    private boolean thisTransfer;
    private AccountNumber sender;

    public Operation(TypeNameTransfer name, int sum, boolean thisTransfer, AccountNumber sender) {
        this.name = name;
        this.sum = sum;
        this.thisTransfer = thisTransfer;
        this.sender = sender;
    }

    public Operation(TypeNameTransfer name, int sum, boolean thisTransfer) {
        this.name = name;
        this.sum = sum;
        this.thisTransfer = thisTransfer;
        this.sender = null;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "name=" + name +
                ", sum=" + sum +
                ", thisTransfer=" + thisTransfer +
                ", sender=" + sender +
                '}';
    }
}
