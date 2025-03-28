package CareApp.users;

import CareApp.medication.Medication;
import CareApp.patients.Patient;

/**
 * The Pharmacist class is a subclass of User representing a pharmacist. This role has permissions to view
 * all medications for patients but may be restricted in terms of adding medications, depending on the
 * application’s specific rules.
 */


public class Pharmacist extends User {

    public Pharmacist(int id, String name) {
        super(id, name);
    }

    @Override
    public void viewMedication(Patient patient) {
        System.out.println("Retrieving the prescribed medication for " + patient.fullName() + ".");
        patient.viewAllMedications();
    }

    @Override
    public boolean canAddMedication(Medication.MedicationType type) {
        return false;
    }

    @Override
    public void addMedication(Patient patient, String medicationName, String dosage, String frequency, Medication.MedicationType type) {
        System.out.println("You do not have the right to prescribe medication to patients.");
    }
}
