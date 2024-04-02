package racingcar.controller;

import racingcar.domain.*;
import racingcar.domain.dto.CarDto;
import racingcar.view.RacingGameUI;

import java.util.*;
import java.util.stream.Collectors;


public class RacingGameController {
    private final RacingGameUI ui;
    private final RacingGameRule rule;

    public RacingGameController(RacingGameUI ui, RacingGameRule rule) {
        this.ui = ui;
        this.rule = rule;
    }

    public void play() {
        List<String> carNames = ui.getCarNames();
        int rounds = ui.getRounds();

        List<Car> cars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
        RacingGame game = new RacingGame(new Cars(cars), rounds, rule);

        ui.printResultHeader();
        while (!game.isFinish()) {
            List<CarDto> carDtos = game.playRound();
            ui.printCar(carDtos);
        }

        List<CarDto> winners = game.findWinner();
        ui.printWinners(winners);
    }
}
