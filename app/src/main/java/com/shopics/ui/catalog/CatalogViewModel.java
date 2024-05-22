package com.shopics.ui.catalog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CatalogViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CatalogViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Тут будет каталог");
    }

    public LiveData<String> getText() {
        return mText;
    }
}