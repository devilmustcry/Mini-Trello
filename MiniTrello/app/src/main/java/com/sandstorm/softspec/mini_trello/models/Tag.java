package com.sandstorm.softspec.mini_trello.models;

/**
 * Created by Zen on 3/28/16.
 */
public class Tag {

    private String title;

    private static long id = 1;

    public Tag(String title){
        this.title = title;
        id++;
    }

    public String getTagName(){
        return this.title;
    }

    public long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        return this.title.equalsIgnoreCase(tag.getTagName());

    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }
}
