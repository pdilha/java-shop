package shop.model;

public class Trade {
//    private Product product;
    private int tradeId;
    private TradeType tradeType;
    private int quantity;
    private float price;

    public Trade() {
    }

    public Trade(int tradeId, TradeType tradeType, int quantity, float price) {
        this.tradeId = tradeId;
        this.tradeType = tradeType;
        this.quantity = quantity;
        this.price = price;
    }

//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
