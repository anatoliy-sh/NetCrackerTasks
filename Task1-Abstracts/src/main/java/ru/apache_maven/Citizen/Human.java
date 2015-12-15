package ru.apache_maven.Citizen;
import java.util.Random;
/**
 * Created by Анатолий on 09.11.2015.
 */
public class Human extends Creature {
    private Random rnd;


    public Human(String name){
        rnd = new Random();
        this.setName(name);
    }

    @Override
    public void doSomeAction(){
        String action = "[" + this.getName() + "]";
        switch (rnd.nextInt(2)){
            case 0:
                action += "sleeping";
                break;
            case 1:
                action += "working";
                break;
            case 2:
                action += "drinking";
                break;
        }
        action +=" day number "+this.actionDay();
        System.out.println(action);

    }
}
