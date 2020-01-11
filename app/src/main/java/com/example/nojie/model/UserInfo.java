package com.example.nojie.model;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.sql.Timestamp;

public class UserInfo extends LitePalSupport {
    @Column(unique = true)
    private Integer userId;
    @Column
    private String userName;
    @Column
    private String sex;
    @Column
    private Integer age;
    @Column
    private String sign;
    @Column
    private String iconUrl;
    @Column
    private String birthday;
    @Column
    private String location;
    @Column
    private String tag;
    @Column
    private String verify;
    @Column
    private String watch;
    @Column
    private String beWatched;
    @Column
    private String collectList;
    @Column
    private String workList;
    public UserInfo() {
        super();
    }


    public UserInfo(String userName, Integer userId) {
        super();
        this.userName = userName;
        this.userId = userId;
    }

    public void setWorkList(String workList) {
        this.workList = workList;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBeWatched(String beWatched) {
        this.beWatched = beWatched;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setCollectList(String collectList) {
        this.collectList = collectList;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public void setWatch(String watch) {
        this.watch = watch;
    }

    public String getSex() {
        return sex;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getAge() {
        return age;
    }

    public String getBeWatched() {
        return beWatched;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getCollectList() {
        return collectList;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getWorkList() {
        return workList;
    }

    public String getLocation() {
        return location;
    }

    public String getSign() {
        return sign;
    }

    public String getTag() {
        return tag;
    }

    public String getVerify() {
        return verify;
    }

    public String getWatch() {
        return watch;
    }
}
