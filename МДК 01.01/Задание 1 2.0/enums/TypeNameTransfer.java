package Bank_task.enums;

public enum TypeNameTransfer {
    ACCEPT_TRANSFER("Принятый перевод"),
    SEND_TRANSFER("Отправленный перевод"),
    CASH_BACK("Кеш-бэк"),
    PAYMENT("Оплата услуги"),
    ADDITION("Пополнение баланса");

    TypeNameTransfer(String s) {
    }
}
