package chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {
    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        LocalDate billingDate = LocalDate.of(2021, 11, 1);
        int payAmount = 10_000;

        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);

        assertEquals(LocalDate.of(2021, 12, 1), expiryDate);

        LocalDate billingDate2 = LocalDate.of(2021, 11, 5);
        LocalDate expiryDate2 = cal.calculateExpiryDate(billingDate2, payAmount);

        assertEquals(LocalDate.of(2021, 12, 5), expiryDate2);
    }
}
