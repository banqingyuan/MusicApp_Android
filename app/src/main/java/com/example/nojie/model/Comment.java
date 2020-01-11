package com.example.nojie.model;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.sql.Timestamp;

public class Comment extends LitePalSupport {
    @Column
    private Integer commentId;
    @Column
    private Integer userId;
    @Column
    private Integer worksId;
    @Column
    private String Date;
    @Column
    private String Content;
    @Column
    private Timestamp timestamp;

    public String getContent() {
        return Content;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getDate() {
        return Date;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public Integer getWorksId() {
        return worksId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public void setWorksId(Integer worksId) {
        this.worksId = worksId;
    }

    @Override
    public void setToDefault(String fieldName) {
        super.setToDefault(fieldName);
    }

    @Override
    protected long getBaseObjId() {
        return super.getBaseObjId();
    }

    @Override
    protected String getClassName() {
        return super.getClassName();
    }

    @Override
    protected String getTableName() {
        return super.getTableName();
    }
}
