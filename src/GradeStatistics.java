import java.util.InputMismatchException;
import java.util.Scanner;

public class GradeStatistics {

    // Method to compute the maximum grade
    public static int findMax(int[] scores) {
        int max = scores[0];
        for (int score : scores) {
            if (score > max) {
                max = score;
            }
        }
        return max;
    }

    // Method to compute the minimum grade
    public static int findMin(int[] scores) {
        int min = scores[0];
        for (int score : scores) {
            if (score < min) {
                min = score;
            }
        }
        return min;
    }

    // Method to compute the average grade
    public static double findAverage(int[] scores) {
        double sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return sum / scores.length;
    }

    // Method to categorize scores into the stats array
    public static void categorizeScores(int[] scores, int[] stats) {
        for (int score : scores) {
            if (score >= 0 && score <= 20) {
                stats[0]++;
            } else if (score >= 21 && score <= 40) {
                stats[1]++;
            } else if (score >= 41 && score <= 60) {
                stats[2]++;
            } else if (score >= 61 && score <= 80) {
                stats[3]++;
            } else if (score > 80) {
                stats[4]++;
            }
        }
    }

    // Revised printBarGraph method using manual printing instead of a loop
    public static void printBarGraph(int[] stats) {
        System.out.println();
        System.out.println("Bar Graph:");

        for (int level = 6; level >= 1; level--) {
            System.out.printf("%3d  > ", level);
            for (int stat : stats) {
                System.out.print((stat >= level ? "#########" : "         ") + "   ");
            }
            System.out.println();
        }

        // Print the horizontal line and labels for the ranges
        System.out.print("      ");
        for (int j = 0; j < stats.length; j++) {
            System.out.print("+-----------");
        }
        System.out.println("+");

        // Print the grade ranges
        System.out.print("            ");
        for (int j = 0; j < stats.length; j++) {
            System.out.printf("I %2d-%2d  ", j * 20 + (j == 4 ? 1 : 0), (j + 1) * 20);
        }
        System.out.println("I");

        // Print the count of each range at the bottom
        System.out.print("            ");
        for (int stat : stats) {
            System.out.printf(" %3d    ", stat);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = 0;

        // Step 1: Read input for the number of students with validation
        while (true) {
            try {
                System.out.print("Enter the number of students: ");
                N = scanner.nextInt();
                if (N <= 0) {
                    System.out.println("Number of students must be a positive integer. Please try again.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        int[] scores = new int[N];

        // Step 2: Read input grades for each student with validation
        System.out.println("Enter the grades of the students:");
        for (int i = 0; i < N; i++) {
            while (true) {
                try {
                    System.out.print("Grade for student " + (i + 1) + ": ");
                    int score = scanner.nextInt();
                    if (score >= 0 && score <= 100) {
                        scores[i] = score;
                        break; // Valid input, exit loop
                    } else {
                        System.out.println("Invalid input. Please enter a grade between 0 and 100.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer between 0 and 100.");
                    scanner.next(); // Clear the invalid input
                }
            }
        }

        // Step 3: Calculate the max, min, and average grades
        int max = findMax(scores);
        int min = findMin(scores);
        double avg = findAverage(scores);

        // Output the statistics
        System.out.println("\nGrade Statistics:");
        System.out.println("Maximum grade: " + max);
        System.out.println("Minimum grade: " + min);
        System.out.println("Average grade: " + String.format("%.2f", avg));

        // Initialize the stats array
        int[] stats = new int[5];

        // Step 4: Categorize the scores
        categorizeScores(scores, stats);

        // Output the stats array
        System.out.println("\nGrade distribution:");
        System.out.println("Grades between 0 and 20: " + stats[0]);
        System.out.println("Grades between 21 and 40: " + stats[1]);
        System.out.println("Grades between 41 and 60: " + stats[2]);
        System.out.println("Grades between 61 and 80: " + stats[3]);
        System.out.println("Grades above 80: " + stats[4]);

        // Step 5: Print the bar graph
        printBarGraph(stats);

        scanner.close();
    }
}
