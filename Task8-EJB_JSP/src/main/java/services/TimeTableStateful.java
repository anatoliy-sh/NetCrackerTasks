package services;

import db.MyConnection;
import db.Para;

import javax.ejb.Stateful;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Анатолий on 10.02.2016.
 */
@Stateful
public class TimeTableStateful {

    List<Para> paras;

    public void setParas(List<Para> paras){
        this.paras = paras;
    }

    public List<Para> getParas(){
        return paras;
    }

    public void addPara(String time, String para, String subject, String week){
        paras.add(new Para(Integer.parseInt(para), subject, time, Integer.parseInt(week)));
    }
}
