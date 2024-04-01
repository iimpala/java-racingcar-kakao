package racingcar;

import racingcar.domain.DefaultRacingGameRule;
import racingcar.domain.RacingGame;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.RacingGameUI;

public class RacingGameApplication {

	public static void main(String[] args) {
		RacingGame game = new RacingGame(
			new RacingGameUI(),
			new DefaultRacingGameRule(new RandomNumberGenerator())
		);

		game.play();
	}
}
