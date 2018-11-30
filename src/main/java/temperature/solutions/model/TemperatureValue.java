package temperature.solutions.model;

public class TemperatureValue {

    private int value;
    private long timestamp;

    public TemperatureValue(int value, long timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    public TemperatureValue() {
    }

    @Override
    public String toString() {
        return "TemperatureValue " +
                ", value: " + value +
                ", timestamp: " + timestamp;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
