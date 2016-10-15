package com.polyakov.androidgithubclient.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.polyakov.androidgithubclient.R;
import com.polyakov.androidgithubclient.presenter.LoginPresenter;
import com.polyakov.androidgithubclient.view.general.LoadingDialog;
import com.polyakov.androidgithubclient.view.interfaces.ILoadingView;
import com.polyakov.androidgithubclient.view.interfaces.ILoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

/**
 * @author Yaroslav
 */

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.loginEdit)
    EditText mLoginEdit;

    @BindView(R.id.passwordEdit)
    EditText mPasswordEdit;

    @BindView(R.id.loginInputLayout)
    TextInputLayout mLoginInputLayout;

    @BindView(R.id.passwordInputLayout)
    TextInputLayout mPasswordInputLayout;

    private ILoadingView mLoadingView;

    private LoginPresenter mPresenter;

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mLoadingView = LoadingDialog.view(getSupportFragmentManager());
        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        mPresenter = new LoginPresenter(lifecycleHandler, this);
        mPresenter.init();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.logInButton)
    public void onLogInButtonClick() {
        String login = mLoginEdit.getText().toString();
        String password = mPasswordEdit.getText().toString();
        mPresenter.tryLogIn(login, password);
    }

    @Override
    public void showLoading() {
        mLoadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }

    @Override
    public void showLoginError() {
        mLoginInputLayout.setError(getString(R.string.error));
    }

    @Override
    public void showPasswordError() {
        mPasswordInputLayout.setError(getString(R.string.error));
    }

    @Override
    public void openRepositoriesScreen() {
        RepositoriesActivity.start(this);
        finish();
    }
}
