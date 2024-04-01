package racingcar;

import racingcar.dto.CarDto;

import java.util.List;
import java.util.Scanner;

public class RacingGameUI {

	private final Scanner scanner;

	public RacingGameUI() {
		this.scanner = new Scanner(System.in);
	}

	public String getCarNames() {
		System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
		return scanner.nextLine();
	}

	public String getRounds() {
		System.out.println("시도할 회수는 몇회인가요?");
		return scanner.nextLine();
	}

	public void printResultHeader() {
		System.out.println("실행 결과");
	}

	public void printCarStates(List<CarDto> carDtos) {
		carDtos.forEach(carDto -> {
			System.out.printf("%s : %s\n", carDto.getName(), "-".repeat(Math.max(0, carDto.getPosition())));
		});
		System.out.println();
	}

	public void printWinners(List<CarDto> winners) {
		System.out.print(winners.get(0).getName());
		for (int i = 1; i < winners.size(); i++) {
			System.out.printf(", %s", winners.get(i).getName());
		}
		System.out.println("가 최종 우승했습니다.");

	}

	public void printError(ErrorType errorType) {
		System.out.println("[ERROR] "+ errorType.getMessage());
	}
}
