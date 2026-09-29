package part02;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import javax.swing.ImageIcon;

import console.Console;
import part01.ImageAlbum;
import part01.ImageManager;
import part01.ImageRecord;
import part01.ImageType;

public class QUBMediaImages {

	static final String[] options = { "Add Image", "Search", "Display All", "Quit" }; // Options the user can choose

	static final int QUIT = options.length; // QUIT constant

	static ImageManager imgManager = new ImageManager(); // ImageManager object

	static Console con2 = new Console(true); // Console object

	public static void main(String[] args) {
		// This will create a new Console instance
		Console con = new Console(true);

		// Adding some initial images to the console
		addSomeImages(con);

		// Set the width and height for the console
		con.setSize(400, 300);

		// Make sure the console is visible to the user
		con.setVisible(true);

		// Modifying the background colour, font style and font colour
		con.setBgColour(Color.black);
		con.setFont(new Font("Consolas", Font.BOLD, 40));
		con.setColour(Color.white);

		// Print the title to the console
		con.println("                    Welcome to QUB Media Images!");

		con.println();

		// Set the width and height for the console
		con2.setSize(400, 300);

		// Modifying the background colour, font style and font colour
		con2.setBgColour(Color.black);
		con2.setFont(new Font("Consolas", Font.BOLD, 40));
		con2.setColour(Color.white);

		int userChoice = 0; // User's choice

		do {
			con.println();

			// Divider
			con.println("------------");

			// Displaying options which the user can choose
			for (int i = 1; i <= options.length; i++) {
				con.setFont(new Font("Consolas", Font.BOLD, 30));
				con.println(i + ". " + options[i - 1]);
			}

			con.println();

			// Reading in the user's choice
			con.print("Enter Selection: ");
			String userSelection = con.readLn();
			userSelection = userSelection.trim();

			// Checking to see if the user enters an empty String, if so, an error message
			// will be produced
			if (userSelection.isEmpty()) {
				con.setColour(Color.red);
				con.println("Error occured, please enter a valid option");
				con.setColour(Color.white);
			}

			// Attempting to convert the String to an int, if it can't do that, then an
			// exception is caught
			try {
				userChoice = Integer.parseInt(userSelection); // Converting user input from a String to an int
			} catch (Exception e) {
			}

			// Switch statement which determines what will happen when the user enter's
			// their choice
			switch (userChoice) {
			case 1:
				addImage(con); // Adding an image
				break;
			case 2:
				search(con); // Searching for an image based on criteria
				break;
			case 3:
				displayAll(); // Displaying all images
				break;
			case 4:
				System.exit(1); // Exiting the system
				break;
			}

		} while (userChoice != QUIT); // Repeat while the user doesn't quit

	}

	/**
	 * This method is used to print the images to the console
	 * 
	 * @param imgRecord - the ImageRecord to be displayed
	 */
	public static void printImage(ImageRecord imgRecord) {
		// Clearing the console
		con2.clear();

		// Making the console visible
		con2.setVisible(true);

		// Getting the directory and using it to display the images
		String userDir = System.getProperty("user.dir");
		String path = userDir + "/Images/";

		ImageIcon img = new ImageIcon(path + imgRecord.getThumbnail()); // Creating an ImageIcon and getting the
																		// thumbnail of the image in order to print it
		con2.println(img); // Printing the image

		con2.setFont(new Font("Consolas", Font.BOLD, 20));
		con2.println(imgRecord.toString()); // Displaying details relating to the image

		con2.println();

		// Closing the console
		con2.setFont(new Font("Consolas", Font.BOLD, 30));
		con2.println("Hit RETURN to close");
		con2.readLn();
		con2.clear();
		con2.setVisible(false);
	}

