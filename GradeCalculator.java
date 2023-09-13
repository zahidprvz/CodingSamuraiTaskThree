import java.util.Scanner;

public class GradeCalculator {
    // ANSI escape codes for text colors
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Student Grade Calculator!");

        boolean calculateAnother = true;

        while (calculateAnother) {
            double assignmentGrade = getGrade(scanner, "Enter assignment grade: ");
            double quizGrade = getGrade(scanner, "Enter quiz grade: ");
            double midtermGrade = getGrade(scanner, "Enter midterm exam grade: ");
            double finalExamGrade = getGrade(scanner, "Enter final exam grade: ");
            double participationGrade = getGrade(scanner, "Enter participation grade: ");
            double projectGrade = getGrade(scanner, "Enter project grade: ");

            double finalGrade = calculateFinalGrade(
                assignmentGrade, quizGrade, midtermGrade, finalExamGrade, participationGrade, projectGrade
            );

            String letterGrade = determineLetterGrade(finalGrade);

            // Display the final result with color
            System.out.println("Your final grade is: " + getColoredGrade(finalGrade));
            System.out.println("Letter grade: " + getColoredLetterGrade(letterGrade));

            // Ask if the user wants to calculate grades for another student
            System.out.print("Do you want to calculate grades for another student? (yes/no): ");
            String another = scanner.next();
            calculateAnother = another.equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for using the Student Grade Calculator!");
        scanner.close();
    }

    public static double getGrade(Scanner scanner, String prompt) {
        double grade = -1;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid number.");
                System.out.print(prompt);
                scanner.next();
            }
            grade = scanner.nextDouble();
            if (grade < 0 || grade > 100) {
                System.out.println("Invalid input. Grade should be between 0 and 100.");
            }
        } while (grade < 0 || grade > 100);
        return grade;
    }

    public static double calculateFinalGrade(double... grades) {
        double[] weights = {0.15, 0.15, 0.3, 0.2, 0.1, 0.1}; // Adjust the weights to match the number of grades
        if (grades.length != weights.length) {
            throw new IllegalArgumentException("Number of grades and weights should match.");
        }

        double finalGrade = 0;
        for (int i = 0; i < grades.length; i++) {
            finalGrade += grades[i] * weights[i];
        }
        return finalGrade;
    }

    public static String determineLetterGrade(double finalGrade) {
        if (finalGrade >= 90) {
            return "A";
        } else if (finalGrade >= 80) {
            return "B";
        } else if (finalGrade >= 70) {
            return "C";
        } else if (finalGrade >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    // Helper method to add color to the final grade
    public static String getColoredGrade(double finalGrade) {
        if (finalGrade >= 60) {
            return GREEN + finalGrade + RESET; // Green for passing grades
        } else {
            return RED + finalGrade + RESET; // Red for failing grades
        }
    }

    // Helper method to add color to the letter grade
    public static String getColoredLetterGrade(String letterGrade) {
        if (letterGrade.equals("A") || letterGrade.equals("B") || letterGrade.equals("C")) {
            return GREEN + letterGrade + RESET; // Green for A, B, and C
        } else {
            return RED + letterGrade + RESET; // Red for D and F
        }
    }
}
