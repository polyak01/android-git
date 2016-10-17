package com.polyakov.androidgithubclient.model.content;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * @author Yaroslav
 */

public class CommitResponse {

    @SerializedName("commit")
    private Commit mCommit;

    @NonNull
    public Commit getCommit() {
        return mCommit;
    }
}
