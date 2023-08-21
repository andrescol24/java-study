package co.andrescol.javaglobalmentoring.designpatterns;

import co.andrescol.javaglobalmentoring.designpatterns.dto.Session;
import co.andrescol.javaglobalmentoring.designpatterns.dto.User;
import co.andrescol.javaglobalmentoring.designpatterns.exception.InsufficientRightsException;
import co.andrescol.javaglobalmentoring.designpatterns.role.RoleName;
import co.andrescol.javaglobalmentoring.designpatterns.server.ServerConfig;
import co.andrescol.javaglobalmentoring.designpatterns.server.SessionManager;

public class Main {
    public static void main(String[] args) {
        addServerData();
        SessionManager sessionManager = new SessionManager();
        try {
            Session carlSession = sessionManager.createSession(new User("carl"),"GET /lapi/books/{bookId}");
            System.out.println("Session created!");
        } catch (InsufficientRightsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addServerData() {
        addPaths();
        addRoles();
        addUsers();
    }

    private static void addPaths() {
        ServerConfig serverConfig = ServerConfig.getInstance();
        serverConfig.addPath("GET /api/users/{userId}", Constants.GET_USER);
        serverConfig.addPath("POST /api/users", Constants.CREATE_USER);
        serverConfig.addPath("GET /api/books/{bookId}", Constants.GET_BOOK);
        serverConfig.addPath("POST /api/books", Constants.CREATE_BOOK);
    }

    private static void addRoles() {
        ServerConfig serverConfig = ServerConfig.getInstance();
        serverConfig.addRole(RoleName.ADMIN, Constants.GET_USER, Constants.CREATE_USER, Constants.GET_BOOK, Constants.CREATE_BOOK);
        serverConfig.addRole(RoleName.USER, Constants.GET_USER, Constants.GET_BOOK);
        serverConfig.addRole(RoleName.NOT_LOGGED_IN, Constants.GET_BOOK);
    }

    private static void addUsers() {
        ServerConfig serverConfig = ServerConfig.getInstance();
        serverConfig.addUser(new User("andres"), RoleName.ADMIN);
        serverConfig.addUser(new User("maksim"), RoleName.ADMIN);
        serverConfig.addUser(new User("daniela"), RoleName.USER);
        serverConfig.addUser(new User("unknown"), RoleName.NOT_LOGGED_IN);
    }
}