	/**
	 * This method displays all the images within the system to the console
	 */
	public static void displayAll() {
		// Clearing the console
		con2.clear();

		// Making the console visible
		con2.setVisible(true);

		// Modifying the font style and size
		con2.setFont(new Font("Consolas", Font.BOLD, 30));
		con2.println("All Images");
		con2.println();

		// Getting the directory and using it to display the images
		String userDir = System.getProperty("user.dir");
		String path = userDir + "/Images/";

		// Getting all of the images within ImageManager and putting them into
		// ImageALbum
		ImageAlbum imgAlbum = imgManager.getAllImages();

		// Getting the first ImageRecord in ImageAlbum
		ImageRecord imgRecord = imgAlbum.getFirst();

		while (imgRecord != null) { // Repeat while the image isn't null
			ImageIcon img = new ImageIcon(path + imgRecord.getThumbnail()); // Creating an ImageIcon and getting the
																			// thumbnail of the image in order to print
																			// it
			con2.println(img); // Printing the image
			con2.setFont(new Font("Consolas", Font.BOLD, 20)); // Modifying font style and size
			con2.println(imgRecord.toString()); // Displaying details relating to the image
			con2.println();
			con2.println();
			imgRecord = imgAlbum.getNext(); // Going to the next image in the album
		}

		// Closing the console
		con2.setFont(new Font("Consolas", Font.BOLD, 30));
		con2.println("Hit RETURN to close");
		con2.readLn();
		con2.clear();
		con2.setVisible(false);
	}

