package ZorgApp.users;

import ZorgApp.medication.Medication;
import ZorgApp.patients.Patient;

/**
 * The Apotheek class is a subclass of User representing a pharmacist. This role has permissions to view
 * all medications for patients but may be restricted in terms of adding medications, depending on the
 * application’s specific rules.
 */


public class Apotheek extends User {

    public Apotheek(int id, String name) {
        super(id, name);
    }

    @Override
    public void viewMedication(Patient patient) {
        System.out.println("De voorgeschreven medicatie van " + patient.fullName() + " wordt opgehaald.");
        patient.viewAllMedications();
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
