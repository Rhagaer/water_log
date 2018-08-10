package com.example.tamir.watersaver.models;

import java.io.Serializable;

public class Entry implements Serializable{

    private String date;
    private double usage;

    public Entry() {

    }

    public Entry(String date, double usage) {
        this.date = date;
        this.usage = usage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getUsage() {
        return usage;
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }
}
