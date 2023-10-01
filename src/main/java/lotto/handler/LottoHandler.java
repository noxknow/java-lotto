package lotto.handler;

import java.util.Arrays;
import java.util.Optional;

public enum LottoHandler {

    THREE(3, 5000, ""),
    FOUR(4, 50000, ""),
    FIVE(5, 1500000, ""),
    BONUS(5, 30000000, ", 보너스 볼 일치"),
    SIX(6, 2000000000, ""),
    NOPE(0, 0, "");

    private final int count;
    private final int prize;
    private final String message;

    LottoHandler(int count, int prize, String message) {
        this.count = count;
        this.prize = prize;
        this.message = message;
    }

    public static LottoHandler getLottoHandler(long matchingCount) {
        for (LottoHandler lottoHandler : LottoHandler.values()) {
            if (lottoHandler.getCount() == matchingCount) {
                return lottoHandler;
            }
        }

        return NOPE;
    }

    public int getCount() {
        return count;
    }

    public int getPrize() {
        return prize;
    }

    public String getMessage() {
        return message;
    }
}
