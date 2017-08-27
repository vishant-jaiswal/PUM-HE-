package com.payu.vishant.payukickstarter.database;

import android.content.Context;

import com.payu.vishant.payukickstarter.models.KickStarter;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.payu.vishant.payukickstarter.database.RealmDB.getRealm;

/**
 * Created by Vishant on 8/27/2017.
 */

public class RealmInterfaceProjects {

    public static void saveProjects(Context c, KickStarter k) {
        Realm realm = getRealm(c);
        realm.beginTransaction();
        KickStarter n = new KickStarter();
        n.setS_no(k.getS_no());
        n.setAmt_pledged(k.getAmt_pledged());
        n.setBlurb(k.getBlurb());
        n.setBy(k.getBy());
        n.setCountry(k.getCountry());
        n.setCurrency(k.getCurrency());
        n.setEnd_time(k.getEnd_time());
        n.setLocation(k.getLocation());
        n.setPercentage_funded(k.getPercentage_funded());
        n.setNum_backers(k.getNum_backers());
        n.setState(k.getState());
        n.setTitle(k.getTitle());
        n.setType(k.getType());
        n.setUrl(k.getUrl());
        KickStarter managedProject = realm.copyToRealmOrUpdate(n);
        realm.commitTransaction();

    }

    public static ArrayList<KickStarter> getProjectWeLove(Context c) {
        Realm realm = getRealm(c);
        RealmResults<KickStarter> results = realm.where(KickStarter.class).greaterThan("num_backers", 30000)
                .greaterThan("percentage_funded", 60).findAll();
        return new ArrayList(results);
    }
}
