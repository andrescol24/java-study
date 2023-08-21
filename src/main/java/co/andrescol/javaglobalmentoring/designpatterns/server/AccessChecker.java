package co.andrescol.javaglobalmentoring.designpatterns.server;

import co.andrescol.javaglobalmentoring.designpatterns.dto.User;
import co.andrescol.javaglobalmentoring.designpatterns.role.Role;

import java.util.Optional;

public class AccessChecker {
    private AccessChecker() {
    }

    public boolean mayAccess(User user, String path) {
        ServerConfig config = ServerConfig.getInstance();
        Optional<Role> userRole = config.getAccessLevel(user);
        if (userRole.isPresent()) {
            Optional<String> pathScope = config.getPathPermission(path);
            if (pathScope.isPresent()) {
               return userRole.get().getScopes().contains(pathScope.get());
            }
        }
        return false;
    }

    //==================================== Singleton =============================================
    private static AccessChecker instance;

    public static AccessChecker getInstance() {
        if (instance == null) {
            synchronized (AccessChecker.class) {
                if(instance == null)
                    instance = new AccessChecker();
            }
        }
        return instance;
    }
}