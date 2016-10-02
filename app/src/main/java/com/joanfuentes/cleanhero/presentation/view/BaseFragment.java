package com.joanfuentes.cleanhero.presentation.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {

    public BaseFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjection();
    }

    private void initializeInjection() {
        onInitializeInjection();
    }

    abstract void onInitializeInjection();
}
