package Bank_task;

import Bank_task.enums.TypeLevel;

public class App {
    public static void main(String[] args) {
        Client petya = new Client("Петя", "Иванов", TypeLevel.BASE, new Account());
        Client danya = new Client("Даня", "Меньшиков", TypeLevel.PREMIUM, new Account());
        Bank t = new Bank("TBank");
        t.add(petya); t.add(danya);

        t.plusSum(11000, petya.getNumberAccount());
        t.transferP2P(petya.getNumberAccount(), 11000, danya.getNumberAccount());

        t.printClient(petya.getNumberAccount());
    }
}
