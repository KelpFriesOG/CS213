import java.util.Calendar;

public class Date implements Comparable<Date> {

    private Calendar calendar;
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    // No-arg Constructor
    public Date() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    // Parameterized Constructor
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // Parameterized Constructor for date string
    public Date(String dateString){
        calendar = Calendar.getInstance();

        // Parse the string "mm/dd/yyyy"
        String[] parts = dateString.split("/");
        this.month = Integer.parseInt(parts[0]) - 1;
        this.day = Integer.parseInt(parts[1]);
        this.year = Integer.parseInt(parts[2]);

        calendar.set(year, month, day);
    }

    // Getters and Setters
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isLeapYear(){
        if (year % 400 == 0) {
            return true;  // Leap year
        } else if (year % 100 == 0) {
            return false;  // Not a leap year
        } else if (year % 4 == 0) {
            return true;  // Leap year
        } else {
            return false;  // Not a leap year
        }
    }

    public boolean isWeekend(){
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isWithinSixMonths(){
        
        
       
    }

    public boolean isValid(){

        // Ensure that the month is valid
        if (month < 1 || month > 12) {
            return false;
        }

        // Depending on the month, ensure that the day is valid

        // For January, March, May, July, August, October, and December
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            // Ensure the day is between 1 and 31 (inclusive), if not return false
            if (day < 1 || day > 31) {
                return false;
            }
        } 
        // If the month is April, June, September, or November
        else if (month == 4 || month == 6 || month == 9 || month == 11) {
            // Ensure the day is between 1 and 30 (inclusive), if not return false
            if (day < 1 || day > 30) {
                return false;
            }
        } 
        // If the month is February
        else if (month == 2) {
            // Ensure the day is between 1 and 29 (inclusive), if not return false
            if (day < 1 || day > 29) {
                return false;
            }
            // If the day is 29 but the year is not a leap year, return false
            if (day == 29 && !isLeapYear()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date other = (Date) obj;
            return this.year == other.year && this.month == other.month && this.day == other.day;
        }
        return false;
    }

    @Override
    public int compareTo(Date other) {
        // The date of birth is compared in reverse chronological order ( 10/27/2002 < 09/08/2008)
        // The earlier dates come first (earlier dates are considered 'lesser' than the later dates)
        
        // Compare years first
        if (year != other.year) {
            return year - other.year; // Returns negative if this.year < other.year, positive if this.year > other.year
        }

        // Compare months next
        if (month != other.month) {
            return month - other.month; // Returns negative if this.month < other.month, positive if this.month > other.month
        }

        // Compare days last
        return day - other.day; // Returns negative if this.day < other.day, positive if this.day > other.day, or zero if days are equal
    }
        
}

