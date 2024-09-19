public class Appointment implements Comparable<Appointment> {
    private Date date;
    private Timeslot timeslot;
    private Profile patient;
    private Provider provider;

    // No-arg Constructor
    public Appointment() {
        date = new Date();
        timeslot = Timeslot.SLOT1;
        provider = Provider.PATEL;
        patient = new Profile();
    }

    // Parameterized Constructor
    public Appointment(Date date, Timeslot timeslot, Profile patient, Provider provider) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    public boolean isValid(){

        // In order for the appointment to be valid the date of the appointment must be in the future
        // and the date within the profile must be valid.

        return patient.isValid() && date.compareTo(new Date()) > 0;

    }

    // Getters and setters
    @Override
    public String toString() {
        return date.toString() + " " + timeslot.toString() + " " + patient.toString() + " " + provider.toString(); 
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public Profile getPatient() {
        return patient;
    }

    public void setPatient(Profile patient) {
        this.patient = patient;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj instanceof Appointment) {
            Appointment other = (Appointment) obj;
            return this.date.equals(other.date) &&
                    this.timeslot.equals(other.timeslot) &&
                    this.patient.equals(other.patient);
        }

        return false;
    }

    @Override
    public int compareTo(Appointment o) {
        
        // Sort first by the county of the provider (alphabetical order), 
        // then the date (chronological order), 
        // then the timeslot (ordinal order)

        // Extract the county from this appointment and the other appointment
        String this_county = this.provider.getLocation().getCounty();
        String other_county = o.provider.getLocation().getCounty();

        // Compare the counties if they are not the same
        if (this_county.compareTo(other_county) != 0) {
            return this_county.compareTo(other_county);
        }

        // Otherwise if counties are the same, compare the dates
        if (this.date.compareTo(o.date) != 0) {
            return this.date.compareTo(o.date);
        }

        // Otherwise if dates are the same, compare the timeslots (enums have built in compareTo based on declaration order)
        return this.timeslot.compareTo(o.timeslot);
    
    }


}