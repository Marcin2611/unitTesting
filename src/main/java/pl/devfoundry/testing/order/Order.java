package pl.devfoundry.testing.order;

import pl.devfoundry.testing.Meal;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private OrderStatus orderStatus;
    private List<Meal> meals = new ArrayList<>();

    public void addMealToOrder(Meal meal) {
        meals.add(meal);
    }

    void removeMealFromOrder(Meal meal) {
        meals.remove(meal);
    }

    public List<Meal> getMeals() {
        return meals;
    }

    void cancel() {
        this.meals.clear();
    }

    int totalPrice() {

        int sum = this.meals.stream().mapToInt(Meal::getPrice).sum();

        if (sum < 0) {
            throw new IllegalStateException("Price limit exceeded");
        } else {
            return sum;
        }
    }

    public void changeOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }
}
