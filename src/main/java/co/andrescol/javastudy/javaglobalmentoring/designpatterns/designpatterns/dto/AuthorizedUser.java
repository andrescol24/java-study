package co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.dto;

import co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.role.RoleName;

public class AuthorizedUser extends User {
    private final RoleName roleName;
    public AuthorizedUser(User user, RoleName roleName) {
        super(user.getName());
        this.roleName = roleName;
    }
    public RoleName getRoleName() {
        return roleName;
    }

    @Override
    public boolean equals(Object otherUser) {
        if(otherUser == null) return false;

        if (otherUser instanceof User user) {
            return this.getName().equals(user.getName());
        }
        return false;
    }
}
