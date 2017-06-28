package com.polyakov.androidgithubclient.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.polyakov.androidgithubclient.R;
import com.polyakov.androidgithubclient.models.Repository;
import com.polyakov.androidgithubclient.presenter.SearchPresenter;
import com.polyakov.androidgithubclient.view.adapters.RepositoriesAdapter;
import com.polyakov.androidgithubclient.view.general.LoadingDialog;
import com.polyakov.androidgithubclient.view.interfaces.ILoadingView;
import com.polyakov.androidgithubclient.view.interfaces.SearchView;
import com.polyakov.androidgithubclient.view.widgets.BaseAdapter;
import com.polyakov.androidgithubclient.view.widgets.DividerItemDecoration;
import com.polyakov.androidgithubclient.view.widgets.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

/**
 * @author Yaroslav Polyakov
 *         © 2016 https://github.com/polyak01
 */
public class SearchActivity extends AppCompatActivity implements SearchView,
        BaseAdapter.OnItemClickListener<Repository> {

    @BindView(R.id.search_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    EmptyRecyclerView mRecyclerView;

    @BindView(R.id.empty)
    View mEmptyView;

    @BindView(R.id.search_view)
    android.support.v7.widget.SearchView mFindView;

    private ILoadingView mLoadingView = null;
    private RepositoriesAdapter mAdapter = null;
    private SearchPresenter mSearchPresenter = null;

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, SearchActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mFindView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                startSearching(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mRecyclerView.setEmptyView(mEmptyView);

        mAdapter = new RepositoriesAdapter(new ArrayList<>());
        mAdapter.attachToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(this);

        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        mSearchPresenter = new SearchPresenter(lifecycleHandler, this);
    }

    @Override
    public void startSearching(String nameOfSearchRepositories) {
        mSearchPresenter.loadRepositories(nameOfSearchRepositories);
    }

    @Override
    public void showRepositories(@NonNull List<Repository> repositories) {
        mAdapter.changeDataSet(repositories);
    }

    @Override
    public void showCommits(@NonNull Repository repository) {
        CommitsActivity.start(this, repository);
    }

    @Override
    public void showError() {
            mAdapter.clear();
    }

    @Override
    public void showLoading() {
            mLoadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }

    @Override
    public void onItemClick(@NonNull Repository item) {
        mSearchPresenter.onItemClick(item);
    }
}
