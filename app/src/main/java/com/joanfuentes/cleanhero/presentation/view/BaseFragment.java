package com.joanfuentes.cleanhero.presentation.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    public BaseFragment() { }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(onRequestLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeInjection();
        initializeViewsInjection();
        onViewReady();
    }

    private void initializeInjection() {
        onInitializeInjection();
    }

    private void initializeViewsInjection() {
        ButterKnife.bind(this, getView());
    }

    abstract int onRequestLayout();

    abstract void onInitializeInjection();

    abstract void onViewReady();
}
