package com.polyakov.androidgithubclient.view.interfaces;

import android.support.annotation.NonNull;

import com.polyakov.androidgithubclient.model.Repository;

import java.util.List;

/**
 * @author Yaroslav
 */

public interface RepositoriesView extends ILoadingView {

    void showRepositories(@NonNull List<Repository> repositories);

    void showCommits(@NonNull Repository repository);

    void showError();
}
