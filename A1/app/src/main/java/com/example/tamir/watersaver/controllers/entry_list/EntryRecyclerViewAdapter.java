package com.example.tamir.watersaver.controllers.entry_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tamir.watersaver.R;
import com.example.tamir.watersaver.models.Entry;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class EntryRecyclerViewAdapter extends RecyclerView.Adapter<EntryRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Entry> mEntries;
    private Context mContext;
    private IEntryClickListener mEntryClickListener;

    public EntryRecyclerViewAdapter(ArrayList<Entry> entries, Context context) {
        mEntries = entries;
        mContext = context;
    }

    interface IEntryClickListener {
        void onEntryClick(Entry entry);
    }

    public void setEntryClickListener(IEntryClickListener mEntryClickListener) {
        this.mEntryClickListener = mEntryClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.dateTextView.setText(mEntries.get(position).getDate());
        holder.usageTextView.setText(mEntries.get(position).getTotalVol() + "L");

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick");
                mEntryClickListener.onEntryClick(mEntries.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return mEntries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView dateTextView;
        public TextView usageTextView;
        ConstraintLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.date_tv);
            usageTextView = itemView.findViewById(R.id.vol_tv);
            parentLayout = itemView.findViewById(R.id.layout);
        }


    }
}
