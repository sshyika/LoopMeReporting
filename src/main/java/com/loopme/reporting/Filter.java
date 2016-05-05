package com.loopme.reporting;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    private String lineItemId;
    private String campaignId;
    private String appId;
    private String creativeId;
    private String countryId;
    private String categoryId;
    private String genreId;


    public Filter(String lineItemId, String campaignId, String appId, String creativeId, String countryId, String categoryId, String genreId) {
        this.lineItemId = (lineItemId == null ? "" : lineItemId);
        this.campaignId = (campaignId == null ? "" : campaignId);
        this.appId = (appId == null ? "" : appId);
        this.creativeId = (creativeId == null ? "" : creativeId);
        this.countryId = (countryId == null ? "" : countryId);
        this.categoryId = (categoryId == null ? "" : categoryId);
        this.genreId = (genreId == null ? "" : genreId);
    }


    public String buildQuery() {
        List<String> fields = new ArrayList<>();

        if (!lineItemId.isEmpty()) {
            fields.add(buildField("line_item_id", lineItemId));
        }
        if (!campaignId.isEmpty()) {
            fields.add(buildField("campaign_id", campaignId));
        }
        if (!appId.isEmpty()) {
            fields.add(buildField("app_id", appId));
        }
        if (!creativeId.isEmpty()) {
            fields.add(buildField("creative_id", creativeId));
        }
        if (!countryId.isEmpty()) {
            fields.add(buildField("country_id", countryId));
        }
        if (!categoryId.isEmpty()) {
            fields.add(buildField("app_category_id", categoryId));
        }
        if (!genreId.isEmpty()) {
            fields.add(buildField("genre_id", genreId));
        }

        if (fields.isEmpty()) {
            return "";
        } else {
            return "  \"filter\": {\n" +
                    "    \"type\": \"and\",\n" +
                    "    \"fields\": [\n" +
                    String.join(",\n", fields) +
                    "      ]\n" +
                    " }, ";
        }
    }


    private String buildField(String dimension, String value) {
        return  "      {\n" +
                "        \"type\": \"selector\",\n" +
                "        \"dimension\": \"" + dimension + "\",\n" +
                "        \"value\": \"" + value + "\"\n" +
                "      }";
    }

}
