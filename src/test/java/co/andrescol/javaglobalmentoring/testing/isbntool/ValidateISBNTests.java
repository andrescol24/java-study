package co.andrescol.javaglobalmentoring.testing.isbntool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateISBNTests {

    @Test
    public void validateValidISBNTest() {
        ValidateISBN validator = new ValidateISBN();
        boolean valid1 = validator.check("0140449116");
        boolean valid2 = validator.check("0140177396");
        assertTrue(valid1, "First value");
        assertTrue(valid2, "Second value");
    }
    @Test
    public void validateValidISBNWithXAtTheEndTest() {
        ValidateISBN validator = new ValidateISBN();
        boolean validWithX = validator.check("012000030X");
        assertTrue(validWithX, "Ending with X");
    }
    @Test
    public void validateValidISBN13CharactersTest() {
        ValidateISBN validator = new ValidateISBN();
        boolean valid1 = validator.check("9788175992771");
        boolean valid2 = validator.check("9781853267338");
        assertTrue(valid1, "First value");
        assertTrue(valid2, "Second value");
    }
    @Test
    public void validateInvalidISBN13CharactersTest() {
        ValidateISBN validator = new ValidateISBN();
        boolean valid = validator.check("9781853267336");
        assertFalse(valid);
    }
    @Test
    public void validateInvalidISBNTest() {
        ValidateISBN validator = new ValidateISBN();
        boolean valid = validator.check("0140449117");
        assertFalse(valid);
    }
    @Test
    public void invalidISBNLengthTest() {
        ValidateISBN validator = new ValidateISBN();
        assertThrows(NumberFormatException.class, () -> {
            validator.check("123456789");
        });

    }
    @Test
    public void checkAllCharactersAreNumbersTest() {
        ValidateISBN validator = new ValidateISBN();
        assertThrows(NumberFormatException.class, () -> {
            validator.check("helloworld");
        });
    }
}
