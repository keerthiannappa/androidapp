package com.diyaasstudio.theavenue_adminadder;

/**
 * Created by Keerthi on 11/28/2017.
 */

public class DataModel {

    String name,number,email;

    public DataModel(String name, String number, String email) {
        this.name = name;
        this.number = number;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }
}
