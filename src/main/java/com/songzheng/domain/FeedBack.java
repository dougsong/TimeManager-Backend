package com.songzheng.domain;

/**
 * Created by make on 2017/6/26.
 */
public class FeedBack {

    private String date;

    private String content;

    private String user_id;

    public FeedBack() {
    }

    public FeedBack(String date, String content, String user_id) {

        this.date = date;
        this.content = content;
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
