package com.polyakov.androidgithubclient.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.polyakov.androidgithubclient.R;
import com.polyakov.androidgithubclient.view.holders.BenefitResHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Yaroslav
 */

public class ImagesBenefitFragment extends Fragment {
    private static final String BENEFIT_KEY = "benefit";

    @BindView(R.id.benefitIcon)
    ImageView mBenefitIcon;
    @BindView(R.id.benefitText)
    TextView mBenefitText;

    private BenefitResHolder mBenefitResHolder;

    @NonNull
    public static ImagesBenefitFragment create(@NonNull BenefitResHolder benefitResHolder) {
        Bundle bundle = new Bundle();
        bundle.putString(BENEFIT_KEY, benefitResHolder.name());
        ImagesBenefitFragment fragment = new ImagesBenefitFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String benefit = getArguments().getString(BENEFIT_KEY, BenefitResHolder.WORK_TOGETHER.name());
        mBenefitResHolder = BenefitResHolder.valueOf(benefit);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.benefit_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBenefitIcon.setImageResource(mBenefitResHolder.getDrawableId());
        mBenefitText.setText(mBenefitResHolder.getTextId());
    }
}
