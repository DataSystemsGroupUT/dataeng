package ee.ut.cs.dsg.dsg.exercise2.model;

public class Temperature {

    private int value;
    private long timestamp;

    public Temperature(int value, long timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    public Temperature() {
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
