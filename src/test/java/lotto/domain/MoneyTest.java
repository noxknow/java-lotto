package lotto.domain;

import lotto.handler.ErrorHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

    @DisplayName("금액이 0이하인 경우 예외가 발생한다.")
    @ParameterizedTest(name = "[{index}] input {0} ")
    @ValueSource(ints = {-1000, -2000})
    void createMoneyWithNegativeAmount(int purchaseAmount) {
        assertThatThrownBy(() -> new Money(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorHandler.INVALID_NEGATIVE_INPUT.getException().getMessage());
    }

    @DisplayName("금액이 1000으로 나누어 떨어지지 않는 경우 예외가 발생한다.")
    @ParameterizedTest(name = "[{index}] input {0} ")
    @ValueSource(ints = {259, 1258, 3859})
    void createMoneyWithNotDivisibleBy1000 (int purchaseAmount) {
        assertThatThrownBy(() -> new Money(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorHandler.INVALID_AMOUNT.getException().getMessage());
    }
}
