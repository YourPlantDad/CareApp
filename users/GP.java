package CareApp.users;

import CareApp.medication.Medication;
import CareApp.patients.Patient;

/**
 * The GP class is a subclass of User representing a general practitioner. This role has permissions
 * to add and view both narcotic and non-narcotic medications, allowing unrestricted medication management
 * for patients.
 */

public class GP extends User {

    public GP(int id, String name) {
        super(id, name);
    }

    @Override
    public void viewMedication(Patient patient) {
        // GP should be able to view all medications
        System.out.println("Retrieving the prescribed medication for " + patient.fullName() + ".");
        patient.viewAllMedications();
    }

    @Override
    public boolean canAddMedication(Medication.MedicationType type) {
        // GP can add both narcotic and non-narcotic medications
        return true;
    }

    @Override
    public void addMedication(Patient patient, String medicationName, String dosage, String frequency, Medication.MedicationType type) {
        // No need to check type since GP can add all types
        patient.addMedication(medicationName, dosage, frequency, type);
        System.out.println("Medication " + medicationName + " successfully prescribed to " + patient.fullName());
    }
}
