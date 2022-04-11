package com.cotton.algorithmer.algorithmhandler;

import com.cotton.algorithmer.algorithmhandler.exceptions.AlgorithmHandlerException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SumAlgorithmHandlerTest {

    private SumAlgorithmHandler algorithmHandler;

    @BeforeMethod
    public void beforeMethod() {
        algorithmHandler = new SumAlgorithmHandler();
    }

    @Test
    public void testHandle() {
        Object result = algorithmHandler.handle("106   204   305");
        assertEquals(result, 615);
    }

    @Test(expectedExceptions = AlgorithmHandlerException.class)
    public void testHandleExeptionNotInt() {
        algorithmHandler.handle("303 567 gg");
    }

    @Test(expectedExceptions = AlgorithmHandlerException.class)
    public void testHandleExeption() {
        algorithmHandler.handle(null);
    }

    @Test
    public void testGetAlgorithm() {
        assertEquals(algorithmHandler.getAlgorithm(), "SUM");
    }
}