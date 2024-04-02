package racingcar.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> findWinner() {
        Car winner = Collections.max(cars, Car::comparePosition);
        return cars.stream()
                .filter(car -> winner.comparePosition(car) == 0)
                .collect(Collectors.toList());
    }

    public List<Car> race(RacingGameRule rule) {
        cars.stream()
                .filter(car -> rule.decideProceed())
                .forEach(Car::proceed);

        return List.copyOf(cars);
    }
}
