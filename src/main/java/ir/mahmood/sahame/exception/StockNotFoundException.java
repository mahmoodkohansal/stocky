package ir.mahmood.sahame.exception;

public class StockNotFoundException extends Exception {
    public StockNotFoundException(String message) {
        super(message);
    }

    public StockNotFoundException() {
        super("Stock Not Found");
    }
}
