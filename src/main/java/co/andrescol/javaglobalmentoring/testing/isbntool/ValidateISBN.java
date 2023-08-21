package co.andrescol.javaglobalmentoring.testing.isbntool;

/**
 * ISBN is International Standard Book Number
 */
public class ValidateISBN {

    public boolean check(String isbn) {
        if (isbn.length() == 10) {
            return check10Characters(isbn);
        } else if (isbn.length() == 13) {
            return check13Characters(isbn);
        }
        throw new NumberFormatException("ISBN length should be 10 or 13");
    }

    private boolean check10Characters(String isbn) {
        int total = 0;
        for (int i = 1; i < isbn.length(); i++) {
            if (i == isbn.length() - 1 && isbn.charAt(i) == 'X') {
                total += 10;
            } else {
                int digit = Integer.parseInt(String.valueOf(isbn.charAt(i)));
                total += digit * (10 - i);
            }
        }
        return total % 11 == 0;
    }

    private boolean check13Characters(String isbn) {
        int total = 0;
        for (int i = 0; i < isbn.length(); i++) {
            int digit = Integer.parseInt(String.valueOf(isbn.charAt(i)));
            int multiplyFor = i % 2 == 0? 1 : 3;
            total += digit * multiplyFor;
        }
        return total % 10 == 0;
    }
}
