package ZorgApp.medication;

/**
 * The Medication class represents a single medication, storing details such as name, dosage, frequency, and type
 * (narcotic or non-narcotic). It provides essential getter methods in the form of getMedicationType() and medicationDetails()
 * for safe access to these details while keeping fields encapsulated.
 */


public class Medication {
    public enum MedicationType {
        NARCOTIC, NON_NARCOTIC
    }

    private final String medicationName;
    private final String medicationDosage;
    private final String medicationFrequency;
    private final MedicationType medicationType;

    Medication(String medicationName, String medicationDosage, String medicationFrequency, MedicationType medicationType) {
        this.medicationName = medicationName;
        this.medicationDosage = medicationDosage;
        this.medicationFrequency = medicationFrequency;
        this.medicationType = medicationType;
    }

    MedicationType getMedicationType() {
        return medicationType;
    }

    void medicationDetails() {
        System.out.println("Naam: " + medicationName);
        System.out.println("Dosering: " + medicationDosage);
        System.out.println("Frequentie: " + medicationFrequency);
        System.out.println("Type: " + medicationType);
    }
}