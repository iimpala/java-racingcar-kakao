package racingcar;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RacingGameTest {
	RacingGame racingGame;
	List<Car> cars;
	@BeforeEach
	void setUp() {
		cars = new ArrayList<>();
		cars.add(new Car("car1", 0));
		cars.add(new Car("car2", 0));
		cars.add(new Car("car3", 0));
	}

	@Test
	@DisplayName("자동차들 중 가장 많이 이동한 자동차가 우승자가 된다.")
	public void findWinnerTest(){
		Car winner = new Car("winner", 3);
		cars.add(winner);
		racingGame = new RacingGame(5, cars);
		Assertions.assertThat(racingGame.findWinner()).contains(winner);
	}

	@Test
	@DisplayName("복수의 우승자가 발생할 수 있다.")
	public void findWinnersTest() {
		Car winner1 = new Car("winner1", 3);
		cars.add(winner1);
		Car winner2 = new Car("winner2", 3);
		cars.add(winner2);
		racingGame = new RacingGame(5, cars);
		Assertions.assertThat(racingGame.findWinner()).contains(winner1, winner2);
	}
}
