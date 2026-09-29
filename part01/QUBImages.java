package part01;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * This class is used to display the menu and perform actions based on what the
 * user chooses to do
 */
public class QUBImages {

	static String title = "QUB Images"; // Title of menu

	static final String[] options = { "Add Image", "Search", "Display All", "Quit" }; // Options the user can choose

	static final int QUIT = options.length; // QUIT constant

	static Scanner input = new Scanner(System.in); // Scanner object

	static ImageManager imgManager = new ImageManager(); // ImageManager object

	static Menu myMenu = new Menu(title, options); // Menu object

	/**
	 * This method displays the menu to the user and also handles the user's choice
	 * when they choose an option
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		imgManager.addSomeImages(); // Adding some initial images to the system
		int userChoice; // The user's choice
		do {
			userChoice = myMenu.getUserChoice(); // Using the menu to get the user's choice

			// Checking if the user input is null
			if (userChoice == -1) {
				System.out.println("Error occured, please enter a valid option");
				System.out.println();
				continue; // Go back to the start of the loop
			}

			if (userChoice != QUIT) { // Check if user wants to quit
				processUserChoice(userChoice); // Deal with the user's choice
			}

		} while (userChoice != QUIT); // Repeat until user wants to quit
		System.out.println("\nGoodbye!");
		input.close(); // Closing Scanner

	}

	/**
	 * This method processes the user's menu selection. It uses a switch statement
	 * to determine what method to use based on the user's selection.
	 *
	 * @param userChoice - The choice that the user has selected.
	 */
	public static void processUserChoice(int userChoice) {
		switch (userChoice) {
		case 1:
			addImage(); // Add an image to the system
			break;
		case 2:
			search(); // Search for a particular image based on criteria
			break;
		case 3:
			displayAll(); // Display all images in the system
			break;
		default: // Used if the user enters an invalid option
			System.out.println("Option " + userChoice + " is invalid.");
		}
		System.out.println();
	}

	/**
	 * This method displays all of the images currently in ImageManager
	 */
	public static void displayAll() {
		System.out.println("\nDisplay All Images:");
		System.out.println(imgManager);
		System.out.println();
	}

