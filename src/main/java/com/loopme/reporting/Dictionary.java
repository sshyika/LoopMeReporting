package com.loopme.reporting;

import java.util.Collections;
import java.util.Map;

public class Dictionary {
    private Map<String, String> lineItems;
    private Map<String, String> campaigns;
    private Map<String, String> apps;
    private Map<String, String> creatives;
    private Map<String, String> countries;
    private Map<String, String> categories;
    private Map<String, String> genres;


    public Dictionary(Map<String, String> lineItems, Map<String, String> campaigns, Map<String, String> apps,
                      Map<String, String> creatives, Map<String, String> countries, Map<String, String> categories, Map<String, String> genres) {
        this.lineItems = Collections.unmodifiableMap(lineItems);
        this.campaigns = Collections.unmodifiableMap(campaigns);
        this.apps = Collections.unmodifiableMap(apps);
        this.creatives = Collections.unmodifiableMap(creatives);
        this.countries = Collections.unmodifiableMap(countries);
        this.categories = Collections.unmodifiableMap(categories);
        this.genres = Collections.unmodifiableMap(genres);
    }


    public Map<String, String> getLineItems() {
        return lineItems;
    }

    public Map<String, String> getCampaigns() {
        return campaigns;
    }

    public Map<String, String> getApps() {
        return apps;
    }

    public Map<String, String> getCreatives() {
        return creatives;
    }

    public Map<String, String> getCountries() {
        return countries;
    }

    public Map<String, String> getCategories() {
        return categories;
    }

    public Map<String, String> getGenres() {
        return genres;
    }

}
