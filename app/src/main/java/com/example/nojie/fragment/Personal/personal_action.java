package com.example.nojie.fragment.Personal;

import android.widget.ImageView;

public class personal_action {
    private String action_type;
    private String content_text;
    private String time;
    private ImageView image;

    public ImageView getImage() {
        return image;
    }

    public String getAction_type() {
        return action_type;
    }

    public String getContent_text() {
        return content_text;
    }

    public String getTime() {
        return time;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public void setContent_text(String content_text) {
        this.content_text = content_text;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
