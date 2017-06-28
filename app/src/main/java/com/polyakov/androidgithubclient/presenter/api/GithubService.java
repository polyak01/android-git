package com.polyakov.androidgithubclient.presenter.api;

import com.google.gson.JsonObject;
import com.polyakov.androidgithubclient.models.Authorization;
import com.polyakov.androidgithubclient.models.RepositoriesResponse;
import com.polyakov.androidgithubclient.models.Repository;
import com.polyakov.androidgithubclient.models.content.CommitResponse;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Yaroslav
 */

public interface GithubService {

    @POST("/authorizations")
    Observable<Authorization> authorize(@Header("Authorization") String authorization,
                                        @Body JsonObject params);

    @GET("/user/repos")
    Observable<List<Repository>> repositories();

    @GET("/repos/{user}/{repo}/commits")
    Observable<List<CommitResponse>> commits(@Path("user") String user, @Path("repo") String repo);

    @GET("/search/repositories")
    Observable<RepositoriesResponse> repositorySearching(@Query("q") String nameOfRepo);
}
