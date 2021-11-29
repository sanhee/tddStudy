package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        final int addedMonths = payData.getPayAmount() / 10_000;
        if(payData.getFirstBillingDate() != null){
            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);

            if(payData.getFirstBillingDate().getDayOfMonth() != candidateExp.getDayOfMonth()){

                // 후보 만료일이 포함된 달의 마지막 날 < 첫 납부일의 일자
                // 참이면, 후보 만료일을 그달의 마지막 날로 조정
                if(YearMonth.from(candidateExp).lengthOfMonth() < payData.getFirstBillingDate().getDayOfMonth()){
                    return candidateExp.withDayOfMonth(YearMonth.from(candidateExp).lengthOfMonth());
                }

                return candidateExp.withDayOfMonth(payData.getFirstBillingDate()
                                                          .getDayOfMonth());
            }
        }
        return payData.getBillingDate().plusMonths(addedMonths);
    }
}
