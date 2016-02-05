package db;

import javax.security.auth.Subject;

/**
 * Created by Анатолий on 04.02.2016.
 */
public class Para {
    private int num;
    private String subject;
    private String time;

    public Para(int num, String subject, String time){
        this.num = num;
        this.subject = subject;
        this.time = time;
    }

    public String getTime(){
        return time;
    }

    public int getNum(){
        return num;
    }

    public void setNum(int num){
        this.num = num;
    }

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }
}
