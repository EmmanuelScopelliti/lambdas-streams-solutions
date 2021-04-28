package ex1;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

class CustomerTest {

  static final Product COMPUTER = new Product("Computer", 1200.00);
  static final Product NOTEBOOK = new Product("Notebook", 1800.00);
  static final Product TABLET = new Product("Tablet", 900.00);
  static final Product KEYBOARD = new Product("Keyboard", 100.99);
  static final Product MOUSE = new Product("Mouse", 30.49);
  static final Product MONITOR = new Product("Monitor", 249.98);

  @Test
  void shouldReturnComputerNotebookTablet() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));

    Order order1 = customer.createOrder();
    order1.addProduct(COMPUTER, 3);
    order1.addProduct(NOTEBOOK, 2);

    Order order2 = customer.createOrder();
    order2.addProduct(COMPUTER, 1);
    order2.addProduct(MONITOR, 2);
    order2.addProduct(MOUSE, 1);
    order2.addProduct(KEYBOARD, 3);

    Order order3 = customer.createOrder();
    order3.addProduct(NOTEBOOK, 6);
    order3.addProduct(MONITOR, 6);
    order3.addProduct(MOUSE, 6);

    Order order4 = customer.createOrder();
    order4.addProduct(TABLET, 50);

    List<Product> list = customer.getSortedListOfExpensivePurchasedProducts();
    assertThat(list).containsExactly(COMPUTER, NOTEBOOK, TABLET);
  }

  @Test
  void shouldReturnEmptyListIfCustomerHasNoOrders() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));
    List<Product> list = customer.getSortedListOfExpensivePurchasedProducts();
    assertThat(list).isEmpty();
  }

  @Test
  void shouldReturnEmptyListIfCustomerOnlyOrderedCheapProducts() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));

    Order order1 = customer.createOrder();
    order1.addProduct(MOUSE, 100);
    order1.addProduct(KEYBOARD, 10);

    Order order2 = customer.createOrder();
    order2.addProduct(MONITOR, 3);

    List<Product> list = customer.getSortedListOfExpensivePurchasedProducts();
    assertThat(list).isEmpty();
  }

  @Test
  void shouldReturnAnyCheapProduct() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));

    Order order1 = customer.createOrder();
    order1.addProduct(MOUSE, 100);
    order1.addProduct(KEYBOARD, 10);
    order1.addProduct(NOTEBOOK, 8);
    order1.addProduct(TABLET, 1);

    Order order2 = customer.createOrder();
    order2.addProduct(MONITOR, 3);
    order2.addProduct(COMPUTER, 3);

    Optional<Product> cheapProductOpt = customer.findAnyCheapPurchasedProduct();
    assertThat(cheapProductOpt).isPresent();
    assertThat(cheapProductOpt.get()).isAnyOf(MOUSE, KEYBOARD, MONITOR);
  }

  @Test
  void shouldReturnNullIfCustomerHasNoOrders() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));

    Optional<Product> cheapProductOpt = customer.findAnyCheapPurchasedProduct();
    assertThat(cheapProductOpt).isEmpty();
  }

  @Test
  void shouldReturnNullIfCustomerOnlyPurchasedExpensiveProducts() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));

    Order order1 = customer.createOrder();
    order1.addProduct(NOTEBOOK, 8);
    order1.addProduct(TABLET, 1);

    Order order2 = customer.createOrder();
    order2.addProduct(COMPUTER, 3);
    Optional<Product> cheapProductOpt = customer.findAnyCheapPurchasedProduct();
    assertThat(cheapProductOpt).isEmpty();
  }

  @Test
  void shouldReturnMostPurchasedUnique() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));

    Order order1 = customer.createOrder();
    order1.addProduct(NOTEBOOK, 15);
    order1.addProduct(TABLET, 1);
    order1.addProduct(COMPUTER, 3);

    Order order2 = customer.createOrder();
    order2.addProduct(COMPUTER, 3);

    Optional<Product> mostPurchasedOpt = customer.getMostPurchasedProduct();
    assertThat(mostPurchasedOpt).hasValue(COMPUTER);
  }

  @Test
  void shouldReturnMostPurchasedTie() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));

    Order order1 = customer.createOrder();
    order1.addProduct(NOTEBOOK, 15);
    order1.addProduct(TABLET, 7);
    order1.addProduct(COMPUTER, 3);

    Order order2 = customer.createOrder();
    order1.addProduct(TABLET, 4);
    order2.addProduct(COMPUTER, 3);

    Optional<Product> mostPurchasedOpt = customer.getMostPurchasedProduct();
    assertThat(mostPurchasedOpt).isPresent();
    assertThat(mostPurchasedOpt.get()).isAnyOf(TABLET, COMPUTER);
  }

  @Test
  void mostPurchasedShouldReturnNull() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));
    Optional<Product> mostPurchasedOpt = customer.getMostPurchasedProduct();
    assertThat(mostPurchasedOpt).isEmpty();
  }

  @Test
  void shouldReturnProducts() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));

    Order order1 = customer.createOrder();
    order1.addProduct(NOTEBOOK, 15);
    order1.addProduct(TABLET, 7);
    order1.addProduct(COMPUTER, 1);

    Order order2 = customer.createOrder();
    order2.addProduct(TABLET, 1);
    order2.addProduct(COMPUTER, 3);

    List<Product> purchased = customer.getPurchasedProducts();
    assertThat(purchased).hasSize(5);

    long notebooks = purchased.stream().filter(product -> product == NOTEBOOK).count();
    assertThat(notebooks).isEqualTo(1);

    long tablets = purchased.stream().filter(product -> product == TABLET).count();
    assertThat(tablets).isEqualTo(2);

    long computers = purchased.stream().filter(product -> product == COMPUTER).count();
    assertThat(computers).isEqualTo(2);
  }
}
