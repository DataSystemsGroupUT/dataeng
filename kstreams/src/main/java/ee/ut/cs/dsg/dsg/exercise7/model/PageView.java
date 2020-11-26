package ee.ut.cs.dsg.dsg.exercise7.model;

public class PageView {

    private long viewtime;
    private String userid;
    private String pageid;

    private String region;

    public PageView(long viewtime, String userid, String pageid) {
        this.viewtime = viewtime;
        this.userid = userid;
        this.pageid = pageid;
    }

    public long getViewtime() {
        return viewtime;
    }

    public void setViewtime(long viewtime) {
        this.viewtime = viewtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPageid() {
        return pageid;
    }

    public void setPageid(String pageid) {
        this.pageid = pageid;
    }


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    @Override
    public String toString() {
        return "PageView{" +
                "viewtime=" + viewtime +
                ", userid='" + userid + '\'' +
                ", pageid='" + pageid + '\'' +
                '}';
    }
}
