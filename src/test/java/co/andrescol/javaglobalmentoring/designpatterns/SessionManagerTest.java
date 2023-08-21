package co.andrescol.javaglobalmentoring.designpatterns;

import co.andrescol.javaglobalmentoring.designpatterns.dto.User;
import co.andrescol.javaglobalmentoring.designpatterns.exception.InsufficientRightsException;
import co.andrescol.javaglobalmentoring.designpatterns.role.RoleName;
import co.andrescol.javaglobalmentoring.designpatterns.server.ServerConfig;
import co.andrescol.javaglobalmentoring.designpatterns.server.SessionManager;
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
