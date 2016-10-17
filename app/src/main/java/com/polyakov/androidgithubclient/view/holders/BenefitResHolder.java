package com.polyakov.androidgithubclient.view.holders;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.polyakov.androidgithubclient.R;

/**
 * @author Yaroslav
 */

public enum BenefitResHolder {

    WORK_TOGETHER(R.string.benefit_work_together, R.drawable.cat1),
    CODE_HISTORY(R.string.benefit_code_history, R.drawable.cat2),
    PUBLISH_SOURCE(R.string.benefit_publish_source, R.drawable.cat3),;

    private final int mTextId;
    private final int mDrawableId;

    BenefitResHolder(@StringRes int textId, @DrawableRes int drawableId) {
        mTextId = textId;
        mDrawableId = drawableId;
    }

    @StringRes
    public int getTextId() {
        return mTextId;
    }

    @DrawableRes
    public int getDrawableId() {
        return mDrawableId;
    }
}

