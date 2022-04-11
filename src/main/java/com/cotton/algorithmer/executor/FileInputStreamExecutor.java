package com.cotton.algorithmer.executor;

import com.cotton.algorithmer.algorithmhandler.callback.InputDataCallBack;

public interface FileInputStreamExecutor {

    void execute(String fileName, InputDataCallBack inputDataCallBack);
}
