package com.chatonx.chat.models;

public class StatusModel {
    private String date;
    private String username;
    private int picture;
    private int id;

    public StatusModel(int picture, String username, String date) {
        this.picture = picture;
        this.username = username;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}