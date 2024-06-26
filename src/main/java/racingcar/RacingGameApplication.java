package racingcar;

public class RacingGameApplication {

	public static void main(String[] args) {
		RacingGame game = new RacingGame(
			new RacingGameUI(),
			new DefaultRacingGameRule(new RandomNumberGenerator())
		);

		game.play();
	}
}
