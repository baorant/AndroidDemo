package com.baorant.secondmoduel.viewModel;

import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private int number = 0;

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