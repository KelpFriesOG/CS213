import java.util.Scanner;

public class Scheduler {

    private void processCommand(String[] commandBody, List appointments) {

        switch (commandBody[0]) {

            case "S":
                //TODO: Schedule an appointment
                Date apptDate = new Date(commandBody[1]);
                Timeslot timeslot = Timeslot.values()[Integer.parseInt(commandBody[2])];
                String fName = commandBody[3];
                String lName = commandBody[4];
                Date dob = new Date(commandBody[5]);
                String provider = commandBody[6];

                Timeslot ts = Timeslot.values()[timeslot];

                

                break;
            case "C":
                //TODO: Cancel an existing appointment
                break;
            case "R":
                //TODO: Reschedule an existing appointment
                break;
            case "PA":
                //TODO: Print all appointments sorted by date, time, then provider's name
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

    public void run() {
        System.out.println("Scheduler is running.");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        List appointments = new List();
       

        String input;
        while (!(input = scanner.nextLine()).equals("Q")) {
            String[] commandBody = input.split("\\s+");
            processCommand(commandBody, appointments);
        }

        scanner.close();
        System.out.println("Scheduler terminated.");
    }

}
