package lotto.repository;

import lotto.domain.Lotto;
import lotto.handler.LottoHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.handler.LottoHandler.NOPE;

public class ListRepository implements Repository {

    List<Lotto> buyLottoResult = new ArrayList<>();
    Map<LottoHandler, Integer> winLottoResult = new HashMap<>();

    @Override
    public void saveBuyLottoLists(List<Lotto> buyLottoLists) {
        buyLottoResult.addAll(buyLottoLists);
    }

    @Override
    public void showBuyLottoLists() {
        buyLottoResult.stream()
                .map(Lotto::sortLottoNumbers)
                .forEach(System.out::println);
    }

    @Override
    public void saveWinningLottoList(LottoHandler lottoHandler) {
        winLottoResult.put(lottoHandler, winLottoResult.getOrDefault(lottoHandler, 0) + 1);

//        Integer currentCount = winLottoResult.get(lottoHandler); 다른 방법으로 푸는 경우
//        if (currentCount == null) {
//            winLottoResult.put(lottoHandler, 1);
//        } else {
//            winLottoResult.put(lottoHandler, currentCount + 1);
//        }
    }

    @Override
    public void showWinningLottoList() {
        DecimalFormat prizeFormat = new DecimalFormat("###,###");

        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");

        for (LottoHandler lottoHandler : LottoHandler.values()) {
            if (lottoHandler == NOPE) {
                continue;
            }

            System.out.printf("%d개 일치%s (%s원) - %d개\n", lottoHandler.getCount(), lottoHandler.getMessage(),
                    prizeFormat.format(lottoHandler.getPrize()), winLottoResult.getOrDefault(lottoHandler, 0));
        }
    }

    @Override
    public void showProfit(int count) {
        long totalPrize = 0L;

        for (LottoHandler lottoHandler : LottoHandler.values()) {
            if (lottoHandler == NOPE) {
                continue;
            }

            totalPrize += (long) lottoHandler.getPrize() * winLottoResult.getOrDefault(lottoHandler, 0);
        }

        long totalPurchaseAmount = count * 1000;

        float profit = (float) totalPrize / totalPurchaseAmount * 100;

        System.out.printf("총 수익률은 %.1f%%입니다.", profit);
    }
}
