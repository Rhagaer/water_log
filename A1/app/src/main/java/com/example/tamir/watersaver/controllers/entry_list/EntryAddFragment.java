package com.example.tamir.watersaver.controllers.entry_list;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tamir.watersaver.INavigation;
import com.example.tamir.watersaver.MainActivity;
import com.example.tamir.watersaver.R;
import com.example.tamir.watersaver.models.AppDatabase;
import com.example.tamir.watersaver.models.Entry;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EntryAddFragment extends Fragment {

    public static final String TAG = "EntryAddFragment";


    private TextView mDateTV;
    private TextView mTotalVolTV;
    private EditText mShowerET;
    private EditText mToiletET;
    private EditText mHygineET;
    private EditText mLaundryET;
    private EditText mDishesET;
    private EditText mCookingET;
    private EditText mCleaningET;
    private Button mSaveBtn;

    private Entry mEntry;

    private INavigation mINavigation;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mINavigation = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.entry_add, container, false);

        mDateTV = view.findViewById(R.id.date_tv);
        mTotalVolTV = view.findViewById(R.id.total_volume_tv);

        mShowerET = view.findViewById(R.id.shower_et);
        mShowerET.addTextChangedListener(new GenericTextWatcher(mShowerET));

        mToiletET = view.findViewById(R.id.toilet_et);
        mToiletET.addTextChangedListener(new GenericTextWatcher(mToiletET));

        mHygineET = view.findViewById(R.id.hygine_et);
        mHygineET.addTextChangedListener(new GenericTextWatcher(mHygineET));

        mLaundryET = view.findViewById(R.id.laundry_et);
        mLaundryET.addTextChangedListener(new GenericTextWatcher(mLaundryET));


        mDishesET = view.findViewById(R.id.dishes_et);
        mDishesET.addTextChangedListener(new GenericTextWatcher(mDishesET));

        mCookingET = view.findViewById(R.id.cooking_et);
        mCookingET.addTextChangedListener(new GenericTextWatcher(mCookingET));


        mCleaningET = view.findViewById(R.id.cleaning_et);
        mCleaningET.addTextChangedListener(new GenericTextWatcher(mCleaningET));


        mSaveBtn = view.findViewById(R.id.save_btn);
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertTask insertTask = new InsertTask(mEntry);
                insertTask.execute();
            }
        });


        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(today);
        mEntry = new Entry(date);
        mDateTV.setText(mEntry.getDate());

        return view;
    }

    private class InsertTask extends AsyncTask<Void, Void, Boolean> {

        private Entry entry;

        InsertTask(Entry entry) {
            this.entry = entry;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            AppDatabase.getInstance(getContext()).entryDao().insertEntry(entry);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                mINavigation.navigateToEntryListPage();
            }
        }
    }

    private class GenericTextWatcher implements TextWatcher {

        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            String text = editable.toString();
            if (text.equals("")) {
                return;
            }
            switch (view.getId()) {
                case R.id.shower_et:
                    mEntry.setShowerVol(Double.parseDouble(text));
                    updateTotalVolume();

                    break;
                case R.id.toilet_et:
                    mEntry.setToiletVol(Double.parseDouble(text));
                    updateTotalVolume();

                    break;
                case R.id.hygine_et:
                    mEntry.setHygineVol(Double.parseDouble(text));
                    updateTotalVolume();

                    break;

                case R.id.laundry_et:
                    mEntry.setLaundryVol(Double.parseDouble(text));
                    updateTotalVolume();

                    break;

                case R.id.dishes_et:
                    mEntry.setDishesVol(Double.parseDouble(text));
                    updateTotalVolume();

                    break;

                case R.id.cooking_et:
                    mEntry.setCookingVol(Double.parseDouble(text));
                    updateTotalVolume();

                    break;

                case R.id.cleaning_et:
                    mEntry.setCleaningVol(Double.parseDouble(text));
                    updateTotalVolume();
                    break;


            }
        }
    }

    private void updateTotalVolume() {
        Double total = mEntry.getTotalVol();
        mTotalVolTV.setText(getString(R.string.total) + ": " + total + getString(R.string.l_unit));
    }


}
