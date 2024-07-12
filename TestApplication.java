public class TestApplication {
    public static void main(String[] args) {
        // Creating a sample OrderInformation
        OrderInformation order = new OrderInformation(1, "Item 1", 10.0, 2, "12:00", "Counter 1");

        // Creating a sample CustomerInformation
        CustomerInformation customer = new CustomerInformation(1, "ALI");
        customer.addOrder(order);

        // Displaying customer information with orders
        System.out.println("Customer ID: " + customer.getId() +
                           ", Name: " + customer.getName() +
                           ", Total Amount: $" + customer.getTotalAmount());
        System.out.println("Orders:");
        for (OrderInformation ord : customer.getOrders()) {
            System.out.println("Order ID: " + ord.getOrderId() +
                               ", Item Name: " + ord.getItemName() +
                               ", Item Price: $" + ord.getItemPrice() +
                               ", Quantity: " + ord.getQuantity() +
                               ", Order Time: " + ord.getOrderTime() +
                               ", Counter: " + ord.getCounter());
        }
    }
}