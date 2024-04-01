package racingcar.domain;

import racingcar.util.NumberGenerator;

public class DefaultRacingGameRule implements RacingGameRule {
	private static final int PROCEED_THRESHOLD = 3;
	private final NumberGenerator numberGenerator;
	public DefaultRacingGameRule(NumberGenerator numberGenerator) {
		this.numberGenerator = numberGenerator;
	}

	@Override
	public boolean decideProceed() {
		return numberGenerator.generate() > PROCEED_THRESHOLD;
	}
}
