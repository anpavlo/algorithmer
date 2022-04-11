package com.cotton.algorithmer.executor;

import com.cotton.algorithmer.executor.impl.LineByLineExecutor;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class LineByLineExecutorTest {

    private AutoCloseable closeable;

    private LineByLineExecutor lineByLineExecutor;

    @BeforeMethod
    public void beforeMethod() {
        closeable = MockitoAnnotations.openMocks(this);
        lineByLineExecutor = new LineByLineExecutor();
    }

    @AfterMethod
    public void afterMethod() throws Exception {
        closeable.close();
    }

    @Test
    public void testExecute() {
        String initialString = "line_0\nline_1\nline_2\nline_3";
        InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
        final List<String> stringList = new ArrayList<>();
        lineByLineExecutor.execute(inputStream, inputData -> {
            stringList.add(inputData.getAlgorithm());
            stringList.add(inputData.getData());
        });
        assertEquals(stringList.get(0), "line_0");
        assertEquals(stringList.get(1), "line_1");
        assertEquals(stringList.get(2), "line_2");
        assertEquals(stringList.get(3), "line_3");
    }
}