package lotto.domain;

import static lotto.handler.ErrorHandler.INVALID_AMOUNT;
import static lotto.handler.ErrorHandler.INVALID_NEGATIVE_INPUT;

public class Money {

    private final int purchaseAmount;

    public Money(int purchaseAmount) {
        validate(purchaseAmount);

        this.purchaseAmount = purchaseAmount;
    }

    private void validate(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw INVALID_NEGATIVE_INPUT.getException();
        } else if (purchaseAmount % 1000 != 0) {
            throw INVALID_AMOUNT.getException();
        }
    }

    public int getCount() {
        return purchaseAmount / 1000;
    }
}
