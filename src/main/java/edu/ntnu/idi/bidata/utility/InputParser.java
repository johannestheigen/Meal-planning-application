package edu.ntnu.idi.bidata.utility;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * <p>The InputParser class is responsible for reading user input.
 * It uses the Scanner class to read input from the user.
 * The class provides methods for reading different types of input,
 * such as strings, numbers, and dates.</p>
 *
 * @author Johannes Nupen Theigen
 * @version 0.0.5
 * @since 12.02.2024
 */
public class InputParser {

  private final Scanner reader;

  /**
   * <p>Initializes a new instance of the Scanner class
   * for reading user input from the standard input stream (usually the keyboard).
   * The Scanner object, enables interaction between the application and the user.
   * It allows reading different types of input, such as strings, numbers,
   * and dates, from the terminal or console. The Scanner will remain open
   * until the program.</p>
   */
  public InputParser() {
    reader = new Scanner(System.in);
  }

  /**
   * <p>Reads a line of text from the console and returns it as a string.</p>
   *
   * @return the input string provided by the user. (e.g., "Hello, World!")
   */
  public String stringInput() {
    return reader.nextLine();
  }

  /**
   * <p>Reads a line of text from the console and attempts to parse it as a double.
   *  The method keeps prompting the user until a valid double value is entered.</p>
   *
   * @return the input double numerical value provided by the user. (e.g., 3.14)
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
   * @return the input integer value provided by the user. (e.g., 42)
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
   * @return the input as LocalDate provided by the user. (e.g., 2024-02-12)
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
   * <p>Closes the Scanner object when the application is finished running.</p>
   */
  public void close() {
    reader.close();
  }
}