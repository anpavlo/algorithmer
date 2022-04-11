package com.cotton.algorithmer.algorithmhandler;

import com.cotton.algorithmer.algorithmhandler.exceptions.AlgorithmHandlerException;
import org.springframework.stereotype.Component;

@Component
public class CCAlgorithmHandler implements AlgorithmHandler {

    @Override
    public Object handle(String data) {
        StringBuilder result = new StringBuilder();
        try {
            String[] strings = data.split("\\s+");
            int i = 0;
            for (String string : strings) {
                if (i != 0) {
                    result.append(string.substring(0, 1).toUpperCase()).append(string.substring(1));
                } else {
                    result.append(string);
                }
                i++;
            }
        } catch (Exception e) {
            throw new AlgorithmHandlerException("Unable to handle data \"" + data + "\" for algorithm " + this.getAlgorithm(), e);
        }
        return result.toString();
    }

    @Override
    public String getAlgorithm() {
        return "CC";
    }
}
