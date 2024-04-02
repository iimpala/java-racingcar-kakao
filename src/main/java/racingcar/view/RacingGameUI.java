package racingcar.view;

import racingcar.domain.dto.CarDto;

import java.util.*;
import java.util.stream.Collectors;

public class RacingGameUI {

	private static final int MAX_CAR_NAME_LENGTH = 5;
	private static final int MAX_ROUND_STRING_LENGTH = 5;
	private static final String EMPTY_CAR_NAME = "자동차 이름은 공백일 수 없습니다.";
	private static final String TOO_LONG_CAR_NAME = "자동차 이름은 5글자를 넘을 수 없습니다.";
	private static final String DUPLICATED_CAR_NAME = "자동차 이름은 중복될 수 없습니다.";
	private static final String EMPTY_ROUND = "라운드는 공백일 수 없습니다.";
	private static final String TOO_LONG_ROUND = "라운드는 여섯 글자를 넘을 수 없습니다.";
	private static final String NON_NUMERICAL_ROUND = "라운드는 숫자로만 이루어져야 합니다.";

	private final Scanner scanner;

	public RacingGameUI() {
		this.scanner = new Scanner(System.in);
	}

	public List<String> getCarNames() {
		boolean isValid = false;
		List<String> carNames = new ArrayList<>();
		while (!isValid) {
			System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
			String input = scanner.nextLine();
			carNames = Arrays.stream(input.split(","))
					.map(String::trim)
					.collect(Collectors.toList());
			isValid = isValidCarNames(carNames);
		}
		return carNames;
	}

	public int getRounds() {
		boolean isValid = false;
		String rounds = "";
		while (!isValid) {
			System.out.println("시도할 회수는 몇회인가요?");
			rounds = scanner.nextLine();
			isValid = isValidRounds(rounds);
		}
		return Integer.parseInt(rounds);
	}

	public void printResultHeader() {
		System.out.println("실행 결과");
	}

	public void printCar(List<CarDto> carDtos) {
		carDtos.forEach(carDto -> System.out.printf("%s : %s\n", carDto.getName(), "-".repeat(Math.max(0, carDto.getPosition()))));
		System.out.println();
	}

	public void printWinners(List<CarDto> winners) {
		System.out.print(winners.get(0).getName());
		for (int i = 1; i < winners.size(); i++) {
			System.out.printf(", %s", winners.get(i).getName());
		}
		System.out.println("가 최종 우승했습니다.");

	}

	public void printError(String errorMsg) {
		System.out.println("[ERROR] "+ errorMsg);
	}



	// ui level validation
	private boolean isValidCarNames(List<String> carNames) {
		if (carNames.isEmpty()) {
			printError(EMPTY_CAR_NAME);
			return false;
		}

		return !(isEmptyCarNames(carNames) || isLongCarNames(carNames) || isDuplicateCarNames(carNames));
	}

	private boolean isEmptyCarNames(List<String> carNames) {
		List<String> emptyCarNames = carNames.stream()
				.filter(String::isEmpty)
				.collect(Collectors.toList());

		if (!emptyCarNames.isEmpty()) {
			printError(EMPTY_CAR_NAME);
			return true;
		}

		return false;
	}

	private boolean isLongCarNames(List<String> carNames) {
		List<String> longCarNames = carNames.stream()
				.filter(name -> name.length() > MAX_CAR_NAME_LENGTH)
				.collect(Collectors.toList());

		if (!longCarNames.isEmpty()) {
			printError(TOO_LONG_CAR_NAME);
			return true;
		}

		return false;
	}

	private boolean isDuplicateCarNames(List<String> carNameList) {
		Set<String> uniqueName = new HashSet<>(carNameList);

		if (uniqueName.size() != carNameList.size()) {
			printError(DUPLICATED_CAR_NAME);
			return true;
		}

		return false;
	}

	private boolean isValidRounds(String input) {
		String round = input.trim();

		return !(isEmptyRounds(round) || hasNonNumericalCharacter(round) || isLongRounds(round));
	}

	private boolean isEmptyRounds(String round) {
		if (round.isEmpty()) {
			printError(EMPTY_ROUND);
			return true;
		}
		return false;
	}

	private boolean hasNonNumericalCharacter(String round) {
		List<String> nonNumericalCharacters = Arrays.stream(round.split(""))
				.filter(s -> !Character.isDigit(s.charAt(0)))
				.collect(Collectors.toList());

		if (!nonNumericalCharacters.isEmpty()){
			printError(NON_NUMERICAL_ROUND);
			return true;
		}

		return false;
	}

	private boolean isLongRounds(String round) {
		if (round.length() > MAX_ROUND_STRING_LENGTH) {
			printError(TOO_LONG_ROUND);
			return true;
		}

		return false;
	}
}
