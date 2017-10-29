package com.hackerearth.vishant.kickstarter.database;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class RealmDB {

    public static Realm getRealm(Context context) {
        Realm.init(context);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        //Realm.deleteRealm(realmConfiguration); // Clean slate
        Realm.setDefaultConfiguration(realmConfiguration);
        Realm realm = Realm.getDefaultInstance();

        return realm;
    }
}
