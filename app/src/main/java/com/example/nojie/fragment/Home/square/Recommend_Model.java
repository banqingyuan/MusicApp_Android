package com.example.nojie.fragment.Home.square;

import android.media.Image;
import android.provider.ContactsContract;

public class Recommend_Model {
    private String title;
    private String on_short_word;
    private String location;
    private Image image;

    public void setImage(Image image) {
        this.image = image;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOn_short_word(String on_short_word) {
        this.on_short_word = on_short_word;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Image getImage() {
        return image;
    }

    public String getLocation() {
        return location;
    }

    public String getOn_short_word() {
        return on_short_word;
    }

    public String getTitle() {
        return title;
    }

}
