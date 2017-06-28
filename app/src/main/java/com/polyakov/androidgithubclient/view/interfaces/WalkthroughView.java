package com.polyakov.androidgithubclient.view.interfaces;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.polyakov.androidgithubclient.view.holders.BenefitResHolder;

import java.util.List;

/**
 * @author Yaroslav Polyakov
 *         © 2016 https://github.com/polyak01
 */

public interface WalkthroughView {
    void showBenefits(@NonNull List<BenefitResHolder> benefits);

    void showActionButtonText(@StringRes int buttonTextId);

    void scrollToNextBenefit();

    void startAuth();

}
