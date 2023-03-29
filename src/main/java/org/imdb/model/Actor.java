package org.imdb.model;

public class Actor {
    private String character;
    private Name name;

    public Actor(Name name, String id) {
        this.character = id;
        this.name = name;
    }

    public Actor(){
    }

    public String getCharacter() {
        return character;
    }

    public Name getName() {
        return name;
    }
}
