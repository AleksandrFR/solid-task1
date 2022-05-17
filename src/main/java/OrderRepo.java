import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepo {
    private final List<Order> orderList = new ArrayList<>();
    private int orderNumber = 1;
    TrackService trackService = new TrackService();
    Logger logger = new ConsoleLogger();

    public void addOrder(List<Product> products) {
        List<Product> list = new ArrayList<>(products);
        Order order = new Order(list, orderNumber);
        orderList.add(order);
        trackService.addTrack(orderNumber);
        orderNumber++;
    }

    public void deleteOrder(int i) {

        Optional<Order> result = orderList.parallelStream()
                .filter(x -> x.getNumber() == i)
                .findAny();
        result.ifPresentOrElse(
                orderList::remove,
                () -> logger.log("\nНет такого заказа,попробуй ещё!")
        );
        result.ifPresent(
                x -> logger.log("\n Заказ №" + i + ". был удалён")
        );
        result.ifPresent(
                x -> trackService.tracks.remove(i - 1)
        );
    }

    public void repeatOrder(int i) {
        addOrder(getOrders().get(i - 1).getProducts());
        logger.log("\nЗаказ №" + i + ". был оформлен повторно под №" + orderList.size() + ".");
    }

    public void showOrder(int i) {


        Optional<Order> result = orderList.parallelStream()
                .filter(x -> x.getNumber() == i)
                .findAny();

        result.ifPresentOrElse(
                x -> logger.log("" + x + ""),
                () -> logger.log("Нет такого заказа,попробуй ещё!")
        );
    }

    public List<Order> getOrders() {
        return orderList;
    }

    public void orderList() {
        PrintStream ps = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        for (Order order : orderList) {
            logger.log("Заказ №" + order.getNumber());
        }
    }
}
