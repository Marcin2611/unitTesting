package pl.devfoundry.testing;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class OrderTest {

    @Test
    void testAssertArrayEquals() {

        //given
        int[] ints1 = {1, 2, 3};
        int[] ints2 = {1, 2, 3};

        //then
        assertArrayEquals(ints1, ints2);
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOfOrder() {

        //given
        Order order = new Order();

        //then
        assertThat(order.getMeals(), empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), emptyCollectionOf(Meal.class));
    }

    @Test
    void addingMealToOrderShouldIncreaseOrderSize() {

        //given
        Meal meal = new Meal(15, "Burger");
        Meal meal2 = new Meal(5, "Sandwich");
        Order order = new Order();

        //when
        order.addMealToOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(meal));
        assertThat(order.getMeals(), hasItem(meal));
    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize() {

        //given
        Meal meal = new Meal(15, "Burger");
        Order order = new Order();

        //when
        order.addMealToOrder(meal);
        order.removeMealFromOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(0));
    }

    @Test
    void mealShouldBeInCorrectOrderAfterAddingThemToOrder() {

        //given
        Meal meal1 = new Meal(20, "Burger");
        Meal meal2 = new Meal(30, "Pizza");
        Order order = new Order();

        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);

        //then
        assertThat(order.getMeals(), containsInAnyOrder(meal2, meal1));
    }

    @Test
    void testIfTwoMealListsAreTheSame() {

        //given
        Meal meal1 = new Meal(20, "Burger");
        Meal meal2 = new Meal(30, "Pizza");
        Meal meal3 = new Meal(15, "Kebab");

        List<Meal> meals1 = Arrays.asList(meal1, meal2);
        List<Meal> meals2 = Arrays.asList(meal1, meal2);

        //then
        assertThat(meals1, is(meals2));
    }
}