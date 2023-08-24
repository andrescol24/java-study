package co.andrescol.javaglobalmentoring.testing.isbntool;

import co.andrescol.javaglobalmentoring.testing.isbntool.dto.Book;
import co.andrescol.javaglobalmentoring.testing.isbntool.service.ExternalISBNDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StockManagementTests {

    private ExternalISBNDataService webService;
    private ExternalISBNDataService databaseService;
    private StockManager stockManager;

    /**
     * Setup mock. remember you can also implement mock by interface implementations like this:
     * <code>ExternalISBNDataService service = (code) -> new Book(code, "Harry Potter", "J. K Rowling");</code>
     */
    @BeforeEach
    public void setup() {
        webService = mock(ExternalISBNDataService.class);
        databaseService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager(webService, databaseService);
    }
    @Test
    public void getLocatorCodeTest() {
        String isbn = "0140177396";
        when(databaseService.lookup(any())).thenReturn(new Book(isbn, "Harry Potter", "J. K Rowling"));

        String locatorCode = stockManager.getLocatorCode(isbn);

        assertEquals("7396J2", locatorCode);
    }
    @Test
    public void databaseIsUsedIfDataIsPresent() {
        String isbn = "0140177396";
        when(databaseService.lookup(any())).thenReturn(new Book(isbn, "Harry Potter", "J K Rowling"));

        stockManager.getLocatorCode(isbn);

        verify(databaseService, times(1)).lookup(any());
        verify(webService, never()).lookup(any());
    }
    @Test
    public void webServiceIsUsedIfDataIsNotPresent() {
        String isbn = "0140177396";
        when(databaseService.lookup(any())).thenReturn(null);
        when(webService.lookup(any())).thenReturn(new Book(isbn, "Harry Potter", "J K Rowling"));

        stockManager.getLocatorCode(isbn);

        verify(databaseService, times(1)).lookup(any());
        verify(webService, times(1)).lookup(any());
    }
}
