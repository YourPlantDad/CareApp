package ZorgApp.users;

import ZorgApp.medication.Medication;
import ZorgApp.patients.Patient;

/**
 * The Huisarts class is a subclass of User representing a general practitioner. This role has permissions
 * to add and view both narcotic and non-narcotic medications, allowing unrestricted medication management
 * for patients.
 */

public class Huisarts extends User {

    public Huisarts(int id, String name) {
        super(id, name);
    }

    @Override
    public void viewMedication(Patient patient) {
        // Huisarts should be able to view all medications
        System.out.println("De voorgeschreven medicatie van " + patient.fullName() + " wordt opgehaald.");
        patient.viewAllMedications();
    }

    @Override
    public boolean canAddMedication(Medication.MedicationType type) {
        // Huisarts can add both narcotic and non-narcotic medications
        return true;
    }

    @Override
    public void addMedication(Patient patient, String medicationName, String dosage, String frequency, Medication.MedicationType type) {
        // No need to check type since Huisarts can add all types
        patient.addMedication(medicationName, dosage, frequency, type);
        System.out.println("Medicatie " + medicationName + " succesvol voorgeschreven aan " + patient.fullName());
    }
}
