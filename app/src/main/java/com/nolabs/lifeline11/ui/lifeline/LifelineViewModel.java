package com.nolabs.lifeline11.ui.lifeline;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LifelineViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LifelineViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}