package ee.ut.cs.dsg.dsg.exercise3.model;

public class Tuple {
    public Long t1;
    public Long t2;

    public Tuple(Long t1, Long t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public Tuple() {
    }

    public void setT1(Long t1) {
        this.t1 = t1;
    }

    public void setT2(Long t2) {
        this.t2 = t2;
    }

    @Override
    public String toString() {
        return t1 + "-" + t2;
    }
}