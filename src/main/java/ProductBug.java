import entity.Item;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductBug {

    List<Product> purchases = new ArrayList<>();

    public List<Product> getPurchases() {
        return purchases;
    }

    public void goodsAdd(Item item, int quantity) {

        Optional<Product> result = purchases.parallelStream()
                .filter(x -> x.getName().equals(item.getName()))
                .findAny();


        result.ifPresent(
                x -> x.setQuantity(x.getQuantity() + quantity)
        );

        result.ifPresentOrElse(
                x -> x.setAmount(x.getAmount() + item.getCoast() * quantity),
                () -> purchases.add(new Product(item.getName(), quantity, item.getCoast() * quantity, item.getMeasure()))
        );
    }

    public void goodsDelete(Product product, int quantity) {
        if (product.getQuantity() - quantity == 0) {
            purchases.remove(product);
        } else {
            product.setAmount(product.getAmount() - (product.getAmount() / product.getQuantity() * quantity));
            product.setQuantity(product.getQuantity() - quantity);
        }
    }

    public void cleanBug() {
        purchases.clear();
    }

    public void order() {
        float allAmount = 0f;
        for (Product product : purchases) {
            allAmount = allAmount + product.getAmount();
        }
        PrintStream ps = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        ps.printf("%-30s%-15s%-10s%-20s%n", "Наименование", "Количество", "Ед.изм.", "Сумма руб.");
        int i = 1;
        for (Product x : purchases) {
            ps.println((i++) + ". " + x);
        }
        ps.printf("Всего по заказу: %10.2f руб.\n", allAmount);
    }
}


