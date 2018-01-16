package com.diyaasstudio.theavenue_adminadder;

/**
 * Created by Keerthi on 11/28/2017.
 */

public class AdminDetails {
    String name,number,email,psw;
    int admin;

    public AdminDetails(int admin, String email,String name, String number,String psw ) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.psw = psw;
        this.admin = admin;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public String getPsw() {
        return psw;
    }

    public int getAdmin() {
        return admin;
    }

    public String getName() {
        return name;
    }


}
