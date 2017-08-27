package com.payu.vishant.payukickstarter;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

/**
 * Created by Vishant on 8/27/2017.
 */

public class PayUApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this)
                                .withLimit(1000).build())
                        .build());
    }
}
