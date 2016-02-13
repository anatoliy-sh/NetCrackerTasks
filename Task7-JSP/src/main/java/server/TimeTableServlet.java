package server;

import db.MyConnection;
import db.Para;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Анатолий on 04.02.2016.
 */
@WebServlet("/timetable")
public class TimeTableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MyConnection myCon = new MyConnection();
        try (Connection con = myCon.getConnection()) {
            request.setAttribute("timetable", myCon.getTimeTable(con, 1, 2));
            request.getRequestDispatcher("timetable.jsp").forward(request, response);
        } catch (SQLException e) {
            System.out.println(e.getMessage() + e.getErrorCode());
            //log.error(e.getMessage() + e.getErrorCode(), e);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // log.error(e.getMessage(), e);
        }
    }


}