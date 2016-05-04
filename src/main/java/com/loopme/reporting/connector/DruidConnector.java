package com.loopme.reporting.connector;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

public class DruidConnector {

    public static String getReport(String groupBy) {
        Response response = TransportManager.getClient()
                .target("http://136.243.171.177:8082/druid/v2")
                .request()
                .post(Entity.json("{\n" +
                        "  \"queryType\": \"topN\",\n" +
                        "  \"dataSource\": \"apps\",\n" +
                        "  \"threshold\": 50000,\n" +
                        "  \"granularity\": \"all\",\n" +
                        "  \"dimension\": \"" + groupBy + "\",\n" +
                        "  \"metric\": \"ad_clicks\",\n" +
                        "  \"aggregations\": [\n" +
                        "    { \"type\": \"longSum\", \"name\": \"ad_clicks\", \"fieldName\": \"ad_clicks\" },\n" +
                        "    { \"type\": \"longSum\", \"name\": \"ad_views\", \"fieldName\": \"ad_views\" },\n" +
                        "    { \"type\": \"longSum\", \"name\": \"ad_viewable\", \"fieldName\": \"ad_viewable\" },\n" +
                        "    { \"type\": \"longSum\", \"name\": \"b_clicks\", \"fieldName\": \"b_clicks\" },\n" +
                        "    { \"type\": \"longSum\", \"name\": \"b_views\", \"fieldName\": \"b_views\" },\n" +
                        "    { \"type\": \"longSum\", \"name\": \"inbox_opens\", \"fieldName\": \"inbox_opens\" },\n" +
                        "    { \"type\": \"longSum\", \"name\": \"earnings_cents\", \"fieldName\": \"earnings_cents\" },\n" +
                        "    { \"type\": \"longSum\", \"name\": \"app_confirmed_installs\", \"fieldName\": \"app_confirmed_installs\" },\n" +
                        "    { \"type\": \"longSum\", \"name\": \"ad_success\", \"fieldName\": \"ad_success\" },\n" +
                        "    { \"type\": \"longSum\", \"name\": \"video_starts\", \"fieldName\": \"video_starts\" },\n" +
                        "    { \"type\": \"longSum\", \"name\": \"video_completes\", \"fieldName\": \"video_completes\" },\n" +
                        "    { \"type\": \"longSum\", \"name\": \"b_video_completes\", \"fieldName\": \"b_video_completes\" },\n" +
                        "    { \"type\": \"longSum\", \"name\": \"bid_wins\", \"fieldName\": \"bid_wins\" },\n" +
                        "    { \"type\": \"longSum\", \"name\": \"a_price_cents\", \"fieldName\": \"a_price_cents\" },\n" +
                        "    { \"type\": \"longSum\", \"name\": \"tracking\", \"fieldName\": \"tracking\" },\n" +
                        "    { \"type\": \"longSum\", \"name\": \"app_price_cents\", \"fieldName\": \"app_price_cents\" },\n" +
                        "    { \"type\": \"longSum\", \"name\": \"requests\", \"fieldName\": \"requests\" }\n" +
                        "  ],\n" +
                        "  \"intervals\": [ \"2016-03-01T00:00:00.000/2016-04-01T23:59:59.999\" ]\n" +
                        "}"));
        try {
            return response.readEntity(String.class);
        } finally {
            response.close();
        }
    }

}
