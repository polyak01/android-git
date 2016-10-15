package com.polyakov.androidgithubclient.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.polyakov.androidgithubclient.R;
import com.polyakov.androidgithubclient.view.interfaces.ILoginView;
import com.polyakov.androidgithubclient.view.utils.PreferenceUtils;

import ru.arturvasilov.rxloader.LifecycleHandler;

/**
 * @author Yaroslav
 */

public class LoginPresenter {

    private final LifecycleHandler mLifecycleHandler;
    private final ILoginView mAuthView;

    public LoginPresenter(@NonNull LifecycleHandler lifecycleHandler,
                         @NonNull ILoginView authView) {
        mLifecycleHandler = lifecycleHandler;
        mAuthView = authView;
    }

    public void init() {
        String token = PreferenceUtils.getToken();
        if (!TextUtils.isEmpty(token)) {
            mAuthView.openRepositoriesScreen();
        }
    }

    public void tryLogIn(@NonNull String login, @NonNull String password) {
        if (TextUtils.isEmpty(login)) {
            mAuthView.showLoginError();
        } else if (TextUtils.isEmpty(password)) {
            mAuthView.showPasswordError();
        } else {
            RepositoryProvider.provideGithubRepository()
                    .auth(login, password)
                    .doOnSubscribe(mAuthView::showLoading)
                    .doOnTerminate(mAuthView::hideLoading)
                    .compose(mLifecycleHandler.reload(R.id.auth_request))
                    .subscribe(authorization -> mAuthView.openRepositoriesScreen(),
                            throwable -> mAuthView.showLoginError());
        }
    }
}
