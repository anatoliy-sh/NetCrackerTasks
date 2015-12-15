package fileCounter;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.locks.Lock;

/**
 * Created by Анатолий on 14.12.2015.
 */
public class RowCounter extends Thread {
    private static final Logger log = Logger.getLogger(RowCounter.class);

    private BufferedReader in;
    private Lock lockFile;
    private Lock lockSum;
    private int number;
    private int sumLocal;
    private Sum sumGlobal;

    public RowCounter(BufferedReader in, Lock lockFile, Lock lockSum, int number, Sum sumGlobal) {
        this.in = in;
        this.lockFile = lockFile;
        this.number = number;
        this.lockSum = lockSum;
        sumLocal =0;
        this.sumGlobal = sumGlobal;
    }

    public void run() {
        while (true) {
            try {
                lockFile.lock();
                String s = in.readLine();
                lockFile.unlock();
                if (s != null) {
                    sumLocal = 0;
                    calculate(s);
                    log.info("[" + number + "] "+ sumLocal +" "+ s);
                    lockSum.lock();
                    sumGlobal.setSum(sumLocal);
                    lockSum.unlock();
                }
                else
                    break;

            } catch (IOException e) {
                log.error(e.getMessage()+e.getLocalizedMessage(),e);
            }
        }
    }

    private void calculate(String s){
        char[] cArray = s.toCharArray();
        for(int i = 0; i<s.length()-1; i++){
            if (cArray[i] == ' ' && s.toCharArray()[i+1] !=' '){
                sumLocal++;
            }
        }
        if(cArray.length>0 && cArray[0] != ' ')
            sumLocal++;
    }
}
