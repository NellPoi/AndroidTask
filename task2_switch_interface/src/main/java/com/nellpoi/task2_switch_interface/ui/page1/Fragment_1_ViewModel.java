package com.nellpoi.task2_switch_interface.ui.page1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Fragment_1_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Fragment_1_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("第一个 Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}