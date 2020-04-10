package Business;

public class Product {
	//Declare attributes
	private int productID;
	private String name, category;
	private double price;
	//Static counter for id
	private static int id_Count = 101;
	
	//Getters
	public String getName() {
		return name;
	}
	public String getCategory() {
		return category;
	}
	public int getID() {
		return productID;
	}
	public double getPrice() {
		return price;
	}
	
	public Product() {}
	//Constructor **all attributes should be provided to create an object
	public Product(String newName, String newCategory, double newPrice) {
		productID = id_Count++;
		this.name = newName;
		this.category = newCategory;
		this.price = newPrice;
	}
	
	//Setters
	public void setProductId(int id) {
		productID = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString() {
		return productID + "\t\t" + name + "\tCategory: " + category + "\tPrice: " + price;
	}
	

}
