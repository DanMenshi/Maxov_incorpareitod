package fitness;

import fitness.enums.TypeSubscription;

import java.lang.invoke.LambdaConversionException;
import java.time.LocalTime;

public class Fitness {
    int gymIndx = 0;
    int swimPoolIndx = 0;
    int groupExercizeIndx = 0;

    Client[] gym = new Client[50];
    Client[] swimPool = new Client[20];
    Client[] groupExercize = new Client[10];

    static LocalTime startWork = LocalTime.of(6,0);
    static LocalTime finishWork = LocalTime.of(23,0);

    public void addGym(Client client, LocalTime time) {
        if (client.getSubs().getAbon() == TypeSubscription.FULLTIME && time.isAfter(startWork) && time.isBefore(finishWork)) {
            System.out.println("added " + client.getName());
            gym[gymIndx] = client;
            ++gymIndx;
        } else if (time.isAfter(client.getSubs().beginTime) && time.isBefore(client.getSubs().endTime)) {
            System.out.println("added " + client.getName());
            gym[gymIndx] = client;
            ++gymIndx;
        } else {
            System.out.println("Failed add " + client.getName());
        }
    }
    public void addSwimPool(Client client, LocalTime time) {
        if (client.getSubs().getAbon() == TypeSubscription.FULLTIME && time.isAfter(startWork) && time.isBefore(finishWork)) {
            System.out.println("added " + client.getName());
            swimPool[swimPoolIndx] = client;
            ++swimPoolIndx;
        } else if (client.getSubs().getAbon() == TypeSubscription.ONETIME && time.isAfter(client.getSubs().beginTime) && time.isBefore(client.getSubs().endTime)) {
            System.out.println("added " + client.getName());
            swimPool[swimPoolIndx] = client;
            ++swimPoolIndx;
        } else {
            System.out.println("Failed add " + client.getName());
        }
    }
    public void addGroupExercize(Client client, LocalTime time) {
        if (client.getSubs().getAbon() == TypeSubscription.FULLTIME && time.isAfter(startWork) && time.isBefore(finishWork)) {
            System.out.println("added " + client.getName());
            groupExercize[groupExercizeIndx] = client;
            ++groupExercizeIndx;
        }
        else {
            System.out.println("Failed add " + client.getName());
        }
    }
    public void grandmaComeBack(LocalTime time) {
        for (int i = 0; i < gymIndx; ++i) {
            if (time.isAfter(startWork) && time.isAfter(finishWork)) {
                System.out.println("Fuck off: " + gym[i].getName() );
                gym[i] = null;
            }
            else if (gym[i].getSubs().getAbon() != TypeSubscription.FULLTIME && time.isAfter(gym[i].getSubs().endTime)) {
                System.out.println("Fuck off: " + gym[i].getName());
                gym[i] = null;
            }
        }
        for (int i = 0; i < swimPoolIndx; ++i) {
            if (time.isAfter(startWork) && time.isAfter(finishWork)) {
                System.out.println("Fuck off: " + swimPool[i].getName() );
                swimPool[i] = null;
            }
            else if (swimPool[i].getSubs().getAbon() != TypeSubscription.DAYTIME && time.isAfter(swimPool[i].getSubs().endTime)) {
                System.out.println("Fuck off: " + swimPool[i].getName());
                swimPool[i] = null;
            }
        }
        for (int i = 0; i < groupExercizeIndx; ++i) {
            if (time.isAfter(finishWork)) {
                System.out.println("Fuck off: " + groupExercize[i].getName() );
                groupExercize[i] = null;
            }
        }
    }
}
