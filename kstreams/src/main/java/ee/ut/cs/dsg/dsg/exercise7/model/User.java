package ee.ut.cs.dsg.dsg.exercise7.model;

public class User {
    private String userid;
    private String regionid;
    private String gender;
    private Long registertime;

    public User(String userid, String regionid, String gender, Long registertime) {
        this.userid = userid;
        this.regionid = regionid;
        this.gender = gender;
        this.registertime = registertime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRegionid() {
        return regionid;
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Long registertime) {
        this.registertime = registertime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", regionid='" + regionid + '\'' +
                ", gender='" + gender + '\'' +
                ", registertime=" + registertime +
                '}';
    }
}
