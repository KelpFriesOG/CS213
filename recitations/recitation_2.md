Carefully check the validity of all dates upon `Appointment` creation.

- When `PS` is called then we create finalized `Visit` objects which are a linked list of `Appointment` objects.

Make sure to convert the `toString()` method for each appropriate class so that the appointment as a whole has a user-friendly string representation.

Goal representation: `dd/mm/yyyy HH:MM AM/PM [patient.fname] [patient.lname] [patient.dob in dd/mm/yyyy form] `

**For enums make all the constructors private.**

**implement the charge() function in the linked list by calling charge() on each node recursively until you hit the end of the list**

**The `Visit` class is only used when PS is called.**

**After `PS` is called then you**

Java decides what method to run based on the declared type of
the object calling it.