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
	
	//Constructor **all attributes should be provided to create an object
	public Product(String newName, String newCategory, double newPrice) {
		productID = id_Count++;
		this.name = newName;
		this.category = newCategory;
		this.price = newPrice;
	}
	
	public String toString() {
		return "ID: " + productID + ", Name: " + name + ", Category: " + category + ", Price: " + price;
	}
	
	//On this phase of project interface for changing produc data is not provided, so setters in this class are also not provided

}
