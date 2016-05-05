package com.loopme.reporting;

import com.loopme.reporting.connector.DruidConnector;
import com.loopme.reporting.connector.TransportManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/api/*"}, loadOnStartup = 1)
public class ReportingServlet extends HttpServlet {
    private static final LocalDateTime TO = LocalDateTime.of(2016, 4, 28, 0, 0, 0);

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
                content = buildList(Dictionary.Group.LINE);
                break;
            case "/dict/apps" :
                content = buildList(Dictionary.Group.APP);
                break;
            case "/dict/campaigns" :
                content = buildList(Dictionary.Group.CAMPAIGN);
                break;
            case "/dict/categories" :
                content = buildList(Dictionary.Group.CATEGORY);
                break;
            case "/dict/countries" :
                content = buildList(Dictionary.Group.COUNTRY);
                break;
            case "/dict/genres" :
                content = buildList(Dictionary.Group.GENRE);
                break;
            case "/dict/creatives" :
                content = buildList(Dictionary.Group.CREATIVE);
                break;
        }
        resp.getWriter().write(content);
    }


    private String getReport(HttpServletRequest req) {
        String groupBy = req.getParameter("groupBy");
        String period = req.getParameter("period");
        String lineItem = req.getParameter("line_item");
        String app = req.getParameter("app");
        String campaign = req.getParameter("campaign");
        String category = req.getParameter("category");
        String country = req.getParameter("country");
        String genre = req.getParameter("genre");
        String creative = req.getParameter("creative");

        LocalDateTime from = TO.minusDays(7);
        if (period != null && !period.isEmpty()) {
            from = TO.minusDays(Long.parseLong(period));
        }
        Dictionary.Group group = Dictionary.Group.parse(groupBy);
        Filter filter = new Filter(lineItem, campaign, app, creative, country, category, genre);

        String content = DruidConnector.search(filter, group, from, TO);

        return Converter.convert(group, content, dictionary);
    }


    private String buildList(Dictionary.Group group) {
        return "{" +
                dictionary.getMaps()
                        .get(group)
                        .entrySet().stream()
                        .limit(260)
                        .map(e -> String.format("\"%1$s\" : \"%2$s\"", e.getKey(), e.getValue()))
                        .collect(Collectors.joining(","))

                + "}";
    }
}
