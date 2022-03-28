package com.nellpoi.task2_switch_interface.ui.page4;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Fragment_4_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Fragment_4_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("第四个 Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
