package CareApp.users;

import CareApp.medication.Medication;
import CareApp.patients.Patient;

/**
 * The Dentist class is a subclass of User representing a dentist. This role is restricted to viewing and
 * adding only narcotic medications and cannot manage non-narcotic medications. The class customizes the
 * behavior of medication-related methods based on these permissions.
 */


public class Dentist extends User {

    public Dentist(int id, String name) {
        super(id, name);
    }

    @Override
    public void viewMedication(Patient patient) {
        System.out.println("Retrieving the prescribed anesthetic medication from " + patient.fullName() + ".");
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
            System.out.println("Narcotic medication " + medicationName + " correctly prescribed.");
        } else {
            System.out.println("Dentists may only prescribe anesthetic medications.");
        }
    }
}