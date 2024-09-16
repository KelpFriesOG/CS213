public class List {

    private Appointment[] appointments;
    private int size; // number of appointments in the array

    public static int NOT_FOUND = -1;
    public static int INIT_CAPACITY = 4;
    public static int N_EXPAND = 4;


    public List() {
        appointments = new Appointment[INIT_CAPACITY]; // initial capacity
        size = 0;
    }

    private int find(Appointment appointment) {
        for (int i = 0; i < size; i++) {
            if (appointments[i].equals(appointment)) {
                return i;
            }
        }
        return NOT_FOUND; // not found
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
        if (size == appointments.length) {
            grow();
        }
        appointments[size] = appointment;
        size++;
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

    public void printByPatient() {
        // implement sorting by patient profile, date/timeslot
        // for now, just print the appointments
        for (int i = 0; i < size; i++) {
            System.out.println(appointments[i]);
        }
    }

    public void printByLocation() {
        // implement sorting by location
        // for now, just print the appointments
        for (int i = 0; i < size; i++) {
            System.out.println(appointments[i]);
        }
    }

    public void sortBy(String field) {

        // Valid fields: patient, location, appointment (case-insensitive)
        field = field.toLowerCase();

        if (field.equals("patient")) {
            // TODO: Implement in-place sorting by patient profile, date/timeslot
        }

        if (field.equals("location")) {
            // TODO: Implement in-place sorting by location
        }

        if (field.equals("appointment")) {
            // TODO: Implement in-place sorting by county, date/timeslot
        }

    }
}