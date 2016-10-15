package com.polyakov.androidgithubclient.view.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.polyakov.androidgithubclient.view.content.Benefit;
import com.polyakov.androidgithubclient.view.fragments.ImagesBenefitFragment;

import java.util.List;

/**
 * @author Yaroslav
 */

public class StartImagesAdapter extends FragmentStatePagerAdapter {

    private final List<Benefit> mBenefits;

    public StartImagesAdapter(FragmentManager fm, @NonNull List<Benefit> benefits) {
        super(fm);
        mBenefits = benefits;
    }

    @Override
    public Fragment getItem(int position) {
        return ImagesBenefitFragment.create(mBenefits.get(position));
    }

    @Override
    public int getCount() {
        return mBenefits.size();
    }
}