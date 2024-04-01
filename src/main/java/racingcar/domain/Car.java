package racingcar.domain;

import java.util.Objects;

import racingcar.ErrorType;
import racingcar.domain.dto.CarDto;

public class Car {

	public static final int MAX_CAR_NAME_LENGTH = 5;
	private final String name;
	private int position;

	public Car(String name) {
		this(name, 0);
	}

	public Car(String name, int position) {
		validateCarName(name);
		this.name = name;
		this.position = position;
	}

	private void validateCarName(String name) {
		if (name.length() > MAX_CAR_NAME_LENGTH) {
			throw new RuntimeException(ErrorType.TOO_LONG_CAR_NAME.getMessage());
		}
	}

	public CarDto toDto() {
		return new CarDto(this.name, this.position);
	}

	public void proceed() {
		this.position += 1;
	}

	public int comparePosition(Car other) {
		return Integer.compare(this.position, other.position);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Car car = (Car)o;
		return position == car.position && Objects.equals(name, car.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, position);
	}

}
