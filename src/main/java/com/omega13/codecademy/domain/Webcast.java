package com.omega13.codecademy.domain;

public class Webcast {

    private int id;
    private String title;



    public Webcast(int id, String title) {
        this.id = id;
        this.title = title;


    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString(){
        return this.getTitle();
    }


}
