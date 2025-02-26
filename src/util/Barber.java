package util;

public enum Barber {
    JOHN(1, "John Doe"),
    JANE(2, "Jane Smith"),
    MIKE(3, "Mike Johnson");

    private final int id;
    private final String name;

    Barber(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
