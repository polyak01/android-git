package com.polyakov.androidgithubclient.presenter;

import android.support.annotation.NonNull;

import com.polyakov.androidgithubclient.models.Authorization;
import com.polyakov.androidgithubclient.models.Repository;
import com.polyakov.androidgithubclient.presenter.api.ApiFactory;
import com.polyakov.androidgithubclient.view.interfaces.GithubRepository;
import com.polyakov.androidgithubclient.utils.AuthorizationUtils;
import com.polyakov.androidgithubclient.utils.KeyValueStorage;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;


/**
 * @author Yaroslav Polyakov
 *         © 28.06.17 https://github.com/polyak01
 */


public class DefaultGithubRepository implements GithubRepository {

    @NonNull
    @Override
    public Observable<List<Repository>> repositories() {
        return ApiFactory.getGithubService()
                .repositories()
                .flatMap(repositories -> {
                    Realm.getDefaultInstance().executeTransaction(realm -> {
                        realm.delete(Repository.class);
                        realm.insert(repositories);
                    });
                    return Observable.just(repositories);
                })
                .onErrorResumeNext(throwable -> {
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<Repository> repositories = realm.where(Repository.class).findAll();
                    return Observable.just(realm.copyFromRealm(repositories));
                })
                .compose(RxUtils.async());
    }

    @NonNull
    public Observable<Authorization> auth(@NonNull String login, @NonNull String password) {
        String authorizationString = AuthorizationUtils.createAuthorizationString(login, password);
        return ApiFactory.getGithubService()
                .authorize(authorizationString, AuthorizationUtils.createAuthorizationParam())
                .flatMap(authorization -> {
                    KeyValueStorage storage = RepositoryProvider.provideKeyValueStorage();
                    storage.saveToken(authorization.getToken());
                    storage.saveUserName(login);
                    ApiFactory.recreate();
                    return Observable.just(authorization);
                })
                .compose(RxUtils.async());
    }
}