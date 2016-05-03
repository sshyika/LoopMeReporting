package com.loopme.reporting;

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
        //dictionary = DashboardDao.loadDictionary();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-type", "text/plain; charset=UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        String content = "";
        switch (req.getPathInfo()) {
            case "/report" :
                content = getReport(req);
                break;
        }
        resp.getWriter().write(content);
    }


    private String getReport(HttpServletRequest req) {
        return "[\n" +
                "    {\n" +
                "      \"ad_clicks\": 2380,\n" +
                "      \"bid_wins\": 0,\n" +
                "      \"earnings_cents\": 1140000000,\n" +
                "      \"video_starts\": 25215,\n" +
                "      \"b_video_completes\": 0,\n" +
                "      \"tracking\": 0,\n" +
                "      \"app_price_cents\": 0,\n" +
                "      \"ad_viewable\": 34224,\n" +
                "      \"app_confirmed_installs\": 4,\n" +
                "      \"ad_success\": 543336,\n" +
                "      \"inbox_opens\": 41011,\n" +
                "      \"a_price_cents\": 0,\n" +
                "      \"video_completes\": 0,\n" +
                "      \"campaign_id\": \"7358\",\n" +
                "      \"b_clicks\": 240,\n" +
                "      \"b_views\": 0,\n" +
                "      \"ad_views\": 40775\n" +
                "    }" +
                "]";
    }


}
