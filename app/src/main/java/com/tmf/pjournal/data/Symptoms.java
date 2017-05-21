package com.tmf.pjournal.data;


import io.realm.RealmObject;

public class Symptoms extends RealmObject {
    private String date;

    // common symptoms - default
    private boolean acne;
    private boolean backache;
    private boolean bloating;
    private boolean bloodClots;
    private boolean cramps;
    private boolean dizziness;
    private boolean fatigue;
    private boolean headache;
    private boolean insomnia;
    private boolean nausea;
    private boolean stomachache;
    private boolean tenderBreasts;

    public Symptoms() {}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isAcne() {
        return acne;
    }

    public void setAcne(boolean acne) {
        this.acne = acne;
    }

    public boolean isBackache() {
        return backache;
    }

    public void setBackache(boolean backache) {
        this.backache = backache;
    }

    public boolean isBloodClots() {
        return bloodClots;
    }

    public void setBloodClots(boolean bloodClots) {
        this.bloodClots = bloodClots;
    }

    public boolean isCramps() {
        return cramps;
    }

    public void setCramps(boolean cramps) {
        this.cramps = cramps;
    }

    public boolean isDizziness() {
        return dizziness;
    }

    public void setDizziness(boolean dizziness) {
        this.dizziness = dizziness;
    }

    public boolean isBloating() {
        return bloating;
    }

    public void setBloating(boolean bloating) {
        this.bloating = bloating;
    }

    public boolean isFatigue() {
        return fatigue;
    }

    public void setFatigue(boolean fatigue) {
        this.fatigue = fatigue;
    }

    public boolean isHeadache() {
        return headache;
    }

    public void setHeadache(boolean headache) {
        this.headache = headache;
    }

    public boolean isInsomnia() {
        return insomnia;
    }

    public void setInsomnia(boolean insomnia) {
        this.insomnia = insomnia;
    }

    public boolean isNausea() {
        return nausea;
    }

    public void setNausea(boolean nausea) {
        this.nausea = nausea;
    }

    public boolean isStomachache() {
        return stomachache;
    }

    public void setStomachache(boolean stomachache) {
        this.stomachache = stomachache;
    }

    public boolean isTenderBreasts() {
        return tenderBreasts;
    }

    public void setTenderBreasts(boolean tenderBreasts) {
        this.tenderBreasts = tenderBreasts;
    }
}
