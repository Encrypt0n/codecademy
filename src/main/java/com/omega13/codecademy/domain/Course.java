package com.omega13.codecademy.domain;

import com.omega13.codecademy.domain.Enums.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class Course {
    private int id;
    private String title;
    private String subject;
    private String introtext;
    private Level level;


    public Course(int id, String title, String subject, String introtext, Level level) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.introtext = introtext;
        this.level = level;

    }

    public Course(){

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() { return subject; }

    public String getIntrotext() { return introtext; }

    public Level getLevel() {
        return level;
    }

    @Override
    public String toString(){
        return this.getTitle();
    }






}
