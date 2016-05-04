package com.loopme.reporting;

import com.loopme.reporting.connector.DruidConnector;
import com.loopme.reporting.connector.TransportManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

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
        resp.setHeader("Content-type", "application/json; charset=UTF-8");
        String content = "";
        switch (req.getPathInfo()) {
            case "/report" :
                content = getReport(req);
                break;
            case "/dict/lineitems" :
                content = "{" +
                        dictionary.getMaps()
                        .get(Dictionary.Group.LINE)
                        .entrySet().stream()
                        .limit(100)
                        .map(e -> String.format("\"%1$s\" : \"%2$s\"", e.getKey(), e.getValue()))
                        .collect(Collectors.joining(","))

                + "}";
                break;
        }
        resp.getWriter().write(content);
    }


    private String getReport(HttpServletRequest req) {
        String groupBy = req.getParameter("groupBy");
        Dictionary.Group group = Dictionary.Group.parse(groupBy);
        String content = DruidConnector.getReport(group.field());
        return Converter.convertTopN(group, content, dictionary);
    }


}
