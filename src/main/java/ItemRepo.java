import entity.Category;
import entity.Item;
import entity.Measure;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ItemRepo implements RatingService,RecommendService{

    List<Item> items = new ArrayList<>();
    List<Item> filteredItems = new ArrayList<>();
//    RatingService ratingService = new RatingService();
//    RecommendService recommendService = new RecommendService();
    FilterService filter = new FilterService();
    int itemNumber = 1;

    public void addItem(String name, Category category, float coast, Measure measure, String manufacturer) {
        Item item = new Item(itemNumber, name, category, coast, measure, manufacturer);
        items.add(item);
        itemNumber++;
    }

    public List<Item> getItems() {
        return items;
    }

    public void filtratedItems(Scanner scanner) {

//        PrintStream ps = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        Logger logger = new ConsoleLogger();
        try {
            while (true) {
                logger.log("\nВыбери действие:" +
                        "\n1. Фильтрация по наименованию" +
                        "\n2. Фильтрация по производителю" +
                        "\n3. Фильтрация по стоимости");
                switch (Integer.parseInt(scanner.nextLine())) {
                    case 1:
                        logger.log("\nВведи ключевое слово:");
                        filteredItems = filter.keyWordFiltration(items, scanner.nextLine());
                        if (filteredItems.size() > 0) {
                            showAvailableItems(filteredItems);
                        } else {
                            logger.log("\nПо заданным параметрам товаров не найдено!" +
                                    "\nВыбери что нибудь из списка:");
                            showAvailableItems(items);
                        }
                        break;
                    case 2:

                        logger.log("\nВведи производителя:");
                        filteredItems = filter.manufacturerFiltration(items, scanner.nextLine());
                        if (filteredItems.size() > 0) {
                            showAvailableItems(filteredItems);
                        } else {
                            logger.log("\nПо заданным параметрам товаров не найдено!" +
                                    "\nВыбери что нибудь из списка:");
                            showAvailableItems(items);
                        }
                        break;
                    case 3:
                        logger.log("\nВведи стоимость от: ");
                        float a = Integer.parseInt(scanner.nextLine());
                        logger.log("\nВведи стоимость до: ");
                        float b = Integer.parseInt(scanner.nextLine());
                        filteredItems = filter.coastFiltration(items, a, b);
                        if (filteredItems.size() > 0) {
                            showAvailableItems(filteredItems);
                        } else {
                            logger.log("\nПо заданным параметрам товаров не найдено!" +
                                    "\nВыбери что нибудь из списка:");
                            showAvailableItems(items);
                        }
                        break;
                    default:
                        logger.log("Выбери корректное действие");
                        continue;
                }
                break;
            }
        } catch (NumberFormatException e) {
            logger.log("Что то пошло не так, будь внимательней!");
        }
    }

    public void showAvailableItems(List<Item> items) {

        PrintStream ps = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        ps.printf("\n%-2s%-30s %-20s %-25s %-20s %-20s %-20s \n", "№",
                "Наименование", "Категория", "Производитель", "Ед.изм.", "Цена руб.", "Рейтинг экспертов");
        for (Item x : items) {
            ps.println(x + "[" + ratingFromInternet() + "]");
        }
        if (items.size() > 0)
            ps.println(recommend());
    }

    @Override
    public int ratingFromInternet() {

            int max = 100;
            Random random = new Random();
            return random.nextInt(max);

    }

    @Override
    public String recommend() {

            Random random = new Random();
            return "ПОКУПАТЕЛИ РЕКОМЕНДУЮТ: " + items.get(random.nextInt(items.size())).getName();

    }
}

