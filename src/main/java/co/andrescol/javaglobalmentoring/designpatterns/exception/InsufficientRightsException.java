package co.andrescol.javaglobalmentoring.designpatterns.exception;

import co.andrescol.javaglobalmentoring.designpatterns.dto.User;

public class InsufficientRightsException extends Exception {
    public InsufficientRightsException(User user, String accessedPath) {
        super(String.format("%s does not have access to %s", user.getName(), accessedPath));
    }
}
