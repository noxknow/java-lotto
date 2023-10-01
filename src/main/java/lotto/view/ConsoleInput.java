package lotto.view;

import lotto.handler.InputHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static lotto.handler.ErrorHandler.INCONVERTIBLE_TYPE;

public class ConsoleInput implements InputHandler {

    @Override
    public int getPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return stringToInt(readLine());
    }

    @Override
    public List<Integer> getWinningLotto() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");

        return stringToList(readLine());
    }

    @Override
    public int getBonusNumber() {
        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");

        return stringToInt(readLine());
    }

    private List<Integer> stringToList(String inputs) {
        try {
            return Arrays.stream(inputs.split(","))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw INCONVERTIBLE_TYPE.getException();
        }
    }

    private int stringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            throw INCONVERTIBLE_TYPE.getException();
        }
    }
}
