package sample;

public class BoughtProduct {

    private Product p;
    private int amount;

    public BoughtProduct(Product p, int amount) {
        this.p = p;
        this.amount = amount;
    }

    public Product getP() {
        return p;
    }

    public int getAmount() {
        return amount;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
