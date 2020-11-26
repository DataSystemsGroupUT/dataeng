package ee.ut.cs.dsg.dsg.exercise5.model;

public class Configuration {

    private int prefVal;
    private long timestamp;
    private String author;


    public Configuration() {
    }

    public Configuration(int prefVal, long timestamp, String author) {
        this.prefVal = prefVal;
        this.timestamp = timestamp;
        this.author = author;
    }

    @Override
    public String toString() {
        return "(" + timestamp + "," + prefVal + "," + author + ")";
    }

    public int getPrefVal() {
        return prefVal;
    }

    public void setPrefVal(int prefVal) {
        this.prefVal = prefVal;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
