package temperature.commons.windowing;


public class WindowImpl implements Window {

    private long c, o, t0;

    public WindowImpl(long tc0, long o, long c) {
        this.o = o;
        this.c = c;
        this.t0 = tc0;
    }

    public long getC() {
        return c;
    }

    public long getO() {
        return o;
    }

    @Override
    public boolean equals(Object o1) {
        if (this == o1) return true;
        if (o1 == null || getClass() != o1.getClass()) return false;

        WindowImpl window = (WindowImpl) o1;

        if (c != window.c) return false;
        return o == window.o;
    }

    @Override
    public String toString() {
        return "Window [" + o + "," + c + ") - [" + (o - t0) + "," + (c - t0) + ")";
    }

    @Override
    public int hashCode() {
        int result = (int) (c ^ (c >>> 32));
        result = 31 * result + (int) (o ^ (o >>> 32));
        return result;
    }
}
