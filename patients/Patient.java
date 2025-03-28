package CareApp.patients;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import CareApp.medication.Medication;
import CareApp.medication.MedicationManager;

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

        System.out.println("Select the information you would like to edit:");
        System.out.println("1. Surname");
        System.out.println("2. First name");
        System.out.println("3. Date of birth");
        System.out.println("4. Weight");
        System.out.println("5. Height");
        System.out.println("6. Stop editing patient information");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter new last name: ");
                surname = scanner.nextLine();
                break;
            case 2:
                System.out.print("Enter new first name: ");
                firstName = scanner.nextLine();
                break;
            case 3:
                System.out.print("Enter new date of birth (dd-mm-yyyy): ");
                String birthDateStr = scanner.nextLine();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                dateOfBirth = LocalDate.parse(birthDateStr, dateFormatter);
                break;
            case 4:
                System.out.print("Enter new weight in kilograms: ");
                weight = scanner.nextDouble();
                break;
            case 5:
                System.out.print("Enter new length in centimeters: ");
                height = scanner.nextDouble();
                break;
            case 6:
                System.out.println("The edit patient-data screen will be closed.");
                return;
            default:
                System.out.println("Invalid choice.");
        }
        System.out.println("Patient data of " + fullName() + " has been updated.");
    }

    public void viewData() {
        calculateBMI();
        calculateAge();
        
        System.out.format("===== Patient id=%d ==============================\n", id);
        System.out.format("%-17s %s\n", "Surname:", surname);
        System.out.format("%-17s %s\n", "First name:", firstName);
        System.out.format("%-17s %s\n", "Date of birth:", dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.format("%-17s %s kg\n", "Weight", weight);
        System.out.format("%-17s %s cm\n", "Height", height);
        System.out.format("%-17s %.1f \n", "BMI:", bmi);
        System.out.format("%-17s %d years\n", "Age:", age);
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
