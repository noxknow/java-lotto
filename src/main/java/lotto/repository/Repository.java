package lotto.repository;

import lotto.domain.Lotto;
import lotto.handler.LottoHandler;

import java.util.List;

public interface Repository {

    void saveBuyLottoLists(List<Lotto> buyLottoLists);
    void showBuyLottoLists();

    void saveWinningLottoList(LottoHandler lottoHandler);
    void showWinningLottoList();
    void showProfit(int count);
}
