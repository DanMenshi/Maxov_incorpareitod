package fitness;

import fitness.enums.Gender;

public class Client {
    String name;
    Gender gender;
    int age;
    Subscription subs;

    public Client(String name, Gender gender, int age, Subscription subs) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.subs = subs;
    }

    public Subscription getSubs() {
        return subs;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }
}
