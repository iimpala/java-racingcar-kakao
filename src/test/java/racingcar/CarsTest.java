package racingcar;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;
import racingcar.domain.Cars;

import java.util.List;

public class CarsTest {

    @Test
    @DisplayName("가장 멀리 간 자동차가 우승자가 된다")
    void findWinner() {
        //given
        Car car1 = new Car("car1", 1);
        Car car2 = new Car("car2", 2);
        Cars cars = new Cars(List.of(car1 ,car2));

        //when
        List<Car> winners = cars.findWinner();

        //then
        Assertions.assertThat(winners).containsExactly(car2);
    }
}
