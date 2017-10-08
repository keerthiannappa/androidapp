package com.diyasstudios.theavenue;

/**
 * Created by Keerthi on 10/8/2017.
 */

public class UserDetails {

    String Name,Mobile,EmailID,Password;

    public UserDetails(String name, String mobile, String emailID, String password) {
        Name = name;
        Mobile = mobile;
        EmailID = emailID;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getEmailID() {
        return EmailID;
    }

    public String getPassword() {
        return Password;
    }
}
