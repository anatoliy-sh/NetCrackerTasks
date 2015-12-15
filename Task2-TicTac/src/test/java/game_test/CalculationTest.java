package game_test;

import game.model.Calculation;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;

/**
 * Created by Анатолий on 22.11.2015.
 */
public class CalculationTest extends Assert {
    private static final Logger log = Logger.getLogger(Calculation.class);
    @Before
    public void setProperty(){
        PropertyConfigurator.configure("src/test/resources/log4j_test.properties");
    }

    @Test
    public void testRetSymbol() {
        log.info("Test: check movement");
        boolean flag = true;
        Calculation tester = new Calculation();

        String symb;
        for (int i = 0; i<2; i++) {
            for (int j = 0; j<3; j++) {
                if (flag)
                    symb = "X";
                else
                    symb = "0";
                flag = !flag;
                assertEquals("move must change", symb, tester.moveAction(i, j));
            }
        }
    }

    @Test
    public void testWinsRow() {
        log.info("Test: check row win");
        Calculation tester = new Calculation();
        tester.moveAction(1, 0);
        tester.moveAction(0, 1);
        tester.moveAction(1, 2);
        tester.moveAction(0, 2);
        tester.moveAction(1, 1);
        assertEquals("win row must be", "Win X", tester.getInscription());
    }

    @Test
    public void testWinsColumn() {
        log.info("Test: check column win");
        Calculation tester = new Calculation();
        tester.moveAction(0, 0);//X
        tester.moveAction(1, 0);
        tester.moveAction(0, 1);
        tester.moveAction(1, 1);
        tester.moveAction(0, 2);

        assertEquals("win column must be", "Win X", tester.getInscription());
    }

    @Test
    public void testWinsMainDiag() {
        log.info("Test: check main diagonal win");
        Calculation tester = new Calculation();
        tester.moveAction(0, 0);//X
        tester.moveAction(1, 0);
        tester.moveAction(1, 1);
        tester.moveAction(1, 2);
        tester.moveAction(2, 2);

        assertEquals("win main diag must be", "Win X", tester.getInscription());
    }

    @Test
    public void testWinsDopDiag() {
        log.info("Test: check additional diagonal win");
        Calculation tester = new Calculation();
        tester.moveAction(0, 2);//X
        tester.moveAction(1, 0);
        tester.moveAction(1, 1);
        tester.moveAction(1, 2);
        tester.moveAction(2, 0);

        assertEquals("win dop diag must be", "Win X", tester.getInscription());
    }

    @Test
    public void testDeadHeat() {
        log.info("Test: check dead heat");
        Calculation tester = new Calculation();
        tester.moveAction(0, 0);//X
        tester.moveAction(1, 0);
        tester.moveAction(0, 1);

        tester.moveAction(1, 1);
        tester.moveAction(1, 2);
        tester.moveAction(0, 2);
        tester.moveAction(2, 0);

        assertEquals("must be dead hit", "dead heat", tester.getInscription());
    }

    @Test
    public void testPlusEnter() {
        log.info("Test: enter simbol");
        Calculation tester = new Calculation();
        try {
            tester.moveAction(54, 5);
        }
        catch(Exception e) {
            log.error("ERROR testPlusEnter");
        }
        assertEquals("must be dead hit", "error", tester.getInscription());
    }


    @Test
    public void testMinusEnter() {
        log.info("Test: enter simbol");
        Calculation tester = new Calculation();
        try {
            tester.moveAction(-1, 0);
        }
        catch(Exception e) {
            log.error(" ERROR testMinusEnter");
        }
        assertEquals("must be dead hit", "error", tester.getInscription());
    }
    @Test
    public void testBigEnter() {
        log.info("Test: enter simbol");
        Calculation tester = new Calculation();
        try {
            tester.moveAction(999999*9999999, 0);
        }
        catch(Exception e) {
            log.error("ERROR testBigEnter");
        }
        assertEquals("must be dead hit", "error", tester.getInscription());
    }
    @Test
    public void testDoubleEnter() {
        log.info("Test: enter simbol");
        Calculation tester = new Calculation();
        tester.moveAction(0, 0);
        tester.moveAction(0, 0);
        tester.moveAction(0, 0);
        assertEquals("must be dead hit", "Move 0", tester.getInscription());
    }


}
