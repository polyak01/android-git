package com.polyakov.androidgithubclient.view.interfaces;

/**
 * @author Yaroslav
 */

public interface ILoginView extends ILoadingView {
    void openRepositoriesScreen();

    void showLoginError();

    void showPasswordError();
}
