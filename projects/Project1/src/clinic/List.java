public class List {

    private Appointment[] appointments;
    private int size; // number of appointments in the array

    public static final int NOT_FOUND = -1;
    public static final int INIT_CAPACITY = 4;
    public static final int N_EXPAND = 4;

    // No-arg constructor
    public List() {
        appointments = new Appointment[INIT_CAPACITY]; // initial capacity
        size = 0;
    }

    // Copy constructor
    public List(List list) {
        this.appointments = list.appointments.clone();
        this.size = list.size;
    }

    private int find(Appointment appointment) {
        for (int i = 0; i < size; i++) {
            if (appointments[i].equals(appointment)) {
                return i;
            }
        }
        return NOT_FOUND; // not found
    }


    public int getSize() {
        return size;
    }

    private void grow() {
        
        Appointment[] newAppointments = new Appointment[appointments.length + N_EXPAND];
        
        for (int i = 0; i < appointments.length; i++) {
            newAppointments[i] = this.appointments[i];
        }

        appointments = newAppointments;
    }


    public boolean contains(Appointment appointment) {
        return find(appointment) != -1;
    }

    public void add(Appointment appointment) {
        
        // If there is no room, grow first!
        if (size == appointments.length) {
            grow();
        }

        // Ensure the appointment is valid and profile is valid
        if (!appointment.isValid() || !appointment.getPatient().isValid()) {
            return;
        }

        // Before adding the appointment, check if the doctor's timeslot for the day is already full
        if (this.size > 0) {
            for (int i = 0; i < size; i++) {
                boolean timeslotIsSame = appointments[i].getTimeslot().equals(appointment.getTimeslot());
                boolean dayIsSame = appointments[i].getDate().equals(appointment.getDate());
                boolean providerIsSame = appointments[i].getProvider().equals(appointment.getProvider());
                
                // If all three conditions are true then the timeslot is full on that day for that provider
                if (timeslotIsSame && dayIsSame && providerIsSame) {
                    return;
                }
            }
        } 

        

        appointments[size] = appointment;
        size+=1;
    }

    public void remove(Appointment appointment) {
        // Remove without arraycopy

        int index = find(appointment);
        if (index == NOT_FOUND) {
            return;
        }

        // Shift all elements after the found indenx down 1
        // effectively keeping the order but removing the element
        for (int i = index; i < size - 1; i++) {
            appointments[i] = appointments[i + 1];
        }

        size--;
    }


    public void sortBy(String fields) {

        int n = this.appointments.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Appointment a = appointments[i];
                Appointment b = appointments[j];
                if (fields.equals("PA")) {
                    // Sort by appointment date, then time, then provider name. (not default behavior)
                    if (compareWith(a, b, fields) < 0) {
                        Appointment temp = appointments[j];
                        appointments[j] = appointments[j + 1];
                        appointments[j + 1] = temp;
                    }
                } else if (fields.equals("PP")) {
                    if (compareWith(a, b, fields) < 0) {
                        Appointment temp = appointments[j];
                        appointments[j] = appointments[j + 1];
                        appointments[j + 1] = temp;
                    }
                } else if (fields.equals("PL")) {
                    if (compareWith(a, b, fields) < 0) {
                        Appointment temp = appointments[j];
                        appointments[j] = appointments[j + 1];
                        appointments[j + 1] = temp;
                    }
                }
            }
        }

    }

    public int compareWith(Appointment a, Appointment b, String field){

        // If either appointment is null return an error
        if (a == null || b == null) {
            return NOT_FOUND;
        }

        if (field.equals("PA")) {
            // Sort by appointment date, then time, then provider name. (not default behavior)
            
            // Perform comparisons ahead of time
            int dateComparison = a.getDate().compareTo(b.getDate());
            
            // If data comparison results in a difference, return its value
            if (dateComparison != 0) {
                return dateComparison;
            }
            
            // If data comparison results in 0, then compare the appointment times
            int timeComparison = a.getTimeslot().compareTo(b.getTimeslot());
            
            // If time comparison results in a difference, return its value
            if (timeComparison != 0) {
                return timeComparison;
            }
            
            // If time comparison results in 0, then compare the provider
            int providerComparison = a.getProvider().compareTo(b.getProvider());
            
            // If provider comparison results in a difference, return its value
            if (providerComparison != 0) {
                return providerComparison;
            }

            // Otherwise the appointments are identical so just return either or.
            return 1;
            
        }

        if (field.equals("PP")) {
            
            // Compare the patient first
            // Internally the patient class's compareTo method already sorts
            // by last name, then first name, then dob
            int patientComparison = a.getPatient().compareTo(b.getPatient());

            // If the patient comparison results in a difference, return its value
            if (patientComparison != 0) {
                return patientComparison;
            }

            // If the patient comparison results in 0, then compare the appointment dates.
            int dateComparison = a.getDate().compareTo(b.getDate());

            // If the appointment date comparison results in a difference, return its value
            if (dateComparison != 0) {
                return dateComparison;
            }

            // If the appointment date comparison results in 0, then compare the appointment times
            int timeComparison = a.getTimeslot().compareTo(b.getTimeslot());

            // If the appointment time comparison results in a difference, return its value
            if (timeComparison != 0) {
                return timeComparison;
            }

            // Otherwise the appointments are identical so just return either or.
            return 1;

        }

        if (field.equals("PL")) {

            // This is to sort by county name, then the appointment date and time.
            // I already built this order of comparison directly into the Appointment's compareTo object.
            return a.compareTo(b);

        }

        else{
            // The field choice is invalid.
            return NOT_FOUND;
        }

    }

    @Override
    public String toString() {
        String s = "";
        
        for (int i = 0; i < size; i++) {
            s += appointments[i].toString() + "\n";
        }
        
        return s;
    }
}