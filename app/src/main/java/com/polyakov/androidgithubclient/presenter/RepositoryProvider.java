package com.polyakov.androidgithubclient.presenter;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.polyakov.androidgithubclient.utils.HawkKeyValueStorage;
import com.polyakov.androidgithubclient.utils.KeyValueStorage;
import com.polyakov.androidgithubclient.view.interfaces.GithubRepository;

/**
 * @author Yaroslav
 */
public final class RepositoryProvider {

    private static GithubRepository sGithubRepository;
    private static KeyValueStorage sKeyValueStorage;

    private RepositoryProvider() {
    }

    @NonNull
    public static GithubRepository provideGithubRepository() {
        if (sGithubRepository == null) {
            sGithubRepository = new DefaultGithubRepository();
        }
        return sGithubRepository;
    }

    public static void setGithubRepository(@NonNull GithubRepository githubRepository) {
        sGithubRepository = githubRepository;
    }

    @NonNull
    public static KeyValueStorage provideKeyValueStorage() {
        if (sKeyValueStorage == null) {
            sKeyValueStorage = new HawkKeyValueStorage();
        }
        return sKeyValueStorage;
    }

    public static void setKeyValueStorage(@NonNull KeyValueStorage keyValueStorage) {
        sKeyValueStorage = keyValueStorage;
    }

    @MainThread
    public static void init() {
        sGithubRepository = new DefaultGithubRepository();
        sKeyValueStorage = new HawkKeyValueStorage();
    }
}
