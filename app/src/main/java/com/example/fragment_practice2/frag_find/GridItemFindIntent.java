package com.example.fragment_practice2.frag_find;

public class GridItemFindIntent {
    private String intent;
    private String feature;
    private String good;
    private String bad;

    public GridItemFindIntent(String intent, String feature, String good, String bad) {
        this.intent = intent;
        this.feature = feature;
        this.good = good;
        this.bad = bad;
    }

    public String getIntent() {
        return intent;
    }

    public String getFeature() {
        return feature;
    }

    public String getGood() {
        return good;
    }

    public String getBad() {
        return bad;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public void setBad(String bad) {
        this.bad = bad;
    }
}
