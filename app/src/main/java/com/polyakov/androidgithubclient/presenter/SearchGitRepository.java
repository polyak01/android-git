package com.polyakov.androidgithubclient.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.JsonObject;
import com.polyakov.androidgithubclient.model.RepositoriesResponse;
import com.polyakov.androidgithubclient.model.Repository;
import com.polyakov.androidgithubclient.presenter.api.ApiFactory;
import com.polyakov.androidgithubclient.view.interfaces.ISearchGithubRepository;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;

/**
 * Created by SnowFlake on 17.10.2016.
 */

public class SearchGitRepository implements ISearchGithubRepository {
    @NonNull
    @Override
    public Observable<RepositoriesResponse> repositories(String repositoryName) {
        Log.i("AAAAA", repositoryName);
        return ApiFactory.getGithubService()
                .repositorySearching(repositoryName)
                //.doOnNext(rep -> { Log.i("AAAA","" + rep.size()); })
              /*  .flatMap(repositories -> {
                    Log.i("AAAAAA", "" + repositories.size());
                    Realm.getDefaultInstance().executeTransaction(realm -> {
                        realm.delete(Repository.class);
                        realm.insert(repositories);
                    });
                    return Observable.just(repositories);
                })*/
//                .onErrorResumeNext(throwable -> {
//                    Realm realm = Realm.getDefaultInstance();
//                    RealmResults<Repository> repositories = realm.where(Repository.class).findAll();
//                    return Observable.just(realm.copyFromRealm(repositories));
//                })
                .compose(RxUtils.async())
                .doOnNext( rep ->{ Log.i("TAGGG", "" + rep.getList().size());});
    }
}
