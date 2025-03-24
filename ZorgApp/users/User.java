package ZorgApp.users;

import ZorgApp.medication.Medication;
import ZorgApp.patients.Patient;

/**
 * The User class is an abstract base class for different types of healthcare professionals.
 * It defines the common properties (userName and userID) and abstract methods for viewing and adding
 * medications, which are customized in each subclass according to specific permissions.
 */


public abstract class User {
    private final String userName;
    private final int userID;

    public User(int id, String name) {
        this.userID = id;
        this.userName = name;
    }

    public abstract void viewMedication(Patient patient);
    public abstract boolean canAddMedication(Medication.MedicationType type);
    public abstract void addMedication(Patient patient, String medicationName, String dosage, String frequency, Medication.MedicationType type);

    public String getUserName() {
        return userName;
    }

    public int getUserID() {
        return userID;
    }
}