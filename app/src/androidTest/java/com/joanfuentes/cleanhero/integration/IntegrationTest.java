package com.joanfuentes.cleanhero.integration;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public abstract class IntegrationTest {
    @Before
    public final void setup() {
        onInitializeInjection();
    }

    protected abstract void onInitializeInjection();
}
