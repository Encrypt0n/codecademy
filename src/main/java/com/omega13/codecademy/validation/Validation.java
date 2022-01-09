package com.omega13.codecademy.validation;

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

    public boolean DateValidation(int day, int month, int year){
        if(month == 2){
            if((day >= 1 && day <= 29) && (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))){
                return true;
            }else if((day >= 1 && day <= 28) && (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0))){
                return true;
            }
        }
        else if((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day >= 1 && day <= 31) {
            return true;
        }else if((month == 4 || month == 6 || month == 9 || month == 11) && day >= 1 && day <= 30){
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
        Pattern pattern = Pattern.compile("/^[1-9][0-9]{3} [A-Z]{2}$/i", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(zipcode);
        return matcher.find();
    }
}
