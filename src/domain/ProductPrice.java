package domain;

public abstract class ProductPrice {
	private int unity;
	private float price;

	public int getUnity() {
		return this.unity;
	}

	public void setUnity(int unity) {
		this.unity = unity;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
