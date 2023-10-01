package lotto.handler;

import lotto.domain.Money;
import lotto.repository.Repository;

public interface OutputHandler {

    public void printBuyLottoList(Money money, Repository repository);
    public void printWinningLottoList(Repository repository);
    public void printYield(Money money, Repository repository);
}
