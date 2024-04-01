package racingcar;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;

public class CarTest {

	@Test
	@DisplayName("자동차가 전진하면 위치가 1 증가한다.")
	void proceedTest() {
		Car car1 = new Car("car1");
		car1.proceed();
		Car car2 = new Car("car1", 1);
		Assertions.assertThat(car1).isEqualTo(car2);
	}

}
