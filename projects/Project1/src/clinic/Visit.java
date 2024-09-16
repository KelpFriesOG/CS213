public class Visit {

    // A linked list chain of appointments encapsulated in the Visit class
    
    private Appointment appointment;
    private Visit next;


    // No-arg Constructor
    public Visit() {
        this.appointment = new Appointment();
        this.next = null;
    }

    // Parameterized Constructor
    public Visit(Appointment appointment) {
        this.appointment = appointment;
        this.next = null;
    }

    // Getter for the appointment
    public Appointment getAppointment() {
        return appointment;
    }

    // Setter for the appointment
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    // Getter for the next Visit node
    public Visit getNext() {
        return next;
    }

    // Setter for the next Visit node
    public void setNext(Visit next) {
        this.next = next;
        this.next.next = null;
    }

    // Override toString to display the appointment
    @Override
    public String toString() {
        return appointment.toString();
    }

}
