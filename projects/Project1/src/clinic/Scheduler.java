import java.util.Scanner;

public class Scheduler {

    private void processCommand(String[] commandBody, List appointments) {

        System.out.println(commandBody[0]);

        
        switch (commandBody[0]) {

            case "S":
                
                try{
                    // Extract portions of the appointment and create respective objects
                    Date apptDate = new Date(commandBody[1]);
                    Timeslot timeslot = Timeslot.values()[Integer.parseInt(commandBody[2])];
                    String fName = format(commandBody[3]);
                    String lName = format(commandBody[4]);
                    Date dob = new Date(commandBody[5]);
                    Provider provider = Provider.valueOf(commandBody[6].toUpperCase());

                    // Combine objects into an appointment
                    Appointment appt = new Appointment(apptDate, timeslot, new Profile(fName, lName, dob), provider);

                    // Add appointment to list
                    appointments.add(appt);

                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                    return;
                }

                break;
            case "C":
                //TODO: Cancel an existing appointment
                break;
            case "R":
                //TODO: Reschedule an existing appointment
                break;
            case "PA":
                
                appointments.sortBy("PA");

                System.out.println(appointments.toString());

                break;
            case "PP":
                // TODO: Print all appointments sorted by patient (by last name, first name, dob, then appointment date and time)
                break;
            case "PL":
                // TODO: Print all appointments sorted by county name, then the appointment date and time
                break;
            case "PS":
                // TODO: Print the billing statments for all patients assuming all appointments have been completed
                break;
            case "Q":
                // TODO: Quit the scheduler
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
