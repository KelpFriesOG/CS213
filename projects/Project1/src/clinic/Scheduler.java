import java.util.Scanner;

public class Scheduler {

    private static final boolean DEBUG = true;
    private Appointment extractAppointment(String[] commandBody) throws IllegalArgumentException {

        // Create variables that could potential throw exceptions as nulls
        Date apptDate = null;
        Date dob = null;
        Timeslot timeslot = null;
        Provider provider = null;
        Profile patient = null;

        // Create variables for the first and last name (which cannot be incorrect)
        String fName = format(commandBody[3]);
        String lName = format(commandBody[4]);

        // Deal with potential invalid calendar date for appointment
        try {
            apptDate = new Date(commandBody[1]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Appointment date: " + e.getMessage());
        }

        // Even if the appt date is valid, ensure it is in the future
        if (apptDate.compareTo(new Date()) <= 0) {
            throw new IllegalArgumentException("Appointment date: " + apptDate + " is today or a date before today.");
        }

        // If the date is in the future, still ensure that it is within six months
        Date today = new Date();
        int yearDiff = apptDate.getYear() - today.getYear();
        int monthDiff = apptDate.getMonth() - today.getMonth();
        int totalDiff = yearDiff * 12 + monthDiff;
        
        if (totalDiff > 6) {
            throw new IllegalArgumentException("Appointment date: " + apptDate + " is not within six months.");
        }

        // Deal with a potential timeslot mismatch (error must be customized then rethrown)
        try {
            timeslot = Timeslot.values()[Integer.parseInt(commandBody[2])-1];
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(commandBody[2] + " is not a valid time slot.");
        }
        
        // Deal with a potential invalid calendar date for dob
        try{
            dob = new Date(commandBody[5]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Patient dob: " + e.getMessage());
        }
        
        // Deal with potential invalid data for profile (i.e. dob is today or in the future)
        try {
            patient = new Profile(fName, lName, dob);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        // Deal with potential invalid provider name
        try {
            provider = Provider.valueOf(commandBody[6].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(commandBody[6] + "- provider doesn't exist.");
        }

        // Combine objects into an appointment
        Appointment appt = new Appointment(apptDate, timeslot, patient, provider);

        return appt;
    }

    private void processCommand(String[] commandBody, List appointments) {

        // System.out.println(commandBody[0]);

        
        switch (commandBody[0]) {

            case "S":
                
                try{
                    Appointment appt = extractAppointment(commandBody);

                    // Add appointment to list
                    appointments.add(appt);

                } catch (IllegalArgumentException e) {
                    if (DEBUG) System.err.println(e.getMessage());
                    return;
                }

                break;
            case "C":
                // Cancel an existing appointment
                try {
                    Appointment appt = extractAppointment(commandBody);
                    if (appointments.contains(appt)) {
                        appointments.remove(appt);
                    }

                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                    return;
                }

                break;
            case "R":
                //Reschedule an existing appointment
                try {
                    Appointment appt = extractAppointment(commandBody);
                    // Find an appointment in the list with the same profile, day, and provider
                    for (int i = 0; i < appointments.getSize(); i++) {
                        Appointment current_appt = appointments.get(i);
                        if (appt.getDate().equals(current_appt.getDate()) && appt.getProvider().equals(appt.getProvider())
                        && appt.getPatient().equals(current_appt.getPatient())) {
                            // Remove the old appointment
                            appointments.remove(current_appt);
                            // Add the new appointment
                            appointments.add(appt);       
                            // I cannot modify the old appointment as it should be immmutable.                     
                        }
                    }
                } catch (Exception e) {
                    if (DEBUG) System.err.println(e.getMessage());
                    return;
                }

                break;
            case "PA":
                
                try{

                    appointments.sortBy("PA");

                    System.out.println(appointments.toString());

                } catch (IllegalArgumentException e) {

                    System.err.println(e.getMessage());
                    return;

                }

                break;

            case "PP":
                
                try{

                    appointments.sortBy("PP");

                    System.out.println(appointments.toString());

                } catch (IllegalArgumentException e) {

                    System.err.println(e.getMessage());
                    return;

                }

                break;

            case "PL":
                
                try{

                    appointments.sortBy("PL");

                    System.out.println(appointments.toString());

                } catch (IllegalArgumentException e) {

                    System.err.println(e.getMessage());
                    return;

                }

            case "PS":
                
                try{

                    appointments.sortBy("PS");

                    System.out.println(appointments.toString());

                } catch (IllegalArgumentException e) {

                    System.err.println(e.getMessage());
                    return;

                }

                break;

            case "Q":
                // Quit the scheduler
                break;

            default:
                System.out.println("Invalid command!");

        }
    
        

    }

    private String format(String s) {
        
        // If the string is empty return it
        if (s.isEmpty()) {
            return s;
        }

        // If the string is a single character return it in upper case
        if (s.length() == 1) {
            return s.toUpperCase();
        }

        // Otherwise return the first character in upper case and the rest in lower case
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public void run() {
        System.out.println("Scheduler is running.");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        List appointments = new List();
       

        String input;
        while (!(input = scanner.nextLine()).equals("Q")) {
            String[] commandBody = input.split(",");
            processCommand(commandBody, appointments);
        }

        scanner.close();
        System.out.println("Scheduler terminated.");
    }

}