	/**
	 * This method allows the user to search for a particular image. They can search
	 * by ID, title, description, type or within a date range
	 */
	public static void search() {
		System.out.println("OK -> What would you like to search for?");

		// Options the user can search for
		System.out.println("1. ID");
		System.out.println("2. Title");
		System.out.println("3. Description");
		System.out.println("4. Type");
		System.out.println("5. Date Range");

		// Reading in user's choice
		System.out.print("Enter a choice: ");
		String choice = input.nextLine();

		// Checking to see if the user enters an empty String, if so, an error message
		// will be produced
		if (choice.isEmpty()) {
			System.out.println("Error occured, please try again");
			return;
		}
		int searchChoice = Integer.parseInt(choice); // Converting from a String to an int

		// Switch statement which determines what the user wants to search for based off
		// their choice
		switch (searchChoice) {
		case 1:
			try {
				System.out.print("Please enter an ID you would like to search for: ");
				String targetID = input.nextLine(); // Reading user's input

				// Checking to see if the user enters an empty String, if so, an error message
				// will be produced
				if (targetID.isEmpty()) {
					System.out.println("Error occured, please try again");
					return;
				}
				int id = Integer.parseInt(targetID); // Converting from a String to an int

				ImageRecord target = imgManager.searchId(id); // Searching for the ID the user entered

				// If there is no image found, a message will be displayed
				if (target == null) {
					System.out.println("There were no images found with that ID");
					break;
				}

				// If an image is found, then the details relating to the image will be output
				System.out.println(target);

				// Exception handling if there is an error
			} catch (Exception ex) {
				System.out.println("An error has occured. Please try again");
			}
			break;
		case 2:
			try {
				System.out.print("Please enter title you would like to search for: ");
				String title = input.nextLine().trim(); // Reading user's input

				// Checking to see if the user enters an empty String, if so, an error message
				// will be produced
				if (title.isEmpty()) {
					System.out.println("Error occured, please try again");
					return;
				}

				ImageAlbum album = imgManager.searchTitle(title); // Searching for the title the user entered
				ImageRecord img = album.getFirst(); // Getting the first ImageRecord in the ImageAlbum

				// If there is no image found, a message will be displayed
				if (img == null) {
					System.out.println("There were no images found with that title");
					break;
				}

				boolean browsing = true; // Controls whether the user continues browsing through the images

				while (browsing) { // While true, the user can navigate through the images

					// Prints the current image
					System.out.println("\nCurrent Image:");
					System.out.println(img);

					// Options which the user can choose to do
					System.out.println("\n1. Next Image");
					System.out.println("2. Previous Image");
					System.out.println("3. Exit");

					System.out.print("Enter choice: ");
					String navigationChoice = input.nextLine(); // User's choice

					// Checking to see if the user enters an empty String, if so, an error message
					// will be produced
					if (navigationChoice.isEmpty()) {
						System.out.println("Error occured, please try again");
						return;
					}

					int navChoice = Integer.parseInt(navigationChoice); // Converting from a String to an int

					// Switch statement which allows the user to go to the next image, the previous
					// image or exit
					switch (navChoice) {
					case 1:
						ImageRecord nextIMG = album.getNext(); // Go to the next image

						// Check if the next image is null, if it is, then there are no more images
						if (nextIMG == null) {
							System.out.println("No more images.");
							browsing = false; // Exiting out of loop
						} else {
							img = nextIMG; // If it isn't null, assign nextIMG to img
						}
						break;
					case 2:
						ImageRecord prevIMG = album.getPrevious(); // Go to the previous image

						// Check if the previous image is null, if it is, then there are no previous
						// images
						if (prevIMG == null) {
							System.out.println("There are no previous images, you are at the first image.");
							browsing = false; // Exiting out of loop
						} else {
							img = prevIMG; // If it isn't null, assign prevIMG to img
						}
						break;
					case 3:
						browsing = false; // Set to false to stop loop and quit browsing
						break;
					default: // Used if the user enters an invalid option
						System.out.println("Option " + navChoice + " is invalid.");
					}
				}

				// Exception handling if there is an error
			} catch (Exception ex) {
				System.out.println("An error has occurred. Please try again.");
			}
			break;
		case 3:
			try {
				System.out.print("Please enter description you would like to search for: ");
				String description = input.nextLine().trim(); // Reading user's input

				// Checking to see if the user enters an empty String, if so, an error message
				// will be produced
				if (description.isEmpty()) {
					System.out.println("Error occured, please try again");
					return;
				}

				ImageAlbum album = imgManager.searchDescription(description); // Searching for the description which the
																				// user enters
				ImageRecord img = album.getFirst(); // Getting the first ImageRecord in ImageAlbum

				// If there is no image found, a message will be displayed
				if (img == null) {
					System.out.println("There were no images found with that description");
					break;
				}

				boolean browsing = true; // Controls whether the user continues browsing through the images

				while (browsing) { // While true, the user can navigate through the images

					// Prints the current image
					System.out.println("\nCurrent Image:");
					System.out.println(img);

					// Options which the user can choose to do
					System.out.println("\n1. Next Image");
					System.out.println("2. Previous Image");
					System.out.println("3. Exit");

					System.out.print("Enter choice: ");
					String navigationChoice = input.nextLine(); // User's choice

					// Checking to see if the user enters an empty String, if so, an error message
					// will be produced
					if (navigationChoice.isEmpty()) {
						System.out.println("Error occured, please try again");
						return;
					}

					int navChoice = Integer.parseInt(navigationChoice); // Converting from a String to an int

					// Switch statement which allows the user to go to the next image, the previous
					// image or exit
					switch (navChoice) {
					case 1:
						ImageRecord nextIMG = album.getNext(); // Go to the next image

						// Check if the next image is null, if it is, then there are no more images
						if (nextIMG == null) {
							System.out.println("No more images.");
							browsing = false; // Exiting out of loop
						} else {
							img = nextIMG; // If it isn't null, assign nextIMG to img
						}
						break;
					case 2:
						ImageRecord prevIMG = album.getPrevious(); // Go to the previous image

						// Check if the previous image is null, if it is, then there are no previous
						// images
						if (prevIMG == null) {
							System.out.println("There are no previous images, you are at the first image.");
							browsing = false; // Exiting out of loop
						} else {
							img = prevIMG; // If it isn't null, assign prevIMG to img
						}
						break;
					case 3:
						browsing = false; // Set to false to stop loop and quit browsing
						break;
					default: // Used if the user enters an invalid option
						System.out.println("Option " + navChoice + " is invalid.");
					}
				}

				// Exception handling if there is an error
			} catch (Exception ex) {
				System.out.println("An error has occurred. Please try again.");
			}
			break;
		case 4:
			try {
				System.out.print("Please enter the type you would like to search for: ");
				String type = input.nextLine().trim(); // Reading user's input

				// Checking to see if the user enters an empty String, if so, an error message
				// will be produced
				if (type.isEmpty()) {
					System.out.println("Error occured, please try again");
					return;
				}

				ImageType imgType = ImageType.valueOf(type.toUpperCase()); // Converting the user's input to an ENUM
				ImageAlbum album = imgManager.searchGenre(imgType); // Searching for the type that the user entered
				ImageRecord img = album.getFirst(); // Getting the first ImageRecord in the ImageAlbum

				// If there is no image found, a message will be displayed
				if (img == null) {
					System.out.println("There were no images found with that image type");
					break;
				}

				boolean browsing = true; // Controls whether the user continues browsing through the images

				while (browsing) { // While true, the user can navigate through the images

					// Prints the current image
					System.out.println("\nCurrent Image:");
					System.out.println(img);

					// Options which the user can choose to do
					System.out.println("\n1. Next Image");
					System.out.println("2. Previous Image");
					System.out.println("3. Exit");

					System.out.print("Enter choice: ");
					String navigationChoice = input.nextLine(); // User's choice

					// Checking to see if the user enters an empty String, if so, an error message
					// will be produced
					if (navigationChoice.isEmpty()) {
						System.out.println("Error occured, please try again");
						return;
					}

					int navChoice = Integer.parseInt(navigationChoice); // Converting from a String to an int

					// Switch statement which allows the user to go to the next image, the previous
					// image or exit
					switch (navChoice) {
					case 1:
						ImageRecord nextIMG = album.getNext(); // Go to the next image

						// Check if the next image is null, if it is, then there are no more images
						if (nextIMG == null) {
							System.out.println("No more images.");
							browsing = false; // Exiting out of loop
						} else {
							img = nextIMG; // If it isn't null, assign nextIMG to img
						}
						break;
					case 2:
						ImageRecord prevIMG = album.getPrevious(); // Go to the previous image

						// Check if the previous image is null, if it is, then there are no previous
						// images
						if (prevIMG == null) {
							System.out.println("There are no previous images, you are at the first image.");
							browsing = false; // Exiting out of loop
						} else {
							img = prevIMG; // If it isn't null, assign prevIMG to img
						}
						break;
					case 3:
						browsing = false; // Set to false to stop loop and quit browsing
						break;
					default: // Used if the user enters an invalid option
						System.out.println("Option " + navChoice + " is invalid.");
					}
				}

				// Exception handling if there is an error
			} catch (Exception ex) {
				System.out.println("An error has occurred. Please try again.");
			}
			break;
		case 5:
			try {
				System.out.println("This is a date range search");

				System.out.print("Please enter a starting date (yyyy-mm-dd): ");
				String start = input.nextLine().trim(); // Reading in the starting date

				// Checking to see if the user enters an empty String, if so, an error message
				// will be produced
				if (start.isEmpty()) {
					System.out.println("Error occured, please try again");
					return;
				}
				LocalDate startDate = LocalDate.parse(start); // Converting the input from a String to a LocalDate

				System.out.print("Please enter an ending date (yyyy-mm-dd): ");
				String end = input.nextLine().trim(); // Reading in the ending date

				// Checking to see if the user enters an empty String, if so, an error message
				// will be produced
				if (end.isEmpty()) {
					System.out.println("Error occured, please try again");
					return;
				}
				LocalDate endDate = LocalDate.parse(end); // Converting the input from a String to a LocalDate

				ImageAlbum album = imgManager.searchDates(startDate, endDate); // Searching for any images within the
																				// start and end dates
				ImageRecord img = album.getFirst(); // Getting the first ImageRecord in the ImageAlbum

				// If there is no image found, a message will be displayed
				if (img == null) {
					System.out.println("There were no images found within that date range");
					break;
				}

				boolean browsing = true; // Controls whether the user continues browsing through the images

				while (browsing) { // While true, the user can navigate through the images

					// Prints the current image
					System.out.println("\nCurrent Image:");
					System.out.println(img);

					// Options which the user can choose to do
					System.out.println("\n1. Next Image");
					System.out.println("2. Previous Image");
					System.out.println("3. Exit");

					System.out.print("Enter choice: ");
					String navigationChoice = input.nextLine(); // User's choice

					// Checking to see if the user enters an empty String, if so, an error message
					// will be produced
					if (navigationChoice.isEmpty()) {
						System.out.println("Error occured, please try again");
						return;
					}

					int navChoice = Integer.parseInt(navigationChoice); // Converting from a String to an int

					// Switch statement which allows the user to go to the next image, the previous
					// image or exit
					switch (navChoice) {
					case 1:
						ImageRecord nextIMG = album.getNext(); // Go to the next image

						// Check if the next image is null, if it is, then there are no more images
						if (nextIMG == null) {
							System.out.println("No more images.");
							browsing = false; // Exiting out of loop
						} else {
							img = nextIMG; // If it isn't null, assign nextIMG to img
						}
						break;
					case 2:
						ImageRecord prevIMG = album.getPrevious(); // Go to the previous image

						// Check if the previous image is null, if it is, then there are no previous
						// images
						if (prevIMG == null) {
							System.out.println("There are no previous images, you are at the first image.");
							browsing = false; // Exiting out of loop
						} else {
							img = prevIMG; // If it isn't null, assign prevIMG to img
						}
						break;
					case 3:
						browsing = false; // Set to false to stop loop and quit browsing
						break;
					default: // Used if the user enters an invalid option
						System.out.println("Option " + navChoice + " is invalid.");
					}
				}

				// Exception handling if there is an error
			} catch (Exception ex) {
				System.out.println("An error has occurred. Please try again.");
			}
			break;
		default: // Used if the user enters an invalid option
			System.out.println("Option " + searchChoice + " is invalid");
		}

	}

