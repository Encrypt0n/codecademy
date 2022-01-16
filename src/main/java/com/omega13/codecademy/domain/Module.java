package com.omega13.codecademy.domain;

import com.omega13.codecademy.domain.Enums.Level;

public class Module {

    private int id;
    private String title;
    private int contentID;


    public Module(int id, String title, int contentID) {
        this.id = id;
        this.title = title;
        this.contentID = contentID;

    }

    public int getId() {
        return id;
    }

    public int getContentID(){
        return this.contentID;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString(){
        return this.getTitle();
    }




}
