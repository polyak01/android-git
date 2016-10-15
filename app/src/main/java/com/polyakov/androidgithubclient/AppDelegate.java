package com.polyakov.androidgithubclient;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;
import com.polyakov.androidgithubclient.model.api.ApiFactory;
import com.polyakov.androidgithubclient.presenter.RepositoryProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

/**
 * @author Yaroslav
 */

public class AppDelegate extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;

        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSharedPrefStorage(this))
                .setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE)
                .build();

        RealmConfiguration configuration = new RealmConfiguration.Builder(this)
                .rxFactory(new RealmObservableFactory())
                .build();
        Realm.setDefaultConfiguration(configuration);

        ApiFactory.recreate();
        RepositoryProvider.init();
    }

    @NonNull
    public static Context getContext() {
        return sContext;
    }
}
