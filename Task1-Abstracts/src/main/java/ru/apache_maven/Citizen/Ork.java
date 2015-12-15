package ru.apache_maven.Citizen;

import java.util.Random;

/**
 * Created by Анатолий on 09.11.2015.
 */
public class Ork extends Creature {
    private Random rnd;


    public Ork(String name){
        rnd = new Random();
        this.setName(name);
    }

    @Override
    public void doSomeAction(){
        String action = "[" + this.getName() + "]";
        switch (rnd.nextInt(3)){
            case 0:
                action += "sleeping";
                break;
            case 1:
                action += "rowdy";
                break;
            case 2:
                action += "praying";
                break;
        }
        action +=" day number "+this.actionDay();
        System.out.println(action);

    }
}
