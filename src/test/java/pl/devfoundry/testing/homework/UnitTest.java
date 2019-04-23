package pl.devfoundry.testing.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UnitTest {

    private static Coordinates coordinates;
    private static Unit unit;

    @BeforeEach
    void setUp() {
        coordinates = new Coordinates(10, 10);
        unit = new Unit(coordinates, 40, 100);
    }

    @Test
    void fuelHaveToBeHigherThanCoordinatesSum() {
        assertThrows(IllegalStateException.class, () -> unit.move(25, 25));
    }

    @Test
    void coordinatesShouldBeChangeAfterMove() {
        Coordinates coordinatesAfterMove = unit.move(10, 10);

        assertEquals(20, coordinatesAfterMove.getX());
        assertEquals(20, coordinatesAfterMove.getY());
    }

    @Test
    void fuelShouldBeDecreasedBySumOfCoordinates() {
        Coordinates coordinates = unit.move(15, 10);

        assertEquals(15, unit.getFuel());
    }

    @Test
    void fuelAfterTankUpShouldBeIncreased() {

        unit.tankUp();

        assertThat(unit.getFuel(), is(40));
    }

    @Test
    void cargoCannotExceedMaxWeightLimit() {

        Cargo cargo = new Cargo("Cargo 1", 50);
        Cargo cargo1 = new Cargo("Cargo 2", 60);

        unit.loadCargo(cargo);

        assertThrows(IllegalStateException.class, () -> unit.loadCargo(cargo1));
    }

    @Test
    void unloadCargoShouldDecreaseWeightOfCargos() {
        Cargo cargo = new Cargo("Cargo 1", 50);
        Cargo cargo1 = new Cargo("Cargo 2", 40);

        unit.loadCargo(cargo);
        unit.loadCargo(cargo1);

        assertThat(unit.getLoad(), is(90));

        unit.unloadCargo(cargo);

        assertThat(unit.getLoad(), equalTo(40));
    }

    @Test
    void unloadAllCargosShouldClearWeight() {
        Cargo cargo = new Cargo("Cargo 1", 50);
        Cargo cargo1 = new Cargo("Cargo 2", 40);

        unit.loadCargo(cargo);
        unit.loadCargo(cargo1);
        unit.unloadAllCargo();

        assertThat(unit.getLoad(), equalTo(0));
    }
}