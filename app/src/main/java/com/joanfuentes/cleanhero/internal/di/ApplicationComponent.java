package com.joanfuentes.cleanhero.internal.di;

import com.joanfuentes.cleanhero.Application;
import com.joanfuentes.cleanhero.threading.PostExecutionThread;
import com.joanfuentes.cleanhero.threading.ThreadExecutor;

public interface ApplicationComponent {
        Application getApplication();
        ThreadExecutor getThreadExecutor();
        PostExecutionThread getPostExecutionThread();
}
