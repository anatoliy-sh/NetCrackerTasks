package game;

import game.model.Calculation;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
//private static final org.apache.log4j.Logger logger = org.apache.log4j.LogManager.getLogger(Main.class);


    public static void main(String[] args) {
        //logger.debug("smth");
       //DOMConfigurator.configure("log4j.xml");
        //PropertyConfigurator.configure("src/main/java/resources/log4j.properties");
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        Calculation calc = new Calculation();
        System.out.println(calc.moveAction(1, 0));
        System.out.println(calc.getInscription());
        System.out.println(calc.moveAction(0, 1));
        System.out.println(calc.getInscription());
        System.out.println(calc.moveAction(1, 2));
        System.out.println(calc.getInscription());
        System.out.println(calc.moveAction(0, 2));
        System.out.println(calc.getInscription());
        System.out.println(calc.moveAction(1, 1));
        System.out.println(calc.getInscription());

        calc = new Calculation();
        System.out.println(calc.moveAction(1, 0));
        System.out.println(calc.getInscription());
        System.out.println(calc.moveAction(1, 0));
        System.out.println(calc.getInscription());
    }
}
