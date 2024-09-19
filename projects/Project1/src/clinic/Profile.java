public class Profile implements Comparable<Profile> {

    private String fname;
    private String lname;
    private Date dob;

    // No-arg constructor
    public Profile() {
        fname = "John";
        lname = "Doe";
        dob = new Date();
    }

    // Parameterized constructor
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    // Getters and setters
    public String getFName() {
        return fname;
    }

    public void setFName(String fname) {
        this.fname = fname;
    }

    public String getLName() {
        return lname;
    }

    public void setLName(String lname) {
        this.lname = lname;
    }

    // Overrided methods
    @Override
    public String toString() {
        return fname + " " + lname + ", " + dob;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile other = (Profile) obj;
            return fname.equals(other.fname) && lname.equals(other.lname) && dob.equals(other.dob);
        }
        return false;
    }

    public boolean isValid() {
        // Ensure that the provided date is valid and the birthday is not today
        // or in the future.
        return dob.isValid() && dob.compareTo(new Date()) != 0 && dob.compareTo(new Date()) != 1;
    }

    @Override
    public int compareTo(Profile other) {

        // Compare first by last name, then by first name, then by dob
        // The letters are compared in standard lexicographic order ( a > b > c)
        // The date of birth is compared in chronological order ( 10/27/2002 < 09/08/2008)
    
        if (lname.compareTo(other.lname) != 0) {
            return lname.compareTo(other.lname);
        } else if (fname.compareTo(other.fname) != 0) {
            return fname.compareTo(other.fname);
        } else {
            return dob.compareTo(other.dob); // Ensure this compares chronologically in Date class
        }
    }

}
