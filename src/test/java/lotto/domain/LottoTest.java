package lotto.domain;

import lotto.domain.Lotto;
import lotto.handler.ErrorHandler;
import lotto.handler.InputHandler;
import lotto.view.ConsoleInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoTest {

    private static Stream<Arguments> generateBasicLotto() {
        return Stream.of(
                Arguments.of(new Lotto(List.of(1, 20, 10, 45, 40, 30)), List.of(1, 10, 20, 30, 40, 45)),
                Arguments.of(new Lotto(List.of(1, 3, 6, 5, 11, 10)), List.of(1, 3, 5, 6, 10, 11))
        );
    }

    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 1~45 이내의 숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest(name = "[{index}] input {0} ")
    @ValueSource(strings = {"0,2,3,4,5,6", "-1,10,20,30,40,45", "40,41,42,43,44,46"})
    void createLottoByOverNumber(String inputString) {
        List<Integer> inputList = Arrays.stream(inputString.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> new Lotto(inputList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorHandler.INVALID_RANGE.getException().getMessage());
    }

    @DisplayName("로또 번호가 오름차순으로 정상적으로 반환된다.")
    @ParameterizedTest(name = "[{index}] Input: {0} ")
    @MethodSource("generateBasicLotto")
    void createLotto(Lotto lotto, List<Integer> expected) {
        assertThat(lotto.sortLottoNumbers()).isEqualTo(expected);
    }

//    @DisplayName("로또 번호가 오름차순으로 정상적으로 반환된다.")
//    @ParameterizedTest(name = "[{index}] input {0} ")
//    @ValueSource(strings = {"1,10,20,30,40,45"})
//    void createLotto(String inputString) {
//        List<Integer> inputList = Arrays.stream(inputString.split(","))
//                .map(Integer::parseInt)
//                .collect(Collectors.toList());
//
//        Lotto lotto = new Lotto(inputList);
//        assertThat(lotto.sortLottoNumbers()).isEqualTo(List.of(1, 10, 20, 30, 40, 45));
//    }
}
