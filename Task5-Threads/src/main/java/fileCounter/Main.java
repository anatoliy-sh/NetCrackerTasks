package fileCounter;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Анатолий on 14.12.2015.
 */
public class Main {

    private static String fileName = "input.txt";
    private static final Logger log = Logger.getLogger(RowCounter.class.getName());

    public static void main(String[] args) {
        File file = new File(fileName);

        try {
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));

            RowCounter[] rowCounters = new RowCounter[3];
            Lock lockFile = new ReentrantLock();
            Lock lockSum = new ReentrantLock();
            Sum sum = new Sum();
            for (int i = 0; i < rowCounters.length; i++) {
                rowCounters[i] = new RowCounter(in, lockFile,lockSum, i,sum);
            }
            createThreads(rowCounters);
            in.close();
            log.info("Sum = "+ sum.getSum());
        } catch (IOException e ) {
            log.error(e.getMessage(),e);
        }
        catch (InterruptedException e){
            log.error(e.getMessage(),e);
        }
    }
    private static void createThreads(RowCounter[] rowCounters) throws InterruptedException{
        Thread[] threads = new Thread[rowCounters.length];
        for (int i = 0; i < rowCounters.length; i++) {
            threads[i] = new Thread(rowCounters[i]);
            threads[i].start();
        }
        for (int j = 0; j < rowCounters.length; j++) {
            threads[j].join();
        }
    }
}
