package com.polyakov.androidgithubclient.screen.auth;

import android.support.annotation.NonNull;

import com.polyakov.androidgithubclient.models.Authorization;
import com.polyakov.androidgithubclient.presenter.LoginPresenter;
import com.polyakov.androidgithubclient.presenter.RepositoryProvider;
import com.polyakov.androidgithubclient.test.MockLifecycleHandler;
import com.polyakov.androidgithubclient.test.TestGithubRepository;
import com.polyakov.androidgithubclient.test.TestKeyValueStorage;
import com.polyakov.androidgithubclient.utils.KeyValueStorage;
import com.polyakov.androidgithubclient.view.interfaces.ILoginView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.io.IOException;

import ru.arturvasilov.rxloader.LifecycleHandler;
import rx.Observable;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.times;

/**
 * @author Yaroslav Polyakov
 *         © 28.06.17 https://github.com/polyak01
 */
@RunWith(JUnit4.class)
public class AuthPresenterTest {
    private ILoginView loginView;
    private LoginPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        LifecycleHandler lifecycleHandler = new MockLifecycleHandler();
        loginView = Mockito.mock(ILoginView.class);

        mPresenter = new LoginPresenter(lifecycleHandler, loginView);
    }

    @Test
    public void testCreated() throws Exception {
        assertNotNull(mPresenter);
    }

    @Test
    public void testNoActionsWithView() throws Exception {
        Mockito.verifyNoMoreInteractions(loginView);
    }

    @Test
    public void testEmptyToken() throws Exception {
        KeyValueStorage storage = new TokenKeyValueStorage("");
        RepositoryProvider.setKeyValueStorage(storage);

        mPresenter.init();

        Mockito.verifyNoMoreInteractions(loginView);
    }

    @Test
    public void testMainScreenOpened() throws Exception {
        KeyValueStorage storage = new TokenKeyValueStorage("ac781f9d0eb890d1e107d2898db9");
        RepositoryProvider.setKeyValueStorage(storage);

        mPresenter.init();

        Mockito.verify(loginView).openRepositoriesScreen();
        Mockito.verify(loginView, times(0)).showLoading();
    }

    @Test
    public void testEmptyLogin() throws Exception {
        mPresenter.tryLogIn("", "password");
        Mockito.verify(loginView).showLoginError();
    }

    @Test
    public void testEmptyPassword() throws Exception {
        mPresenter.tryLogIn("login", "");
        Mockito.verify(loginView).showPasswordError();
    }

    @Test
    public void testLoginAndPasswordEmpty() throws Exception {
        mPresenter.tryLogIn("", "");
        Mockito.verify(loginView).showLoginError();
    }

    @Test
    public void testSuccessAuth() throws Exception {
        RepositoryProvider.setGithubRepository(new AuthTestRepository());

        mPresenter.tryLogIn("alice", "qwerty");
        Mockito.verify(loginView).openRepositoriesScreen();
    }

    @Test
    public void testErrorAuth() throws Exception {
        RepositoryProvider.setGithubRepository(new AuthTestRepository());

        mPresenter.tryLogIn("bob", "123456");
        Mockito.verify(loginView).showLoginError();
    }

    @SuppressWarnings("ConstantConditions")
    @After
    public void tearDown() throws Exception {
        RepositoryProvider.setKeyValueStorage(null);
        RepositoryProvider.setGithubRepository(null);
    }

    @Test
    public void testScreenScenario() throws Exception {
        KeyValueStorage storage = new TokenKeyValueStorage("");
        RepositoryProvider.setKeyValueStorage(storage);

        mPresenter.init();
        Mockito.verifyNoMoreInteractions(loginView);

        RepositoryProvider.setGithubRepository(new AuthTestRepository());
        mPresenter.tryLogIn("abc", "xzy");
        Mockito.verify(loginView).showLoading();
        Mockito.verify(loginView).hideLoading();
        Mockito.verify(loginView).showLoginError();

        mPresenter.tryLogIn("alice", "qwerty");
        Mockito.verify(loginView, times(2)).showLoading();
        Mockito.verify(loginView, times(2)).hideLoading();
        Mockito.verify(loginView).openRepositoriesScreen();
    }

    private class TokenKeyValueStorage extends TestKeyValueStorage {

        private final String mToken;

        public TokenKeyValueStorage(@NonNull String token) {
            mToken = token;
        }

        @NonNull
        @Override
        public String getToken() {
            return mToken;
        }
    }

    private class AuthTestRepository extends TestGithubRepository {

        @NonNull
        @Override
        public Observable<Authorization> auth(@NonNull String login, @NonNull String password) {
            if ("alice".equals(login) && "qwerty".equals(password)) {
                return Observable.just(new Authorization());
            }
            return Observable.error(new IOException());
        }
    }
}
