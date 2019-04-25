package pl.devfoundry.testing.meal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;

public class MealRepositoryTest {

    private MealRepository mealRepository = new MealRepository();

    @BeforeEach
    void cleanUp() {
        mealRepository.getAllMeals().clear();
    }

    @Test
    void shouldBeAbleToAddMealToRepository() {

        //given
        Meal meal = new Meal(10, "Pizza");

        //when
        mealRepository.add(meal);

        //then
        assertThat(mealRepository.getAllMeals().get(0), is(meal));
    }

    @Test
    void shouldBeAbleToRemoveMealFromRepository() {

        //given
        Meal meal = new Meal(10, "Pizza");
        mealRepository.add(meal);

        //when
        mealRepository.delete(meal);

        //then
        assertThat(mealRepository.getAllMeals(), not(contains(meal)));
    }

    @Test
    void shouldBeAbleToFindMealByExactName() {

        //given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10, "Pi");
        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByName("Pizza", true);

        //then
        assertThat(results, hasSize(1));
    }

    @Test
    void shouldBeAbleToFindMealByStartingLetters() {

        //given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10, "Pi");
        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByName("Pi", false);

        //then
        assertThat(results, hasSize(2));
    }

    @Test
    void shouldBeAbleToFindMealByExactPrice() {

        //given
        Meal meal = new Meal(10, "Pizza");
        mealRepository.add(meal);

        //when
        List<Meal> results = mealRepository.findByPrice(10, SearchType.EXACT_PRICE);

        assertThat(results, hasSize(1));
    }

    @Test
    void shouldBeAbleToFindMealByLessPrice() {

        //given
        Meal meal = new Meal(10, "Pizza");
        mealRepository.add(meal);

        //when
        List<Meal> results = mealRepository.findByPrice(12, SearchType.LESS_THAN);

        assertThat(results, hasSize(1));
    }

    @Test
    void shouldBeAbleToFindMealByGreaterPrice() {

        //given
        Meal meal = new Meal(10, "Pizza");
        mealRepository.add(meal);

        //when
        List<Meal> results = mealRepository.findByPrice(7, SearchType.GREATER_THAN);

        assertThat(results, hasSize(1));
    }

    @Test
    void shouldBeAbleToFindMealByExactNameAndExactPrice() {

        //given
        Meal meal = new Meal(12, "Pizza");
        Meal meal2 = new Meal(24, "Hamburger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("Pizza", true, 12, SearchType.EXACT_PRICE);

        //then
        assertThat(results, hasSize(1));
        assertThat(results.get(0), sameInstance(meal));
    }

    @Test
    void shouldBeAbleToFindMealByExactNameAndLessPrice() {

        //given
        Meal meal = new Meal(12, "Pizza");
        Meal meal2 = new Meal(24, "Hamburger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("Pizza", true, 13, SearchType.LESS_THAN);

        //then
        assertThat(results, hasSize(1));
        assertThat(results.get(0), sameInstance(meal));
    }

    @Test
    void shouldBeAbleToFindMealByExactNameAndGreaterPrice() {

        //given
        Meal meal = new Meal(12, "Pizza");
        Meal meal2 = new Meal(24, "Hamburger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("Pizza", true, 10, SearchType.GREATER_THAN);

        //then
        assertThat(results, hasSize(1));
        assertThat(results.get(0), sameInstance(meal));
    }

    @Test
    void shouldBeAbleToFindMealByFirstLetterAndLessPrice() {

        //given
        Meal meal = new Meal(12, "Pizza");
        Meal meal2 = new Meal(24, "Hamburger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("P", false, 13, SearchType.LESS_THAN);

        //then
        assertThat(results, hasSize(1));
        assertThat(results.get(0), sameInstance(meal));
    }

    @Test
    void shouldBeAbleToFindMealByFirstLetterAndExactPrice() {

        //given
        Meal meal = new Meal(12, "Pizza");
        Meal meal2 = new Meal(24, "Hamburger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("P", false, 12, SearchType.EXACT_PRICE);

        //then
        assertThat(results, hasSize(1));
        assertThat(results.get(0), sameInstance(meal));
    }

    @Test
    void shouldBeAbleToFindMealByFirstLetterAndGreaterPrice() {

        //given
        Meal meal = new Meal(12, "Pizza");
        Meal meal2 = new Meal(24, "Hamburger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("P", false, 10, SearchType.GREATER_THAN);

        //then
        assertThat(results, hasSize(1));
        assertThat(results.get(0), sameInstance(meal));
    }
}
