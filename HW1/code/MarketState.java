public interface MarketState {
    public boolean open();
    public boolean close();
    public double buyStock(Stock stock, int quantity, User buyer);
    public double sellStock(Stock stock, int quantity, User seller);
}
