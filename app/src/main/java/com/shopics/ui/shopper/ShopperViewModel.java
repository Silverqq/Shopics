package com.shopics.ui.shopper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShopperViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ShopperViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Тут будет корзина товаров");
    }

    public LiveData<String> getText() {
        return mText;
    }
}