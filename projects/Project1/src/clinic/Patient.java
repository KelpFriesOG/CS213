public class Patient implements Comparable<Patient> {

    private Profile profile;
    private Visit visits;


    // No-arg Constructor
    public Patient() {
        profile = new Profile();
        visits = null;
    }

    // Parameterized Constructor #1
    public Patient(Profile profile) {
        this.profile = profile;
        this.visits = null; // initialize the list to be empty
    }


    // Parameterized Constructor #2
    public Patient(Profile profile, Visit visits) {
        this.profile = profile;
        this.visits = visits;
    }

    // Add a new visit to the linked list
    public void addVisit(Appointment appointment) {
        Visit newVisit = new Visit(appointment);
        if (visits == null) {
            visits = newVisit; // if the list is empty, the new visit is the visits
        } else {
            Visit current = visits;
            while (current.getNext() != null) {
                current = current.getNext(); // traverse to the last node
            }
            current.setNext(newVisit); // link the last node to the new visit
        }
    }

    // Traverse and print all visits
    public void printVisits() {
        Visit current = visits;
        while (current != null) {
            System.out.println(current); // print the current visit
            current = current.getNext(); // move to the next node
        }
    }

    public int charge(){
        // Traverses the linked list and calculates the cumulative charge across all visits (appointments)
        Visit current = visits;
        int total = 0;
        
        // While the current node is not null, calculate the cumulative charge and move to the next node
        while (current != null) {
            total += current.getAppointment().getProvider().getSpecialty().getCharge();
            current = current.getNext();
        }

        return total;
    }

    // Overrided methods
    @Override
    public String toString() {
        return profile.toString();
    }

    @Override
    public boolean equals(Object obj) {

        // If the object is an instance of the Patient class
        if (obj instanceof Patient) {
            Patient other = (Patient) obj;
            
            // Return true if the profiles are equal
            return profile.equals(other.profile);
        }
        return false;

    }

    @Override
    public int compareTo(Patient o) {
        // Compare the profiles
        return profile.compareTo(o.profile);
    }

}
