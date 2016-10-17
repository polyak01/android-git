package com.polyakov.androidgithubclient.view.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.polyakov.androidgithubclient.R;
import com.polyakov.androidgithubclient.model.Repository;
import com.polyakov.androidgithubclient.presenter.RepositoriesPresenter;
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
 * Created by SnowFlake on 17.10.2016.
 */

public class SearchFragment extends Fragment implements SearchView,
        BaseAdapter.OnItemClickListener<Repository>{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    EmptyRecyclerView mRecyclerView;

    @BindView(R.id.empty)
    View mEmptyView;

//    private SearchView mSearchView = null;

    private ILoadingView mLoadingView = null;
    private RepositoriesAdapter mAdapter = null;
    private SearchPresenter mSearchPresenter = null;



//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.benefit_fragment, container, false);
//    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      /*  ButterKnife.bind(getActivity());


//        mLoadingView = LoadingDialog.view(getFragmentManager());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        mRecyclerView.setEmptyView(mEmptyView);

        mAdapter = new RepositoriesAdapter(new ArrayList<>());
        mAdapter.attachToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(this);

        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(getActivity(), getLoaderManager());
        mSearchPresenter = new SearchPresenter(lifecycleHandler, this);
        mSearchPresenter.init();



        searchPresenter = new SearchPresenter(this);*/
    }

    @Override
    public void showRepositories(@NonNull List<Repository> repositories) {

    }

    @Override
    public void showCommits(@NonNull Repository repository) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void startSearching(String nameOfSearchRepositories) {

    }

    @Override
    public void onItemClick(@NonNull Repository item) {
//        searchPresenter.loadRepositories(repName);
    }
}
