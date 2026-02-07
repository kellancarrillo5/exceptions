import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A program that checks the format of input files against given criteria.
 * The program validates that the file contains the correct number of rows and
 * columns specified in the first line of the file.
 * 
 * @author kellancarrillo5
 */
public class FormatChecker {
    /**
     * Main method that processes command-line arguements
     * @param args
     */
    public static void main(String[] args) {
        // display usage
        if (args.length == 0) {
            System.out.println("Usage: $ java FormatChecker file1 [file2 ... fileN]");
            return;
        }
        // Test each given file name individually
        for (int i = 0; i < args.length; i++) {
            String fileName = args[i];
            System.out.println();
            System.out.println(fileName);
            checkFileFormat(fileName);
        }
    }

    /**
     * Checks the file format and throws an expection when an error is caught.
     * @param fileName fileName is given from user input in the command line
     */
    private static void checkFileFormat(String fileName) {
        Scanner fileScanner = null;

        try {
            fileScanner = new Scanner(new File(fileName));

            // First line: rows and columns
            int rows = fileScanner.nextInt();
            int cols = fileScanner.nextInt();

            // Check that the rows and columns are positive
            if (rows <= 0 || cols <= 0) {
                fileScanner.close();
                System.out.println("Assigned rows and columns must be positive");
                System.out.println("INVALID");
                return;
            }
            // Check that the first line has only two values
            if (!fileScanner.nextLine().trim().equals("")) {
                System.out.println("The first line has to many values.");
                System.out.println("INVALID");
                fileScanner.close();
                return;
            }
            // Read EXACTLY the expected number of rows
            for (int i = 0; i < rows; i++) {
                if (!fileScanner.hasNextLine()) {
                    System.out.println("There aren't enough rows!");
                    System.out.println("INVALID");
                    fileScanner.close();
                    return;
                }

                String line = fileScanner.nextLine(); // move to the next line
                Scanner lineScanner = new Scanner(line); // scan the line

                int colCount = 0;
                while (lineScanner.hasNext()) {
                    String check = lineScanner.next();
                    Double.parseDouble(check);
                    colCount++;
                }

                if (colCount != cols) {
                    fileScanner.close();
                    lineScanner.close();
                    System.out.println("There aren't enough columns!");
                    System.out.println("INVALID");
                    return;
                }
                lineScanner.close();
            }

            if (fileScanner.hasNext()) {
                fileScanner.close();
                System.out.println("There are to many rows!");
                System.out.println("INVALID");
                return;
            }
            System.out.println("VALID");

        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            System.out.println("INVALID");
        } catch (InputMismatchException e) {
            System.out.println(e.toString());
            System.out.println("INVALID");
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
            System.out.println("INVALID");
        } finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
        }
    }
}
