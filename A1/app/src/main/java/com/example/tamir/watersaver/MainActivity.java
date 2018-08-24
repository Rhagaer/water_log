package com.example.tamir.watersaver;


import android.arch.persistence.room.Room;
import android.nfc.Tag;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.tamir.watersaver.controllers.entry_list.EntryAddFragment;
import com.example.tamir.watersaver.controllers.entry_list.EntryDetailFragment;
import com.example.tamir.watersaver.controllers.entry_list.EntryListFragment;
import com.example.tamir.watersaver.controllers.entry_list.EntryRecyclerViewAdapter;
import com.example.tamir.watersaver.models.AppDatabase;
import com.example.tamir.watersaver.models.Entry;
import com.example.tamir.watersaver.utils.Intents;


public class MainActivity extends AppCompatActivity implements INavigation {

    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        navigateToEntryListPage();
    }


    @Override
    public void navigateToEntryDetailPage(Entry entry, boolean addToBackStack) {
        EntryDetailFragment entryDetailFragment = new EntryDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Intents.Entry.toString(), entry);
        entryDetailFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.main_container, entryDetailFragment, EntryDetailFragment.TAG);
        fragmentTransaction.addToBackStack(EntryDetailFragment.TAG);
        fragmentTransaction.commit();

    }

    @Override
    public void navigateToAddEntryPage() {
        EntryAddFragment entryAddFragment = new EntryAddFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, entryAddFragment, EntryAddFragment.TAG);
        fragmentTransaction.addToBackStack(EntryAddFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void navigateToEntryListPage() {
        EntryListFragment entryListFragment = new EntryListFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, entryListFragment, entryListFragment.TAG);
        fragmentTransaction.addToBackStack(entryListFragment.TAG);
        fragmentTransaction.commit();

    }


}
