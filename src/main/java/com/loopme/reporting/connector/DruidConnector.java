package com.loopme.reporting.connector;

import com.loopme.reporting.Dictionary;
import com.loopme.reporting.Filter;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DruidConnector {

    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");


    public static String search(Filter filter, Dictionary.Group groupBy, LocalDateTime from, LocalDateTime to) {
        String header = "\"queryType\": \"groupBy\",\n" +
                        "  \"dataSource\": \"apps\",\n" +
                        "  \"granularity\": \"all\"," +
                        "  \"dimensions\": [\"" + groupBy.field() + "\"],\n";
        if (groupBy == Dictionary.Group.DATE) {
            header = "  \"queryType\": \"timeseries\",\n" +
                    "  \"dataSource\": \"apps\",\n" +
                    "  \"granularity\": \"day\",\n" +
                    "  \"descending\": \"false\",\n";
        }
        Response response = TransportManager.getClient()
                .target("http://136.243.171.177:8082/druid/v2")
                .request()
                .post(Entity.json("{\n" +
                        header +
                        filter.buildQuery() +
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
                        "  \"intervals\": [ \"" + from.format(format) + "/" + to.format(format) + "\" ]\n" +
                        "}"));
        try {
            return response.readEntity(String.class);
        } finally {
            response.close();
        }
    }

}
