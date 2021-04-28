package ex1;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {

  final Product COMPUTER = new Product("Computer", 1200.00);
  final Product NOTEBOOK = new Product("Notebook", 1800.00);
  final Product KEYBOARD = new Product("Keyboard", 100.99);
  final Product MOUSE = new Product("Mouse", 30.49);
  final Product MONITOR = new Product("Monitor", 249.98);

  Order order = new Order();

  @Test
  void getOrderLineAfterAddingOnce() {
    order.addProduct(COMPUTER, 2);
    Optional<OrderLine> lineOpt = order.getOrderLine(COMPUTER);
    assertThat(lineOpt).isPresent();
    assertThat(lineOpt.get().getProduct()).isEqualTo(COMPUTER);
    assertThat(lineOpt.get().getQuantity()).isEqualTo(2);
  }

  @Test
  void addProductShouldBeCumulative() {
    order.addProduct(COMPUTER, 2);
    order.addProduct(COMPUTER, 5);
    Optional<OrderLine> lineOpt = order.getOrderLine(COMPUTER);
    assertThat(lineOpt).isPresent();
    assertThat(lineOpt.get().getQuantity()).isEqualTo(7);
  }

  @Test
  void addZeroShouldRemoveProductFromOrder() {
    order.addProduct(COMPUTER, 2);
    order.addProduct(COMPUTER, 0);
    assertThat(order.getOrderLine(COMPUTER)).isEmpty();
  }

  @Test
  void addNegativeProductShouldThrowException() {
    assertThrows(IllegalArgumentException.class,
            () -> order.addProduct(COMPUTER, -5));
  }

  @Test
  void addNullProductShouldThrowException() {
    assertThrows(IllegalArgumentException.class,
            () -> order.addProduct(null, 5));
  }

  @Test
  void shouldCalculateOrderTotal() {
    order.addProduct(COMPUTER, 1);
    order.addProduct(MONITOR, 2);
    order.addProduct(MOUSE, 1);
    order.addProduct(KEYBOARD, 1);

    double expectedTotal = 1200 + 249.98 * 2 + 30.49 + 100.99;
    assertThat(order.getTotal()).isEqualTo(expectedTotal);
  }

  @Test
  void shouldCalculateEmptyOrderTotal() {
    assertThat(order.getTotal()).isZero();
  }

  @Test
  void averageProductPriceOfEmptyOrderShouldBeZero() {
    assertThat(order.getAverageProductPrice()).isZero();
  }

  @Test
  void averagePriceOfOrderWithOneProductTypeIsProductPrice() {
    order.addProduct(COMPUTER, 1);
    assertThat(order.getAverageProductPrice()).isEqualTo(1200);
  }

  @Test
  void averagePriceOfOrderWithQuantityOfOneProductIsProductPrice() {
    order.addProduct(COMPUTER, 5);
    assertThat(order.getAverageProductPrice()).isEqualTo(1200);
  }

  @Test
  void shouldReturnAveragePriceOfTwoProducts() {
    order.addProduct(COMPUTER, 1);
    order.addProduct(NOTEBOOK, 1);
    assertThat(order.getAverageProductPrice()).isEqualTo(1500);
  }

  @Test
  void shouldReturnAveragePriceOfThreeProducts() {
    order.addProduct(COMPUTER, 2);
    order.addProduct(NOTEBOOK, 3);
    order.addProduct(KEYBOARD, 3);

    double expectedAverage = (1800 + 1200 + 100.99) / 3;
    assertThat(order.getAverageProductPrice()).isEqualTo(expectedAverage);
  }

  @Test
  void shouldReturnNotebookAsMostExpensive() {
    order.addProduct(COMPUTER, 2);
    order.addProduct(NOTEBOOK, 3);
    order.addProduct(KEYBOARD, 3);

    assertThat(order.getMostExpensiveProduct()).hasValue(NOTEBOOK);
  }

  @Test
  void shouldReturnComputerAsMostExpensive() {
    order.addProduct(COMPUTER, 1);
    order.addProduct(KEYBOARD, 1000);

    assertThat(order.getMostExpensiveProduct()).hasValue(COMPUTER);
  }

}
