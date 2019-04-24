package pl.devfoundry.testing.homework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UnitServiceTest {

    @InjectMocks
    private UnitService unitService;

    @Mock
    private UnitRepository unitRepository;

    @Mock
    private CargoRepository cargoRepository;

    @Test
    void addedCargoShouldBeLoadedOnUnit() {

        //given
        Unit unit = new Unit(new Coordinates(0, 0), 10, 10);
        Cargo cargo = new Cargo("package", 5);

        given(cargoRepository.findCargoByName("package")).willReturn(Optional.of(cargo));

        //when
        unitService.addCargoByName(unit, "package");

        //then
        then(cargoRepository).should().findCargoByName("package");
        assertThat(unit.getLoad(), is(5));
        assertThat(unit.getCargo().get(0), is(sameInstance(cargo)));
    }

    @Test
    void nullCargoShouldThrowException() {

        //given
        Unit unit = new Unit(new Coordinates(0, 0), 10, 10);

        given(cargoRepository.findCargoByName("cargoName")).willReturn(Optional.empty());

        //when
        //then
        assertThrows(NoSuchElementException.class, () -> unitService.addCargoByName(unit, "cargoName"));
        assertThat(unit.getCargo(), hasSize(0));
    }

    @Test
    void shouldReturnExceptionIfUnitNotFound() {

        given(unitRepository.getUnitByCoordinates(new Coordinates(0, 0))).willReturn(null);

        assertThrows(NoSuchElementException.class, () -> unitService.getUnitOn(new Coordinates(0, 0)));
    }

    @Test
    void shouldReturnUnitByCoordinates() {

        Unit unit = new Unit(new Coordinates(0, 0), 10, 10);

        given(unitRepository.getUnitByCoordinates(new Coordinates(0, 0))).willReturn(unit);

        Unit result = unitService.getUnitOn(new Coordinates(0, 0));

        assertThat(result, sameInstance(unit));
    }
}