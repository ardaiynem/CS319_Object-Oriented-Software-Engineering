import java.util.HashMap;
import java.util.Map;

abstract class User implements Observer {
    protected StockMarket stockMarket;
    protected String name;
    protected double investment_budget;
    protected Map<String, Integer> investment_portfolio;
    protected int transactionDone;
    protected final int TRANSACTION_LIMIT;
    protected final String investorType;

    // Constructor
    public User(StockMarket stockMarket, String name, double investment_budget, int TRANSACTION_LIMIT,
            String investorType) {
        this.stockMarket = stockMarket;
        this.name = name;
        this.investment_budget = investment_budget;
        this.transactionDone = 0;
        this.TRANSACTION_LIMIT = TRANSACTION_LIMIT;
        this.investorType = investorType;
        investment_portfolio = new HashMap<>();

        stockMarket.registerObserver(this);
    }

    // Getter and Setter methods
    public final StockMarket getStockMarket() {
        return stockMarket;
    }

    public final String getName() {
        return name;
    }

    public final double getInvestment_budget() {
        return investment_budget;
    }

    public final Map<String, Integer> getInvestment_portfolio() {
        return investment_portfolio;
    }

    public final int getTransactionDone() {
        return transactionDone;
    }

    public int getTRANSACTION_LIMIT() {
        return TRANSACTION_LIMIT;
    }

    public String getInvestorType() {
        return investorType;
    }

    public final void setInvestment_portfolio(Map<String, Integer> investment_portfolio) {
        this.investment_portfolio = investment_portfolio;
    }

    public final void setInvestment_budget(double investment_budget) {
        this.investment_budget = investment_budget;
    }

    public final void setStockMarket(StockMarket stockMarket) {
        this.stockMarket = stockMarket;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final void setTransactionDone(int transactionDone) {
        this.transactionDone = transactionDone;
    }

    // Methods
    public void printBuyInfo(String name, int quantity, Stock stock) {
        System.out.println(name + " calculated that they should buy " + quantity + " shares of " + stock.getName()
                + " (" + stock.getSymbol() + ").");
    }

    public void printSellInfo(String name, int quantity, Stock stock) {
        if (!investment_portfolio.containsKey(stock.getSymbol()) || investment_portfolio.get(stock.getSymbol()) == 0) {
            System.out.println(name + " does not own any shares so cannot sell " + stock.getName() + " ("
                    + stock.getSymbol() + ").");
        } else {
            System.out.println(name + " calculated that they should sell " + quantity + " shares of " + stock.getName()
                    + " (" + stock.getSymbol() + ").");
        }

    }

    @Override
    public void update(Object object) {
        if (object instanceof Stock) {
            Stock stock = (Stock) object;

            System.out.println(
                    this.name + " is alerted of change in " + stock.getName() + " (" + stock.getSymbol() + ").");

            stockMarket.buyStock(stock, shouldBuyStock(stock), this);
            stockMarket.sellStock(stock, shouldSellStock(stock), this);

        } else if (object instanceof StockMarket) {
            StockMarket stockMarket = (StockMarket) object;

            System.out.println(
                    this.name + " is alerted of change in market state of " + stockMarket.getName() + " to "
                            + stockMarket.getMarketState() + ".");

            if (stockMarket.getMarketState().toString().equals("OpenState")) {
                this.transactionDone = 0;
            }
        }
    }

    // Abstract Methods
    public abstract Integer shouldBuyStock(Stock stock);

    public abstract Integer shouldSellStock(Stock stock);
}
