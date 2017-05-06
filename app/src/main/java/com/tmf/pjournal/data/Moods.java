package com.tmf.pjournal.data;

import io.realm.RealmObject;

public class Moods extends RealmObject {
    private long dateMillis;

    // common moods - default
    private boolean angry;
    private boolean blah;
    private boolean calm;
    private boolean depressed;
    private boolean fine;
    private boolean happy;
    private boolean hyper;
    private boolean inLove;
    private boolean irritable;
    private boolean sad;
    private boolean sick;
    private boolean stressed;
    private boolean tired;

    public Moods() {}

    public long getDateMillis() {
        return dateMillis;
    }

    public void setDateMillis(long dateMillis) {
        this.dateMillis = dateMillis;
    }

    public boolean isAngry() {
        return angry;
    }

    public void setAngry(boolean angry) {
        this.angry = angry;
    }

    public boolean isBlah() {
        return blah;
    }

    public void setBlah(boolean blah) {
        this.blah = blah;
    }

    public boolean isCalm() {
        return calm;
    }

    public void setCalm(boolean calm) {
        this.calm = calm;
    }

    public boolean isDepressed() {
        return depressed;
    }

    public void setDepressed(boolean depressed) {
        this.depressed = depressed;
    }

    public boolean isFine() {
        return fine;
    }

    public void setFine(boolean fine) {
        this.fine = fine;
    }

    public boolean isHappy() {
        return happy;
    }

    public void setHappy(boolean happy) {
        this.happy = happy;
    }

    public boolean isHyper() {
        return hyper;
    }

    public void setHyper(boolean hyper) {
        this.hyper = hyper;
    }

    public boolean isInLove() {
        return inLove;
    }

    public void setInLove(boolean inLove) {
        this.inLove = inLove;
    }

    public boolean isIrritable() {
        return irritable;
    }

    public void setIrritable(boolean irritable) {
        this.irritable = irritable;
    }

    public boolean isSad() {
        return sad;
    }

    public void setSad(boolean sad) {
        this.sad = sad;
    }

    public boolean isSick() {
        return sick;
    }

    public void setSick(boolean sick) {
        this.sick = sick;
    }

    public boolean isStressed() {
        return stressed;
    }

    public void setStressed(boolean stressed) {
        this.stressed = stressed;
    }

    public boolean isTired() {
        return tired;
    }

    public void setTired(boolean tired) {
        this.tired = tired;
    }
}