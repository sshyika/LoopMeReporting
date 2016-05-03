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
                "        \"name\": \"Tiger Nixon\",\n" +
                "        \"position\": \"System Architect\",\n" +
                "        \"salary\": \"$320,800\",\n" +
                "        \"start_date\": \"2011/04/25\",\n" +
                "        \"office\": \"Edinburgh\",\n" +
                "        \"extn\": \"5421\"\n" +
                "    }" +
                "]";
    }


}
