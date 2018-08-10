package com.example.tamir.watersaver.controllers.entry_list;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.tamir.watersaver.R;
import com.example.tamir.watersaver.models.Entry;
import com.example.tamir.watersaver.utils.Intents;

public class EntryDetailFragment extends Fragment {

    public static final String TAG = "EntryDetailFragment";

    private TextView mDateTV;
    private TextView mVolumeTV;


    private Entry mEntry;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();


        if (bundle != null) {
            mEntry = (Entry) bundle.getSerializable(Intents.Entry.toString());
            Log.i(TAG, mEntry.getDate());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.entry_detail, container, false);

        mDateTV = view.findViewById(R.id.date_tv);
        mVolumeTV = view.findViewById(R.id.volume_tv);

        if(mEntry != null) {
            mDateTV.setText(mEntry.getDate());
            mVolumeTV.setText(Double.toString(mEntry.getUsage()));
        }
        return view;
    }
}
