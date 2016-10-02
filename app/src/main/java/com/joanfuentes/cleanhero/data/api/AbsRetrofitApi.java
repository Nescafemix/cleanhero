package com.joanfuentes.cleanhero.data.api;

import android.support.annotation.NonNull;

abstract class AbsRetrofitApi {
    protected static final String PUBLIC_KEY = "6a7ed890b4b941a925202a5630d5b162";
    protected static final String PRIVATE_KEY = "0f1d0fdf46a0bf32f962b0b9997233c0395cdf8e";

    @NonNull
    protected String getCurrentTimestamp() {
        return String.valueOf(System.currentTimeMillis()/1000);
    }
}
