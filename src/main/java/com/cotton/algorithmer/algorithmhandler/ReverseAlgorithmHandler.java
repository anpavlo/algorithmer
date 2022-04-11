package com.cotton.algorithmer.algorithmhandler;

import com.cotton.algorithmer.algorithmhandler.exceptions.AlgorithmHandlerException;
import org.springframework.stereotype.Component;

@Component
public class ReverseAlgorithmHandler implements AlgorithmHandler {

    @Override
    public Object handle(String data) {
        try {
            StringBuilder result = new StringBuilder(data);
            return result.reverse().toString();
        } catch (Exception e) {
            throw new AlgorithmHandlerException("Unable to handle data \"" + data + "\" for algorithm " + this.getAlgorithm(), e);
        }
    }

    @Override
    public String getAlgorithm() {
        return "REVERSE";
    }
}
