package com.cotton.algorithmer.service;

import com.cotton.algorithmer.algorithmhandler.AlgorithmHandler;
import com.cotton.algorithmer.algorithmhandler.callback.InputDataCallBack;
import com.cotton.algorithmer.algorithmhandler.callback.ResultHandler;
import com.cotton.algorithmer.algorithmhandler.exceptions.AlgorithmHandlerException;
import com.cotton.algorithmer.executor.FileInputStreamExecutor;
import com.cotton.algorithmer.model.InputData;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import static org.testng.Assert.*;

public class AlgorithmServiceTest {

    private AutoCloseable closeable;

    @Mock
    private AlgorithmHandler sumAlgorithmHandler;
    @Mock
    private AlgorithmHandler ccAlgorithmHandler;

    @Mock
    private FileInputStreamExecutor fileInputStreamExecutor;

    @BeforeMethod
    public void beforeMethod() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterMethod
    public void afterMethod() throws Exception {
        closeable.close();
    }

    @Test
    public void testExecute() {
        when(sumAlgorithmHandler.getAlgorithm()).thenReturn("SUM");
        when(sumAlgorithmHandler.handle("0 0 0")).thenReturn(0);

        when(ccAlgorithmHandler.getAlgorithm()).thenReturn("CC");
        when(ccAlgorithmHandler.handle("1 1 1")).thenReturn("1");

        List<AlgorithmHandler> algorithmHandlerList = new ArrayList<>();
        algorithmHandlerList.add(sumAlgorithmHandler);
        algorithmHandlerList.add(ccAlgorithmHandler);
        AlgorithmService algorithmService = new AlgorithmService(algorithmHandlerList, fileInputStreamExecutor);

        String fileName = "dummy";
        List<Object> resultList = new ArrayList<>();
        ResultHandler resultHandler = (result, inputData) -> {
            resultList.add(result);
        };

        doAnswer((Answer<Void>) invocationOnMock -> {
            InputDataCallBack inputDataCallBack = invocationOnMock.getArgument(1);

            inputDataCallBack.callForData(new InputData("SUM", "0 0 0"));
            inputDataCallBack.callForData(new InputData("CC", "1 1 1"));

            return null;
        }).when(fileInputStreamExecutor).execute(anyString(), any(InputDataCallBack.class));

        algorithmService.execute(resultHandler, fileName);

        assertEquals(resultList.get(0), 0);
        assertEquals(resultList.get(1), "1");
    }

    @Test
    public void testExecuteException() {
        when(sumAlgorithmHandler.getAlgorithm()).thenReturn("SUM");
        when(sumAlgorithmHandler.handle("0 0 0")).thenThrow(new AlgorithmHandlerException("dummy message"));

        List<AlgorithmHandler> algorithmHandlerList = new ArrayList<>();
        algorithmHandlerList.add(sumAlgorithmHandler);
        AlgorithmService algorithmService = new AlgorithmService(algorithmHandlerList, fileInputStreamExecutor);

        String fileName = "dummy";
        List<Object> resultList = new ArrayList<>();
        ResultHandler resultHandler = (result, inputData) -> {
            resultList.add(result);
        };

        doAnswer((Answer<Void>) invocationOnMock -> {
            InputDataCallBack inputDataCallBack = invocationOnMock.getArgument(1);

            inputDataCallBack.callForData(new InputData("SUM", "0 0 0"));

            return null;
        }).when(fileInputStreamExecutor).execute(anyString(), any(InputDataCallBack.class));

        algorithmService.execute(resultHandler, fileName);

        assertEquals(resultList.get(0), "dummy message");
    }

    @Test
    public void testExecuteNoHandler() {

        List<AlgorithmHandler> algorithmHandlerList = new ArrayList<>();
        AlgorithmService algorithmService = new AlgorithmService(algorithmHandlerList, fileInputStreamExecutor);

        String fileName = "dummy";
        List<Object> resultList = new ArrayList<>();
        ResultHandler resultHandler = (result, inputData) -> {
            resultList.add(result);
        };

        doAnswer((Answer<Void>) invocationOnMock -> {
            InputDataCallBack inputDataCallBack = invocationOnMock.getArgument(1);

            inputDataCallBack.callForData(new InputData("SUM", "0 0 0"));

            return null;
        }).when(fileInputStreamExecutor).execute(anyString(), any(InputDataCallBack.class));

        algorithmService.execute(resultHandler, fileName);

        assertEquals(resultList.get(0), "The algorithm \"SUM\" is not supported yet.");

    }
}