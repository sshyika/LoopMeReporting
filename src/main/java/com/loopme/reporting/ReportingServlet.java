package com.loopme.reporting;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/report"}, loadOnStartup = 1)
public class ReportingServlet extends HttpServlet {

    private Dictionary dictionary;

    @Override
    public void init() throws ServletException {
        dictionary = DashboardDao.loadDictionary();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-type", "text/plain; charset=UTF-8");
        dictionary
                .getApps()
                .entrySet()
                .stream()
                .map(e -> e.getKey() + " : " + e.getValue() + "\n")
                .forEach(e -> {
                    try {
                        resp.getWriter().write(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
    }


}
