package com.polyakov.androidgithubclient.utils;

import android.support.annotation.Nullable;

/**
 * @author Yaroslav Polyakov
 *         © 28.06.17 https://github.com/polyak01
 */

public final class TextUtils {

    private TextUtils() {
    }

    public static boolean isEmpty(@Nullable CharSequence text) {
        return text == null || text.length() == 0;
    }

}
