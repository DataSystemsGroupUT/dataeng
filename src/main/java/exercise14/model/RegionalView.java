package exercise14.model;

public class ViewWithUser {

    private User user;
    private PageView view;

    public ViewWithUser(User user, PageView view) {
        this.user = user;
        this.view = view;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                "user=" + user +
                ", view=" + view +
                '}';
    }
}
