package com.example.nojie.fragment.Article;

import android.widget.ImageView;
import android.widget.TextView;

public class Article_Comment {
    private ImageView image;
    private String username;
    private String comment_type;
    private  String date;
    private String comment_content_text;

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setComment_content_text(String comment_content_text) {
        this.comment_content_text = comment_content_text;
    }

    public void setComment_type(String comment_type) {
        this.comment_type = comment_type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public ImageView getImage() {
        return image;
    }

    public String getComment_content_text() {
        return comment_content_text;
    }

    public String getComment_type() {
        return comment_type;
    }

    public String getDate() {
        return date;
    }
}
