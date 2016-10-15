package com.polyakov.androidgithubclient.view.general;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.polyakov.androidgithubclient.R;
import com.polyakov.androidgithubclient.view.interfaces.ILoadingView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Yaroslav
 * 14.10.2016.
 */

public class LoadingDialog extends DialogFragment {

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    @NonNull
    public static ILoadingView view(@NonNull FragmentManager fm) {
        return new LoadingDialogView(fm);
    }

    @NonNull
    public static ILoadingView view(@NonNull Fragment fragment) {
        return view(fragment.getFragmentManager());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, getTheme());
        setCancelable(false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setView(View.inflate(getActivity(), R.layout.dialog_loading, null))
                .create();
    }

    private static class LoadingDialogView implements ILoadingView {

        private final FragmentManager mFragmentManager;

        private final AtomicBoolean mWaitForHide;

        private LoadingDialogView(@NonNull FragmentManager fragmentManager) {
            mFragmentManager = fragmentManager;
            boolean shown = fragmentManager.findFragmentByTag(LoadingDialog.class.getName()) != null;
            mWaitForHide = new AtomicBoolean(shown);
        }

        @Override
        public void showLoading() {
            if (mWaitForHide.compareAndSet(false, true)) {
                if (mFragmentManager.findFragmentByTag(LoadingDialog.class.getName()) == null) {
                    DialogFragment dialog = new LoadingDialog();
                    dialog.show(mFragmentManager, LoadingDialog.class.getName());
                }
            }
        }

        @Override
        public void hideLoading() {
            if (mWaitForHide.compareAndSet(true, false)) {
                HANDLER.post(new HideTask(mFragmentManager));
            }
        }
    }

    private static class HideTask implements Runnable {

        private final Reference<FragmentManager> mReferenceFragmentManager;

        private int mAttempts = 10;

        public HideTask(@NonNull FragmentManager fm) {
            mReferenceFragmentManager = new WeakReference<>(fm);
        }

        @Override
        public void run() {
            HANDLER.removeCallbacks(this);
            final FragmentManager fragmentManager = mReferenceFragmentManager.get();
            if (fragmentManager != null) {
                final LoadingDialog dialog = (LoadingDialog) fragmentManager.findFragmentByTag(LoadingDialog.class.getName());
                if (dialog != null) {
                    dialog.dismissAllowingStateLoss();
                } else if (--mAttempts >= 0) {
                    HANDLER.postDelayed(this, 300);
                }
            }
        }
    }
}
