package chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {

    private static final ExpiryDateCalculator CAL = new ExpiryDateCalculator();

    private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expiryDate) {
        PayData payData = PayData.builder()
                                 .billingDate(billingDate)
                                 .payAmount(payAmount)
                                 .build();
        assertExpiryDate(payData, expiryDate);
    }

    private void assertExpiryDate(PayData payData, LocalDate expiryDate) {
        LocalDate actualExpiryDate = CAL.calculateExpiryDate(payData);
        assertEquals(actualExpiryDate, expiryDate);
    }

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(
                LocalDate.of(2021, 11, 1), 1000,
                LocalDate.of(2021, 12, 1));
        assertExpiryDate(
                LocalDate.of(2021, 11, 5), 1000,
                LocalDate.of(2021, 12, 5));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
                LocalDate.of(2021, 1, 31), 1000,
                LocalDate.of(2021, 2, 28));
        assertExpiryDate(
                LocalDate.of(2019, 1, 31), 1000,
                LocalDate.of(2019, 2, 28));
        assertExpiryDate(
                LocalDate.of(2021, 5, 31), 1000,
                LocalDate.of(2021, 6, 30));
    }


}
