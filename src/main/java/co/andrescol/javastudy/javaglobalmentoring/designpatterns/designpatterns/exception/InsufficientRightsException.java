package co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.exception;

import co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.dto.User;

public class InsufficientRightsException extends Exception {
    public InsufficientRightsException(User user, String accessedPath) {
        super(String.format("%s does not have access to %s", user.getName(), accessedPath));
    }
}
