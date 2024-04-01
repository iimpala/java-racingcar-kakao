package racingcar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import racingcar.dto.CarDto;

import static racingcar.Car.MAX_CAR_NAME_LENGTH;

public class RacingGame {

	private final RacingGameUI ui;
	private final RacingGameRule rule;
	private final List<Car> cars = new ArrayList<>();
	private int rounds;

	public RacingGame(RacingGameUI ui, RacingGameRule rule) {
		this.ui = ui;
		this.rule = rule;
	}

	public void play() {
		initialize();

		ui.printResultHeader();
		for (int i = 0; i < rounds; i++) {
			playRound();
			List<CarDto> carDtos = cars.stream()
					.map(Car::toDto)
					.collect(Collectors.toList());
			ui.printCarStates(carDtos);
		}

		ui.printWinners(findWinner());
	}

	private void initialize() {
		initCars();
		initRounds();
	}

	private void initCars() {
		boolean isValid = false;
		List<String> carNames = new ArrayList<>();
		while (!isValid) {
			String carNameInput = ui.getCarNames();
			carNames = Arrays.stream(carNameInput.split(","))
					.map(String::trim)
					.collect(Collectors.toList());
			isValid = isValidCarNames(carNames);
		}

		for (String name : carNames) {
			cars.add(new Car(name));
		}
	}

	private void initRounds() {
		boolean validationPass = false;
		String input = "";
		while(!validationPass) {
			input = ui.getRounds();
			validationPass = isValidRounds(input);
		}
		rounds = Integer.parseInt(input);
	}

	private boolean isValidCarNames(List<String> carNames) {
		if (carNames.isEmpty()) {
			ui.printError(ErrorType.EMPTY_CAR_NAME);
			return false;
		}
		
		return !(isEmptyCarNames(carNames) || isLongCarNames(carNames) || isDuplicateCarNames(carNames));
	}

	private boolean isValidRounds(String input) {
		String round = input.trim();

		return !(isEmptyRounds(round) || hasNonNumericalCharacter(round) || isLongRounds(round));
	}

	private boolean isEmptyCarNames(List<String> carNames) {
		List<String> emptyCarNames = carNames.stream()
				.filter(String::isEmpty)
				.collect(Collectors.toList());

		if (!emptyCarNames.isEmpty()) {
			ui.printError(ErrorType.EMPTY_CAR_NAME);
			return true;
		}

		return false;
	}

	private boolean isLongCarNames(List<String> carNames) {
		List<String> longCarNames = carNames.stream()
				.filter(name -> name.length() > MAX_CAR_NAME_LENGTH)
				.collect(Collectors.toList());
		
		if (!longCarNames.isEmpty()) {
			ui.printError(ErrorType.TOO_LONG_CAR_NAME);
			return true;
		}
		
		return false;
	}

	private boolean isDuplicateCarNames(List<String> carNameList) {
		Set<String> uniqueName = new HashSet<>(carNameList);
		
		if (uniqueName.size() != carNameList.size()) {
			ui.printError(ErrorType.DUPLICATED_CAR_NAME);
			return true;
		}
		
		return false;
	}

	private boolean isEmptyRounds(String round) {
		if (round.isEmpty()) {
			ui.printError(ErrorType.EMPTY_ROUND);
			return true;
		}
		return false;
	}

	private boolean hasNonNumericalCharacter(String round) {
		List<String> nonNumericalCharacters = Arrays.stream(round.split(""))
				.filter(s -> !Character.isDigit(s.charAt(0)))
				.collect(Collectors.toList());

		if (!nonNumericalCharacters.isEmpty()){
			ui.printError(ErrorType.NON_NUMERICAL_ROUND);
			return true;
		}

		return false;
	}

	private boolean isLongRounds(String round) {
		if (round.length() > 5) {
			ui.printError(ErrorType.TOO_LONG_ROUND);
			return true;
		}

		return false;
	}

	private void playRound() {
		cars.stream()
				.filter(car -> rule.decideProceed())
				.forEach(Car::proceed);
	}

	private List<CarDto> findWinner() {
		Car winner = Collections.max(cars, Car::comparePosition);

		return cars.stream()
			.filter(car -> winner.comparePosition(car) == 0)
			.map(Car::toDto)
				.collect(Collectors.toList());
	}
}
