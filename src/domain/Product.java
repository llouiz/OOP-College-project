package domain;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id, unity;
	private String name;
	private String barcode;
	private String category;
	float price;

	public Product(int id, String name, String barcode, String category, float price, int unity) {
		this.id = id;
		this.name = name;
		this.barcode = barcode;
		this.category = category;
		this.price = price;
		this.unity = unity;
	}

	public Product() {

	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barCode) {
		this.barcode = barCode;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getUnity() {
		return this.unity;
	}

	public void setUnity(int unity) {
		this.unity = unity;
	}
}
