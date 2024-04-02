package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;

import racingcar.domain.dto.CarDto;

public class RacingGame {
	private final RacingGameRule rule;
	private final Cars cars;
	private final int rounds;
	private int currentRounds;

	public RacingGame(Cars cars, int rounds, RacingGameRule rule) {
		this.cars = cars;
		this.rounds = rounds;
		this.rule = rule;
		this.currentRounds = 0;
	}
	public boolean isFinish() {
		return currentRounds >= rounds;
	}

	public List<CarDto> playRound() {
		List<Car> results = cars.race(rule);
		currentRounds += 1;

		return results.stream()
				.map(Car::toDto)
				.collect(Collectors.toList());
	}

	public List<CarDto> findWinner() {
		List<Car> winner = cars.findWinner();
		return winner.stream()
				.map(Car::toDto)
				.collect(Collectors.toList());
	}
}
