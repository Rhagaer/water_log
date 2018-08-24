package com.example.tamir.watersaver.controllers.entry_list;

import android.content.Context;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.tamir.watersaver.INavigation;
import com.example.tamir.watersaver.MainActivity;
import com.example.tamir.watersaver.R;
import com.example.tamir.watersaver.models.AppDatabase;
import com.example.tamir.watersaver.models.Entry;
import com.example.tamir.watersaver.utils.Intents;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EntryDetailFragment extends Fragment {

    public static final String TAG = "EntryDetailFragment";

    private INavigation mINavigation;


    private TextView mDateTV;
    private TextView mVolumeTV;

    private TextView mShowerVolTV;
    private TextView mToiletVolTV;
    private TextView mHygineVolTV;
    private TextView mLaundryTV;
    private TextView mDishesTV;
    private TextView mCookingTV;
    private TextView mCleaningTV;


    private Button mPrevBtn;
    private Button mNextBtn;


    private Entry mEntry;
    private Entry mNext;
    private Entry mPrev;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mINavigation = (MainActivity) getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();


        if (bundle != null) {
            mEntry = (Entry) bundle.getSerializable(Intents.Entry.toString());
            Log.i(TAG, mEntry.getUid() + "");

            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                mNext = AppDatabase.getInstance(getContext()).entryDao().findByUid(mEntry.getUid() + 1);
                mPrev = AppDatabase.getInstance(getContext()).entryDao().findByUid(mEntry.getUid() - 1);
            });


        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.entry_detail, container, false);

        bindViews(view);
        bindDataToViews();


        return view;
    }

    private void bindViews(View view) {
        mDateTV = view.findViewById(R.id.date_tv);
        mVolumeTV = view.findViewById(R.id.vol_tv);

        mPrevBtn = view.findViewById(R.id.prev_btn);
        mPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mINavigation.navigateToEntryDetailPage(mPrev, false);
            }
        });
        mNextBtn = view.findViewById(R.id.next_btn);
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mINavigation.navigateToEntryDetailPage(mNext, false);
            }
        });

        mShowerVolTV = view.findViewById(R.id.shower_vol_tv);
        mToiletVolTV = view.findViewById(R.id.toilet_vol_tv);
        mHygineVolTV = view.findViewById(R.id.hygine_vol_tv);
        mLaundryTV = view.findViewById(R.id.laundry_vol_tv);
        mDishesTV = view.findViewById(R.id.dishes_vol_tv);
        mCookingTV = view.findViewById(R.id.cooking_vol_tv);
        mCleaningTV = view.findViewById(R.id.cleaning_vol_tv);

    }

    private void bindDataToViews() {

        if (mEntry != null) {
            mDateTV.setText(mEntry.getDate());
            mVolumeTV.setText(Double.toString(mEntry.getTotalVol()));
            mShowerVolTV.setText(Double.toString(mEntry.getShowerVol()));
            mToiletVolTV.setText(Double.toString(mEntry.getToiletVol()));
            mHygineVolTV.setText(Double.toString(mEntry.getHygineVol()));
            mLaundryTV.setText(Double.toString(mEntry.getLaundryVol()));
            mDishesTV.setText(Double.toString(mEntry.getDishesVol()));
            mCookingTV.setText(Double.toString(mEntry.getCookingVol()));
            mCleaningTV.setText(Double.toString(mEntry.getCleaningVol()));
        }
        if (mNext == null) {
            mNextBtn.setVisibility(View.INVISIBLE);
        }
        if (mPrev == null) {
            mPrevBtn.setVisibility(View.INVISIBLE);
        }


    }


}
