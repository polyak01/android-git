package com.polyakov.androidgithubclient.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SnowFlake on 18.10.2016.
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
