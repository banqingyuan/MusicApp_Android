package com.example.nojie;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.nojie.model.Current_User;
import com.example.nojie.utility.SendRequest;

import org.litepal.LitePal;

public class Myapp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        SQLiteDatabase db = LitePal.getDatabase();
        getCurrentUser();

        setLogin_status(false);
    }

    private void getCurrentUser() {
        try {
            Current_User user = LitePal.find(Current_User.class,0);
        }catch (Exception e){
            return ;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    SendRequest sendRequest = new SendRequest("/pull_current_user?userName=test0");
                    String response = sendRequest.sendRequest();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public Boolean login_status;
    public String userName;
    public Integer userId;
    private String sex;
    private Integer age;
    private String sign;
    private String iconUrl;
    private String birthday;
    private String location;
    private String tag;
    private String verify;
    private String watch;
    private String beWatched;
    private String collectList;
    private String workList;

    public String getSign() {
        return sign;
    }

    public String getLocation() {
        return location;
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

    public String getSex() {
        return sex;
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

    public String getWorkList() {
        return workList;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public void setWatch(String watch) {
        this.watch = watch;
    }

    public void setWorkList(String workList) {
        this.workList = workList;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setLogin_status(Boolean login_status) {
        this.login_status = login_status;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public Boolean getLogin_status() {
        return login_status;
    }

    public String getUserName() {
        return userName;
    }



}
