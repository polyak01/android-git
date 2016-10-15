package com.polyakov.androidgithubclient.presenter;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.polyakov.androidgithubclient.view.interfaces.GithubRepository;

/**
 * @author Yaroslav
 * todo
 *
 */

public final class RepositoryProvider {

    private static GithubRepository sGithubRepository;

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

    @MainThread
    public static void init() {
        sGithubRepository = new DefaultGithubRepository();
    }
}
