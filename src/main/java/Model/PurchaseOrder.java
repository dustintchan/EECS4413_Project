package Model;

public class PurchaseOrder {
    private int id;
    private int userId;
    private int itemId;
    private int quantity;
    private double totalAmount;
    private String dateOfPurchase;

    public PurchaseOrder(int id, int userId, int itemId, int quantity, double totalAmount, String dateOfPurchase) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.dateOfPurchase = dateOfPurchase;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getItemId() { return itemId; }
    public int getQuantity() { return quantity; }
    public double getTotalAmount() { return totalAmount; }
    public String getDateOfPurchase() { return dateOfPurchase; }
}
