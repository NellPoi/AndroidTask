package com.nellpoi.task2_switch_interface.ui.page2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Fragment_2_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Fragment_2_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("第二个 Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}