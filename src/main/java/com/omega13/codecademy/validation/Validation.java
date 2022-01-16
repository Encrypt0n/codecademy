package com.omega13.codecademy.validation;

import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public boolean URLValidation(String url){
        Pattern pattern = Pattern.compile("((http|https)://)([a-zA-Z@:%.\\+~#?&//=]{1,256}\\.[a-z])?” \n" +" + “[a-zA-Z0-9@:%.\\+~#?&//=]{1,256}\\.[a-z]” \n" + " + “{1,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        return matcher.find();
    }

    public boolean EmailValidation(String email){
        Pattern pattern = Pattern.compile("(.*)(@)(.*)(.[a-z])", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public boolean DateValidation(LocalDate date){
        if(!date.isAfter(LocalDate.now())){
            return true;
        }
        return false;

    }

    public boolean PercentageValidation(int percentage){
        if(percentage >= 0 && percentage <= 100){
            return true;
        }
        return false;
    }

    public boolean ZipcodeValidation(String zipcode){
        Pattern pattern = Pattern.compile("^[1-9][0-9]{3} [A-Z]{2}$");
        Matcher matcher = pattern.matcher(zipcode);
        return matcher.matches();
    }
}
