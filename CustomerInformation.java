import java.util.ArrayList;
import java.util.List;

public class CustomerInformation {
    private int id;
    private String name;
    private double totalAmount;
    private List<OrderInformation> orders;

    public CustomerInformation(int id, String name, double totalAmount) {
        this.id = id;
        this.name = name;
        this.totalAmount = totalAmount;
        this.orders = new ArrayList<>();
    }

    public CustomerInformation(int id, String name) {
        this(id, name, 0.0); // Default totalAmount to 0.0 if not provided
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<OrderInformation> getOrders() {
        return orders;
    }

    public void addOrder(OrderInformation order) {
        orders.add(order);
    }

@Override
public String toString() {
    String result = "Customer ID: " + id +
                    ", Name: " + name +
                    ", Total Amount: $" + totalAmount;

    if (!orders.isEmpty()) {
        result += "\nOrders:\n";
        for (OrderInformation order : orders) {
            result += "Order ID: " + order.getOrderId() +
                      ", Item Name: " + order.getItemName() +
                      ", Item Price: $" + order.getItemPrice() +
                      ", Quantity: " + order.getQuantity() +
                      ", Order Time: " + order.getOrderTime() +
                      ", Counter: " + order.getCounter() +
                      "\n";
        }
    }

    return result;
}
}