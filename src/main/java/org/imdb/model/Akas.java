package org.imdb.model;

public class Akas {
    private String title;
    private String region;
    private String language;
    String isOriginal;

    public Akas(String title, String region, String language, String isOriginal) {
        this.title = title;
        this.region = region;
        this.language = language;
        this.isOriginal = isOriginal;
    }

    public Akas(){
    }

    public String getTitle() {
        return title;
    }

    public String getRegion() {
        return region;
    }

    public String getLanguage() {
        return language;
    }

    public String getIsOriginal() {
        return isOriginal;
    }
}
