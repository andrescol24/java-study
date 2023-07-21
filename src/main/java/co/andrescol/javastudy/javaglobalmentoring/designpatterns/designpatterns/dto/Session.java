package co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.dto;

public class Session {
    private User user;
    public Session(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
