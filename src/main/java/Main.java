import entity.Category;
import entity.Measure;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    static boolean orderListIsEmpty(OrderRepo orderRepo, Logger logger) {
        if (orderRepo.getOrders().isEmpty()) {
            logger.log("\n Список заказов пуст");
            return false;
        } else {
            logger.log("\n Список заказов:");
            orderRepo.orderList();
            return true;
        }
    }

    public static void main(String[] args) {

        ItemRepo itemRepo = new ItemRepo();
        itemRepo.addItem("Хлеб", Category.FOODSTUFF, 25.55f, Measure.PC, "Аксайский хлебокомбинат");
        itemRepo.addItem("Колбаса", Category.FOODSTUFF, 324.40f, Measure.KG, "Тавр");
        itemRepo.addItem("Туфли мужские", Category.FOOTWEAR, 3120.00f, Measure.PC, "Обувь России");
        itemRepo.addItem("Туфли женские", Category.FOOTWEAR, 4052.00f, Measure.PC, "Обувь России");
        itemRepo.addItem("Туфли детские", Category.FOOTWEAR, 1900.20f, Measure.PC, "Котофей");
        itemRepo.addItem("Белизна", Category.HOUSEHOLD_CHEMISTRY, 115.34f, Measure.L, "Казаньхимпром");
        itemRepo.addItem("Лошадка деревянная", Category.TOYS, 00.00f, Measure.PC, "Селфмэйд");
        Logger logger = new ConsoleLogger();
        ProductBug productBug = new ProductBug();
        OrderRepo orderRepo = new OrderRepo();
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        while (true) {

            try {
                logger.log("\nВыбери действие:" +
                        "\n1. Корзина" +
                        "\n2. Список заказов" +
                        "\n3. Выход");
                switch (Integer.parseInt(scanner.nextLine())) {

                    case 1:
                        while (true) {
                            logger.log("\n Товары в корзине: ");
                            if (productBug.purchases.isEmpty()) {
                                logger.log("\nКорзина пуста");
                            } else {
                                productBug.order();
                            }
                            logger.log("\nВыбери действие:" +
                                    "\n1. Добавить товар из списка в корзину" +
                                    "\n2. Удалить товар из корзины" +
                                    "\n3. Оформить заказ" +
                                    "\n4. Очистить корзину" +
                                    "\n5. Выход");

                            switch (Integer.parseInt(scanner.nextLine())) {
                                case 1:
                                    logger.log("\n");
                                    itemRepo.showAvailableItems(itemRepo.getItems());
                                    logger.log("\n Отфильтровать заказы?(Y/N)");
                                    if (scanner.nextLine().equals("Y")) {
                                        itemRepo.filtratedItems(scanner);
                                    }

                                    try {
                                        logger.log("\nВведи номер товара, для добавления в корзину:");

                                        int n = Integer.parseInt(scanner.nextLine());
                                        logger.log("\nВведи количество товара:");
                                        int q = Integer.parseInt(scanner.nextLine());
                                        productBug.goodsAdd(itemRepo.getItems().get(n - 1), q);
                                        logger.log("\nТовар " + itemRepo.getItems().get(n - 1).getName()
                                                + " в количестве: " + q + " " + itemRepo.getItems().get(n - 1).getMeasure() +
                                                " успешно добавлен в корзину");
                                    } catch (Exception e) {
                                        logger.log("\nВведены некорректные данные,попробуй ещё!");
                                    }
                                    continue;
                                case 2:
                                    if (productBug.purchases.isEmpty()) {
                                        logger.log("\nКорзина пуста, удалять нечего!");
                                    } else {
                                        logger.log("\n");
                                        productBug.order();
                                        try {
                                            logger.log("\nВведи номер товара, для удаления из корзины:");
                                            int nd = Integer.parseInt(scanner.nextLine());
                                            logger.log("\nВведи количество товара для удаления:");
                                            int qd = Integer.parseInt(scanner.nextLine());


                                            logger.log("\nТовар " + productBug.getPurchases().get(nd - 1).getName()
                                                    + "в количестве: " + qd + " " + itemRepo.getItems().get(nd - 1).getMeasure() +
                                                    " удалён из корзины");
                                            productBug.goodsDelete(productBug.getPurchases().get(nd - 1), qd);
                                        } catch (Exception e) {
                                            logger.log("\nВведены некорректные данные,попробуй ещё!");
                                        }
                                    }
                                    continue;
                                case 3:
                                    if (productBug.purchases.isEmpty()) {
                                        logger.log("\nКорзина пуста, невозможно оформить пустой заказ!");
                                    } else {
                                        logger.log("\n");
                                        productBug.order();
                                        logger.log("\n Оформить заказ?(Y/N)");
                                        if (scanner.nextLine().equals("Y")) {
                                            orderRepo.addOrder(productBug.getPurchases());
                                            logger.log("\n Заказ оформлен");
                                        }
                                    }
                                    continue;
                                case 4:
                                    productBug.cleanBug();
                                    logger.log("\nКорзина очищена\n");
                                    continue;
                                case 5:
                                    break;
                                default:
                                    logger.log("Что то пошло не так, будь внимательней! Unexpected value");
                                    continue;
                            }
                            break;
                        }
                        continue;
                    case 2:
                        while (true) {
                            orderListIsEmpty(orderRepo, logger);
                            logger.log("\nВыбери действие:" +
                                    "\n1. Посмотреть состав заказа" +
                                    "\n2. Повторить заказ" +
                                    "\n3. Вернуть(удалить) заказ" +
                                    "\n4. Логистический статус заказа" +
                                    "\n5. Выход");
                            switch (Integer.parseInt(scanner.nextLine())) {

                                case 1:
                                    if (orderListIsEmpty(orderRepo, logger)) {
                                        try {
                                            logger.log("\n Введи номер заказа для просмотра: ");
                                            int orderNumber = Integer.parseInt(scanner.nextLine());
                                            orderRepo.showOrder(orderNumber);
                                        } catch (Exception e) {
                                            logger.log("\nВведены некорректные данные,попробуй ещё!");
                                        }
                                    }
                                    continue;
                                case 2:
                                    if (orderListIsEmpty(orderRepo, logger)) {
                                        try {
                                            logger.log("\n Введи номер заказа для повторного оформления: ");
                                            orderRepo.repeatOrder(Integer.parseInt(scanner.nextLine()));
                                        } catch (Exception e) {
                                            logger.log("\nНет такого заказа,попробуй ещё!");
                                        }
                                    }
                                    continue;
                                case 3:
                                    if (orderListIsEmpty(orderRepo, logger)) {
                                        try {
                                            logger.log("\n Введи номер заказа для удаления:");
                                            int deleteOrderNumber = Integer.parseInt(scanner.nextLine());
                                            orderRepo.deleteOrder(deleteOrderNumber);
                                        } catch (Exception e) {
                                            logger.log("\nВведены некорректные данные,попробуй ещё!");
                                        }
                                    }
                                    continue;
                                case 4:
                                    if (orderListIsEmpty(orderRepo, logger)) {
                                        try {
                                            logger.log("\n Введи номер заказа для отслеживания: ");
                                            Integer u = Integer.parseInt(scanner.nextLine());
                                            Optional<Integer> track = orderRepo.trackService.tracks.parallelStream()
                                                    .filter(x -> Objects.equals(x, u))
                                                    .findAny();
                                            track.ifPresentOrElse(
                                                    x -> orderRepo.trackService.showStatus(),
                                                    () -> logger.log("\nЗаказ отменён, или не был оформлен!")
                                            );
                                            continue;
                                        } catch (Exception e) {
                                            logger.log("\nВведены некорректные данные,попробуй ещё!");
                                        }
                                    }
                                    continue;
                                case 5:
                                    break;
                                default:
                                    logger.log("Что то пошло не так, будь внимательней! Unexpected value");
                                    continue;
                            }
                            break;
                        }
                        continue;
                    case 3:
                        logger.log("\nДо новых встреч!");
                        break;
                    default:
                        logger.log("Введены некорректные данные,попробуй ещё");
                        continue;
                }
            } catch (NumberFormatException e) {
                logger.log("Введены некорректные данные,попробуй ещё");
                continue;
            }
            break;
        }
    }
}
