package com.example.tamir.watersaver;

import com.example.tamir.watersaver.models.Entry;

public interface INavigation {

    void navigateToEntryDetailPage(Entry entry, boolean addToBackStack);
    void navigateToAddEntryPage();
    void navigateToEntryListPage();

}
