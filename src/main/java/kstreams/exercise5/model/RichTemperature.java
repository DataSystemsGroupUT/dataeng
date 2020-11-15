package kstreams.exercise5.model;

import kstreams.exercise2.model.Temperature;

public class RichTemperature {

    private Temperature value;
    private Configuration configuration;

    public RichTemperature() {
    }

    public RichTemperature(Temperature value, Configuration configuration) {
        this.value = value;
        this.configuration = configuration;
    }

    public Temperature getValue() {
        return value;
    }

    public void setValue(Temperature value) {
        this.value = value;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String toString() {
        return "RichTemperature{" +
                "value=" + value +
                ", configuration=" + configuration +
                '}';
    }
}