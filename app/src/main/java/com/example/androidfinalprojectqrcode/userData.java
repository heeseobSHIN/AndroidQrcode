package com.example.androidfinalprojectqrcode;

//useRealtimeDatabse
public class userData {
    private String name;
    public String location;
    private String withWho;
    private String datetime;

    public userData(){} // 생성자 메서드


//    public userData(String toString, String locationInfo) {
//        this.location = locationInfo;
//    }
    ///?

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public String getLocation() {
        return location;
    }

//    public void setLocation(String location) {
//        this.location = location;
//    }

    public String getWithWho() {
        return withWho;
    }

//    public void setWithWho(String withWho) {
//        this.withWho = withWho;
//    }

    public String getDateTime() {
        return datetime;
    }

//    public void setDateTime(Date dateTime) {
//        this.dateTime = dateTime;
//    }

    public userData(String name, String location, String withWho, String datetime) {
        this.name = name;
        this.location = location;
        this.withWho = withWho;
        this.datetime = datetime;
    }



}
