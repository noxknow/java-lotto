package lotto.domain;

import lotto.handler.LottoHandler;

import java.util.List;

public class LottoJudge {

    private final List<Integer> sortedNumbers;
    private final WinLottoWithBonusNumber winLottoWithBonusNumber;

    public LottoJudge(List<Integer> sortedNumbers, WinLottoWithBonusNumber winLottoWithBonusNumber) {
        this.sortedNumbers = sortedNumbers;
        this.winLottoWithBonusNumber = winLottoWithBonusNumber;
    }

    public LottoHandler matchLottoHandler(int count) {
        if (count == 5 && sortedNumbers.contains(winLottoWithBonusNumber.getBonusNumber())) {
            return LottoHandler.BONUS;
        } else if (count == 5 && !sortedNumbers.contains(winLottoWithBonusNumber.getBonusNumber())) {
            return LottoHandler.FIVE;
        }

        LottoHandler lottoHandler = LottoHandler.getLottoHandler(count);

        return lottoHandler;
    }
}
