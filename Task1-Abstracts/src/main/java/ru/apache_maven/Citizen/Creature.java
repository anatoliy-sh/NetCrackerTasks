package ru.apache_maven.Citizen;

/**
 * Created by Анатолий on 09.11.2015.
 */
public abstract class Creature {
    private int livedDays;
    private String status;
    private String name;

    public Creature(){
        livedDays=0;
    }

    public String getName(){
        return name;
    }
    public int getDay(){
        return livedDays;
    }

    public void setName(String value){
        name = value;
    }

    public int actionDay(){
       return livedDays++;
    }
    public abstract void doSomeAction();
}
