package fitness;

import fitness.enums.Gender;
import fitness.enums.TypeSubscription;

import java.time.LocalTime;
import java.util.IdentityHashMap;

public class App {
    // абонементы:
    // дневной (10:00 - 17:00) - зал,
    // полный (06:00 - 23:59) - все зоны,
    // разовый (10:00 - 17:00) - зал ,басен

    // Три зоны: спортивный зал, групповые занятие, бассейн
    // Зал - 50 человек
    // Групповые занятие - 10 человек
    // Бассейн - 20 человек
    public static void main(String[] args) {
        Fitness a = new Fitness();

        Client Anton = new Client("Anton", Gender.FEMALE, 52, new Subscription(TypeSubscription.DAYTIME));
        Client Igor = new Client("Igor", Gender.FEMALE, 23, new Subscription(TypeSubscription.ONETIME));
        Client Vanya = new Client("Vanya", Gender.FEMALE, 18, new Subscription(TypeSubscription.FULLTIME));

        a.addSwimPool(Igor, LocalTime.of(11,2));
        a.addGym(Anton, LocalTime.of(10,2));
        a.addGroupExercize(Vanya,LocalTime.of(14,0));
        a.grandmaComeBack(LocalTime.of(20,0));
    }
}
