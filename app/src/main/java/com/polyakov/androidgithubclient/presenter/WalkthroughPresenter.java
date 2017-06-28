package com.polyakov.androidgithubclient.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.polyakov.androidgithubclient.R;
import com.polyakov.androidgithubclient.view.holders.BenefitResHolder;
import com.polyakov.androidgithubclient.view.interfaces.WalkthroughView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yaroslav Polyakov
 *         © 28.06.17 https://github.com/polyak01
 */

public class WalkthroughPresenter {

    private static final int PAGES_COUNT = 3;

    private final WalkthroughView mWalkthroughView;

    private int mCurrentItem = 0;

    public WalkthroughPresenter(@NonNull WalkthroughView walkthroughView) {
        mWalkthroughView = walkthroughView;
    }

    public void init() {
        if (RepositoryProvider.provideKeyValueStorage().isWalkthroughPassed()) {
            mWalkthroughView.startAuth();
        } else {
            mWalkthroughView.showBenefits(getBenefits());
            mWalkthroughView.showActionButtonText(R.string.next_uppercase);
        }
    }

    public void onActionButtonClick() {
        if (isLastBenefit()) {
            RepositoryProvider.provideKeyValueStorage().saveWalkthroughPassed();
            mWalkthroughView.startAuth();
        } else {
            mCurrentItem++;
            showBenefitText();
            mWalkthroughView.scrollToNextBenefit();
        }
    }

    public void onPageChanged(int selectedPage, boolean fromUser) {
        if (fromUser) {
            mCurrentItem = selectedPage;
            showBenefitText();
        }
    }

    @NonNull
    private List<BenefitResHolder> getBenefits() {
        return new ArrayList<BenefitResHolder>() {
            {
                add(BenefitResHolder.WORK_TOGETHER);
                add(BenefitResHolder.CODE_HISTORY);
                add(BenefitResHolder.PUBLISH_SOURCE);
            }
        };
    }

    private boolean isLastBenefit() {
        return mCurrentItem == PAGES_COUNT - 1;
    }

    private void showBenefitText() {
        @StringRes int buttonTextId = isLastBenefit() ? R.string.finish_uppercase : R.string.next_uppercase;
        mWalkthroughView.showActionButtonText(buttonTextId);
    }
}
