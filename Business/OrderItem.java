package Business;

public class OrderItem {
	
	private Product product;
	private int id;
	private String size, iceQuantity, sugar;
	private double price;
	
	private static int id_Count = 1;
	
	
	//Constructor
	public OrderItem(Product newProduct) {
		product = newProduct;
		size = "Regular";
		iceQuantity = "Normal ice";
		sugar = "Normal";
		id = id_Count++;
		price = product.getPrice();
	}
	
	public String toString() {
		return "ItemID: " + String.format("%04d", id) + " " + product.getName();
	}
	
	//Setters
	public void setSize(String newSize) {
		size = newSize;
	}
	public void setIce(String newIce) {
		iceQuantity = newIce;
	}
	public void setSugar(String newSugar) {
		sugar = newSugar;
	}
	
	public String getSize() {
		return size;
	}
	public String getIce() {
		return iceQuantity;
	}
	public String getSugar() {
		return sugar;
	}
	public String getProductName() {
		return product.getName();
	}
	public double getPrice() {
		double price = product.getPrice();
		
		if (size.equals("Large"))
			price += 0.5;
		return price;
	}
	public int getID() {
		return id;
	}

}
