package com.polyakov.androidgithubclient.view.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import com.polyakov.androidgithubclient.R;
import com.polyakov.androidgithubclient.presenter.WalkthroughPresenter;
import com.polyakov.androidgithubclient.view.adapters.StartImagesAdapter;
import com.polyakov.androidgithubclient.view.holders.BenefitResHolder;
import com.polyakov.androidgithubclient.utils.KeyValueStorage;
import com.polyakov.androidgithubclient.view.interfaces.WalkthroughView;
import com.polyakov.androidgithubclient.view.widgets.PageChangeViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Yaroslav Polyakov
 *         © 2016 https://github.com/polyak01
 */

public class ImagesActivity extends AppCompatActivity implements
        PageChangeViewPager.PagerStateListener, WalkthroughView {

    @BindView(R.id.pager)
    PageChangeViewPager mPager;

    @BindView(R.id.btn_walkthrough)
    Button mActionButton;

    private WalkthroughPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.images_layout);
        ButterKnife.bind(this);
        mPager.setOnPageChangedListener(this);

        mPresenter = new WalkthroughPresenter(this);
        mPresenter.init();
    }

    @Override
    public void showBenefits(@NonNull List<BenefitResHolder> benefits) {
        mPager.setAdapter(new StartImagesAdapter(getFragmentManager(), benefits));
    }

    @Override
    public void showActionButtonText(@StringRes int buttonTextId) {
        mActionButton.setText(buttonTextId);
    }

    @Override
    public void scrollToNextBenefit() {
        mPager.smoothScrollNext(getResources().getInteger(android.R.integer.config_mediumAnimTime));
    }

    @Override
    public void startAuth() {
        LoginActivity.start(this);
        finish();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btn_walkthrough)
    public void onActionButtonClick() {
        mPresenter.onActionButtonClick();
    }

    @Override
    public void onPageChanged(int selectedPage, boolean fromUser) {
        mPresenter.onPageChanged(selectedPage, fromUser);
    }

}
