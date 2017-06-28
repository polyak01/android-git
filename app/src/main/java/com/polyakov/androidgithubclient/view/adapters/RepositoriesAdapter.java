package com.polyakov.androidgithubclient.view.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.polyakov.androidgithubclient.R;
import com.polyakov.androidgithubclient.models.Repository;
import com.polyakov.androidgithubclient.view.widgets.BaseAdapter;

import java.util.List;

/**
 * @author Yaroslav Polyakov
 *         © 2016 https://github.com/polyak01
 */

public class RepositoriesAdapter extends BaseAdapter<RepositoryHolder, Repository> {

    public RepositoriesAdapter(@NonNull List<Repository> items) {
        super(items);
    }

    @Override
    public RepositoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepositoryHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repository, parent, false));
    }

    @Override
    public void onBindViewHolder(RepositoryHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Repository repository = getItem(position);
        holder.bind(repository);
    }

}