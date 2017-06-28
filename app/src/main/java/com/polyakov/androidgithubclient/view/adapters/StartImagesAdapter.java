package com.polyakov.androidgithubclient.view.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.polyakov.androidgithubclient.view.holders.BenefitResHolder;
import com.polyakov.androidgithubclient.view.fragments.ImagesBenefitFragment;

import java.util.List;

/**
 * @author Yaroslav Polyakov
 *         © 2016 https://github.com/polyak01
 */

public class StartImagesAdapter extends FragmentStatePagerAdapter {

    private final List<BenefitResHolder> mBenefitResHolders;

    public StartImagesAdapter(FragmentManager fm, @NonNull List<BenefitResHolder> benefitResHolders) {
        super(fm);
        mBenefitResHolders = benefitResHolders;
    }

    @Override
    public Fragment getItem(int position) {
        return ImagesBenefitFragment.create(mBenefitResHolders.get(position));
    }

    @Override
    public int getCount() {
        return mBenefitResHolders.size();
    }
}