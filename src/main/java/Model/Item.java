package Model;

public class Item {
    private int id;
    private String name;
    private String description;
    private String category;
    private String brand;
    private String size;
    private String color;
    private String type;
    private double price;
    private int quantity;
    private int quantityInCart;
    private String imgPath;

    public Item(int id, String name, String description, String category, String brand, String size, String color, String type, double price, int quantity, String imgPath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.size = size;
        this.color = color;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.quantityInCart = 1;
        this.imgPath = imgPath;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public String getBrand() { return brand; }
    public String getSize() { return size; }
    public String getColor() { return color; }
    public String getType() { return type; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getQuantityInCart() { return quantityInCart; }
    public String getImgPath() { return imgPath; }

	public void setQuantity(int newQty) {
		 this.quantity = newQty;
		
	}
	public void setQuantityInCart(int newQty) {
		 this.quantityInCart = newQty;
		
	}
}