package com.cotton.algorithmer.executor;

import com.cotton.algorithmer.algorithmhandler.callback.InputDataCallBack;

import java.io.InputStream;

public interface InputStreamExecutor {
    void execute(InputStream inputStream, InputDataCallBack inputDataCallBack);
}
