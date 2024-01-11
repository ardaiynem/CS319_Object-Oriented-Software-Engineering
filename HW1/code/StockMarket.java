import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StockMarket implements Subject, Observer {
    private String name;
    private String symbol;
    private LinkedList<Stock> stocks;
    private boolean isOpen;
    private int transactionAmount;
    private double transactionCost;

    private List<Observer> observers;

    private MarketState marketState;

    // States
    private MarketState openState;
    private MarketState closedState;
    private MarketState highVolatileState;
    private MarketState lowVolatileState;

    public StockMarket(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.stocks = new LinkedList<>();
        this.observers = new ArrayList<>();

        openState = new OpenState(this);
        closedState = new ClosedState(this);
        highVolatileState = new HighVolatileState(this);
        lowVolatileState = new LowVolatileState(this);

        marketState = closedState;

        System.out.println("Stock Market with name " + "\"" + name + " (" + symbol + ")\" created.");
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public LinkedList<Stock> getStocks() {
        return stocks;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public double getTransactionCost() {
        return transactionCost;
    }

    public MarketState getMarketState() {
        return marketState;
    }

    // Setters
    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setTransactionCost(double transactionCost) {
        this.transactionCost = transactionCost;
    }

    public void setMarketState(MarketState marketState) {
        this.marketState = marketState;
        notifyObservers(this);
    }

    // Functions
    public void addStock(Stock stock) {
        stocks.add(stock);
        stock.registerObserver(this);
        
        System.out.println(stock.getName() + "(" + stock.getSymbol() + ") stock with price " + stock.getPrice()
                + " TRY, " + stock.getPercentChange() + " percent change, " + stock.getVolume() + " volume and "
                + stock.getMarketCap() + " market cap is added to " + name + " (" + symbol + ").");
    }

    public boolean open() {
        return marketState.open();
    }

    public boolean close() {
        return marketState.close();
    }

    public double buyStock(Stock stock, int quantity, User buyer) {
        return marketState.buyStock(stock, quantity, buyer);
    }

    public double sellStock(Stock stock, int quantity, User seller) {
        return marketState.sellStock(stock, quantity, seller);
    }

    // State Accessors
    public MarketState getOpenState() {
        return openState;
    }

    public MarketState getClosedState() {
        return closedState;
    }

    public MarketState getHighVolatileState() {
        return highVolatileState;
    }

    public MarketState getLowVolatileState() {
        return lowVolatileState;
    }

    @Override
    public String toString() {
        String result = "--- Stock Market Information ---\n" +
                "Name: " + name + " (" + symbol + ")\n" +
                "Stocks: \n";

        if (stocks.size() == 0) {
            result += "No stocks available.";
        } else {
            for (Stock stock : stocks) {
                result += "\n" + stock.toString() + "\n";
            }
        }

        return result;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object object) {
        for (Observer observer : observers) {
            observer.update(object);
        }
    }

    @Override
    public void update(Object object) {
        if (object instanceof Stock) {
            notifyObservers(object);
        }
    }

}
