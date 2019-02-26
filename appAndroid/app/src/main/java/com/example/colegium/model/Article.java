package com.example.colegium.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {

    String objectID;
    String title;
    String story_title;
    String author;
    String url;
    String story_url;
    long created_at_i;

    public Article(String objectID, String title, String author, String url, int created_at_i) {
        this.objectID = objectID;
        this.title = title;
        this.author = author;
        this.url = url;
        this.created_at_i = created_at_i;
    }

    protected Article(Parcel in) {
        objectID = in.readString();
        title = in.readString();
        story_title = in.readString();
        author = in.readString();
        url = in.readString();
        story_url = in.readString();
        created_at_i = in.readInt();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

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

    public long getCreated_at_i() {
        return created_at_i * 1000;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(objectID);
        parcel.writeString(title);
        parcel.writeString(story_title);
        parcel.writeString(author);
        parcel.writeString(url);
        parcel.writeString(story_url);
        parcel.writeLong(created_at_i);
    }
}
