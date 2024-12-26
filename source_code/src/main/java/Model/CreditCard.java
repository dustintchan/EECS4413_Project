package Model;

public class CreditCard {
    private int id;
    private int userId;
    private String cardNumber, cardName, cardExpDate, cardSecCode;

    public CreditCard(int id, int userId, String cardNumber, String cardName, String cardExpDate, String cardSecCode) {
        this.id = id;
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.cardExpDate = cardExpDate;
        this.cardSecCode = cardSecCode;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getCardNumber() { return cardNumber; }
    public String getCardName() { return cardName; }
    public String getCardExpDate() { return cardExpDate; }
    public String getCardSecCode() { return cardSecCode; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public void setCardName(String cardName) { this.cardName = cardName; }
    public void setCardExpDate(String cardExpDate) { this.cardExpDate = cardExpDate; }
    public void setCardSecCode(String cardSecCode) { this.cardSecCode = cardSecCode; }

}
