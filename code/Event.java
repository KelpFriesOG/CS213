import java.util.Date;


/* A class that represents an event implements Comparable to ensure compareTo is overridden.
 * You can play around and realize that the generic type within Comparable<Event> is not strictly
 * needed but as a result there might be errors in runtime.
 * 
 * In general we want to enforce constraints ahead of time (to catch issues in compile time rather
 * than runtime). This is simply good practice but not strictly needed.
 * 
*/
public class Event implements Comparable<Event> {
    

    /* Instance variables */
    protected Date date;
    private String name;
    private int duration;
    private String location;

    /* Default constructor */
    public Event() {
        this.date = new Date();
        this.name = "";
        this.duration = 0;
        this.location = "";
    }

    /* Copy constructor */
    public Event(Event event){
        this.date = event.date;
        this.name = event.name;
        this.duration = event.duration;
        this.location = event.location;
    }

    /* Parameterized constructor 1*/
    public Event(Date date, String name){
        this.date = date;
        this.name = name;
        this.duration = 0;
        this.location = "";
    }


    /* Parameterized constructor 2 */
    public Event(Date date, String name, int duration, String location){
        this.date = date;
        this.name = name;
        this.duration = duration;
        this.location = location;
    }

    /* Overriding the equals method */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Event) {
            // Downcast (typecast) obj to Event
            Event other = (Event) obj;
            // Compare each field (if everything is equal then return true)
            return this.date.equals(other.date) && this.name.equals(other.name);
        }
        return false;

    }

    /* Overriding the toString method */
    @Override
    public String toString() {
        return "Event: " + name + " on " + date; 
    }


    @Override
    public int compareTo(Event e) {

        // Compare the dates
        if (this.date.compareTo(e.date) < 0) {
            return -1;
        } else if (this.date.compareTo(e.date) > 0) {
            return 1;
        } else {
            return 0;
        }

        // Returns an int based on the most recent date

    }


    public static void main(String[] args) {

        Event e1 = new Event(new Date(), "CS213");
        Event e2 = new Event(e1);

        // This should return False (always)
        System.out.println(e1 == e2);

        // This should return False if we have not overrided the equals method
        System.out.println(e1.equals(e2));

        // Java automatically smartly calls the toString method if we attempt to print an object
        System.out.println(e1);

    }

}

