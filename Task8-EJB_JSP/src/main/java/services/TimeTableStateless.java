package services;

import db.MyConnection;
import db.Para;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Анатолий on 10.02.2016.
 */
@Stateless
public class TimeTableStateless {

    public List<Para> calculateProbability(List<Para> paras) {
        double probability;
        for (Para para : paras) {
            probability = 1/para.getNum() * para.getWeek() * 0.1;
            para.setProbability(probability);
        }
        return paras;
    }
}
