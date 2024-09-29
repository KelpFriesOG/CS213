public enum Timeslot { 
    SLOT1 (9, 00),       // 9:00 AM
    SLOT2 (10, 45),     // 10:45 AM
    SLOT3 (11, 15),     // 11:15 AM
    SLOT4 (13, 30),     // 1:30 PM
    SLOT5 (15, 00),      // 3:00 PM
    SLOT6 (16, 15);     // 4:15 PM

    private final int hour;
    private final int minute;

    // Constructor for the enum
    private Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    // Getter methods
    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public String toString() {
        // I used a ternary expression to make quick work of formatting
        return (this.hour < 12 ? this.hour : this.hour - 12) + ":" + (this.minute == 0 ? "00" : this.minute) + " " + (this.hour < 12 ? "AM" : "PM");
    }
}


