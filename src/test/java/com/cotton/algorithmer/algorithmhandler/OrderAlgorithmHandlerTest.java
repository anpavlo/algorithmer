package com.cotton.algorithmer.algorithmhandler;

import com.cotton.algorithmer.algorithmhandler.exceptions.AlgorithmHandlerException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OrderAlgorithmHandlerTest {

    private OrderAlgorithmHandler algorithmHandler;

    @BeforeMethod
    public void beforeMethod() {
        algorithmHandler = new OrderAlgorithmHandler();
    }

    @Test
    public void testHandle() {
        Object result = algorithmHandler.handle("bd   cb   ac");
        assertEquals(result, "ac bd cb");
    }

    @Test(expectedExceptions = AlgorithmHandlerException.class)
    public void testHandleExeption() {
        algorithmHandler.handle(null);
    }

    @Test
    public void testGetAlgorithm() {
        assertEquals(algorithmHandler.getAlgorithm(), "ORDER");
    }
}