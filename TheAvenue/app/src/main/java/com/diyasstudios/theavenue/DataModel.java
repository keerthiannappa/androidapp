package com.diyasstudios.theavenue;


public class DataModel {

    String name;
    String type;
    String date;
    String time;


    public DataModel(String name, String type, String date, String time ) {
        this.name=name;
        this.type=type;
        this.date=date;
        this.time=time;

    }


    public String getName() {
        return name;
    }


    public String getType() {
        return type;
    }


    public String getDate() {
        return date;
    }




}
