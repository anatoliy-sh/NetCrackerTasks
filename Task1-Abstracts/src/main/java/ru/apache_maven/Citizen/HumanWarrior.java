package ru.apache_maven.Citizen;

import ru.apache_maven.City;

import java.util.Random;

/**
 * Created by Анатолий on 09.11.2015.
 */
public class HumanWarrior extends Creature implements Warrior {
    private Random rnd;
    private City city;

    public HumanWarrior(String name, City city){
        rnd = new Random();
        this.setName(name);
        this.city = city;
    }



    @Override
    public void doSomeAction(){
        String action = "[" + this.getName() + "]";
        if (!city.getFlagSiege()) {
            switch (rnd.nextInt(2)) {
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
        }
        else
            action+="protecting";

        action +=" day number "+this.actionDay();
        System.out.println(action);

    }

    public int killEnemies(){
        return rnd.nextInt(10);
    }


    public void getDamage(int value){

    }


}
