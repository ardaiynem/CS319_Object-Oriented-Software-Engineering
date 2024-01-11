public class ClosedState implements MarketState {
    private StockMarket stockMarket;

    public ClosedState(StockMarket stockMarket) {
        this.stockMarket = stockMarket;
    }

    @Override
    public boolean open() {
        stockMarket.setIsOpen(true);
        stockMarket.setMarketState(stockMarket.getOpenState());
        stockMarket.setTransactionAmount(0);
        stockMarket.setTransactionCost(0);

        System.out.println(
                "The stock market: " + stockMarket.getName() + " (" + stockMarket.getSymbol() + ") is now open.");
        return true;
    }

    @Override
    public boolean close() {
        return false;
    }

    @Override
    public double buyStock(Stock stock, int quantity, User buyer) {
        String result = announceResult(buyer.getName(), "sell", quantity, stock.getName(), stock.getSymbol(), "open");
        result += "Unable to process the transaction.";
        System.out.println(result);

        return -1;
    }

    @Override
    public double sellStock(Stock stock, int quantity, User seller) {
        String result = announceResult(seller.getName(), "sell", quantity, stock.getName(), stock.getSymbol(), "open");
        result += "Unable to process the transaction.";
        System.out.println(result);

        return -1;
    }

    private String announceResult(String actorName, String action, int quantity, String stockName, String stockSymbol,
            String marketState) {
        return actorName + " has made a transaction to " + action + " " + quantity + " shares of " + stockName
                + " (" + stockSymbol + ") while the stock market is " + marketState + ".\n";
    }

    @Override
    public String toString() {
        return "ClosedState";
    }
}
