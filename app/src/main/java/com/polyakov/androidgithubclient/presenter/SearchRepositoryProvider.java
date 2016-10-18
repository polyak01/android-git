package com.polyakov.androidgithubclient.presenter;

import android.support.annotation.NonNull;

import com.polyakov.androidgithubclient.view.interfaces.ISearchGithubRepository;

/**
 * @author Yaroslav
 */

public final class SearchRepositoryProvider {

    private static ISearchGithubRepository sGithubRepository;

    private SearchRepositoryProvider() {

    }

    @NonNull
    public static ISearchGithubRepository searchGithubRepository() {
        if (sGithubRepository == null) {
            sGithubRepository = new SearchGitRepository();
        }
        return sGithubRepository;
    }
}
