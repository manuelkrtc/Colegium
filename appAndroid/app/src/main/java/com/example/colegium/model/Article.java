package com.example.colegium.model;

public class Article {

    String objectID;
    String title;
    String author;
    String story_url;
    int created_at_i;

    public Article(String objectID, String title, String author, String story_url, int created_at_i) {
        this.objectID = objectID;
        this.title = title;
        this.author = author;
        this.story_url = story_url;
        this.created_at_i = created_at_i;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStory_url() {
        return story_url;
    }

    public void setStory_url(String story_url) {
        this.story_url = story_url;
    }

    public int getCreated_at_i() {
        return created_at_i;
    }

    public void setCreated_at_i(int created_at_i) {
        this.created_at_i = created_at_i;
    }
}
