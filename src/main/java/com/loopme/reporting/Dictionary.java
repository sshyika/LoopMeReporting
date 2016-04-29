package com.loopme.reporting;

import java.util.Map;

public class Dictionary {
    private Map<Long, String> lineItems;
    private Map<Long, String> campaigns;
    private Map<Long, String> apps;
    private Map<Long, String> creatives;
    private Map<Long, String> countries;
    private Map<Long, String> categories;
    private Map<Long, String> genres;


    public Dictionary(Map<Long, String> lineItems, Map<Long, String> campaigns, Map<Long, String> apps,
                      Map<Long, String> creatives, Map<Long, String> countries, Map<Long, String> categories, Map<Long, String> genres) {
        this.lineItems = lineItems;
        this.campaigns = campaigns;
        this.apps = apps;
        this.creatives = creatives;
        this.countries = countries;
        this.categories = categories;
        this.genres = genres;
    }


    public Map<Long, String> getLineItems() {
        return lineItems;
    }

    public Map<Long, String> getCampaigns() {
        return campaigns;
    }

    public Map<Long, String> getApps() {
        return apps;
    }

    public Map<Long, String> getCreatives() {
        return creatives;
    }

    public Map<Long, String> getCountries() {
        return countries;
    }

    public Map<Long, String> getCategories() {
        return categories;
    }

    public Map<Long, String> getGenres() {
        return genres;
    }

}
