package ZorgApp.coordination;

import ZorgApp.users.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The InitializeZorgApp class is the main entry point for the application, allowing the user to select a role
 * (e.g., Huisarts, Tandarts, Apotheek) and begin a session. It initializes the list of users for each role
 * and provides an interactive console-based interface for selecting a role and user.
 * After selection, InitializeZorgApp creates a SessionCoordinator to manage the session for the selected user.
 */


class InitializeZorgApp {
    public static void main(String[] args) {
        List<User> huisartsUsers = new ArrayList<>();
        huisartsUsers.add(new Huisarts(1, "Dr. Duin, Huisarts"));
        huisartsUsers.add(new Huisarts(2, "Dr. Badell, Huisarts"));

        List<User> physiotherapyUsers = new ArrayList<>();
        physiotherapyUsers.add(new Fysiotherapeut(1, "Dr. van de Lagemaat, Fysiotherapeut"));
        physiotherapyUsers.add(new Fysiotherapeut(2, "Dr. van Es, Fysiotherapeut"));

        List<User> tandartsUsers = new ArrayList<>();
        tandartsUsers.add(new Tandarts(1, "Dr. Bakker, Tandarts"));
        tandartsUsers.add(new Tandarts(2, "Dr. de Groot, Tandarts"));

        List<User> apotheekUsers = new ArrayList<>();
        apotheekUsers.add(new Apotheek(1, "Dr. de Jong, Apotheker"));
        apotheekUsers.add(new Apotheek(2, "Dr. van den Berg, Apotheker"));

        System.out.println("Kies uw zorgverlener rol:");
        System.out.println("1: Huisarts");
        System.out.println("2: Fysiotherapeut");
        System.out.println("3: Tandarts");
        System.out.println("4: Apotheek");

        Scanner scanner = new Scanner(System.in);
        int selectedCategoryId = scanner.nextInt();
        scanner.nextLine();

        List<User> selectedCategoryUsers;

        switch (selectedCategoryId) {
            case 1:
                selectedCategoryUsers = huisartsUsers;
                break;
            case 2:
                selectedCategoryUsers = physiotherapyUsers;
                break;
            case 3:
                selectedCategoryUsers = tandartsUsers;
                break;
            case 4:
                selectedCategoryUsers = apotheekUsers;
                break;
            default:
                System.out.println("Ongeldige keuze.");
                return;
        }

        User selectedUser = getUserByName(selectedCategoryUsers);

        if (selectedUser != null) {
            // Initialize a SessionCoordinator with the selected user and start the session
            SessionCoordinator sessionCoordinator = new SessionCoordinator(selectedUser);
            sessionCoordinator.menu(); // Start session through the coordinator
        } else {
            System.out.println("Ongeldige keuze.");
        }
    }

    private static User getUserByName(List<User> users) {
        System.out.println("Kies een gebruiker:");

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
