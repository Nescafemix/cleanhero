package com.joanfuentes.cleanhero.internal.di;

import com.joanfuentes.cleanhero.threading.PostExecutionThread;
import com.joanfuentes.cleanhero.threading.ThreadExecutor;

public interface ApplicationComponent {
        ThreadExecutor getThreadExecutor();
        PostExecutionThread getPostExecutionThread();
}
