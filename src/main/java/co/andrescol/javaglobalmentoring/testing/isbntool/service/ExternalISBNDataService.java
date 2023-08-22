package co.andrescol.javaglobalmentoring.testing.isbntool.service;

import co.andrescol.javaglobalmentoring.testing.isbntool.dto.Book;

public interface ExternalISBNDataService {
    Book lookup(String isbn);
}
