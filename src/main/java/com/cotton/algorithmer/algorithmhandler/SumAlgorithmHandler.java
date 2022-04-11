package com.cotton.algorithmer.algorithmhandler;

import com.cotton.algorithmer.algorithmhandler.exceptions.AlgorithmHandlerException;
import org.springframework.stereotype.Component;

@Component
public class SumAlgorithmHandler implements AlgorithmHandler {

    @Override
    public Object handle(String data) {
        int result = 0;
        try {
            String[] numbers = data.split("\\s+");
            for (String number : numbers) {
                result += Integer.parseInt(number);
            }
        } catch (Exception e) {
            throw new AlgorithmHandlerException("Unable to handle data \"" + data + "\" for algorithm " + this.getAlgorithm(), e);
        }

        return result;
    }

    @Override
    public String getAlgorithm() {
        return "SUM";
    }
}
