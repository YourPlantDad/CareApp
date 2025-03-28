package CareApp.users;

import CareApp.medication.Medication;
import CareApp.patients.Patient;

/**
 * The Physiotherapist class is a subclass of User representing a physical therapist. This role has permissions
 * to view and add only non-narcotic medications and cannot access narcotic medications. It customizes the
 * behavior of medication-related methods to respect these permissions.
 */


public class Physiotherapist extends User {

    public Physiotherapist(int id, String name) {
        super(id, name);
    }

    @Override
    public void viewMedication(Patient patient) {
        System.out.println("You do not have the proper permissions to view patients' prescribed medications.");
    }

    @Override
    public boolean canAddMedication(Medication.MedicationType type) {
        return false;
    }

    @Override
    public void addMedication(Patient patient, String medicationName, String dosage, String frequency, Medication.MedicationType type) {
        System.out.println("You do not have the proper rights to prescribe medication to patients.");
    }
}
