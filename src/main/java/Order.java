import java.util.List;

public class Order {
    private final List<Product> products;
    private final int number;

    public Order(List<Product> products, int number) {
        this.products = products;
        this.number = number;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Заказ №").append(number).append("\n")
                .append(String.format("%-30s%-15s%-10s%-20s%n", "Наименование", "Количество", "Ед.изм.", "Сумма руб."));
        float allAmount = 0f;
        for (Product product : products) {
            sb.append(product).append("\n");
            allAmount = allAmount + product.getAmount();
        }
        sb.append(String.format("\nВсего по заказу: %10.2f руб.", allAmount));
        return sb.toString();
    }
}
