package util;

public enum TimeSlot {
    SLOT_1(1, "10:00 AM"),
    SLOT_2(2, "11:00 AM"),
    SLOT_3(3, "12:00 PM"),
    SLOT_4(4, "1:00 PM");

    private final int id;
    private final String time;

    TimeSlot(int id, String time) {
        this.id = id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }
}
