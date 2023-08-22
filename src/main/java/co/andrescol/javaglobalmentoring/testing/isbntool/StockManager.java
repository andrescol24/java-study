package co.andrescol.javaglobalmentoring.testing.isbntool;

import co.andrescol.javaglobalmentoring.testing.isbntool.dto.Book;
import co.andrescol.javaglobalmentoring.testing.isbntool.service.ExternalISBNDataService;

public class StockManager {
    private ExternalISBNDataService webService;
    private ExternalISBNDataService databaseService;

    public StockManager(ExternalISBNDataService webService, ExternalISBNDataService databaseService) {
        this.webService = webService;
        this.databaseService = databaseService;
    }
    public String getLocatorCode(String isbn) {
        Book book = databaseService.lookup(isbn);
        if(book == null) book = webService.lookup(isbn);

        String last4Digits = book.isbn().substring(isbn.length()-4);
        char authorLetter = Character.toUpperCase(book.author().charAt(0));
        int wordsInTitle = book.title().split(" ").length;
        return last4Digits + authorLetter + wordsInTitle;
    }
}
