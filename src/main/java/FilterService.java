
import entity.Item;

import java.util.ArrayList;
import java.util.List;

public class FilterService {
    List<Item> filtratedItems = new ArrayList<>();

    public List<Item> keyWordFiltration(List<Item> items, String string) {
        filtratedItems.clear();
        String finalString = string.trim();
        items.stream()
                .filter(x -> x.getName().contains(finalString))
                .forEach(x -> filtratedItems.add(x));
        return filtratedItems;
    }

    public List<Item> coastFiltration(List<Item> items, float min, float max) {
        filtratedItems.clear();
        items.stream()
                .filter(x -> x.getCoast() > min)
                .filter(x -> x.getCoast() < max)
                .forEach(x -> filtratedItems.add(x));
        return filtratedItems;
    }

    public List<Item> manufacturerFiltration(List<Item> items, String string) {
        filtratedItems.clear();
        String finalString = string.trim();
        items.stream()
                .filter(x -> x.getManufacturer().contains(finalString))
                .forEach(x -> filtratedItems.add(x));
        return filtratedItems;
    }

}
