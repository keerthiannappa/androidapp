package com.diyasstudios.theavenue;

/**
 * Created by Keerthi on 10/8/2017.
 */

public class UserDetails {

    //variables declared
    String Name,Mobile,EmailID,Password,Admin;

    //constructor
    public UserDetails(String name, String mobile, String emailID, String password,String admin) {
        Name = name;
        Mobile = mobile;
        EmailID = emailID;
        Password = password;
        Admin = admin;

    }

    //getters
    public String getName() {
        return Name;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getAdmin() { return Admin;}

    public String getEmailID() {
        return EmailID;
    }

    public String getPassword() {
        return Password;
    }
}
