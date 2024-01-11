public class AggressiveInvestor extends User {
    public AggressiveInvestor(StockMarket stockMarket, String name, double investment_budget) {
        super(stockMarket, name, investment_budget, 10, "Aggressive Investor");
    }

    @Override
    public Integer shouldBuyStock(Stock stock) {
        final double BUDGET_LIMIT_MULTIPLIER = 0.1;
        final double CHANGE_THRESHOLD = 0.0;
        final double NEGATIVE_CHANGE_THRESHOLD = -2.0;

        int quantity = 0;

        if (NEGATIVE_CHANGE_THRESHOLD < stock.getPercentChange() && stock.getPercentChange() < CHANGE_THRESHOLD) {
            quantity = (int) ((investment_budget * BUDGET_LIMIT_MULTIPLIER) / stock.getPrice());

            if (quantity > stock.getVolume()) {
                quantity = (int) stock.getVolume();
            }
        }

        printBuyInfo(name, quantity, stock);
        return quantity;
    }

    @Override
    public Integer shouldSellStock(Stock stock) {
        final double BUDGET_LIMIT_MULTIPLIER = 0.12;
        final double CHANGE_THRESHOLD = 0.0;
        final double NEGATIVE_CHANGE_THRESHOLD = -2.0;

        int quantity = 0;
        Integer stockOfUser = investment_portfolio.get(stock.getSymbol());

        if ((stockOfUser != null) && (stock.getPercentChange() > CHANGE_THRESHOLD
                || stock.getPercentChange() < NEGATIVE_CHANGE_THRESHOLD)) {
            quantity = (int) ((investment_budget * BUDGET_LIMIT_MULTIPLIER) / stock.getPrice());

            if (quantity > stockOfUser || stock.getPercentChange() < NEGATIVE_CHANGE_THRESHOLD) {
                quantity = stockOfUser;
            }
        }

        printSellInfo(name, quantity, stock);
        return quantity;
    }

}
