package com.joanfuentes.cleanhero.presentation.view;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            avoidBlinkingBetweenAnimationsWithAnimationTransitions();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void avoidBlinkingBetweenAnimationsWithAnimationTransitions() {
        getWindow().setExitTransition(null);
        getWindow().setEnterTransition(null);
    }
}
