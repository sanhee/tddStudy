package chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(
                LocalDate.of(2021, 11, 1), 1000,
                LocalDate.of(2021, 12, 1));
        assertExpiryDate(
                LocalDate.of(2021, 11, 5), 1000,
                LocalDate.of(2021, 12, 5));
    }

    private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate actualExpiryDate = cal.calculateExpiryDate(billingDate, payAmount);

        assertEquals(actualExpiryDate, expiryDate);
    }
}
