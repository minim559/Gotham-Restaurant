import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class QueueManagement {
    private Queue<CustomerInformation> counter1;
    private Queue<CustomerInformation> counter2;
    private Queue<CustomerInformation> counter3;
    private UserDashboard userDashboard;

    public QueueManagement() {
        counter1 = new LinkedList<>();
        counter2 = new LinkedList<>();
        counter3 = new LinkedList<>();
    }

    public void setUserDashboard(UserDashboard userDashboard) {
        this.userDashboard = userDashboard;
    }

    public Queue<CustomerInformation> getCounter1() {
        return counter1;
    }

    public Queue<CustomerInformation> getCounter2() {
        return counter2;
    }

    public Queue<CustomerInformation> getCounter3() {
        return counter3;
    }

    public void addCustomer(String counterName, CustomerInformation customer) {
        switch (counterName) {
            case "Counter 1":
                counter1.offer(customer);
                break;
            case "Counter 2":
                counter2.offer(customer);
                break;
            case "Counter 3":
                counter3.offer(customer);
                break;
            default:
                System.out.println("Invalid counter name.");
        }
        if (userDashboard != null) {
            userDashboard.updateCountersPanel();
        }
    }

    public void removeCustomer(String counterName, int customerId) {
        Queue<CustomerInformation> counter = getCounterByName(counterName);
        if (counter != null) {
            Iterator<CustomerInformation> iterator = counter.iterator();
            while (iterator.hasNext()) {
                CustomerInformation customer = iterator.next();
                if (customer.getId() == customerId) {
                    iterator.remove();
                    break;
                }
            }
            if (userDashboard != null) {
                userDashboard.updateCountersPanel();
            }
        }
    }

    public CustomerInformation findCustomerById(int customerId) {
        CustomerInformation foundCustomer = null;
        foundCustomer = findCustomerInQueue(counter1, customerId);
        if (foundCustomer == null) {
            foundCustomer = findCustomerInQueue(counter2, customerId);
        }
        if (foundCustomer == null) {
            foundCustomer = findCustomerInQueue(counter3, customerId);
        }
        return foundCustomer;
    }

    private CustomerInformation findCustomerInQueue(Queue<CustomerInformation> queue, int customerId) {
        for (CustomerInformation customer : queue) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    public void loadCustomers(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                double totalAmount = Double.parseDouble(parts[2].trim());
                String counterName = parts[3].trim();

                CustomerInformation customer = new CustomerInformation(id, name, totalAmount);
                addCustomer(counterName, customer);
            }
            if (userDashboard != null) {
                userDashboard.updateCountersPanel();
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private Queue<CustomerInformation> getCounterByName(String counterName) {
        switch (counterName) {
            case "Counter 1":
                return counter1;
            case "Counter 2":
                return counter2;
            case "Counter 3":
                return counter3;
            default:
                return null;
        }
    }
}