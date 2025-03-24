package ZorgApp.medication;

import java.util.ArrayList;
import java.util.List;

/**
 * The MedicationManager class is responsible for managing a patient's medications. It maintains a list of
 * Medication objects and provides methods to add medications to this list. MedicationManager encapsulates
 * all operations related to organizing and storing medications, isolating these details from other parts
 * of the application.
 */


public class MedicationManager {
    private final List<Medication> medications;

    public MedicationManager() {
        this.medications = new ArrayList<>();
    }

    // Add medication to the list
    public void addMedication(String name, String dosage, String frequency, Medication.MedicationType type) {
        Medication medication = new Medication(name, dosage, frequency, type);
        medications.add(medication);
    }

    // View medications, with an option to filter for narcotics only
    public void viewMedications(boolean narcoticOnly) {
        if (medications.isEmpty()) {
            System.out.println("Er zijn geen recepten.");
            return;
        }

        for (Medication medication : medications) {
            if (!narcoticOnly || medication.getMedicationType() == Medication.MedicationType.NARCOTIC) {
                medication.medicationDetails();
                System.out.println("----------------------------");
            }
        }
    }


}
