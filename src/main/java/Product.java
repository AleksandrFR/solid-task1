import entity.Measure;

public class Product {
    String name;
    int quantity;
    float amount;
    Measure measure;

    public Product(String name, int quantity, float amount, Measure measure) {
        this.name = name;
        this.quantity = quantity;
        this.amount = amount;
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("%-30s  %-15d %-10s %-15.2f", name, quantity, measure.toString(), amount);
    }
}
