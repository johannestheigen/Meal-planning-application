package edu.ntnu.idi.bidata.utility;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * <p>The InputHandler class is used to read different types of input from the user.
 *  It helps the user provide data in various formats (strings, numbers, dates)
 *  and checks that the input is valid.</p>
 *
 *<p>This class provides the following methods: </p>
 * <li>stringInput: Reads a line of text from the user.</li>
 * <li>doubleInput: Reads a number and checks it's a valid double.</li>
 * <li>intInput: Reads a number and checks it's a valid integer.</li>
 * <li>expirationDateInput: Reads a date and
 * makes sure it's in the correct format (yyyy-MM-dd).</li>
 *
 *
 * <p>The input methods validates the user input, and if the input is invalid
 * (e.g., incorrect date format),
 * the method will continue prompting the user until valid input is provided.</p>
 * The <b>close</b> method ends the user interaction when the application finishes running.
 *
 * @author Johannes Nupen Theigen
 * @version 0.0.3
 * @since 11.21.2024
 */

public class InputHandler {

  private final Scanner reader;

  /**
   * <p>Initializes a new instance of the <b>Scanner</b> class
   * for reading user input from the standard input stream (usually the keyboard).</p>
   *
   * <p>The <b>Scanner</b> object,
   * enables interaction between the application and the user.
   * It allows reading different types of input,
   * such as strings, numbers, and dates, from the terminal or console.</p>
   *
   * <p>The <b>Scanner</b> will remain open until the program
   * finishes</p>
   *
   * <p><b>Example of usage:</b></p>
   *      <pre><code>
   *       input = new InputHandler();;
   *      </code></pre>
   */
  public InputHandler() {
    reader = new Scanner(System.in);
  }

  /**
   * <p>Reads a line of text from the console and returns it as a string.</p>
   *
   * @return the input string provided by the user.
   *
     <p><b>Example of usage:</b></p>
     <pre><code>
     String userInput = input.stringInput();
     </code></pre>
   */
  public String stringInput() {
    return reader.nextLine();
  }

  /**
   * <p>Reads a line of text from the console and attempts to parse it as a double.
   *  The method keeps prompting the user until a valid double value is entered.</p>
   *
   * @return the input double numerical value provided by the user.
   *
     <p><b>Example of usage:</b></p>
   *          <pre><code>
   *         double value = input.doubleInput();
   *         </code></pre>
   */
  public double doubleInput() {
    while (true) {
      try {
        String input = reader.nextLine();
        return Double.parseDouble(input);
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid double.");
      }
    }
  }

  /**
   * <p>Reads a line of text from the console and attempts to parse it as an integer.
   * The method keeps prompting the user until a valid integer is entered.</p>
   *
   * @return the input integer value provided by the user.
   *
     <p><b>Example of usage:</b></p>
     <pre><code>
     int value = input.intInput();
     </code></pre>
   */
  public int intInput() {
    while (true) {
      try {
        String input = reader.nextLine();
        return Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid integer.");
      }
    }
  }

  /**
   * <p>Reads a line of text from the console and attempts to parse it as a LocalDate.
   * The method keeps prompting the user until a valid expiration date is entered.</p>
   *
   * @return the input as LocalDate provided by the user.
   *
      <p><b>Example of usage:</b></p>
         <pre><code>
        LocalDate expirationDate = input.expirationDateInput();
        </code></pre>
   */
  public LocalDate expirationDateInput() {
    LocalDate expirationDate = null;
    while (expirationDate == null) {
      String expirationDateStr = stringInput();
      try {
        expirationDate = LocalDate.parse(expirationDateStr);
      } catch (DateTimeParseException e) {
        System.out.println("Invalid date format. Please enter a valid date (YYYY-MM-DD).");
      }
    }
    return expirationDate;
  }

  /**
   * <p>Closes the Scanner when the application is finished running.</p>
   *
   * <p><b>Example of usage:</b></p>
   *          <pre><code>
   *         InputHandler.close();
   *         </code></pre>
   */
  public void close() {
    reader.close();
  }
}