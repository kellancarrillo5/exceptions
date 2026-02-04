import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A program that checks the format of input files against given criteria
 * The program validates that the file contains the correct number of rows and
 * columns specified
 * in the first line of the file.
 * 
 * @author kellancarrillo5
 */
public class FormatChecker {
    /**
     * Main method that processes command-line arguements and validates each file
     * 
     * @param args
     */
    public static void main(String[] args) {
        // display usage
        if (args.length == 0) {
            System.out.println("Usage: $ java FormatChecker file1 [file2 ... fileN]");
            return;
        }
        // Test each given file name individually
        for (String fileName : args) {
            System.out.println(fileName);
            checkFileFormat(fileName);
            System.out.println();
        }
    }

    private static void checkFileFormat(String fileName) {

        Scanner fileScan = null;

        try {
            fileScan = new Scanner(new File(fileName));

            if (!fileScan.hasNextInt()) {
                fileScan.close();
                System.out.println("INVALID");
                throw new InputMismatchException("Missing one value of the first integers in " + fileName + " defining rows and colums");
            }

            int rows = fileScan.nextInt();
            int cols = fileScan.nextInt();

            if (rows < 0 || cols < 0) {
                fileScan.close();
                System.out.println("INVALID");
                throw new InputMismatchException("The first two values in the file " + fileName + " must be positive");
            }

            int rowsCount = 0;
            fileScan.nextLine();

            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine().trim();

                // Empty line is invalid
                if (line.isEmpty()) {
                    System.out.println("INVALID");
                    return;
                }
                rowsCount++;

                Scanner lineScanner = new Scanner(line);
                int colCount = 0;

                // Parse values in the row
                while (lineScanner.hasNext()) {
                    if (!lineScanner.hasNextDouble()) {
                        System.out.println("INVALID");
                        lineScanner.close();
                        return;
                    }
                    lineScanner.nextDouble();
                    colCount++;
                }

                lineScanner.close();

                // Check if colCount is the same as given number
                if (colCount != cols) {
                    System.out.println("INVALID");
                    return;
                }

                // Check if rowCountt is the same as given number
                if (rowsCount != rows) {
                    System.out.println("INVALID");
                    return;
                }

                // If everything checks out
                System.out.println("VALID");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Could not open or read " + fileName);
        //} catch (InputMistmatchException e) {
            //System.out.println(fileScan.next() + " isn't an int in " + fileName);
        } finally {
            if (fileScan != null) {
                fileScan.close();
            }
        }
    }
}
