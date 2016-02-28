package server;

import db.MyConnection;
import db.Para;
import services.TimeTableStateful;
import services.TimeTableStateless;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Анатолий on 04.02.2016.
 */
@WebServlet("/timetable")
public class TimeTableServlet extends HttpServlet {

    @EJB
    TimeTableStateful timeTableStateFul;
    @EJB
    TimeTableStateless timeTableStateless;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(timeTableStateFul.getParas() == null)
            timeTableStateFul.setParas(returnTimeTable());

        request.setAttribute("timetable", timeTableStateless.calculateProbability(timeTableStateFul.getParas()));
        request.getRequestDispatcher("timetable.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("add") != null)
            timeTableStateFul.addPara(request.getParameter("time"), request.getParameter("para"),
                    request.getParameter("subject"), request.getParameter("week"));
        doGet(request, response);
    }

    public List<Para> returnTimeTable() {
        List<Para> paras;
        MyConnection myCon = new MyConnection();
        try (Connection con = myCon.getConnection()) {
            paras = getTimeTable(con, 1, 2);
        } catch (SQLException e) {
            System.out.println(e.getMessage() + e.getErrorCode());
            //log.error(e.getMessage() + e.getErrorCode(), e);
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // log.error(e.getMessage(), e);
            return null;
        }
        return paras;
    }

    private List<Para> getTimeTable(Connection con, int group, int day) throws SQLException {
        String query = "SELECT l.para, s.name, p.time,l.week  FROM list l JOIN subjects s ON (l.subjID = s.ID) JOIN para p ON (p.number = l.para) " +
                "WHERE l.groupID = ? and l.day = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            List<Para> parasTmp = new ArrayList<>();
            pstmt.setInt(1, group);
            pstmt.setInt(2, day);
            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs != null)
                    while (rs.next()) {
                        Para para = new Para(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                        parasTmp.add(para);
                    }
                return parasTmp;
            } catch (SQLException e) {
                System.out.println(e.getErrorCode() + e.getMessage());
                //log.error(e.getErrorCode() + e.getMessage(), e);
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            // log.error(e.getErrorCode() + e.getMessage(), e);
            return null;
        }

    }
}