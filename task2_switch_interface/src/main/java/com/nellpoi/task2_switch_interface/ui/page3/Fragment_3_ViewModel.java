package com.nellpoi.task2_switch_interface.ui.page3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Fragment_3_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Fragment_3_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("第三个 Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}