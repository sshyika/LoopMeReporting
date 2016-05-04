package com.loopme.reporting;

import com.loopme.reporting.connector.DruidConnector;
import com.loopme.reporting.connector.TransportManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/*"}, loadOnStartup = 1)
public class ReportingServlet extends HttpServlet {

    private Dictionary dictionary;

    @Override
    public void init() throws ServletException {
        dictionary = DashboardDao.loadDictionary();
        TransportManager.init();
    }

    @Override
    public void destroy() {
        TransportManager.destroy();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-type", "text/plain; charset=UTF-8");
        String content = "";
        switch (req.getPathInfo()) {
            case "/report" :
                content = getReport(req);
                break;
        }
        resp.getWriter().write(content);
    }


    private String getReport(HttpServletRequest req) {
        String content = DruidConnector.getReport();
        return Converter.convertTopN(Dictionary.Group.APP, content, dictionary);
    }


}
