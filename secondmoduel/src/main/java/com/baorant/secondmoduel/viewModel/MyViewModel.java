package com.baorant.secondmoduel.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private int number = 0;

    // Create a LiveData with a String
    private MutableLiveData<String> currentName;

    public MutableLiveData<String> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<>();
        }
        return currentName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    // 正数为加，负数为减
    public void doSet(int n){
        number += n;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // 当activity或者fragment销毁时会调用该方法
    }
}