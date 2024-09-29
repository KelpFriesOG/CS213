public class MedicalRecord{

    private Patient[] patients;
    private int size;
    public static final int INIT_CAPACITY = 4;


    // No-arg Constructor
    public MedicalRecord() {
        patients = new Patient[INIT_CAPACITY];
        size = 0;
    }


    // Parameterized Constructor
    public MedicalRecord(Patient[] patients) {
        this.patients = patients;
        size = patients.length;
    }

    

    private void grow() {

        Patient[] newPatients = new Patient[patients.length + INIT_CAPACITY];

        for (int i = 0; i < patients.length; i++) {
            newPatients[i] = patients[i];
        }

        patients = newPatients;
    }

    public void add(Patient patient) {
        if (size == patients.length) {
            grow();
        }
        patients[size] = patient;
        size++;
    }


}
