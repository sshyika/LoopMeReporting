package com.loopme.reporting;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/report"}, loadOnStartup = 1)
public class ReportingServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        Dictionary dictionary = DashboardDao.loadDictionary();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("test");
    }


}
