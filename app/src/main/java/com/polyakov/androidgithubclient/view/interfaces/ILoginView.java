package com.polyakov.androidgithubclient.view.interfaces;

/**
 * @author Yaroslav Polyakov
 *         © 2016 https://github.com/polyak01
 */

public interface ILoginView extends ILoadingView {
    void openRepositoriesScreen();

    void showLoginError();

    void showPasswordError();
}
