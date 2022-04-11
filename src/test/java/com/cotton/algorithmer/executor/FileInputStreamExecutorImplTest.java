package com.cotton.algorithmer.executor;

import com.cotton.algorithmer.algorithmhandler.callback.InputDataCallBack;
import com.cotton.algorithmer.executor.impl.LineByLineExecutor;
import com.cotton.algorithmer.executor.impl.FileInputStreamExecutorImpl;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FileInputStreamExecutorImplTest {

    private AutoCloseable closeable;

    private FileInputStreamExecutor fileInputStreamExecutor;

    @Mock
    private LineByLineExecutor lineByLineExecutor;

    @BeforeMethod
    public void beforeMethod() {
        closeable = MockitoAnnotations.openMocks(this);
        fileInputStreamExecutor = new FileInputStreamExecutorImpl(lineByLineExecutor);
    }

    @AfterMethod
    public void afterMethod() throws Exception {
        closeable.close();
    }

    @Test
    public void testExecute() {

        InputDataCallBack inputDataCallBack = mock(InputDataCallBack.class);
        fileInputStreamExecutor.execute("src/test/resources/input.txt", inputDataCallBack);
        verify(lineByLineExecutor, times(1))
                .execute(argThat(new InputStreamMatcher()), ArgumentMatchers.eq(inputDataCallBack));
    }

    private class InputStreamMatcher implements ArgumentMatcher<InputStream> {
        //to avoid issue that mockito invokes matcher 2 times
        private int counterOfInvocations = 0;

        @Override
        public boolean matches(InputStream inputStream) {
            if (counterOfInvocations > 0) return true;
            String actual = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            String expected = "SUM\n1 2 3";
            counterOfInvocations++;
            return expected.equals(actual);

        }
    }
}

