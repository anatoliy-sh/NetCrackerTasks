package game.model;



import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 * Created by Анатолий on 25.09.2015.
 */
public class Calculation {

    private static final Logger log = Logger.getLogger(Calculation.class);

    public static final int XVAL = 2;
    public static final int OVAL = 1;
    public static final int SIZE = 3;

    private boolean move;
    private int[][] matrix;
    private boolean checkGameOver;
    private boolean checkDeadHit;
    private boolean flagRow;
    private boolean flagColumn;
    private boolean flagMainDiag;
    private boolean flagDopDiag;
    private int numMove;
    private String inscription;


    public Calculation(){
        move = true;
        matrix = new int[SIZE][SIZE];
        checkGameOver = true;
        checkDeadHit = false;
        numMove = 0;
    }

    public boolean getCheckGameOver(){
        return checkGameOver;
    }

    public boolean getCheckDeadHit(){
        return checkDeadHit;
    }

    public String getInscription(){
        return inscription;
    }



    //Main method

    public String moveAction(int i, int j){
        String str = "";
        int k = i*SIZE+j;
        if(checkGameOver) {
            str = returnSymbol(i, j, k);
            checkGame(i, j);
            setLabelText(i,j);
        }
        log.info("Move " + returnGamer(move));
        return str;
    }


    public String returnSymbol(int i, int j, int k){
        if (matrix[i][j] == 0) {
            move = !move;
            numMove++;
            if (!move) {
                matrix[i][j] = XVAL;
                return "X";
            } else {
                matrix[i][j] = OVAL;
                return "0";
            }
        }
        log.error("Movement error");
        return "";
    }

    public void checkGame(int i, int j){

        int value = matrix[i][j];
        setFlags();
        for(int k = 0; k<SIZE; k++){
            checkNextEl(i,j,k,value);
        }
        checkGameOver = !(flagRow || flagColumn || flagMainDiag || flagDopDiag);
    }

    private void setFlags(){
        flagRow = true;
        flagColumn = true;
        flagMainDiag = true;
        flagDopDiag = true;
    }
    private void checkNextEl(int i, int j,int k,int value){
        if(flagRow)
            flagRow = value == matrix[i][k];
        if(flagColumn)
            flagColumn = value == matrix[k][j];
        if(flagMainDiag)
            flagMainDiag = value == matrix[k][k];
        if(flagDopDiag)
            flagDopDiag = value == matrix[k][SIZE-k-1];
    }

    public void setLabelText(int i, int j){
        if(!checkGameOver) {
            inscription = "Win " + returnGamer(!move);
            log.info("Win " + returnGamer(!move));
        }
        else {
            if (returnDH()) {
                inscription = "dead heat";
                checkGameOver = false;
                checkDeadHit = true;
                log.info("Dead heat");
            }
            else
                inscription= "Move " + returnGamer(move);
        }
    }
    //
    private String returnGamer(boolean moveG){
        if(moveG)
            return "X";
        else
            return"0";
    }
    private int retNumGamer(boolean moveG){
        if(moveG)
            return XVAL;
        else
            return OVAL;
    }


    //Dead heat

    private boolean returnDH(){
        if (numMove == SIZE*SIZE) {
            return finalDH();
        }
        else if(numMove >= 7){

            return extraDH();
        }
        return false;
    }

    private boolean finalDH(){
        boolean flag = true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (flag)
                    flag = matrix[i][j] != 0;
            }
        }
        return flag;
    }

    private boolean extraDH(){
        ArrayList<Integer> emptyIJ = new ArrayList<Integer>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (matrix[i][j] == 0){
                    emptyIJ.add(i);
                    emptyIJ.add(j);
                }
            }
        }
        return switchOneTwo(emptyIJ);
    }

    private boolean switchOneTwo(ArrayList<Integer> emptyIJ){
        switch (emptyIJ.size()){
            case 2:
                int iM = emptyIJ.get(0);
                int jM = emptyIJ.get(1);
                boolean flag = oneEmptyDH(iM,jM,retNumGamer(move));
                set0(emptyIJ);
                return flag;
            case 4:
                return twoEmptyDH(emptyIJ);
        }
        return false;
    }
    private boolean twoEmptyDH(ArrayList<Integer> emptyIJ){
        boolean flagDH = returnFlagDH(emptyIJ,move);
        set0(emptyIJ);
        if (flagDH) {
            flagDH = returnFlagDH(emptyIJ,!move);;
            set0(emptyIJ);
        }

        return flagDH;
    }

    private boolean oneEmptyDH(int iM, int jM, int numGamer){
        boolean flagDH;
        boolean intermGMFlag = checkGameOver;
        matrix[iM][jM] = numGamer;
        checkGame(iM, jM);
        flagDH = checkGameOver;
        checkGameOver = intermGMFlag;
        return flagDH;
    }

    private void set0(ArrayList<Integer> emptyIJ){
        for (int i = 0; i<emptyIJ.size(); i+=2){
            matrix[emptyIJ.get(i)][emptyIJ.get(i+1)] = 0;
        }
    }

    private boolean returnFlagDH(ArrayList<Integer> emptyIJ, boolean moveDH){
        return oneEmptyDH(emptyIJ.get(0),emptyIJ.get(1),retNumGamer(moveDH)) && oneEmptyDH(emptyIJ.get(2),emptyIJ.get(3),retNumGamer(!moveDH));
    }

}
