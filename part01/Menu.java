package part01;

import java.util.Scanner;

/**
 * This class is used to create the Menu object which will be used in QUBImages
 */
public class Menu {
	private String items[]; // The menu options
	private String title; // The title of the menu
	private Scanner input; // Scanner object to read input

	/**
	 * This is the constructor for the Menu object
	 * 
	 * @param title - title which the menu will have
	 * @param data  - menu options
	 */
	public Menu(String title, String data[]) {
		this.title = title;
		this.items = data;
		this.input = new Scanner(System.in); // Initialising Scanner
	}

	/**
	 * This method will be used to display the menu and the options available to the
	 * user
	 */
	private void display() {
		// Displaying title
		System.out.println(title);
		for (int count = 0; count < title.length(); count++) {
			System.out.print("+");
		}

		System.out.println();

		// Displaying options
		for (int option = 1; option <= items.length; option++) {
			System.out.println(option + ". " + items[option - 1]);
		}

		System.out.println();
	}

	/**
	 * This method is used to read the user's menu choice
	 * 
	 * @return the option which the user chose
	 */
	public int getUserChoice() {
		display();
		System.out.print("Enter Selection: ");

		// Reading in user input as a String so we can compare and check if it is empty
		String choice = input.nextLine().trim();

		// Checking if what the user entered is empty/null
		if (choice.isEmpty()) {
			return -1;
		}

		// If it is a valid number, then the integer choice will be returned
		try {
			return Integer.parseInt(choice); // Converting from a String to an int
		} catch (Exception e) {
			return -1; // If it is an invalid number, -1 will be returned
		}
	}
}