	/**
	 * This is a method which allows the user to add an image to the system. The
	 * user has to input all details relating to the image they want to add
	 */
	public static void addImage() {
		System.out.println("OK -> Please enter all details relating to the image:");

		System.out.println();

		try {
			System.out.print("Enter a title: ");
			String title = input.nextLine().trim();

			// Checking to see if the user enters an empty String, if so, an error message
			// will be produced
			if (title.isEmpty()) {
				System.out.println("Error occured, please try again");
				return;
			}

			System.out.print("Enter a description: ");
			String description = input.nextLine().trim();

			// Checking to see if the user enters an empty String, if so, an error message
			// will be produced
			if (description.isEmpty()) {
				System.out.println("Error occured, please try again");
				return;
			}

			System.out.print("Enter a genre: ");
			String type = input.nextLine();

			// Checking to see if the user enters an empty String, if so, an error message
			// will be produced
			if (type.isEmpty()) {
				System.out.println("Error occured, please try again");
				return;
			}
			ImageType imgType = ImageType.valueOf(type.toUpperCase()); // Converting the String to an ImageType

			System.out.print("Enter the date which the image was taken on (yyyy-mm-dd): ");
			String dateTaken = input.nextLine();

			// Checking to see if the user enters an empty String, if so, an error message
			// will be produced
			if (dateTaken.isEmpty()) {
				System.out.println("Error occured, please try again");
				return;
			}
			LocalDate parsedDate = LocalDate.parse(dateTaken); // Converting the String to DateTaken

			System.out.print("Enter a thumbnail: ");
			String thumbnail = input.nextLine();

			// Checking to see if the user enters an empty String, if so, an error message
			// will be produced
			if (thumbnail.isEmpty()) {
				System.out.println("Error occured, please try again");
				return;
			}

			ImageRecord img = new ImageRecord(title, description, imgType, parsedDate, thumbnail); // Creating the new
																									// image based on
																									// the user's input
			imgManager.addImage(img); // Adding the image to ImageManager
			System.out.println();
			System.out.println("-> Image Added");

			// Exception handling if there is an error
		} catch (Exception ex) {
			System.out.println("An error has occured, please try again.");
		}

	}

}
