package com.cotton.algorithmer.algorithmhandler;

import com.cotton.algorithmer.algorithmhandler.exceptions.AlgorithmHandlerException;

public interface AlgorithmHandler {

    Object handle(String data) throws AlgorithmHandlerException;

    String getAlgorithm();
}
