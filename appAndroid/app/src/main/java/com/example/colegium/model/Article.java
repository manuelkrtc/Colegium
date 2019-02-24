package com.example.colegium.model;

public class Article {

    String objectID;
    String title;
    String story_title;
    String author;
    String url;
    String story_url;
    int created_at_i;

    public Article(String objectID, String title, String author, String url, int created_at_i) {
        this.objectID = objectID;
        this.title = title;
        this.author = author;
        this.url = url;
        this.created_at_i = created_at_i;
    }

    public String getId() {
        return objectID;
    }

    public String getTitle() {
        return title == null ? story_title : title;
    }


    public String getAuthor() {
        return author;
    }


    public String getUrl() {
        return url == null ? story_url : url;
    }

    public int getCreated_at_i() {
        return created_at_i;
    }
}
