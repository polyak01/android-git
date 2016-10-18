package com.polyakov.androidgithubclient.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.polyakov.androidgithubclient.view.interfaces.ISearchGithubRepository;

/**
 * Created by SnowFlake on 18.10.2016.
 */

public final class SearchRepositoryProvider {

    private static ISearchGithubRepository sGithubRepository;

    private SearchRepositoryProvider() {

    }

    @NonNull
    public static ISearchGithubRepository searchGithubRepository() {
        Log.i("SearchRepositoryProvider", "start SearchRepositoryProvider");
        if (sGithubRepository == null) {
            sGithubRepository = new SearchGitRepository();
        }
        Log.i("SearchRepositoryProvider", " " + sGithubRepository);
        return sGithubRepository;
    }
}
