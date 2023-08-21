package co.andrescol.javaglobalmentoring.designpatterns.server;

import co.andrescol.javaglobalmentoring.designpatterns.dto.Session;
import co.andrescol.javaglobalmentoring.designpatterns.dto.User;
import co.andrescol.javaglobalmentoring.designpatterns.exception.InsufficientRightsException;

public class SessionManager {
    public Session createSession(User user, String accessedPath) throws InsufficientRightsException {
        AccessChecker access = AccessChecker.getInstance();
        if (access.mayAccess(user, accessedPath)) {
            return new Session(user);
        } else {
            throw new InsufficientRightsException(user, accessedPath);
        }
    }
}
