import java.util.ArrayList;
import java.util.List;

public class Stock implements Subject {
    private String symbol;
    private String name;
    private double price;
    private double percentChange;
    private double volume;
    private double marketCap;

    private List<Observer> observers;

    public Stock(String name, String symbol, double price, double percentChange, double volume, double marketCap) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.percentChange = percentChange;
        this.volume = volume;
        this.marketCap = marketCap;
        this.observers = new ArrayList<>();
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getPercentChange() {
        return percentChange;
    }

    public double getVolume() {
        return volume;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPercentChange(double percentChange) {
        this.percentChange = percentChange;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    @Override
    public String toString() {
        return "--- Stock Information ---\n" +
                "Name: " + name + " (" + symbol + ")\n" +
                "Price: " + price + " TRY\n" +
                "Percent Change: " + percentChange + "\n" +
                "Volume: " + volume + "\n" +
                "Market Cap: " + marketCap + "\n";
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

    public void update(double price, double percentChange, double volume, double marketCap) {
        this.price = price;
        this.percentChange = percentChange;
        this.volume = volume;
        this.marketCap = marketCap;

        System.out.println("Stock " + this.name + " (" + this.symbol + ") is updated to price: " + this.price
                + " TRY, percent change: " + this.percentChange + ", volume: " + this.volume + " and market cap: "
                + this.marketCap);
                
        notifyObservers(this);
    }
}