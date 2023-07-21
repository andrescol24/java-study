package co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.server;

import co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.dto.Session;
import co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.dto.User;
import co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.exception.InsufficientRightsException;
import co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.server.AccessChecker;

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
