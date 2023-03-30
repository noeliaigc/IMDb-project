package org.imdb.model;

public class Akas {
    private String title;
    private String region;
    private String language;
    private boolean isOriginal;

    public Akas(String title, String region, String language,
                boolean isOriginal) {
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

    public boolean getIsOriginal() {
        return isOriginal;
    }
}
