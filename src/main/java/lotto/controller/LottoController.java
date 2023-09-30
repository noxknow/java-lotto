package lotto.controller;

import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.repository.Repository;
import lotto.service.LottoService;
import lotto.domain.Money;
import lotto.handler.InputHandler;
import lotto.handler.OutputHandler;

import java.util.List;

public class LottoController {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final Repository repository;
    private final LottoService lottoService;

    public LottoController(InputHandler inputHandler, OutputHandler outputHandler, Repository repository,LottoService lottoService) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.repository = repository;
        this.lottoService = lottoService;
    }

    public void run() {
        Money money = inputMoney();

        List<Lotto> buyLottoLists = getBuyLottoList(money);

        BonusNumber bonusNumber = getBonusNumber();

        getStatistics(buyLottoLists);

        outputHandler.printYield(count, repository);
    }

    private Money inputMoney() {
        int purchaseAmount = inputHandler.getPurchaseAmount();

        return new Money(purchaseAmount);
    }

    private List<Lotto> getBuyLottoList(Money money) {
        List<Lotto> buyLottoLists = lottoService.getLottoNumbers(money, repository);
        outputHandler.printBuyLottoList(money, repository);

        return buyLottoLists;
    }

    private BonusNumber getBonusNumber() {
        Lotto winningLotto = new Lotto(inputHandler.getWinningLotto());
        int bonusNumber = inputHandler.getBonusNumber();

        return new BonusNumber(winningLotto.sortLottoNumbers(), bonusNumber);
    }

    private void getStatistics(List<Lotto> buyLottoLists) {
        Lotto winningLotto = new Lotto(inputHandler.getWinningLotto());
        int bonusNumber = inputHandler.getBonusNumber();
        lottoService.calculateMatchingCount(repository, buyLottoLists, winningLotto.sortLottoNumbers(), bonusNumber);

        outputHandler.printWinningLottoList(repository);
    }
}
