package relationaldbs.model;

public class Product {

	// 🔹 atributos
	private long id;
	private String name;
	private String description;
	private double price;
	private int stock;
	private String category;
	private String brand;
	private String tag;

	// 🔹 constructor
	public Product(long id, String name, String description, double price, int stock, String category, String brand,
			String tag) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.category = category;
		this.brand = brand;
		this.tag = tag;
	}

	// 🔹 constructor vacío
	public Product() {
	}

	// 🔹 getters y setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	// 🔹 toString para imprimir fácilmente
	@Override
	public String toString() {
		return "Product{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", price="
				+ price + ", stock=" + stock + ", category='" + category + '\'' + ", brand='" + brand + '\'' + ", tag='"
				+ tag + '\'' + '}';
	}
}
