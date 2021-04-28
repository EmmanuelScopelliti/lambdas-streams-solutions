package ex3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class Customer {
  String firstName;
  String lastName;
  LocalDate dateOfBirth;
  List<Order> orders = new ArrayList<>();


  public Customer(String firstName, String lastName, LocalDate dateOfBirth) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
  }

  public Order createOrder() {
    Order order = new Order(this);
    orders.add(order);
    return order;
  }

  /**
   * Returns all products purchased by the customer. If a product was purchased more than once, it
   * is returned multiple times.
   */
  public List<Product> getPurchasedProducts() {
    return orders.stream()
            .flatMap(order -> order.getOrderLines().stream())
            .map(OrderLine::getProduct)
            .collect(toList());
  }

  /**
   * Returns a list of unique expensive products, sorted by product name (a-z). Returns an empty
   * list if the customer has not made any orders or never purchased an expensive product.
   *
   * @see Product#isExpensive()
   */
  public List<Product> getSortedListOfExpensivePurchasedProducts() {
    return getPurchasedProducts()
            .stream()
            .filter(Product::isExpensive)
            .distinct()
            .sorted(comparing(Product::getName))
            .collect(toList());
  }

  /**
   * Returns a cheap product purchased by the customer. Returns null if the customer has not made
   * any orders or never purchased a cheap product.
   *
   * @see Product#isCheap()
   */
  public Optional<Product> findAnyCheapPurchasedProduct() {
    return getPurchasedProducts()
            .stream()
            .filter(Product::isCheap)
            .findAny();
  }

  /**
   * Returns the most purchased product by the customer. If there is a tie, the method returns any
   * of the most purchased products. Returns null if the customer has not made any orders. This
   * method does not consider how quantities in each order, simply if a product was purchased or
   * not.
   */
  public Optional<Product> getMostPurchasedProduct() {
    Map<Product, Long> productPurchaseFrequencyMap = getPurchasedProducts()
            .stream()
            .collect(groupingBy(identity(), counting()));

    return productPurchaseFrequencyMap
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey);
  }

}
