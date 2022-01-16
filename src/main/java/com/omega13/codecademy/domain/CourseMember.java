package com.omega13.codecademy.domain;

public class CourseMember {
    private String name;
    private String email;
    private java.sql.Date birthday;
    private boolean gender;
    private  String address;
    private String city;
    private String country;
    private int id;


    public CourseMember(int id, String name, String email, java.sql.Date birthday, boolean gender, String address, String city, String country) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.country = country;
    }

    /*public CourseMember(){

    }*/

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public java.sql.Date getBirthday() {
        return birthday;
    }

    public String getGender() {
        if(this.gender) {
            return "Man";
        } else {
            return "Vrouw";
        }
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString(){
        return this.getName();
    }
}
