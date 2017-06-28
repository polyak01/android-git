package com.polyakov.androidgithubclient.view.interfaces;

import android.support.annotation.NonNull;

import com.polyakov.androidgithubclient.models.Repository;

import java.util.List;

/**
 * @author Yaroslav Polyakov
 *         © 2016 https://github.com/polyak01
 */

public interface RepositoriesView extends ILoadingView {

    void showRepositories(@NonNull List<Repository> repositories);

    void showCommits(@NonNull Repository repository);

    void showError();
}
