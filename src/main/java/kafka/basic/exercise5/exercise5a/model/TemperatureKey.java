package kafka.basic.exercise5.exercise5a.model;

public class TemperatureKey {
    private String location;

    public TemperatureKey(String location) {
        this.location = location;
    }

    public TemperatureKey() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "TemperatureKey " +
                "'location '" + location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TemperatureKey that = (TemperatureKey) o;

        return location.equals(that.getLocation());
    }

    @Override
    public int hashCode() {
        return location.hashCode();
    }
}
