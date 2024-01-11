public class Tester {
    public static void main(String[] args) {

        // stock2.update(97.7, 2.3, 274242, 252343224);

        // PART 1 TESTS
        System.out.println("\n--------------- PART 1 TESTS INITIALIZATON ---------------");
        StockMarket stockMarket1 = new StockMarket("Market 1", "M1");
        StockMarket stockMarket2 = new StockMarket("Market 2", "M2");
        StockMarket stockMarket3 = new StockMarket("Market 3", "M3");

        Stock stock1 = new Stock("Stock 1", "S1", 1000.00, -1.0, 1000000, 100000);
        Stock stock2 = new Stock("Stock 2", "S2", 2000.00, 2.0, 2000000, 200000);
        Stock stock3 = new Stock("Stock 3", "S3", 6000.00, -3.0, 3000000, 300000);

        stockMarket1.addStock(stock1);
        stockMarket1.addStock(stock2);
        stockMarket2.addStock(stock2);

        User aggresiveInvestor = new AggressiveInvestor(stockMarket1, "Aggressive Investor", 100000);
        User balancedInvestor = new BalancedInvestor(stockMarket2, "Balanced Investor", 100000);
        User conservativeInvestor = new ConservativeInvestor(stockMarket3, "Conservative Investor", 200000);

        System.out.println("\n################ PART 1 TESTS START ################");
        // toString() tests of Stocks
        System.out.println("\n--------------- toString() tests of Stocks ---------------");
        System.out.println(stock1);
        System.out.println(stock2);
        System.out.println(stock3);

        // toString() tests of Stock Markets
        System.out.println("\n--------------- toString() tests of Stock Markets ---------------");
        System.out.println(stockMarket1);
        System.out.println(stockMarket2);
        System.out.println(stockMarket3);

        // shouldBuyStock() tests of AggressiveInvestor
        System.out.println("\n--------------- shouldBuyStock() tests of AggressiveInvestor ---------------");
        aggresiveInvestor.shouldBuyStock(stock1);
        aggresiveInvestor.shouldBuyStock(stock2);
        aggresiveInvestor.shouldBuyStock(stock3);

        // shouldBuyStock() tests of BalancedInvestor
        System.out.println("\n--------------- shouldBuyStock() tests of BalancedInvestor ---------------");
        balancedInvestor.shouldBuyStock(stock1);
        balancedInvestor.shouldBuyStock(stock2);
        balancedInvestor.shouldBuyStock(stock3);

        // shouldBuyStock() tests of ConservativeInvestor
        System.out.println("\n--------------- shouldBuyStock() tests of ConservativeInvestor ---------------");
        conservativeInvestor.shouldBuyStock(stock1);
        conservativeInvestor.shouldBuyStock(stock2);
        conservativeInvestor.shouldBuyStock(stock3);

        // Populate investment portfolios of investors to test shouldSellStock() methods
        System.out.println(
                "\n--------------- Populate investment portfolios of investors to test shouldSellStock() methods ---------------");
        aggresiveInvestor.getInvestment_portfolio().put(stock1.getSymbol(), 100);
        aggresiveInvestor.getInvestment_portfolio().put(stock2.getSymbol(), 100);
        aggresiveInvestor.getInvestment_portfolio().put(stock3.getSymbol(), 100);

        balancedInvestor.getInvestment_portfolio().put(stock2.getSymbol(), 100);
        balancedInvestor.getInvestment_portfolio().put(stock3.getSymbol(), 100);

        conservativeInvestor.getInvestment_portfolio().put(stock1.getSymbol(), 100);
        conservativeInvestor.getInvestment_portfolio().put(stock2.getSymbol(), 100);

        // shouldSellStock() tests of AggressiveInvestor
        System.out.println("\n--------------- shouldSellStock() tests of AggressiveInvestor ---------------");
        aggresiveInvestor.shouldSellStock(stock1);
        aggresiveInvestor.shouldSellStock(stock2);
        aggresiveInvestor.shouldSellStock(stock3);

        // shouldSellStock() tests of BalancedInvestor
        System.out.println("\n--------------- shouldSellStock() tests of BalancedInvestor ---------------");
        balancedInvestor.shouldSellStock(stock1);
        balancedInvestor.shouldSellStock(stock2);
        balancedInvestor.shouldSellStock(stock3);

        // shouldSellStock() tests of ConservativeInvestor
        System.out.println("\n--------------- shouldSellStock() tests of ConservativeInvestor ---------------");
        conservativeInvestor.shouldSellStock(stock1);
        conservativeInvestor.shouldSellStock(stock2);
        conservativeInvestor.shouldSellStock(stock3);

        System.out.println("\n################ PART 1 TESTS END ################");

        // PART 2 TESTS
        System.out.println("\n################ PART 2 TESTS START ################");
        // close() tests while markets are closed
        stockMarket1.close();
        stockMarket2.close();
        stockMarket3.close();

        // open() tests while markets are closed
        stockMarket1.open();
        stockMarket2.open();
        stockMarket3.open();

        // Open state tests
        System.out.println("\n--------------- Open state tests ---------------");
        for (int i = 0; i < 5; i++) {
            stockMarket1.buyStock(stock1, 10, aggresiveInvestor);
            System.out.println();
            stockMarket1.sellStock(stock1, 10, aggresiveInvestor);
            System.out.println();
        }

        // Transaction limit test
        System.out.println("\n--------------- Transaction limit test ---------------");
        stockMarket1.buyStock(stock1, 10, aggresiveInvestor);

        User richAggresiveInvestor = new AggressiveInvestor(stockMarket1, "Rich Aggressive Investor", 1000000);

        // Low volatile state tests
        stockMarket1.buyStock(stock1, 300, richAggresiveInvestor);
        System.out.println();
        System.out.println("--------------- Low volatile state tests ---------------");

        // High volatile state tests
        stockMarket1.buyStock(stock1, 300, richAggresiveInvestor);
        System.out.println();
        stockMarket1.sellStock(stock1, 300, richAggresiveInvestor);
        System.out.println();

        System.out.println("--------------- High volatile state tests ---------------");
        stockMarket1.buyStock(stock1, 300, richAggresiveInvestor);
        System.out.println();

        // open() tests while markets are open
        stockMarket1.open();
        stockMarket2.open();
        stockMarket3.open();

        // close() tests while markets are open
        stockMarket1.close();
        stockMarket2.close();
        stockMarket3.close();
        System.out.println();

        // Closed state tests
        stockMarket1.buyStock(stock1, 10, aggresiveInvestor);
        System.out.println();
        stockMarket1.buyStock(stock1, 300, richAggresiveInvestor);
        System.out.println();

        System.out.println("\n################ PART 2 TESTS END ################");

        // PART 3 TESTS
        System.out.println("\n################ PART 3 TESTS START ################");


        stockMarket1.open();
        stockMarket2.open();
        stockMarket3.open();

        // update() tests
        stock1.update(3000.00, 5.0, 500000, 110000);
        stock2.update(1500.00, -1.5, 600000, 12000);
        stock3.update(3000.00, 2.0, 700000, 130000);
        System.out.println("\n################ PART 3 TESTS END ################");
    }
}
