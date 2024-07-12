public class OrderInformation {
    private int orderId;
    private String itemName;
    private double itemPrice;
    private int quantity;
    private String orderTime;
    private String counter;

    public OrderInformation(int orderId, String itemName, double itemPrice, int quantity, String orderTime, String counter) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.orderTime = orderTime;
        this.counter = counter;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public String getCounter() {
        return counter;
    }
}
