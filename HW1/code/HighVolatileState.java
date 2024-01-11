public class HighVolatileState implements MarketState {
    private StockMarket stockMarket;
    private double TRANSACTION_FEE = 0.03;

    public HighVolatileState(StockMarket stockMarket) {
        this.stockMarket = stockMarket;
    }

    @Override
    public boolean open() {
        return false;
    }

    @Override
    public boolean close() {
        stockMarket.setIsOpen(false);
        stockMarket.setMarketState(stockMarket.getClosedState());

        System.out.println("The stock market: " + stockMarket.getName() + " (" + stockMarket.getSymbol() + ") is now closed.");
        return true;
    }

    @Override
    public double buyStock(Stock stock, int quantity, User buyer) {
        if (buyer.getTransactionDone() >= buyer.getTRANSACTION_LIMIT()) {
            System.out.println(buyer.name + " tried to make their " + (buyer.getTransactionDone() + 1)
                    + "th transaction of the day, however as an " + buyer.getInvestorType()
                    + " they are only allowed up to " + buyer.getTRANSACTION_LIMIT() + ".");
            return -1;
        }

        double totalCost = quantity * stock.getPrice() * (1 + TRANSACTION_FEE);
        String result = announceResult(buyer.getName(), "buy", quantity, stock.getName(), stock.getSymbol(),
                "high volatile");

        if (totalCost > buyer.getInvestment_budget() || quantity == 0) {
            totalCost = -1;
            return totalCost;
        } else {
            Integer stockOfUser = buyer.investment_portfolio.get(stock.getSymbol());
            if (stockOfUser == null) {
                stockOfUser = 0;
            }

            stock.setVolume(stock.getVolume() - quantity);
            buyer.investment_portfolio.put(stock.getSymbol(),
                    stockOfUser + quantity);
            stockMarket.setTransactionAmount(stockMarket.getTransactionAmount() + 1);
            stockMarket.setTransactionCost(stockMarket.getTransactionCost() + totalCost);

            buyer.setInvestment_budget(buyer.getInvestment_budget() - totalCost);
            buyer.setTransactionDone(buyer.getTransactionDone() + 1);

            result += "\nOriginal cost: " + quantity * stock.getPrice() + " TRY"
                    + "\nFee: " + TRANSACTION_FEE * 100 + " %"
                    + "\nTotal cost: " + totalCost + " TRY"
                    + "\n" + buyer.getName() + " has " + buyer.getInvestment_budget() + " TRY left.";
            System.out.println(result);
        }

        return totalCost;
    }

    @Override
    public double sellStock(Stock stock, int quantity, User seller) {
        if (seller.getTransactionDone() >= seller.getTRANSACTION_LIMIT()) {
            System.out.println(seller.name + " tried to make their " + (seller.getTransactionDone() + 1)
                    + "th transaction of the day, however as an " + seller.getInvestorType()
                    + " they are only allowed up to " + seller.getTRANSACTION_LIMIT() + ".");
            return -1;
        }

        double totalCost = quantity * stock.getPrice() * (1 + TRANSACTION_FEE);
        String result = announceResult(seller.getName(), "sell", quantity, stock.getName(), stock.getSymbol(),
                "high volatile");

        if (!seller.getInvestment_portfolio().containsKey(stock.getSymbol())
                || quantity > seller.getInvestment_portfolio().get(stock.getSymbol()) || quantity == 0) {
            totalCost = -1;
            return totalCost;
        } else {
            Integer stockOfUser = seller.investment_portfolio.get(stock.getSymbol());
            if (stockOfUser == null) {
                stockOfUser = 0;
            }

            stock.setVolume(stock.getVolume() + quantity);
            seller.investment_portfolio.put(stock.getSymbol(),
                    stockOfUser - quantity);
            stockMarket.setTransactionAmount(stockMarket.getTransactionAmount() + 1);
            stockMarket.setTransactionCost(stockMarket.getTransactionCost() + totalCost);

            seller.setInvestment_budget(seller.getInvestment_budget() + totalCost);
            seller.setTransactionDone(seller.getTransactionDone() + 1);

            result += "\nOriginal cost: " + quantity * stock.getPrice() + " TRY"
                    + "\nFee: " + TRANSACTION_FEE * 100 + " %"
                    + "\nTotal cost: " + totalCost + " TRY"
                    + "\n" + seller.getName() + " has " + seller.getInvestment_budget() + " TRY left.";
            System.out.println(result);
        }

        return totalCost;
    }

    private String announceResult(String actorName, String action, int quantity, String stockName, String stockSymbol,
            String marketState) {
        return actorName + " has made a transaction to " + action + " " + quantity + " shares of " + stockName
                + " (" + stockSymbol + ") while the stock market is " + marketState + ".";
    }

    @Override
    public String toString() {
        return "HighVolatileState";
    }
}
