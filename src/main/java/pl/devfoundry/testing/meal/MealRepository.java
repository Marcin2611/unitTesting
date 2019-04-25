package pl.devfoundry.testing.meal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class MealRepository {

    private List<Meal> meals = new ArrayList<>();

    void add(Meal meal) {
        this.meals.add(meal);
    }

    List<Meal> getAllMeals() {
        return meals;
    }

    void delete(Meal meal) {
        this.meals.remove(meal);
    }

    public List<Meal> findByName(String name, boolean exactMatch) {

        List<Meal> result;

        if (exactMatch) {
            result = this.meals.stream()
                    .filter(meal -> meal.getName().equals(name))
                    .collect(Collectors.toList());
        } else {
            result = this.meals.stream()
                    .filter(meal -> meal.getName().startsWith(name))
                    .collect(Collectors.toList());
        }
        return result;
    }

    public List<Meal> findByPrice(int price, SearchType searchType) {
        return findByPriceWithInitialData(price, searchType, meals);
    }

    public List<Meal> find(String name, boolean exactMatch, int price, SearchType searchType) {

        List<Meal> names = findByName(name, exactMatch);

        List<Meal> results = findByPriceWithInitialData(price, searchType, names);

        return results;
    }

    public List<Meal> findByPriceWithInitialData(int price, SearchType searchType, List<Meal> initialData) {

        List<Meal> results = new ArrayList<>();

        switch (searchType) {
            case EXACT_PRICE:
                results = initialData.stream()
                        .filter(meal -> meal.getPrice() == price)
                        .collect(Collectors.toList());
                break;
            case LESS_THAN:
                results = initialData.stream()
                        .filter(meal -> meal.getPrice() < price)
                        .collect(Collectors.toList());
                break;
            case GREATER_THAN:
                results = initialData.stream()
                        .filter(meal -> meal.getPrice() > price)
                        .collect(Collectors.toList());
                break;
        }
        return results;
    }
}
