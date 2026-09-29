package part01;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This is a class which manages all of the images within the system
 */
public class ImageManager {
	private ArrayList<ImageRecord> images; // An ArrayList which will store all the images within the manager

	/**
	 * This is the constructor for ImageManager
	 */
	public ImageManager() {
		this.images = new ArrayList<ImageRecord>(); // Initialising the ArrayList
	}

	/**
	 * This method allows the user to add an image to the images ArrayList
	 * 
	 * @param image - image object which the user enters
	 */
	public void addImage(ImageRecord image) {
		// Go through all images in the ArrayList and check if there is an existing
		// image with the same ID, if so, print an error message, else, print the image
		for (int i = 0; i < images.size(); i++) {
			int id = images.get(i).getId();
			if (image.getId() == id) {
				System.out.println("Error, there is an existing image with the same ID.");
				break;
			}
		}
		images.add(image);

	}

	/**
	 * This method allows us to search for an ImageRecord with specific ID in
	 * images.
	 * 
	 * @param id - the ID which the user wants to search for
	 * @return an ImageRecord object which contains the same ID that the user
	 *         entered, or null if there are no matching images
	 */
	public ImageRecord searchId(int id) {
		// Initialising an ImageRecord
		ImageRecord target = null;

		// Going through all of the images in the ArrayList, checking if there are any
		// images with the same ID that the user enters
		for (int i = 0; i < images.size(); i++) {
			ImageRecord img = images.get(i);
			if (img.getId() == id) {
				target = img;
				break;
			}
		}
		return target;
	}

	/**
	 * This is as method which allows the user to search for the title of an image.
	 * 
	 * @param str - the String which the user enters and wants to search for. It can
	 *            be a partial part of a title, or a full title.
	 * @return an ImageAlbum object which will contain all of the images which
	 *         contain the title the user searched for, or null if there are no
	 *         matching images
	 */
	public ImageAlbum searchTitle(String str) {
		// Initialising an ImageAlbum
		ImageAlbum target = new ImageAlbum();

		// Using an enhanced for loop to go through all the images in the ArrayList and
		// checking if any of them contain the String which the user entered
		for (ImageRecord img : images) {
			if (img.getTitle().toLowerCase().contains(str.toLowerCase())) {
				target.addImage(img);
			}
		}
		return target;
	}

	/**
	 * This is as method which allows the user to search for the description of an
	 * image.
	 * 
	 * @param str - the String which the user enters and wants to search for. It can
	 *            be a partial part of a description, or a full description.
	 * @return an ImageAlbum object which will contain all of the images which
	 *         contain the description the user searched for, or null if there are
	 *         no matching images
	 */
	public ImageAlbum searchDescription(String str) {
		// Initialising an ImageAlbum
		ImageAlbum target = new ImageAlbum();

		// Using an enhanced for loop to go through all the images in the ArrayList and
		// checking if any of them contain the String which the user entered
		for (ImageRecord img : images) {
			if (img.getDescription().toLowerCase().contains(str.toLowerCase())) {
				target.addImage(img);
			}
		}
		return target;
	}

	/**
	 * This is a method which allows the user to search for a specific genre/type of
	 * an image.
	 * 
	 * @param type - The type which the user enters and wants to search for
	 * @return an ImageAlbum object which will contain all of the images which
	 *         contain the genre the user searched for, or null if there are no
	 *         matching images
	 */
	public ImageAlbum searchGenre(ImageType type) {
		// Initialising an ImageAlbum
		ImageAlbum target = new ImageAlbum();

		// Going through all of the images in the ArrayList and checking if there are
		// any images with the same type that the user enters
		for (int i = 0; i < images.size(); i++) {
			ImageRecord img = images.get(i);
			if (img.getType() == type) {
				target.addImage(img);
			}
		}
		return target;
	}

	/**
	 * This is a method which allows the user to search for an image within a date
	 * range
	 * 
	 * @param start - the beginning of the date range
	 * @param end   - the end of the date range
	 * @return an ImageAlbum object which will contain all of the images which were
	 *         taken between the start and end dates, or null if there are no
	 *         matching images
	 */
	public ImageAlbum searchDates(LocalDate start, LocalDate end) {
		// Initialising an ImageAlbum
		ImageAlbum target = new ImageAlbum();

		// Going through all of the images in the ArrayList and checking if any of the
		// images were taken between the start and end dates entered by the user
		for (int i = 0; i < images.size(); i++) {
			ImageRecord img = images.get(i);
			if (img.getDateTaken().isAfter(start) && img.getDateTaken().isBefore(end)
					|| img.getDateTaken().isEqual(start) || img.getDateTaken().isEqual(end)) {
				target.addImage(img);
			}
		}
		return target;
	}

	/**
	 * This is a method which returns all of the images within ImageManager
	 * 
	 * @return an ImageAlbum object which will contain all images within
	 *         ImageManager
	 */
	public ImageAlbum getAllImages() {
		// Initialising an ImageAlbum
		ImageAlbum target = new ImageAlbum();

		// Going through all the images within the ArrayList and adding them to the
		// ImageAlbum
		for (int i = 0; i < images.size(); i++) {
			ImageRecord img = images.get(i);
			target.addImage(img);
		}
		return target;
	}

	/**
	 * This is a method which adds some initial images into the system as described
	 * in the specification
	 */
	public void addSomeImages() {
		LocalDate date1 = LocalDate.of(2023, 1, 1);
		LocalDate date2 = LocalDate.of(2023, 2, 1);
		LocalDate date3 = LocalDate.of(2023, 3, 1);
		LocalDate date4 = LocalDate.of(2023, 4, 1);
		LocalDate date5 = LocalDate.of(2023, 5, 1);
		LocalDate date6 = LocalDate.of(2023, 6, 1);
		LocalDate date7 = LocalDate.of(2023, 6, 1);

		try {
			addImage(new ImageRecord("Andromeda Galaxy", "Image of the Andromeda galaxy.", ImageType.ASTRONOMY, date1,
					"Andromeda.png"));
		} catch (Exception ex) {
			System.out.println(ex);
		}

		try {
			addImage(new ImageRecord("Lanyon QUB", "An image of the QUB Lanyon building.", ImageType.ARCHITECTURE,
					date2, "LanyonQUB.png"));
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			addImage(new ImageRecord("Kermit Plays Golf", "An image of Kermit the frog playing golf", ImageType.SPORT,
					date3, "KermitGolf.png"));
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			addImage(new ImageRecord("Mourne Mountains", "A panoramic view of the Mourne Mountains",
					ImageType.LANDSCAPE, date4, "Mournes.png"));
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			addImage(new ImageRecord("Homer Simpson", "Homer Simpson - A portrait of the man", ImageType.PORTRAIT,
					date3, "Homer.png"));
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			addImage(new ImageRecord("Red Kite", "A Red Kite bird of prey in flight", ImageType.NATURE, date4,
					"RedKite.png"));
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			addImage(new ImageRecord("Central Park", "An overhead view of Central Park New York USA", ImageType.AERIAL,
					date5, "CentralPark.png"));
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			addImage(new ImageRecord("Apples", "A bunch of apples", ImageType.FOOD, date6, "Apples.png"));
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			addImage(new ImageRecord("Programming Meme", "A ChatGPT programming meme", ImageType.OTHER, date7,
					"ChatGPT.png"));
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * A toString() method which returns the details relating to an ImageRecord
	 */
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < images.size(); i++) {
			result += images.get(i) + "\n";
		}
		return result;
	}
}
