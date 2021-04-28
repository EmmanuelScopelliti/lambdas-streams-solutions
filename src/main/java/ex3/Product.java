package ex3;

public class Product {

  private String name;
  private double price;

  public Product(String name, double unitPrice) {
    this.name = name;
    this.price = unitPrice;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  boolean isExpensive() {
    return getPrice() > 500;
  }

  boolean isCheap() {
    return !isExpensive();
  }

  @Override
  public String toString() {
    return name + " (" + price + ")";
  }

}
