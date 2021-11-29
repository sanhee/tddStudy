package chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        final int addedMonths = 1;
        if(payData.getFirstBillingDate() != null){
            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);

            if(payData.getFirstBillingDate().getDayOfMonth() != candidateExp.getDayOfMonth()){
                return candidateExp.withDayOfMonth(payData.getFirstBillingDate()
                                                          .getDayOfMonth());
            }
        }
        return payData.getBillingDate().plusMonths(addedMonths);
    }
}
