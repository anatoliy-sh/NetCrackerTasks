package fileCounter;

/**
 * Created by Анатолий on 14.12.2015.
 */
public class Sum {
    private int sum;

    public Sum(){
        sum = 0;
    }

    public int getSum(){
        return sum;
    }

    public void setSum(int sum){
        this.sum += sum;
    }
}
