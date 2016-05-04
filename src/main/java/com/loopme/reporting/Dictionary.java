package com.loopme.reporting;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class Dictionary {

    public static enum Group {
        LINE("line_item_id"),
        CAMPAIGN("campaign_id"),
        APP("app_id"),
        CREATIVE("creative_id"),
        COUNTRY("country_id"),
        CATEGORY("app_category_id"),
        GENRE("genre_id");

        private String field;

        Group(String field) {
            this.field = field;
        }

        public String field() {
            return field;
        }

        public static Group parse(String field) {
            for (Group group : Group.values()) {
                if (group.field().equals(field)) {
                    return group;
                }
            }
            return Group.CAMPAIGN;
        }
    }

    private Map<Group, Map<String, String>> maps;


    public Dictionary(Map<String, String> lineItems, Map<String, String> campaigns, Map<String, String> apps,
                      Map<String, String> creatives, Map<String, String> countries, Map<String, String> categories, Map<String, String> genres) {
        maps = new EnumMap<>(Group.class);
        maps.put(Group.LINE, Collections.unmodifiableMap(lineItems));
        maps.put(Group.CAMPAIGN, Collections.unmodifiableMap(campaigns));
        maps.put(Group.APP, Collections.unmodifiableMap(apps));
        maps.put(Group.CREATIVE, Collections.unmodifiableMap(creatives));
        maps.put(Group.COUNTRY, Collections.unmodifiableMap(countries));
        maps.put(Group.CATEGORY, Collections.unmodifiableMap(categories));
        maps.put(Group.GENRE, Collections.unmodifiableMap(genres));
        maps = Collections.unmodifiableMap(maps);
    }


    public Map<Group, Map<String, String>> getMaps() {
        return maps;
    }

}
