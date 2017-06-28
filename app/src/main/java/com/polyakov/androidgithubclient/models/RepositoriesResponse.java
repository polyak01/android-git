package com.polyakov.androidgithubclient.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Yaroslav
 */

public class RepositoriesResponse {

    @SerializedName("items")
    private List<Repository> list = null;

    public List<Repository> getList() {
        return list;
    }

    public void setList(List<Repository> list) {
        this.list = list;
    }
}
