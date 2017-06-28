package com.polyakov.androidgithubclient.models.content;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * @author Yaroslav
 * TODO
 */

public class CommitResponse {

    @SerializedName("commit")
    private Commit mCommit;

    @NonNull
    public Commit getCommit() {
        return mCommit;
    }
}
