package Bank_task;

import Bank_task.enums.TypeNameTransfer;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;

public class Operation {
    private TypeNameTransfer name;
    private LocalDateTime dateTime;
    private int sum;
    private boolean thisTransfer;
    private AccountNumber sender;

    public Operation(TypeNameTransfer name, int sum, boolean thisTransfer, AccountNumber sender) {
        this.name = name;
        this.dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        this.sum = sum;
        this.thisTransfer = thisTransfer;
        this.sender = sender;
    }

    public Operation(TypeNameTransfer name, int sum) {
        this.name = name;
        this.dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        this.sum = sum;
        this.thisTransfer = false;
        this.sender = null;
    }

    public TypeNameTransfer getName() {
        return name;
    }

    public int getSum() {
        return sum;
    }

    public boolean isThisTransfer() {
        return thisTransfer;
    }

    public AccountNumber getSender() {
        return sender;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
