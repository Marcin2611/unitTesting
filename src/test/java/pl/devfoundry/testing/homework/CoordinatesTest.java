package pl.devfoundry.testing.homework;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoordinatesTest {


    @Test
    void positionShouldBeMoreThanZero() {

        Coordinates coordinates = new Coordinates(5, 3);

        assertEquals(5, coordinates.getX());
        assertEquals(3, coordinates.getY());

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Coordinates(-4, 5)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Coordinates(10, -5))
        );
    }

    @Test
    void positionShouldBeLessThanHundred() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Coordinates(120, 5)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Coordinates(10, 150))
        );
    }

    @Test
    void copy() {

        Coordinates coordinates = new Coordinates(5, 3);

        Coordinates copyCoordinates = Coordinates.copy(coordinates, 5, 7);

        assertThat(copyCoordinates, notNullValue());
        assertThat(copyCoordinates, not(sameInstance(coordinates)));
        assertThat(copyCoordinates.getX(), is(10));
        assertThat(copyCoordinates.getY(), is(10));
    }
}