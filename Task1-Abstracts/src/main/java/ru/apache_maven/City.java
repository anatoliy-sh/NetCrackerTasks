package ru.apache_maven;

import ru.apache_maven.Citizen.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Анатолий on 09.11.2015.
 */
public class City {
    private ArrayList<Creature> citizens;
    private boolean flagSiege = true;
    private Random rnd;

    public boolean getFlagSiege(){
        return flagSiege;
    }
    public City(){
        rnd = new Random();
        citizens = new ArrayList<Creature>();
        citizens.add(new Human("Human 1"));
        citizens.add(new Ork("Ork 1"));
        citizens.add(new HumanWarrior("Human 2 (Warrior)",this));
    }
    public void simulation(){
        while(citizens.get(0).getDay()<10) {
            for (int i = 0; i < citizens.size(); i++) {
                citizens.get(i).doSomeAction();
                if (flagSiege && citizens.get(i) instanceof Warrior) {
                    Warrior war = (Warrior) citizens.get(i);
                    System.out.println("kill "+ war.killEnemies()+" enemies");
                }
            }
            delay(1000);
            if (rnd.nextInt(100) > 80)
                flagSiege = !flagSiege;
        }
    }

    private void delay(int time){
        try {
            Thread.currentThread().sleep(time);
        }
        catch (Exception e) {}
        System.out.println("-------------------");
    }
}
