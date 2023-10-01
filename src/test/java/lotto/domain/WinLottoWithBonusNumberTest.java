package lotto.domain;

import lotto.handler.ErrorHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WinLottoWithBonusNumberTest {

    Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

    @DisplayName("보너스 번호가 올바르게 출력 된다.")
    @ParameterizedTest(name = "[{index}] input {0} ")
    @ValueSource(ints = {7, 10, 25})
    void createBonusNumber(int bonusNumber) {
        WinLottoWithBonusNumber winLottoWithBonusNumber = new WinLottoWithBonusNumber(winningLotto.sortLottoNumbers(), bonusNumber);
        assertThat(winLottoWithBonusNumber.getBonusNumber()).isEqualTo(bonusNumber);
    }

    @DisplayName("당첨 번호와 보너스 번호 사이에 중복된 숫자가 있으면 예외가 발생한다.")
    @ParameterizedTest(name = "[{index}] input {0} ")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void createBonusNumberByDuplicatedNumber(int bonusNumber) {
        assertThatThrownBy(() -> new WinLottoWithBonusNumber(winningLotto.sortLottoNumbers(), bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorHandler.DUPLICATE_NUMBER.getException().getMessage());
    }

    @DisplayName("보너스 번호가 1~45 이내의 숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest(name = "[{index}] input {0} ")
    @ValueSource(ints = {-1, 0, 46})
    void createBonusNumberByOverNumber(int bonusNumber) {
        assertThatThrownBy(() -> new WinLottoWithBonusNumber(winningLotto.sortLottoNumbers(), bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorHandler.INVALID_RANGE.getException().getMessage());
    }
}
