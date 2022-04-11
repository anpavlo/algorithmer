package com.cotton.algorithmer.service;

import com.cotton.algorithmer.algorithmhandler.AlgorithmHandler;
import com.cotton.algorithmer.algorithmhandler.callback.ResultHandler;
import com.cotton.algorithmer.algorithmhandler.exceptions.AlgorithmHandlerException;
import com.cotton.algorithmer.executor.FileInputStreamExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AlgorithmService {

    private final Map<String, AlgorithmHandler> algorithmHandlerMap;
    private final FileInputStreamExecutor fileInputStreamExecutor;

    public AlgorithmService(
            List<AlgorithmHandler> algorithmHandlerList,
            FileInputStreamExecutor fileInputStreamExecutor) {

        this.algorithmHandlerMap = algorithmHandlerList.stream()
                .collect(Collectors.toMap(AlgorithmHandler::getAlgorithm, Function.identity()));
        this.fileInputStreamExecutor = fileInputStreamExecutor;
    }

    public void execute(ResultHandler resultHandler, String fileName) {
        fileInputStreamExecutor.execute(fileName, inputData -> {
            AlgorithmHandler algorithmHandler = algorithmHandlerMap.get(inputData.getAlgorithm());
            if (algorithmHandler == null) {
                resultHandler.handleResult("The algorithm \""+ inputData.getAlgorithm() +"\" is not supported yet.", inputData);
            } else {
                try {
                    Object result = algorithmHandler.handle(inputData.getData());
                    resultHandler.handleResult(result, inputData);
                } catch (AlgorithmHandlerException e) {
                    resultHandler.handleResult(e.getMessage(), inputData);
                }
            }
        });
    }

}
