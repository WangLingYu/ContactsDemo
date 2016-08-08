package com.example.wang.mynotedemo;

/**
 * Created by wang on 16/8/8.
 */
public class Person {
    String note_title;
    String note_content;
    String note_create_time;

    public String getNote_create_time() {
        return note_create_time;
    }

    public void setNote_create_time(String note_create_time) {
        this.note_create_time = note_create_time;
    }

    public String getNote_content() {
        return note_content;
    }

    public void setNote_content(String note_content) {
        this.note_content = note_content;
    }

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }
}
