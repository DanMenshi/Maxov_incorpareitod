package fitness;

import fitness.enums.FitnessZone;
import fitness.enums.TypeSubscription;

import java.time.LocalDate;
import java.time.LocalTime;

public class Subscription {
    TypeSubscription abon;
    FitnessZone[] vilotileZone = new FitnessZone[3];
    //indexes:
    //0 - gym
    //1 - group excersize
    //2 - swimpool
    LocalTime beginTime;
    LocalTime endTime;

    LocalDate registrationDate;
    LocalDate expireDate;

    public Subscription(TypeSubscription abon) {
        this.abon = abon;
        if(abon == TypeSubscription.DAYTIME)
        {
            beginTime = LocalTime.of(10, 00);
            endTime = LocalTime.of(17, 00);
            vilotileZone[0] = FitnessZone.GYM;
            vilotileZone[1] = null;
            vilotileZone[2] = FitnessZone.SWIMPOOL;
        }
        else if(abon == TypeSubscription.FULLTIME)
        {
            beginTime = LocalTime.of(6, 00);
            endTime = LocalTime.of(23, 59);
            vilotileZone[0] = FitnessZone.GYM;
            vilotileZone[1] = FitnessZone.GROUP_EXERCISE;
            vilotileZone[2] = FitnessZone.SWIMPOOL;
        }
        else if(abon == TypeSubscription.ONETIME)
        {
            beginTime = LocalTime.of(10, 00);
            endTime = LocalTime.of(17, 00);
            vilotileZone[0] = FitnessZone.GYM;
            vilotileZone[1] = null;
            vilotileZone[2] = null;
        }

    }

    public Subscription(TypeSubscription abon, LocalDate regDate, LocalDate expDate) {
        this(abon); // Вызываем старый конструктор для настройки времени (часов)
        this.registrationDate = regDate;
        this.expireDate = expDate;
    }


    public TypeSubscription getAbon() {
        return abon;
    }

    public LocalTime getBeginTime() {
        return beginTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
