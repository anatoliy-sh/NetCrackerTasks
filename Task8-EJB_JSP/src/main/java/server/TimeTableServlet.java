package server;

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
        List<Para> paras = timeTableStateFul.returnTimeTable();
        request.setAttribute("timetable", timeTableStateless.calculateProbability(paras));
        request.getRequestDispatcher("timetable.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("add") != null)
        timeTableStateFul.addPara(request.getParameter("time"), request.getParameter("para"),
                request.getParameter("subject"), request.getParameter("week"));
        doGet(request, response);
    }
}