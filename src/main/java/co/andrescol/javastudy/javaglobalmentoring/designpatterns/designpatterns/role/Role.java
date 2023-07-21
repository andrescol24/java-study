package co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.role;

import java.util.LinkedList;
import java.util.List;

public class Role {
    private RoleName name;
    private List<String> scopes;

    public Role() {
        scopes = new LinkedList<>();
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }
}
