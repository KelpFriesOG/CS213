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

    // Method that recursively calculates the total charges of the visits based on provider specialty
    public double charge() {
        // Get charge based on the specialty
        double charge = appointment.getProvider().getSpecialty().getCharge();
        if (next == null) {
            // As this is the base case, simply return the charge
            return charge;
        } else {
            // Otherwise send a recursive call to the next node and calculate the sum of the charges
            return charge + next.charge();
        }
    }

    // Override toString to display the appointment
    @Override
    public String toString() {
        return appointment.toString();
    }

}
