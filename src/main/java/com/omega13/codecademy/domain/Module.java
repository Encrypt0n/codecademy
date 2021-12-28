package com.omega13.codecademy.domain;

import com.omega13.codecademy.domain.Enums.Level;

public class Module {

    private int id;
    private String title;



    public Module(int id, String title) {
        this.id = id;
        this.title = title;


    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }




}
