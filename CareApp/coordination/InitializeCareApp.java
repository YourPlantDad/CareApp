package CareApp.coordination;

import CareApp.users.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The InitializeZorgApp class is the main entry point for the application, allowing the user to select a role
 * (e.g., GP, Dentist, Pharmacist) and begin a session. It initializes the list of users for each role
 * and provides an interactive console-based interface for selecting a role and user.
 * After selection, InitializeZorgApp creates a SessionCoordinator to manage the session for the selected user.
 */

class InitializeCareApp {
    public static void main(String[] args) {
        List<User> gpUsers = new ArrayList<>();
        gpUsers.add(new GP(1, "Dr. Duin, GP"));
        gpUsers.add(new GP(2, "Dr. Badell, GP"));

        List<User> physiotherapistUsers = new ArrayList<>();
        physiotherapistUsers.add(new Physiotherapist(1, "Dr. van de Lagemaat, Physiotherapist"));
        physiotherapistUsers.add(new Physiotherapist(2, "Dr. van Es, Physiotherapist"));

        List<User> dentistUsers = new ArrayList<>();
        dentistUsers.add(new Dentist(1, "Dr. Bakker, Dentist"));
        dentistUsers.add(new Dentist(2, "Dr. de Groot, Dentist"));

        List<User> pharmacistUsers = new ArrayList<>();
        pharmacistUsers.add(new Pharmacist(1, "Dr. de Jong, Pharmacist"));
        pharmacistUsers.add(new Pharmacist(2, "Dr. van den Berg, Pharmacist"));

        System.out.println("Choose your caregiver role:");
        System.out.println("1: GP");
        System.out.println("2: Physiotherapist");
        System.out.println("3: Dentist");
        System.out.println("4: Pharmacist");

        Scanner scanner = new Scanner(System.in);
        int selectedCategoryId = scanner.nextInt();
        scanner.nextLine();

        List<User> selectedCategoryUsers;

        switch (selectedCategoryId) {
            case 1:
                selectedCategoryUsers = gpUsers;
                break;
            case 2:
                selectedCategoryUsers = physiotherapistUsers;
                break;
            case 3:
                selectedCategoryUsers = dentistUsers;
                break;
            case 4:
                selectedCategoryUsers = pharmacistUsers;
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        User selectedUser = getUserByName(selectedCategoryUsers);

        if (selectedUser != null) {
            // Initialize a SessionCoordinator with the selected user and start the session
            SessionCoordinator sessionCoordinator = new SessionCoordinator(selectedUser);
            sessionCoordinator.menu(); // Start session through the coordinator
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static User getUserByName(List<User> users) {
        System.out.println("Choose a user:");

        for (User user : users) {
            System.out.format("[%d] %s\n", user.getUserID(), user.getUserName());
        }

        Scanner scanner = new Scanner(System.in);
        int selectedUserId = scanner.nextInt();
        scanner.nextLine();

        for (User user : users) {
            if (user.getUserID() == selectedUserId) {
                return user;
            }
        }
        return null;
    }
}
