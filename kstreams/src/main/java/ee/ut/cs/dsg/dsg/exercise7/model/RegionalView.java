package ee.ut.cs.dsg.dsg.exercise7.model;

public class RegionalView {

    public String getUser_region() {
        return user_region;
    }

    public void setUser_region(String user_region) {
        this.user_region = user_region;
    }

    private String user_region;
    private PageView view;

    public RegionalView(String user_region, PageView view) {
        this.user_region = user_region;
        this.view = view;
    }


    public PageView getView() {
        return view;
    }

    public void setView(PageView view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return "ViewWithUser{" +
                "user_region=" + user_region +
                ", view=" + view +
                '}';
    }
}
