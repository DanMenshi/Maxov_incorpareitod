package Bank_task.enums;

public enum TypeNameTransfer {
    WELCOME_BONUS("Приветственный бонус"),
    ACCEPT_TRANSFER("Принятый перевод"),
    SEND_TRANSFER("Отправленный перевод"),
    CASH_BACK("Кеш-бэк"),
    PAYMENT("Оплата услуги"),
    ADDITION("Пополнение баланса");
    private String s;

    TypeNameTransfer(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }
}
