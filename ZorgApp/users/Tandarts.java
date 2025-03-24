package ZorgApp.users;

import ZorgApp.medication.Medication;
import ZorgApp.patients.Patient;

/**
 * The Tandarts class is a subclass of User representing a dentist. This role is restricted to viewing and
 * adding only narcotic medications and cannot manage non-narcotic medications. The class customizes the
 * behavior of medication-related methods based on these permissions.
 */


public class Tandarts extends User {

    public Tandarts(int id, String name) {
        super(id, name);
    }

    @Override
    public void viewMedication(Patient patient) {
        System.out.println("De voorgeschreven verdovende medicatie van " + patient.fullName() + " wordt opgehaald.");
        patient.viewNarcoticMedications();
    }

    @Override
    public boolean canAddMedication(Medication.MedicationType type) {
        return type == Medication.MedicationType.NARCOTIC;
    }

    @Override
    public void addMedication(Patient patient, String medicationName, String dosage, String frequency, Medication.MedicationType type) {
        if (canAddMedication(type)) {
            patient.addMedication(medicationName, dosage, frequency, type);
            System.out.println("Verdovende medicatie " + medicationName + " correct voorgeschreven.");
        } else {
            System.out.println("Tandartsen mogen alleen verdovende medicatie voorschrijven.");
        }
    }
}