	/**
	 * This method is used to search for an image in the system. The user can search
	 * by ID, title, description, type or date range
	 * 
	 * @param con - console object used to print out information to the console
	 */
	public static void search(Console con) {
		con.println();
		con.println("OK -> What would you like to search for?");

		// Options the user can search for
		con.println("1. ID");
		con.println("2. Title");
		con.println("3. Description");
		con.println("4. Type");
		con.println("5. Date Range");

		con.println();

		int choice = 0; // Initialising choice

		// Reading in the user's choice
		con.print("Enter a choice: ");
		String searchChoice = con.readLn();

		// Checking to see if the user enters an empty String, if so, an error message
		// will be produced
		if (searchChoice.isEmpty()) {
			con.setColour(Color.red);
			con.println("Error occured, please enter a valid option");
			con.setColour(Color.white);
		}

		try {
			choice = Integer.parseInt(searchChoice); // Converting the input from a String to an int
		} catch (Exception e) {
		}

		// Switch statement which determines what the user wants to search for based off
		// their choice
		switch (choice) {
		case 1:
			try {
				con.print("Please enter an ID you would like to search for: ");
				String userID = con.readLn(); // Reading in the input
				int id = Integer.parseInt(userID); // Converting the input from a String to an int
				ImageRecord target = imgManager.searchId(id); // Searching for the ID the user entered

				// If there is no image found, a message will be displayed
				if (target == null) {
					con.println("There were no images found with that ID");
				}

				con.println();

				// If an image is found, then the details relating to the image will be output
				con.println(target);
				con.println();

				// Printing the image to a new console
				printImage(target);

				// Exception handling if there is an error
			} catch (Exception ex) {
				con.setColour(Color.red);
				con.println("An error has occured. Please try again");
				con.setColour(Color.white);
			}
			break;
		case 2:
			try {
				con.print("Please enter title you would like to search for: ");
				String title = con.readLn().trim(); // Reading in the input

				// Checking to see if the user enters an empty String, if so, an error message
				// will be produced
				if (title.isEmpty()) {
					con.setColour(Color.red);
					con.println("Error occured, please enter a valid title");
					con.setColour(Color.white);
					break;
				}

				ImageAlbum album = imgManager.searchTitle(title); // Searching for the title the user entered
				ImageRecord img = album.getFirst(); // Getting the first ImageRecord in the ImageAlbum

				// If there is no image found, a message will be displayed
				if (img == null) {
					con.println("There were no images found with that title");
					break;
				}

				boolean browsing = true; // Controls whether the user continues browsing through the images

				while (browsing) { // While true, the user can navigate through the images

					// Prints the current image
					con.println("\nCurrent Image:");
					con.println(img);

					con.println();

					// Printing the current image to the console
					printImage(img);

					// Options which the user can choose to do
					con.println("\n1. Next Image");
					con.println("2. Previous Image");
					con.println("3. Exit");

					con.println();

					// Reading in user's navigation choice
					con.print("Enter choice: ");
					String navigationChoice = con.readLn(); // Reading in the input
					int navChoice = Integer.parseInt(navigationChoice); // Converting the input from a String to an int

					// Switch statement which allows the user to go to the next image, the previous
					// image or exit
					switch (navChoice) {
					case 1:
						ImageRecord nextIMG = album.getNext(); // Go to the next image

						// Check if the next image is null, if it is, then there are no more images
						if (nextIMG == null) {
							con.println("No more images.");
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
							con.println("There are no previous images, you are at the first image.");
							browsing = false; // Exiting out of loop
						} else {
							img = prevIMG; // If it isn't null, assign prevIMG to img
						}
						break;
					case 3:
						browsing = false; // Set to false to stop loop and quit browsing
						break;
					default: // Used if the user enters an invalid option
						con.println("Option " + navChoice + " is invalid.");
					}
				}

				// Exception handling if there is an error
			} catch (Exception ex) {
				con.setColour(Color.red);
				con.println("An error has occurred. Please try again.");
				con.setColour(Color.white);
			}
			break;
		case 3:
			try {
				con.print("Please enter description you would like to search for: ");
				String description = con.readLn().trim(); // Reading in the input

				// Checking to see if the user enters an empty String, if so, an error message
				// will be produced
				if (description.isEmpty()) {
					con.setColour(Color.red);
					con.println("Error occured, please enter a valid description");
					con.setColour(Color.white);
					break;
				}

				ImageAlbum album = imgManager.searchDescription(description); // Searching for the description which the
																				// user enters
				ImageRecord img = album.getFirst(); // Getting the first ImageRecord in ImageAlbum

				// If there is no image found, a message will be displayed
				if (img == null) {
					con.println("There were no images found with that description");
					break;
				}

				boolean browsing = true; // Controls whether the user continues browsing through the images

				while (browsing) { // While true, the user can navigate through the images

					// Prints the current image
					con.println("\nCurrent Image:");
					con.println(img);

					con.println();

					// Printing the current image to the console
					printImage(img);

					// Options which the user can choose to do
					con.println("\n1. Next Image");
					con.println("2. Previous Image");
					con.println("3. Exit");

					// Reading in user's navigation choice
					con.print("Enter choice: ");
					String navigationChoice = con.readLn();
					int navChoice = Integer.parseInt(navigationChoice); // User's choice

					// Switch statement which allows the user to go to the next image, the previous
					// image or exit
					switch (navChoice) {
					case 1:
						ImageRecord nextIMG = album.getNext(); // Go to the next image

						// Check if the next image is null, if it is, then there are no more images
						if (nextIMG == null) {
							con.println("No more images.");
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
							con.println("There are no previous images, you are at the first image.");
							browsing = false; // Exiting out of loop
						} else {
							img = prevIMG; // If it isn't null, assign prevIMG to img
						}
						break;
					case 3:
						browsing = false; // Set to false to stop loop and quit browsing
						break;
					default: // Used if the user enters an invalid option
						con.println("Option " + navChoice + " is invalid.");
					}
				}

				// Exception handling if there is an error
			} catch (Exception ex) {
				con.setColour(Color.red);
				con.println("An error has occurred. Please try again.");
				con.setColour(Color.white);
			}
			break;
		case 4:
			try {
				con.print("Please enter the type you would like to search for: ");
				String type = con.readLn().trim(); // Reading in the input

				// Checking to see if the user enters an empty String, if so, an error message
				// will be produced
				if (type.isEmpty()) {
					con.setColour(Color.red);
					con.println("Error occured, please enter a valid type");
					con.setColour(Color.white);
					break;
				}

				ImageType imgType = ImageType.valueOf(type.toUpperCase()); // Converting from a String to an ENUM
				ImageAlbum album = imgManager.searchGenre(imgType); // Searching for the type the user enters
				ImageRecord img = album.getFirst(); // Getting the first ImageRecord in ImageAlbum

				// If there is no image found, a message will be displayed
				if (img == null) {
					con.println("There were no images found with that image type");
					break;
				}

				boolean browsing = true; // Controls whether the user continues browsing through the images

				while (browsing) { // While true, the user can navigate through the images

					// Prints the current image
					con.println("\nCurrent Image:");
					con.println(img);

					con.println();

					// Printing the current image to the console
					printImage(img);

					// Options which the user can choose to do
					con.println("\n1. Next Image");
					con.println("2. Previous Image");
					con.println("3. Exit");

					con.println();

					// Reading in the user's navigation choice
					con.print("Enter choice: ");
					String navigationChoice = con.readLn(); // Reading in the input
					int navChoice = Integer.parseInt(navigationChoice); // Converting the input from a String to an int

					// Switch statement which allows the user to go to the next image, the previous
					// image or exit
					switch (navChoice) {
					case 1:
						ImageRecord nextIMG = album.getNext(); // Go to the next image

						// Check if the next image is null, if it is, then there are no more images
						if (nextIMG == null) {
							con.println("No more images.");
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
							con.println("There are no previous images, you are at the first image.");
							browsing = false; // Exiting out of loop
						} else {
							img = prevIMG; // If it isn't null, assign prevIMG to img
						}
						break;
					case 3:
						browsing = false; // Set to false to stop loop and quit browsing
						break;
					default: // Used if the user enters an invalid option
						con.println("Option " + navChoice + " is invalid.");
					}
				}

				// Exception handling if there is an error
			} catch (Exception ex) {
				con.setColour(Color.red);
				con.println("An error has occurred. Please try again.");
				con.setColour(Color.white);
			}
			break;
		case 5:
			try {
				con.println("This is a date range search");

				con.print("Please enter a starting date (yyyy-mm-dd): ");
				String start = con.readLn().trim(); // Reading in the starting date

				// Checking to see if the user enters an empty String, if so, an error message
				// will be produced
				if (start.isEmpty()) {
					con.setColour(Color.red);
					con.println("Error occured, please enter a valid start date");
					con.setColour(Color.white);
					break;
				}

				LocalDate startDate = LocalDate.parse(start); // Converting the input from a String to a LocalDate

				con.print("Please enter an ending date (yyyy-mm-dd): ");
				String end = con.readLn().trim(); // Reading in the ending date

				// Checking to see if the user enters an empty String, if so, an error message
				// will be produced
				if (end.isEmpty()) {
					con.setColour(Color.red);
					con.println("Error occured, please enter a end date");
					con.setColour(Color.white);
					break;
				}

				LocalDate endDate = LocalDate.parse(end); // Converting the input from a String to a LocalDate

				ImageAlbum album = imgManager.searchDates(startDate, endDate); // Searching for any images within the
																				// start and end dates
				ImageRecord img = album.getFirst(); // Getting the first ImageRecord in the ImageAlbum

				// If there is no image found, a message will be displayed
				if (img == null) {
					con.println("There were no images found within that date range");
					break;
				}

				boolean browsing = true; // Controls whether the user continues browsing through the images

				while (browsing) { // While true, the user can navigate through the images

					// Prints the current image
					con.println("\nCurrent Image:");
					con.println(img);

					con.println();

					// Printing the current image to the console
					printImage(img);

					// Options which the user can choose to do
					con.println("\n1. Next Image");
					con.println("2. Previous Image");
					con.println("3. Exit");

					// Reading in the user's navigation choice
					con.print("Enter choice: ");
					String navigationChoice = con.readLn(); // Reading in the input
					int navChoice = Integer.parseInt(navigationChoice); // Converting from a String to an int

					// Switch statement which allows the user to go to the next image, the previous
					// image or exit
					switch (navChoice) {
					case 1:
						ImageRecord nextIMG = album.getNext(); // Go to the next image

						// Check if the next image is null, if it is, then there are no more images
						if (nextIMG == null) {
							con.println("No more images.");
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
							con.println("There are no previous images, you are at the first image.");
							browsing = false; // Exiting out of loop
						} else {
							img = prevIMG; // If it isn't null, assign prevIMG to img
						}
						break;
					case 3:
						browsing = false; // Set to false to stop loop and quit browsing
						break;
					default: // Used if the user enters an invalid option
						con.println("Option " + navChoice + " is invalid.");
					}
				}

				// Exception handling if there is an error
			} catch (Exception ex) {
				con.setColour(Color.red);
				con.println("An error has occurred. Please try again.");
				con.setColour(Color.white);
			}
			break;
		default: // Used if the user enters an invalid option
			con.println("Option " + searchChoice + " is invalid");
		}

	}

