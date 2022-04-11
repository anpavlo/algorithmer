package com.cotton.algorithmer.executor.impl;

import com.cotton.algorithmer.algorithmhandler.callback.InputDataCallBack;
import com.cotton.algorithmer.executor.InputStreamExecutor;
import com.cotton.algorithmer.executor.FileInputStreamExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Component
public class FileInputStreamExecutorImpl implements FileInputStreamExecutor {

    private final InputStreamExecutor inputStreamExecutor;

    @Autowired
    public FileInputStreamExecutorImpl(InputStreamExecutor inputStreamExecutor) {
        this.inputStreamExecutor = inputStreamExecutor;
    }

    public void execute(String fileName, InputDataCallBack inputDataCallBack) {
        try {
            File file = new File(fileName);
            inputStreamExecutor.execute(new FileInputStream(file), inputDataCallBack);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
