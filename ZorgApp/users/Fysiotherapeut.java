package ZorgApp.users;

import ZorgApp.medication.Medication;
import ZorgApp.patients.Patient;

/**
 * The Fysiotherapeut class is a subclass of User representing a physical therapist. This role has permissions
 * to view and add only non-narcotic medications and cannot access narcotic medications. It customizes the
 * behavior of medication-related methods to respect these permissions.
 */


public class Fysiotherapeut extends User {

    public Fysiotherapeut(int id, String name) {
        super(id, name);
    }

    @Override
    public void viewMedication(Patient patient) {
        System.out.println("Je hebt niet de juiste rechten om patiënten hun voorgeschreven medicatie te bekijken.");
    }

    @Override
    public boolean canAddMedication(Medication.MedicationType type) {
        return false;
    }

    @Override
    public void addMedication(Patient patient, String medicationName, String dosage, String frequency, Medication.MedicationType type) {
        System.out.println("Je hebt niet de juiste rechten om patiënten medicatie voor te schrijven.");
    }
}
