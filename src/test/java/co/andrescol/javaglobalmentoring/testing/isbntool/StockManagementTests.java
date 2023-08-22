package co.andrescol.javaglobalmentoring.testing.isbntool;

import co.andrescol.javaglobalmentoring.testing.isbntool.dto.Book;
import co.andrescol.javaglobalmentoring.testing.isbntool.service.ExternalISBNDataService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StockManagementTests {

    @Test
    public void getLocatorCodeTest() {
        String isbn = "0140177396";
        ExternalISBNDataService service = (code) -> new Book(code, "Harry Potter", "J. K Rowling");
        StockManager stockManager = new StockManager(service, service);
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J2", locatorCode);
    }
}
