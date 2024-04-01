package racingcar;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.DefaultRacingGameRule;

@DisplayName("전진 여부를 판단하는 클래스 테스트")
public class DefaultRacingGameRuleTest {

	@Test
	@DisplayName("4이상의 값에 대해 전진 판정을 내린다.")
	void askProceedTrueTest() {
		DefaultRacingGameRule rule = new DefaultRacingGameRule(() -> 4);
		boolean result = rule.decideProceed();
		Assertions.assertThat(result).isTrue();
	}

	@Test
	@DisplayName("3이하의 값에 대해 전진 불가 판정을 내린다.")
	void askProceedFalseTest() {
		DefaultRacingGameRule rule = new DefaultRacingGameRule(() -> 3);
		boolean result = rule.decideProceed();
		Assertions.assertThat(result).isFalse();
	}

}
