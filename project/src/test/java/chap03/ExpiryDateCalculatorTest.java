package chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {

    private static final ExpiryDateCalculator CAL = new ExpiryDateCalculator();

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        LocalDate actualExpiryDate = CAL.calculateExpiryDate(payData);
        assertEquals(actualExpiryDate, expectedExpiryDate);
    }

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(
                PayData.builder()
                       .billingDate(LocalDate.of(2021, 11, 1))
                       .payAmount(10_000)
                       .build(),
                LocalDate.of(2021, 12, 1));
        assertExpiryDate(
                PayData.builder()
                       .billingDate(LocalDate.of(2021, 11, 5))
                       .payAmount(10_000)
                       .build(),
                LocalDate.of(2021, 12, 5));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
                PayData.builder()
                       .billingDate(LocalDate.of(2021, 1, 31))
                       .payAmount(10_000)
                       .build(),
                LocalDate.of(2021, 2, 28));
        assertExpiryDate(
                PayData.builder()
                       .billingDate(LocalDate.of(2019, 1, 31))
                       .payAmount(10_000)
                       .build(),
                LocalDate.of(2019, 2, 28));
        assertExpiryDate(
                PayData.builder()
                       .billingDate(LocalDate.of(2021, 5, 31))
                       .payAmount(10_000)
                       .build(),
                LocalDate.of(2021, 6, 30));
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를떄_만원_납부(){
        PayData payData = PayData.builder()
                                 .firstBillingDate(LocalDate.of(2019,1,31))
                                 .billingDate(LocalDate.of(2019,2,28))
                                 .payAmount(10_000)
                                 .build();

        assertExpiryDate(payData, LocalDate.of(2019,3,31));

        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,1,30))
                .billingDate(LocalDate.of(2019,2,28))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData2, LocalDate.of(2019,3,30));

        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,5, 31))
                .billingDate(LocalDate.of(2019,6,30))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData3, LocalDate.of(2019,7,31));
    }

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산(){
        assertExpiryDate(
                PayData.builder()
                       .billingDate(LocalDate.of(2019,3,1))
                       .payAmount(20_000)
                       .build(),
                LocalDate.of(2019,5,1)
        );
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를떄_이만원_이상_납부(){
         assertExpiryDate(
                 PayData.builder()
                        .firstBillingDate(LocalDate.of(2019,1,31))
                        .billingDate(LocalDate.of(2019,2,28))
                        .payAmount(20_000)
                        .build(),
                 LocalDate.of(2019,4,30)
         );
    }

    @Test
    void 십만원을_납부하면_1년_제공(){
        assertExpiryDate(
                PayData.builder()
                       .billingDate(LocalDate.of(2019,1,28))
                       .payAmount(100_000)
                       .build(),
                LocalDate.of(2020,1,28)
        );
    }

    @Test
    void 윤달_마지막_날에_10만원을_납부(){
        assertExpiryDate(
                PayData.builder()
                       .billingDate(LocalDate.of(2020,2,29))
                       .payAmount(100_000)
                       .build(),
                LocalDate.of(2021,2,28)
        );
    }

    @Test
    void 십_삼만원을_납부하는_경우(){
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2021,11,29))
                        .payAmount(130_000)
                        .build(),
                LocalDate.of(2023,2,28)
        );
    }
}
