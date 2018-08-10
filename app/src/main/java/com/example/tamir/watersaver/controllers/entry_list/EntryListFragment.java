package com.example.tamir.watersaver.controllers.entry_list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tamir.watersaver.INavigation;
import com.example.tamir.watersaver.MainActivity;
import com.example.tamir.watersaver.R;
import com.example.tamir.watersaver.models.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class EntryListFragment extends Fragment{

    public static final String TAG = "EntryListFragment";

    private RecyclerView mRecyclerView;
    private EntryRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private INavigation mINavigation;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.entry_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<Entry> entries = getEntriesFromJSON();

        mAdapter = new EntryRecyclerViewAdapter(entries, this.getContext());
        mAdapter.setEntryClickListener(new EntryRecyclerViewAdapter.IEntryClickListener() {
            @Override
            public void onEntryClick(Entry entry) {
                mINavigation.navigateToEntryDetailPage(entry);
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mINavigation = (MainActivity) getActivity();
    }

    private ArrayList<Entry> getEntriesFromJSON() {
        String json = null;

        ArrayList<Entry> entries = new ArrayList<>();

        try {
            InputStream inputStream = this.getContext().getAssets().open("entries.json");

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);

            inputStream.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }


        try {
            JSONObject obj = new JSONObject(json);

            JSONArray jsonArray = obj.getJSONArray("entries");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject entryJson = (JSONObject) jsonArray.get(i);
                String date = entryJson.getString("date");
                double usage = entryJson.getDouble("usage");
                entries.add(new Entry(date, usage));
            }


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return entries;


    }

}
