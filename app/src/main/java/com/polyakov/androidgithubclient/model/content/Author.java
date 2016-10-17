package com.polyakov.androidgithubclient.model.content;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * @author Yaroslav
 */

public class Author extends RealmObject {

    @SerializedName("name")
    private String mAuthorName;

    @NonNull
    public String getAuthorName() {
        return mAuthorName;
    }

    public void setAuthorName(@NonNull String authorName) {
        mAuthorName = authorName;
    }
}
