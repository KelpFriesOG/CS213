import java.util.Calendar;
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

    /**
     * Checks if the appointment is valid.
     * The appointment is valid if the patient profile is valid (has a valid dob) and the appointment
     * date is in the future
     * @return a boolean representing the validity of the date
     * @author Kalpesh Chavan (krc177)
     */
    public boolean isValid(){

        // In order for the appointment to be valid the date of the appointment must be in the future
        // and the date within the profile must be valid.

        // Ensure that the date is valid
        if(!date.isValid()){
            return false;
        }

        // Ensure the timeslot exists
        if(timeslot == null){
            return false;
        }

        // Ensure that the date is not on Saturday or Sunday
        if(date.isWeekend()){
            return false;
        }

        // Ensure that the date is within six months
        Calendar today = Calendar.getInstance();
        // Calculate 12 * n_years + n_months for both
        int total_months_today = today.get(Calendar.YEAR) * 12 + (today.get(Calendar.MONTH) + 1);
        int total_months_date = date.getYear() * 12 + date.getMonth();

        if(Math.abs(total_months_today - total_months_date) > 6){
            return false;
        }

        // Ensure that the patient profile is valid (i.e. their date of birth is valid),
        // and that the date is in the future.
        return patient.isValid() && date.compareTo(new Date()) > 0;

    }

    // Getters and setters

    /** 
     * An overrided toString method that generates a representation akin to: "dd/mm/yyyy HH:MM AM/PM [patient.fname] [patient.lname] [patient.dob in dd/mm/yyyy form]"
    */
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