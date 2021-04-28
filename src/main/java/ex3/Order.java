package ex3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;

public class Order {
  Customer customer;
  List<OrderLine> orderLines = new ArrayList<>();

  public Order() {
  }

  public Order(Customer customer) {
    this.customer = customer;
  }

  public Customer getCustomer() {
    return customer;
  }

  public List<OrderLine> getOrderLines() {
    return orderLines;
  }

  /**
   * Adds a quantity of the product to the order. If the product is already listed in the order, adds
   * the quantity to the previously ordered quantity.
   *
   * <p>If quantity is 0, remove the product from the order.
   *
   * @throws IllegalArgumentException if quantity is negative or the product is null
   */
  public void addProduct(Product product, int quantity) {
    if (quantity < 0) {
      throw new IllegalArgumentException("Cannot add a negative quantity of product " + product.getName());
    }

    if (quantity == 0) {
      removeProduct(product);
      return;
    }

    OrderLine line = getOrderLine(product)
            .orElseGet(createEmptyOrderLine(product));

    int updatedQuantity = line.getQuantity() + quantity;
    line.setQuantity(updatedQuantity);
  }

  /**
   * Removes the product from the order. After invoking this method, {@link #getOrderLine(Product)}
   * will return null for the removed product.
   */
  public void removeProduct(Product product) {
    getOrderLine(product).ifPresent(orderLines::remove);
  }

  /**
   * Returns an Optional with the order line for the product.
   */
  public Optional<OrderLine> getOrderLine(Product product) {
    return orderLines.stream()
            .filter(line -> line.getProduct().equals(product))
            .findFirst();
  }

  /**
   * Calculates the total price of the order.
   */
  public double getTotal() {
    return orderLines.stream()
            .mapToDouble(OrderLine::getTotal)
            .sum();
  }

  /**
   * Returns the average price of the products in the order. If the order is empty, returns 0.0; The
   * average does not take into account how many products were ordered.
   */
  public double getAverageProductPrice() {
    return orderLines.stream()
            .map(OrderLine::getProduct)
            .mapToDouble(Product::getPrice)
            .average()
            .orElse(0);
  }

  /**
   * Returns an Optional with the most expensive product in the order.
   */
  public Optional<Product> getMostExpensiveProduct() {
    return orderLines.stream()
            .map(OrderLine::getProduct)
            .max(comparingDouble(Product::getPrice));
  }

  /**
   * Returns true if there is at least one order line in the order.
   */
  public boolean isEmpty() {
    return orderLines.isEmpty();
  }

  @Override
  public String toString() {
    return String.format("%s%n---------%nTotal = %5.2f", getOrderLinesString(), getTotal());
  }

  private Supplier<OrderLine> createEmptyOrderLine(Product product) {
    return () -> {
      OrderLine line = new OrderLine(product);
      orderLines.add(line);
      return line;
    };
  }

  private String getOrderLinesString() {
    return orderLines.stream()
            .map(OrderLine::toString)
            .collect(Collectors.joining("\n"));
  }

}
