# CareApp

This project is a Java-based school assignment designed to simulate a simplified healthcare management system. It demonstrates a range of fundamental programming and object-oriented design concepts through the interaction between healthcare professionals and patient data.

---

## Project Overview

The application is structured into several packages:

- **coordination:**  
  Contains the main classes to initialize and manage the application flow, including user role selection and session coordination.
  
- **medication:**  
  Manages medication details and operations, such as adding and viewing prescribed medication.
  
- **patients:**  
  Represents patient information, including personal details, calculated BMI, age, and methods for updating patient data.
  
- **users:**  
  Defines various healthcare professional roles (Huisarts, Tandarts, Apotheek, Fysiotherapeut) that inherit from an abstract base class, tailoring specific permissions for viewing and prescribing medication.

---

## Key Programming Concepts Demonstrated

- **Object-Oriented Programming (OOP):**  
  The project is organized into classes and packages, each encapsulating data and behaviors relevant to a specific domain.

- **Encapsulation:**  
  Private fields (e.g., patient details, medication attributes) are accessed via public methods, protecting internal state and ensuring controlled data manipulation.

- **Abstraction:**  
  An abstract `User` class defines common properties and abstract methods, allowing subclasses to implement role-specific behavior.

- **Inheritance and Polymorphism:**  
  Different healthcare roles (Huisarts, Tandarts, etc.) inherit from the `User` class and override abstract methods to enforce custom permissions for medication management.

- **Data Validation and Error Handling:**  
  User inputs are managed via the console with basic validation (e.g., patient selection, date parsing) ensuring the application handles invalid data gracefully.

- **Separation of Concerns:**  
  The code is divided into clear functional areas (coordination, patients, medication, and user management), making it easier to maintain and extend.

---

## Running the Application

1. **Compile the Project:**  
   Download the repository.

2. **Run the Application:**  
   Open the root folder in VSCode and run the InitializeZorgApp.java file

3. **Follow the Console Prompts:**  
   Select your role, choose a user, and interact with the application to view or edit patient information and manage medication.

---

This assignment provided hands-on experience with core Java programming constructs and essential software design principles, serving as a practical demonstration of how object-oriented approaches can model real-world systems.