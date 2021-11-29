package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {

        int addedMonths = 0;

        if(payData.getPayAmount() >= 100_000){
            int q = payData.getPayAmount() / 100_000;
            int r = payData.getPayAmount() % 100_000;
            addedMonths = (q * 12) + (r / 10_000);
        }else{
            addedMonths = payData.getPayAmount() / 10_000;
        }

        if(payData.getFirstBillingDate() != null){
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        }
        return payData.getBillingDate().plusMonths(addedMonths);
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);

        final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
        if(isDifferentDayOfMonth(candidateExp, dayOfFirstBilling)){
            // 후보 만료일이 포함된 달의 마지막 날 < 첫 납부일의 일자
            // 참이면, 후보 만료일을 그달의 마지막 날로 조정
            final int dayLenOfCandiMon = lastDayOfMonth(candidateExp);
            if(dayLenOfCandiMon < dayOfFirstBilling){
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        }else{
            return candidateExp;
        }
    }

    private int lastDayOfMonth(LocalDate candidateExp) {
        return YearMonth.from(candidateExp).lengthOfMonth();
    }

    private boolean isDifferentDayOfMonth(LocalDate candidateExp, int dayOfFirstBilling) {
        return dayOfFirstBilling != candidateExp.getDayOfMonth();
    }
}