	/**
	 * This method is used to add an image to the system
	 * 
	 * @param con - the console object which the image is added to
	 */
	public static void addImage(Console con) {
		con.println();

		con.println("OK -> Please enter all details relating to the image:");

		con.println();

		try {
			con.print("Enter a title: ");
			String title = con.readLn();

			// Checking to see if the user enters an empty String, if so, an error message
			// will be produced
			if (title.isEmpty()) {
				con.setColour(Color.red);
				con.println("Error occured, please enter a valid title");
				con.setColour(Color.white);
				return;
			}

			con.print("Enter a description: ");
			String description = con.readLn();

			// Checking to see if the user enters an empty String, if so, an error message
			// will be produced
			if (description.isEmpty()) {
				con.setColour(Color.red);
				con.println("Error occured, please enter a valid description");
				con.setColour(Color.white);
				return;
			}

			con.print("Enter a genre: ");
			String type = con.readLn();

			// Checking to see if the user enters an empty String, if so, an error message
			// will be produced
			if (type.isEmpty()) {
				con.setColour(Color.red);
				con.println("Error occured, please enter a valid type");
				con.setColour(Color.white);
				return;
			}

			ImageType imgType = ImageType.valueOf(type.toUpperCase()); // Converting the String to an ImageType

			con.print("Enter the date which the image was taken on (yyyy-mm-dd): ");
			String dateTaken = con.readLn();

			// Checking to see if the user enters an empty String, if so, an error message
			// will be produced
			if (dateTaken.isEmpty()) {
				con.setColour(Color.red);
				con.println("Error occured, please enter a valid date");
				con.setColour(Color.white);
				return;
			}

			LocalDate parsedDate = LocalDate.parse(dateTaken); // Converting the String to DateTaken

			con.print("Enter a thumbnail: ");
			String thumbnail = con.readLn();

			// Checking to see if the user enters an empty String, if so, an error message
			// will be produced
			if (thumbnail.isEmpty()) {
				con.setColour(Color.red);
				con.println("Error occured, please enter a valid thumbnail");
				con.setColour(Color.white);
				return;
			}

			ImageRecord img = new ImageRecord(title, description, imgType, parsedDate, thumbnail); // Creating
																									// the new
																									// image
																									// based on
																									// the
																									// user's
																									// input
			imgManager.addImage(img); // Adding the image to ImageManager
			con.println();
			con.println("-> Image Added");

			// Exception handling if there is an error
		} catch (Exception ex) {
			con.setColour(Color.red);
			con.println("An error has occured, please try again.");
			con.setColour(Color.white);
			con.println();
		}

	}

