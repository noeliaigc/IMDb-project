package org.imdb.model;

public class Starring {

    private Name name;
    private String characters;
    public Starring(Name name, String characters) {
        this.name = name;
        this.characters = characters;
    }

    public Starring(){
    }

    public String getCharacters() {
        return characters;
    }

    public Name getName() {
        return name;
    }
}
