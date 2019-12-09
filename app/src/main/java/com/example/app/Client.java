package com.example.app;

public class Client {
    private int Device_ID;
    private int Heart_Rate;
    private int ID;
    private String Name;
    private int Spo2;
    private String Surname;
    private int Temperature;
    private String Time;

    private int index;

    public Client(){
        this.index=-1;
    }

    public Client(int device_ID, int heart_Rate, int id, String name, int spo2, String surname, int temperature, String time) {
        Device_ID = device_ID;
        Heart_Rate = heart_Rate;
        ID = id;
        Name = name;
        Spo2 = spo2;
        Surname = surname;
        Temperature = temperature;
        Time = time;
        this.index=-1;
    }

    public int getDevice_ID() {
        return Device_ID;
    }

    public void setDevice_ID(int device_ID) {
        Device_ID = device_ID;
    }

    public int getHeart_Rate() {
        return Heart_Rate;
    }

    public void setHeart_Rate(int heart_Rate) {
        Heart_Rate = heart_Rate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        ID = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getSpo2() {
        return Spo2;
    }

    public void setSpo2(int spo2) {
        Spo2 = spo2;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public int getTemperature() {
        return Temperature;
    }

    public void setTemperature(int temperature) {
        Temperature = temperature;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        if(index==-1){
            this.index = index;
        }
    }
}
