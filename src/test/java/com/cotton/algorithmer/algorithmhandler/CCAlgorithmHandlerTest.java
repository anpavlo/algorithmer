package com.cotton.algorithmer.algorithmhandler;

import com.cotton.algorithmer.algorithmhandler.exceptions.AlgorithmHandlerException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CCAlgorithmHandlerTest {

    private CCAlgorithmHandler algorithmHandler;

    @BeforeMethod
    public void beforeMethod() {
        algorithmHandler = new CCAlgorithmHandler();
    }

    @Test
    public void testHandle() {
        Object result = algorithmHandler.handle("abc   def   ghi");
        assertEquals(result, "abcDefGhi");
    }

    @Test(expectedExceptions = AlgorithmHandlerException.class)
    public void testHandleExeption() {
        algorithmHandler.handle(null);
    }

    @Test
    public void testGetAlgorithm() {
        assertEquals(algorithmHandler.getAlgorithm(), "CC");
    }
}