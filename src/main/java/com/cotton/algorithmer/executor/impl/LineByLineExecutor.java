package com.cotton.algorithmer.executor.impl;

import com.cotton.algorithmer.algorithmhandler.callback.InputDataCallBack;
import com.cotton.algorithmer.executor.InputStreamExecutor;
import com.cotton.algorithmer.model.InputData;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class LineByLineExecutor implements InputStreamExecutor {

    @Override
    public void execute(InputStream inputStream, InputDataCallBack inputDataCallBack) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int i = 0;
            InputData inputData = new InputData();
            while ((line = br.readLine()) != null) {
                if (i==0) inputData.setAlgorithm(line.trim());
                if (i == 1) {
                    inputData.setData(line.trim());
                    inputDataCallBack.callForData(inputData);
                    i=0;
                    inputData = new InputData();
                    continue;
                }
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
