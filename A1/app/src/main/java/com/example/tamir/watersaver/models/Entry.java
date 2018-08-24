package com.example.tamir.watersaver.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity(tableName = "entry")
public class Entry implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "showerVol")
    private double showerVol;

    @ColumnInfo(name = "toiletVol")
    private double toiletVol;

    @ColumnInfo(name = "hygineVol")
    private double hygineVol;

    @ColumnInfo(name = "laundryVol")
    private double laundryVol;

    @ColumnInfo(name = "dishesVol")
    private double dishesVol;

    @ColumnInfo(name = "cookingVol")
    private double cookingVol;

    @ColumnInfo(name = "cleaningVol")
    private double cleaningVol;


    public Entry() {

    }


    public Entry(String date) {
        this.date = date;


        this.showerVol = 0;
        this.toiletVol = 0;
        this.hygineVol = 0;
        this.laundryVol = 0;
        this.dishesVol = 0;
        this.cookingVol = 0;
        this.cleaningVol = 0;
    }

    public Entry(String date, double showerVol, double toiletVol, double hygineVol, double laundryVol, double dishesVol, double cookingVol, double cleaningVol) {
        this.date = date;
        this.showerVol = showerVol;
        this.toiletVol = toiletVol;
        this.hygineVol = hygineVol;
        this.laundryVol = laundryVol;
        this.dishesVol = dishesVol;
        this.cookingVol = cookingVol;
        this.cleaningVol = cleaningVol;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getShowerVol() {
        return showerVol;
    }

    public void setShowerVol(double showerVol) {
        this.showerVol = showerVol;
    }

    public double getToiletVol() {
        return toiletVol;
    }

    public void setToiletVol(double toiletVol) {
        this.toiletVol = toiletVol;
    }

    public double getHygineVol() {
        return hygineVol;
    }

    public void setHygineVol(double hygineVol) {
        this.hygineVol = hygineVol;
    }

    public double getLaundryVol() {
        return laundryVol;
    }

    public void setLaundryVol(double laundryVol) {
        this.laundryVol = laundryVol;
    }

    public double getDishesVol() {
        return dishesVol;
    }

    public void setDishesVol(double dishesVol) {
        this.dishesVol = dishesVol;
    }

    public double getCookingVol() {
        return cookingVol;
    }

    public void setCookingVol(double cookingVol) {
        this.cookingVol = cookingVol;
    }

    public double getCleaningVol() {
        return cleaningVol;
    }

    public void setCleaningVol(double cleaningVol) {
        this.cleaningVol = cleaningVol;
    }

    public Double getTotalVol() {
        return showerVol + toiletVol + hygineVol + laundryVol + dishesVol + cookingVol + cleaningVol;
    }
}
