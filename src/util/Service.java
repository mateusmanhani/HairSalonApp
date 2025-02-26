package util;

public enum Service {
    HAIRCUT(1, "Haircut", 20.0),
    BEARD_TRIM(2, "Beard Trim", 15.0),
    HAIR_COLORING(3, "Hair Coloring", 50.0),
    SHAVE(4, "Shave", 10.0);

    private final int id;
    private final String name;
    private final double price;

    Service(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
