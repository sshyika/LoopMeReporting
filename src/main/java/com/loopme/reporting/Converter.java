package com.loopme.reporting;

import javax.json.*;
import java.io.StringReader;

public class Converter {

    public static String convertTopN(Dictionary.Group group, String content, Dictionary dict) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonArray results = reader.readArray();

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (JsonObject result : results.getValuesAs(JsonObject.class)) {
            result = result.getJsonObject("event");
            String groupValue = "";
            if (result.get(group.field()).getValueType() != JsonValue.ValueType.NULL) {
                groupValue = dict.getMaps().get(group).get(result.getString(group.field()));
            }
            groupValue = groupValue == null ? "" : groupValue;
            arrayBuilder.add(
                Json.createObjectBuilder()
                .add("ad_clicks", result.getJsonNumber("ad_clicks"))
                .add("bid_wins", result.getJsonNumber("bid_wins"))
                .add("earnings_cents", result.getJsonNumber("earnings_cents"))
                .add("video_starts", result.getJsonNumber("video_starts"))
                .add("b_video_completes", result.getJsonNumber("b_video_completes"))
                .add("requests", result.getJsonNumber("requests"))
                .add("tracking", result.getJsonNumber("tracking"))
                .add("app_price_cents", result.getJsonNumber("app_price_cents"))
                .add("ad_viewable", result.getJsonNumber("ad_viewable"))
                .add("app_confirmed_installs", result.getJsonNumber("app_confirmed_installs"))
                .add("ad_success", result.getJsonNumber("ad_success"))
                .add("inbox_opens", result.getJsonNumber("inbox_opens"))
                .add("a_price_cents", result.getJsonNumber("a_price_cents"))
                .add("video_completes", result.getJsonNumber("video_completes"))
                .add("b_clicks", result.getJsonNumber("b_clicks"))
                .add("b_views", result.getJsonNumber("b_views"))
                .add("ad_views", result.getJsonNumber("ad_views"))
                .add("group", groupValue)
            );
        }
        builder.add("data", arrayBuilder);

        return builder.build().toString();
    }


}
