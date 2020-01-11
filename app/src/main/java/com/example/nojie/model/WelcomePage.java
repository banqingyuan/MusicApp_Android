package com.example.nojie.model;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.sql.Timestamp;

public class WelcomePage extends LitePalSupport {
    @Column(nullable = false)
    private Integer pageId;
    @Column
    private String sign;
    @Column
    private String backgroundUrl;
    @Column
    private Timestamp timestamp;

    public Integer getPageId() {
        return pageId;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public String getSign() {
        return sign;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
