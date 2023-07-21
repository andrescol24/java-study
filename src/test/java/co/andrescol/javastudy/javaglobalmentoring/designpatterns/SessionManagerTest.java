package co.andrescol.javastudy.javaglobalmentoring.designpatterns;

import co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.Constants;
import co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.dto.User;
import co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.exception.InsufficientRightsException;
import co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.role.RoleName;
import co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.server.ServerConfig;
import co.andrescol.javastudy.javaglobalmentoring.designpatterns.designpatterns.server.SessionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SessionManagerTest {

    @Test
    public void createSessionTest() {
        // Arrange
        User andres = new User("andres");
        String path = "POST /api/books";
        ServerConfig serverConfig = ServerConfig.getInstance();
        serverConfig.addPath(path, Constants.CREATE_BOOK);
        serverConfig.addRole(RoleName.ADMIN, Constants.CREATE_BOOK);
        serverConfig.addUser(andres, RoleName.ADMIN);

        // Act
        SessionManager sessionManager = new SessionManager();

        // Assert
        assertDoesNotThrow(() -> sessionManager.createSession(andres, path));
    }

    @Test
    public void createSessionUserNoPrivilegesTest() {
        // Arrange
        User andres = new User("andres");
        String path = "POST /api/books";
        ServerConfig serverConfig = ServerConfig.getInstance();
        serverConfig.addPath(path, Constants.CREATE_BOOK);
        serverConfig.addRole(RoleName.ADMIN, Constants.CREATE_BOOK);
        serverConfig.addUser(andres, RoleName.USER);

        // Act
        SessionManager sessionManager = new SessionManager();

        // Assert
        assertThrows(InsufficientRightsException.class, () -> sessionManager.createSession(andres, path));
    }

    @BeforeEach
    public void clearData() {
        ServerConfig.getInstance().clearData();
    }
}
