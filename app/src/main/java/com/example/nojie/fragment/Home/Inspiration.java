package com.example.nojie.fragment.Home;

import android.media.Image;

public class Inspiration {
    private String block_title;
    private String article_title;
    private String block_content;
    private Image block_content_img;
    private String block_date;

    public Inspiration(String block_title, String article_title,String block_content,Image block_content_img,String block_date){
        this.block_content=block_content;
        this.article_title=article_title;
        this.block_title=block_title;
        this.block_content_img=block_content_img;
        this.block_date=block_date;
    }


    public String getBlock_title() {
        return block_title;
    }

    public String getBlock_date() {
        return block_date;
    }

    public String getArticle_title() {
        return article_title;
    }

    public Image getBlock_content_img() {
        return block_content_img;
    }

    public String getBlock_content() {
        return block_content;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public void setBlock_content(String block_content) {
        this.block_content = block_content;
    }

    public void setBlock_content_img(Image block_content_img) {
        this.block_content_img = block_content_img;
    }

    public void setBlock_date(String block_date) {
        this.block_date = block_date;
    }

    public void setBlock_title(String block_title) {
        this.block_title = block_title;
    }
}
