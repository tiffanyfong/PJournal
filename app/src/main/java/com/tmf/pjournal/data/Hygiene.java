package com.tmf.pjournal.data;

import io.realm.RealmObject;

public class Hygiene extends RealmObject {
    private String date;

    private int lightTampons;
    private int mediumTampons;
    private int heavyTampons;

    private int lightPads;
    private int mediumPads;
    private int heavyPads;

    private int menstrualCups;

    public Hygiene() {}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLightTampons() {
        return lightTampons;
    }

    public void setLightTampons(int lightTampons) {
        this.lightTampons = lightTampons;
    }

    public int getMediumTampons() {
        return mediumTampons;
    }

    public void setMediumTampons(int mediumTampons) {
        this.mediumTampons = mediumTampons;
    }

    public int getHeavyTampons() {
        return heavyTampons;
    }

    public void setHeavyTampons(int heavyTampons) {
        this.heavyTampons = heavyTampons;
    }

    public int getLightPads() {
        return lightPads;
    }

    public void setLightPads(int lightPads) {
        this.lightPads = lightPads;
    }

    public int getMediumPads() {
        return mediumPads;
    }

    public void setMediumPads(int mediumPads) {
        this.mediumPads = mediumPads;
    }

    public int getHeavyPads() {
        return heavyPads;
    }

    public void setHeavyPads(int heavyPads) {
        this.heavyPads = heavyPads;
    }

    public int getMenstrualCups() {
        return menstrualCups;
    }

    public void setMenstrualCups(int menstrualCups) {
        this.menstrualCups = menstrualCups;
    }
}
