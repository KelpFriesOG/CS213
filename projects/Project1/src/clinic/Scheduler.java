import java.util.Scanner;

public class Scheduler {

    private Appointment extractAppointment(String[] commandBody) throws IllegalArgumentException {

        // Extract portions of the appointment and create respective objects
        Date apptDate = new Date(commandBody[1]);
        Timeslot timeslot = Timeslot.values()[Integer.parseInt(commandBody[2])-1];
        String fName = format(commandBody[3]);
        String lName = format(commandBody[4]);
        Date dob = new Date(commandBody[5]);
        Provider provider = Provider.valueOf(commandBody[6].toUpperCase());

        // Combine objects into an appointment
        Appointment appt = new Appointment(apptDate, timeslot, new Profile(fName, lName, dob), provider);

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
                    System.err.println(e.getMessage());
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
                    System.err.println(e.getMessage());
                    return;
                }

                break;
            case "PA":
                
                appointments.sortBy("PA");

                System.out.println(appointments.toString());

                break;
            case "PP":
                
                appointments.sortBy("PP");

                System.out.println(appointments.toString());

                break;
            case "PL":
                
                appointments.sortBy("PL");

                System.out.println(appointments.toString());

                break;
            case "PS":
                
                appointments.sortBy("PS");

                System.out.println(appointments.toString());

                break;
            case "Q":
                // Quit the scheduler
                break;

            default:
                System.out.println("Invalid command.");

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
