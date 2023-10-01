package lotto.domain;

import lotto.handler.InputHandler;
import lotto.view.ConsoleInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoJudgeTest {

    Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    WinLottoWithBonusNumber winLottoWithBonusNumber = new WinLottoWithBonusNumber(lotto.sortLottoNumbers(), 7);

    private static Stream<Arguments> generateBasicLotto() {
        return Stream.of(
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 6, "SIX"),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 7)), 5,  "BONUS"),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 8)), 5,  "FIVE"),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 8, 7)), 4,  "FOUR"),
                Arguments.of(new Lotto(List.of(1, 2, 3, 9, 8, 7)), 3,  "THREE"),
                Arguments.of(new Lotto(List.of(1, 2, 10, 9, 8, 7)), 2,  "NOPE")
        );
    }

//    @CsvSource(value = {"SIX:1,2,3,4,5,6", "BONUS:1,2,3,4,5,7", "FIVE:1,2,3,4,5,8",
//            "FOUR:1,2,3,4,8,7", "THREE:1,2,3,9,8,7", "NOPE:1,2,10,9,8,7"}, delimiter = ':')
    @DisplayName("Handler 객체가 정상적으로 반환된다.")
    @ParameterizedTest(name = "[{index}] input {0} " )
    @MethodSource("generateBasicLotto")
    void createLottoHandler(Lotto lotto, int count, String result) {
        LottoJudge lottoJudge = new LottoJudge(lotto.sortLottoNumbers(), winLottoWithBonusNumber);

        assertThat(lottoJudge.matchLottoHandler(count).toString()).isEqualTo(result);
    }
}
