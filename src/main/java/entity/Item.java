package entity;

public class Item {
    private final int number;
    private final String name;
    private final float coast;
    private final Category category;
    private final Measure measure;
    private final String manufacturer;

    public Item(int number, String name, Category category, float coast, Measure measure, String manufacturer) {
        this.number = number;
        this.name = name;
        this.coast = coast;
        this.category = category;
        this.measure = measure;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public float getCoast() {
        return coast;
    }

    public Category getCategory() {
        return category;
    }

    public Measure getMeasure() {
        return measure;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public String toString() {

        return String.format("%-2d.%-30s%-20s%-25s%-20s%-20.2f", number, name, category.toString(), manufacturer, measure.toString(), coast);
    }
}
