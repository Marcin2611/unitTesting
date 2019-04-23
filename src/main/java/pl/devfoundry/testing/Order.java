package pl.devfoundry.testing;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Meal> meals = new ArrayList<>();

    public void addMealToOrder(Meal meal) {
        meals.add(meal);
    }

    public void removeMealFromOrder(Meal meal) {
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

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }
}
