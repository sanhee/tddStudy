package chap07.autodebit;

import java.time.LocalDateTime;

public class AutoDebitInfo {
    private final String userId;
    private String cardNumber;
    private final LocalDateTime registerDate;

    public AutoDebitInfo(String userId, String cardNumber, LocalDateTime registerDate) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.registerDate = registerDate;
    }

    public void changeCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
