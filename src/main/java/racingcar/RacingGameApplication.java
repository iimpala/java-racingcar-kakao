package racingcar;

import racingcar.controller.RacingGameController;
import racingcar.domain.DefaultRacingGameRule;
import racingcar.domain.RacingGameRule;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.RacingGameUI;

public class RacingGameApplication {

	public static void main(String[] args) {
		RacingGameRule rule = new DefaultRacingGameRule(new RandomNumberGenerator());
		RacingGameUI ui = new RacingGameUI();
		RacingGameController controller = new RacingGameController(ui, rule);
		controller.play();
	}
}
