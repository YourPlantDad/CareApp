package ZorgApp.patients;

import ZorgApp.medication.Medication;
import ZorgApp.medication.MedicationManager;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * The Patient class represents a patient with attributes such as name, birthdate, weight, and height.
 * It includes a MedicationManager instance to manage the patient's medications and provides methods to
 * interact with this data, allowing controlled access to patient information and medications while hiding
 * implementation details.
 */


public class Patient {
    private final int id;
    private String surname;
    private String firstName;
    private LocalDate dateOfBirth;
    private int age;
    private double weight;
    private double height;
    private double bmi;
    private final MedicationManager medicationManager;

    public Patient(int id, String surname, String firstName, LocalDate dateOfBirth, double weight, double height) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.height = height;
        this.bmi = calculateBMI();
        this.medicationManager = new MedicationManager();
    }

    private double calculateBMI() {
        bmi = weight / (height * height);
        return bmi;
    }

    private void calculateAge() {
        LocalDate currentDate = LocalDate.now();
        age = Period.between(dateOfBirth, currentDate).getYears();
    }

    public void editPatientInformation() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selecteer de informatie die je wilt aanpassen:");
        System.out.println("1. Achternaam");
        System.out.println("2. Voornaam");
        System.out.println("3. Geboortedatum");
        System.out.println("4. Gewicht");
        System.out.println("5. Lengte");
        System.out.println("6. Stop met het bewerken van patiënt informatie");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Voer nieuwe achternaam in: ");
                surname = scanner.nextLine();
                break;
            case 2:
                System.out.print("Voer nieuwe voornaam in: ");
                firstName = scanner.nextLine();
                break;
            case 3:
                System.out.print("Voer nieuwe geboortedatum in (dd-MM-yyyy): ");
                String birthDateStr = scanner.nextLine();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                dateOfBirth = LocalDate.parse(birthDateStr, dateFormatter);
                break;
            case 4:
                System.out.print("Voer nieuw gewicht in kilogrammen in: ");
                weight = scanner.nextDouble();
                break;
            case 5:
                System.out.print("Voer nieuwe lengte in centimeters in: ");
                height = scanner.nextDouble();
                break;
            case 6:
                System.out.println("Het bewerk patiëntgegevens scherm wordt gesloten.");
                return;
            default:
                System.out.println("Ongeldige keuze.");
        }
        System.out.println("Patiëntgegevens van " + fullName() + " geüpdated");
    }

    public void viewData() {
        System.out.format("===== Patient id=%d ==============================\n", id);
        System.out.format("%-17s %s\n", "Achternaam:", surname);
        System.out.format("%-17s %s\n", "Voornaam:", firstName);
        System.out.format("%-17s %s\n", "Geboortedatum:", dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.format("%-17s %s kg\n", "Gewicht", weight);
        System.out.format("%-17s %s cm\n", "Lengte", height);

        calculateBMI();
        System.out.format("%-17s %.1f \n", "BMI:", bmi);

        calculateAge();
        System.out.format("%-17s %d years\n", "Leeftijd:", age);
    }

    public void addMedication(String name, String dosage, String frequency, Medication.MedicationType type) {
        medicationManager.addMedication(name, dosage, frequency, type);
    }

    public void viewAllMedications() {
        medicationManager.viewMedications(false); // View all medications
    }

    public void viewNarcoticMedications() {
        medicationManager.viewMedications(true); // View only narcotic medications
    }

    public String fullName() {
        return String.format("%s %s [%s]", firstName, surname, dateOfBirth.toString());
    }

    public int getId() {
        return id;
    }
}
