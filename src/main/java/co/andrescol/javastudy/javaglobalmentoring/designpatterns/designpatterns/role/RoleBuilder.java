package co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.role;

import java.util.List;

public class RoleBuilder {

    private Role accessLevel;

    public RoleBuilder() {
        clean();
    }
    public RoleBuilder clean() {
        accessLevel = new Role();
        return this;
    }
    public RoleBuilder withName(RoleName name) {
        accessLevel.setName(name);
        return this;
    }
    public RoleBuilder addScopes(String... scopes) {
        accessLevel.getScopes().addAll(List.of(scopes));
        return this;
    }

    public Role build() {
        Role result = accessLevel;
        clean();
        return result;
    }
}
