public class BalancedInvestor extends User {
    public BalancedInvestor(StockMarket stockMarket, String name, double investment_budget) {
        super(stockMarket, name, investment_budget, 7, "Balanced Investor");
    }

    @Override
    public Integer shouldBuyStock(Stock stock) {
        final double BUDGET_LIMIT_MULTIPLIER = 0.08;
        final int PRICE_THRESHOLD_MULTIPLIER = 20;
        final double CHANGE_THRESHOLD = 0.0;

        int quantity = 0;

        if (stock.getPercentChange() < CHANGE_THRESHOLD
                && investment_budget > stock.getPrice() * PRICE_THRESHOLD_MULTIPLIER) {
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
        final double BUDGET_LIMIT_MULTIPLIER = 0.08;
        final int PRICE_THRESHOLD_MULTIPLIER = 1000;
        final double CHANGE_THRESHOLD = 0.0;

        int quantity = 0;
        Integer stockOfUser = investment_portfolio.get(stock.getSymbol());

        if ((stockOfUser != null) && (stock.getPercentChange() > CHANGE_THRESHOLD
                && investment_budget < stock.getPrice() * PRICE_THRESHOLD_MULTIPLIER)) {
            quantity = (int) ((investment_budget * BUDGET_LIMIT_MULTIPLIER) / stock.getPrice());

            if (quantity > stockOfUser) {
                quantity = stockOfUser;
            }
        }

        printSellInfo(name, quantity, stock);
        return quantity;
    }

}
