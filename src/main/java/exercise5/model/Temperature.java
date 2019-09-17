package exercise5.model;

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
        return "(" + timestamp + "," + value + ")";
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
