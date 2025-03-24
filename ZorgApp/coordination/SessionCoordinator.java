package ZorgApp.coordination;

import ZorgApp.medication.Medication;
import ZorgApp.patients.Patient;
import ZorgApp.users.User;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The SessionCoordinator class manages the interaction between the selected user and patients, controlling
 * the flow of actions based on user permissions. It handles patient selection, viewing, editing, and medication
 * management tasks, and allows each User subclass to interact with patients and medications
 * according to their specific permissions.
 */


class SessionCoordinator {
    private Patient patient;
    private final User user;
    private List<Patient> patients;

    static final int STOP = 0;
    static final int VIEW = 1;
    static final int EDIT_PATIENT = 2;
    static final int MEDICATIONS = 3;
    static final int VIEW_ALL_PATIENTS = 4;
    static final int SWITCH_PATIENT = 5;
    static final int ADD_PATIENT = 6;

    public SessionCoordinator(User user) {
        this.user = user;
        initializePatients();
    }

    private void initializePatients() {
        patients = new ArrayList<>();
        patients.add(new Patient(1, "Van Puffelen", "Pierre", LocalDate.of(2000, 2, 29), 80, 1.81));
        patients.add(new Patient(2, "Prieto", "Evelyn", LocalDate.of(1991, 1, 26), 72, 1.59));
        patients.add(new Patient(3, "De vries", "Mats", LocalDate.of(2000, 1, 15), 70, 1.70));
        patients.add(new Patient(4, "Smith", "Julian", LocalDate.of(1999, 9, 11), 45, 1.50));
        patients.add(new Patient(5, "Neutel", "Niels", LocalDate.of(1990, 12, 1), 64, 1.67));
        this.patient = patients.getFirst();
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean nextCycle = true;

        while (nextCycle) {
            System.out.format("%s\n", "=".repeat(80));
            System.out.format("Huidige patiënt: %s\n", patient.fullName());
            System.out.format("%d: Stop het programma\n", STOP);
            System.out.format("%d: Bekijk huidige patiënt gegevens\n", VIEW);
            System.out.format("%d: Bewerk huidige patiëntinformatie\n", EDIT_PATIENT);
            System.out.format("%d: Patiënt medicatie scherm\n", MEDICATIONS);
            System.out.format("%d: Bekijk alle patiënten\n", VIEW_ALL_PATIENTS);
            System.out.format("%d: Wissel van geselecteerde patiënt\n", SWITCH_PATIENT);
            System.out.format("%d: Nieuwe patiënt toevoegen\n", ADD_PATIENT);
            System.out.print("Kies een optie: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case STOP:
                    nextCycle = false;
                    break;
                case VIEW:
                    patient.viewData();
                    break;
                case EDIT_PATIENT:
                    patient.editPatientInformation();
                    break;
                case MEDICATIONS:
                    handleMedications();
                    break;
                case VIEW_ALL_PATIENTS:
                    viewAllPatients();
                    break;
                case SWITCH_PATIENT:
                    switchPatient();
                    break;
                case ADD_PATIENT:
                    addNewPatient();
                    break;
                default:
                    System.out.println("Ongeldige keuze.");
                    break;
            }
        }
    }

    // Handle medication-related tasks
    public void handleMedications() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1: Nieuwe medicatie toewijzen");
        System.out.println("2: Bekijk voorgeschreven medicatie");
        System.out.println("Kies een optie: ");
        int medicationChoice = scanner.nextInt();
        scanner.nextLine();

        if (medicationChoice == 1) {
            System.out.print("Medicatie naam: ");
            String name = scanner.nextLine();
            System.out.print("Dosering in milligrammen: ");
            String dosage = scanner.nextLine();
            System.out.print("Inname frequentie: ");
            String frequency = scanner.nextLine();
            System.out.print("Type medicatie (1 for VERDOVEND, 2 for NIET VERDOVEND): ");
            int typeChoice = scanner.nextInt();
            Medication.MedicationType type = (typeChoice == 1) ? Medication.MedicationType.NARCOTIC : Medication.MedicationType.NON_NARCOTIC;

            if (user.canAddMedication(type)) {
                user.addMedication(patient, name, dosage, frequency, type);
            } else {
                System.out.println("Je hebt niet de juiste permissie om dit type medicatie voor te schrijven.");
            }
        } else if (medicationChoice == 2) {
            user.viewMedication(patient);
        } else {
            System.out.println("Ongeldige keuze.");
        }
    }

    private void addNewPatient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Voer achternaam in: ");
        String lastName = scanner.next();
        System.out.print("Voer voornaam in: ");
        String firstName = scanner.next();
        System.out.print("Voer geboortedatum in (dd-MM-yyyy): ");
        scanner.nextLine();
        String birthDateStr = scanner.nextLine().trim();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse(birthDateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Ongeldige geboortedatum format. Gebruik dd-MM-yyyy.");
            return;
        }
        System.out.print("Voer gewicht in in kilogrammen): ");
        double weight = scanner.nextDouble();
        System.out.print("Voer lengte in in centimeters: ");
        double height = scanner.nextDouble();
        int newPatientID = patients.size() + 1;
        Patient newPatient = new Patient(newPatientID, lastName, firstName, birthDate, weight, height);
        patients.add(newPatient);
        System.out.println("Patiënt " + firstName + " " + lastName + " toegevoegd.");
    }

    private void viewAllPatients() {
        System.out.println("Een lijst van patiënten wordt opgehaald.");
        for (Patient patient : patients) {
            System.out.println(patient.fullName());
        }
    }

    private void switchPatient() {
        System.out.println("Selecteer een nieuwe actieve patiënt:");
        for (Patient patient : patients) {
            System.out.format("[%d] %s\n", patient.getId(), patient.fullName());
        }

        Scanner scanner = new Scanner(System.in);
        int selectedPatientID = scanner.nextInt();

        Patient selectedPatient = null;
        for (Patient p : patients) {
            if (p.getId() == selectedPatientID) {
                selectedPatient = p;
                break;
            }
        }

        if (selectedPatient != null) {
            patient = selectedPatient;
            System.out.println("De huidige patiënt is nu " + patient.fullName() + ".");
        } else {
            System.out.println("Ongeldig ID geselecteerd.");
        }
    }


}
