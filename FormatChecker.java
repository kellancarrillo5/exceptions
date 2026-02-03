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
    public void main(String[] args) {
        // display usage
        if (args.length == 0) {
            System.out.println("Usage:");
            System.out.println("$java FormatChecker fileName1 + fileName2 + ... + fileNameN");
            return;
        }
        // Test each given file name individually
        for (int i = 0; i < args.length; i++) {
            String fileName = args[i];
            System.out.println(fileName);
            checkFileFormat(fileName);
            System.out.println();
        }
    }

    private void checkFileFormat(String fileName) {

        Scanner fileScan = null;

        try {
            fileScan = new Scanner(new File(fileName));

            if ( !fileScan.hasNextInt() ) {
			fileScan.close();
		}
            int rows = fileScan.nextInt();
            int cols = fileScan.nextInt();
        } catch (FileNotFoundException e) {
            System.out.println("Could not open or read " + fileName);
            System.out.println(e.toString());
        } catch (InputMismatchException e) {
            System.out.println(fileScan.next() + " isn't an int in " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileScan != null) {
                fileScan.close();
            }
        }
    }
}
