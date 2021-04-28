## Exercise 1: Life After Streams

This exercise is very similar to the exercise 4 from collections lab, with one important difference, you should **USE streams, lambdas, and optionals** to solve it. 

This exercise simulates a fragment of a simplified order management system. You should implement the methods listed below, according to their description and the unit tests located in [`test/java/ex1/`](../../../test/java/ex1).

- In the [`Order`](Order.java) class, implement the methods:
    - `public void addProduct(Product product, int quantity)`
    - `public void removeProduct(Product product)`
    - `public OrderLine getOrderLine(Product product)`
    - `public double getTotal()`
    - `public double getAverageProductPrice()`
    - `public Product getMostExpensiveProduct()`
    - `private boolean isEmpty()`
  
- In the [`Customer`](Customer.java) class, implement the methods:
  - `public List<Product> getPurchasedProducts()`
  - `public List<Product> getSortedListOfExpensivePurchasedProducts()`
  - `public Product findAnyCheapPurchasedProduct()`
  - `public Product mostPurchasedProduct()`

You should NOT change any method signature, but you are free to add new methods you see fit.

Your code should pass all tests defined in [`CustomerTest`](../../../test/java/ex1/CustomerTest.java) and [`OrderTest`](../../../test/java/ex1/OrderTest.java).
 