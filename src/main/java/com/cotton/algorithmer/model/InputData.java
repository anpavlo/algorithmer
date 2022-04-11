package com.cotton.algorithmer.model;

public class InputData {

    private String algorithm;
    private String data;

    public InputData(String algorithm, String data) {
        this.algorithm = algorithm;
        this.data = data;
    }

    public InputData() {
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "algorithm='" + algorithm + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
