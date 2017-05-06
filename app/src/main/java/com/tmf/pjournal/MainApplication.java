package com.tmf.pjournal;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainApplication extends Application {

    private Realm realmNote;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }

    public void openRealm() {
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realmNote = Realm.getInstance(config);
    }

    public void closeRealm() {
        realmNote.close();
    }

    public Realm getRealmNote() {
        return realmNote;
    }
}