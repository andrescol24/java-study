package co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.server;

import co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.dto.AuthorizedUser;
import co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.dto.PathPermission;
import co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.dto.User;
import co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.role.RoleName;
import co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.role.Role;
import co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.role.RoleBuilder;

import java.util.*;

public class ServerConfig {

    private final List<PathPermission> pathsConfiguration;
    private final List<Role> roles;
    private final List<AuthorizedUser> users;

    private ServerConfig() {
        users = new LinkedList<>();
        pathsConfiguration = new LinkedList<>();
        roles = new LinkedList<>();
    }

    public void addUser(User user, RoleName roleName) {
        if(users.stream().noneMatch(u -> u.getName().equals(user.getName()))) {
            users.add(new AuthorizedUser(user, roleName));
        }
    }

    public void addPath(String path, String scope)  {
        PathPermission pathPermission = new PathPermission(path, scope);
        if (pathsConfiguration.stream().noneMatch(p -> p.path().equals(path))) {
            pathsConfiguration.add(pathPermission);
        }
    }

    public void addRole(RoleName roleName, String... scopes) {
        if(roles.stream().noneMatch(role -> role.getName().equals(roleName))) {
            RoleBuilder roleBuilder = new RoleBuilder();
            Role userRole = roleBuilder
                    .withName(roleName)
                    .addScopes(scopes)
                    .build();
            roles.add(userRole);
        }
    }

    public Optional<Role> getAccessLevel(User user) {
        Optional<AuthorizedUser> authorizedUser = users
           .stream()
           .filter(u -> u.getName().equals(user.getName()))
           .findFirst();

        if(authorizedUser.isPresent()) {
            return roles.stream()
                    .filter(r -> r.getName().equals(authorizedUser.get().getRoleName()))
                    .findAny();
        }
        return roles.stream()
                .filter(role -> role.getName().equals(RoleName.NOT_LOGGED_IN))
                .findAny();
    }

    public Optional<String> getPathPermission(String path) {
        Optional<PathPermission> permission = pathsConfiguration.stream()
                .filter(p -> p.path().equals(path))
                .findFirst();
        return permission.map(PathPermission::scope);
    }

    public void clearData() {
        this.users.clear();
        this.pathsConfiguration.clear();
        this.roles.clear();
    }

    //==================================== Singleton =============================================
    private static ServerConfig instance;

    public static ServerConfig getInstance() {
        if (instance == null) {
            synchronized (ServerConfig.class) {
                if (instance == null)
                    instance = new ServerConfig();
            }
        }
        return instance;
    }
}