	/**
	 * This is a method which adds some initial images into the system as described
	 * in the specification
	 */
	public static void addSomeImages(Console con) {
		// Creating all of the LocalDate objects
		LocalDate date1 = LocalDate.of(2023, 1, 1);
		LocalDate date2 = LocalDate.of(2023, 2, 1);
		LocalDate date3 = LocalDate.of(2023, 3, 1);
		LocalDate date4 = LocalDate.of(2023, 4, 1);
		LocalDate date5 = LocalDate.of(2023, 5, 1);
		LocalDate date6 = LocalDate.of(2023, 6, 1);
		LocalDate date7 = LocalDate.of(2023, 6, 1);

		// Creating all of the images

		try {
			imgManager.addImage(new ImageRecord("Andromeda Galaxy", "Image of the Andromeda galaxy.",
					ImageType.ASTRONOMY, date1, "Andromeda.png"));
		} catch (Exception ex) {
			con.println(ex);
		}

		try {
			imgManager.addImage(new ImageRecord("Lanyon QUB", "An image of the QUB Lanyon building.",
					ImageType.ARCHITECTURE, date2, "LanyonQUB.png"));
		} catch (Exception e) {
			con.println(e);
		}

		try {
			imgManager.addImage(new ImageRecord("Kermit Plays Golf", "An image of Kermit the frog playing golf",
					ImageType.SPORT, date3, "KermitGolf.png"));
		} catch (Exception e) {
			con.println(e);
		}

		try {
			imgManager.addImage(new ImageRecord("Mourne Mountains", "A panoramic view of the Mourne Mountains",
					ImageType.LANDSCAPE, date4, "Mournes.png"));
		} catch (Exception e) {
			con.println(e);
		}

		try {
			imgManager.addImage(new ImageRecord("Homer Simpson", "Homer Simpson - A portrait of the man",
					ImageType.PORTRAIT, date3, "Homer.png"));
		} catch (Exception e) {
			con.println(e);
		}

		try {
			imgManager.addImage(new ImageRecord("Red Kite", "A Red Kite bird of prey in flight", ImageType.NATURE,
					date4, "RedKite.png"));
		} catch (Exception e) {
			con.println(e);
		}

		try {
			imgManager.addImage(new ImageRecord("Central Park", "An overhead view of Central Park New York USA",
					ImageType.AERIAL, date5, "CentralPark.png"));
		} catch (Exception e) {
			con.println(e);
		}

		try {
			imgManager.addImage(new ImageRecord("Apples", "A bunch of apples", ImageType.FOOD, date6, "Apples.png"));
		} catch (Exception e) {
			con.println(e);
		}

		try {
			imgManager.addImage(new ImageRecord("Programming Meme", "A ChatGPT programming meme", ImageType.OTHER,
					date7, "ChatGPT.png"));
		} catch (Exception e) {
			con.println(e);
		}

	}

}
