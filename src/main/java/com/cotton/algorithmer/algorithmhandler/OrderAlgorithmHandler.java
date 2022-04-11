package com.cotton.algorithmer.algorithmhandler;

import com.cotton.algorithmer.algorithmhandler.exceptions.AlgorithmHandlerException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class OrderAlgorithmHandler implements AlgorithmHandler {

    @Override
    public Object handle(String data) {
        try {
            String[] strings = data.split("\\s+");
            Arrays.sort(strings);
            return String.join(" ", strings);
        } catch (Exception e) {
            throw new AlgorithmHandlerException("Unable to handle data \"" + data + "\" for algorithm " + this.getAlgorithm(), e);
        }
    }

    @Override
    public String getAlgorithm() {
        return "ORDER";
    }
}
