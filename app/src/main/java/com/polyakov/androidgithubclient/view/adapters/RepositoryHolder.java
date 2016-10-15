package com.polyakov.androidgithubclient.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.polyakov.androidgithubclient.R;
import com.polyakov.androidgithubclient.model.Repository;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Yaroslav
 */

public class RepositoryHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.repositoryName)
    TextView mName;

    @BindView(R.id.repositoryLanguage)
    TextView mLanguage;

    public RepositoryHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Repository repository) {
        mName.setText(repository.getName());
        mLanguage.setText(repository.getLanguage());
    }
}
