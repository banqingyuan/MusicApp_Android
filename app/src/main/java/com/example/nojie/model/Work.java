package com.example.nojie.model;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class Work extends LitePalSupport {
    @Column
    private Integer workId;
    @Column
    private String type;
    @Column
    private Integer userId;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String date;
    @Column
    private String approveList;
    @Column
    private String worksTimeStamp;

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getWorkId() {
        return workId;
    }

    public String getApproveList() {
        return approveList;
    }


    public String getType() {
        return type;
    }

    public String getWorksTimeStamp() {
        return worksTimeStamp;
    }

    public String getContent() {
        return content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setApproveList(String approveList) {
        this.approveList = approveList;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public void setWorksTimeStamp(String worksTimeStamp) {
        this.worksTimeStamp = worksTimeStamp;
    }
}
