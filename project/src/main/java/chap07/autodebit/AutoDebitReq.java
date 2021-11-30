package chap07.autodebit;

public class AutoDebitReq {

    private String cardNumber;
    private String userId;

    public AutoDebitReq(String cardNumber, String userId) {
        this.cardNumber = cardNumber;
        this.userId = userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getUserId() {
        return userId;
    }
}
