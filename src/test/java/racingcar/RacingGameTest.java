package racingcar;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.*;
import racingcar.domain.dto.CarDto;
import racingcar.util.RandomNumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGameTest {

    private Cars cars;

    @BeforeEach
    void setUp() {
        cars = new Cars(List.of(new Car("car1"), new Car("car2")));
    }

    @Test
    @DisplayName("라운드가 끝나지 않았으면 false")
    void isFinishFalse() {
        //given
        RacingGameRule rule = new DefaultRacingGameRule(new RandomNumberGenerator());
        int rounds = 3;
        RacingGame game = new RacingGame(cars, rounds, rule);

        //when
        game.playRound();

        //then
        Assertions.assertThat(game.isFinish()).isFalse();
    }

    @Test
    @DisplayName("라운드가 끝났으면 true")
    void isFinishTrue() {
        //given
        RacingGameRule rule = new DefaultRacingGameRule(new RandomNumberGenerator());
        int rounds = 1;
        RacingGame game = new RacingGame(cars, rounds, rule);

        //when
        game.playRound();

        //then
        Assertions.assertThat(game.isFinish()).isTrue();
    }

    @Test
    @DisplayName("라운드가 끝나면 자동차가 전진한다")
    void playRound() {
        //given
        RacingGame game = new RacingGame(cars, 3, () -> true);
        CarDto carDto = game.playRound().get(0);

        //when
        CarDto result = game.playRound().get(0);

        //then
        Assertions.assertThat(result.getPosition()).isEqualTo(carDto.getPosition() + 1);
    }

    @Test
    @DisplayName("가장 멀리 간 자동차가 우승자가 된다")
    void findWinner() {
        //given
        cars = new Cars(List.of(new Car("car1", 1), new Car("car2", 2)));
        RacingGame game = new RacingGame(cars, 1, () -> true);

        //when
        List<CarDto> winners = game.findWinner();

        //then
        List<String> winnerNames = winners.stream()
                .map(CarDto::getName)
                .collect(Collectors.toList());
        Assertions.assertThat(winnerNames).containsExactly("car2");

    }


}
