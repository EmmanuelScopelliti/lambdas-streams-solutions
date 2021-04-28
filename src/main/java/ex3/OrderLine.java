package ex3;

public class OrderLine {
  private Product product;
  private int quantity;

  public OrderLine(Product product, int quantity) {
    if (product == null) {
      throw new IllegalArgumentException("Cannot create an order line for a null product!");
    }

    if (quantity < 0) {
      throw new IllegalArgumentException("Cannot create an order line with a negative quantity! " + quantity);
    }

    this.product = product;
    this.quantity = quantity;
  }

  public OrderLine(Product product) {
    this(product, 0);
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return String.format("%s. %dx%5.2f = %5.2f",
            product.getName(), quantity, product.getPrice(), getTotal());
  }

  public double getTotal() {
    return product.getPrice() * quantity;
  }
}
