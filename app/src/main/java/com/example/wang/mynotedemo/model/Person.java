package com.example.wang.mynotedemo.model;

/**
 * Created by wang on 16/8/8.
 */
public class Person {
    String person_title;
    String person_content;
    String person_portrait;

    public String getPerson_portrait() {
        return person_portrait;
    }

    public void setPerson_portrait(String person_portrait) {
        this.person_portrait = person_portrait;
    }

    public String getPerson_content() {
        return person_content;
    }

    public void setPerson_content(String person_content) {
        this.person_content = person_content;
    }

    public String getPerson_title() {
        return person_title;
    }

    public void setPerson_title(String person_title) {
        this.person_title = person_title;
    }
}
