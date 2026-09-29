package part01;

import java.time.LocalDate;

/**
 * This class is used to create an ImageRecord, using multiple variables to
 * describe each ImageRecord
 */
public class ImageRecord {
	private int imageId; // Unique id for each image
	private static int nextId = 1; // Used to auto increment the ID variable each time a new object is created
	private String title; // Image title
	private String description; // Image description
	private ImageType type; // Image type/genre (enumeration)
	private LocalDate dateTaken; // Date image was taken on
	private String thumbnail; // Image thumbnail

	/**
	 * This is the constructor for the ImageRecord class. It allows us to build
	 * ImageRecord objects. It also contains error handling, so if any errors occur,
	 * a message will be displayed.
	 * 
	 * @param title       - title of the image
	 * @param description - description of the image
	 * @param type        - type/genre of the image
	 * @param dateTaken   - date the image was taken on
	 * @param thumbnail   - thumbnail of the image
	 * @throws Exception - used for error handling
	 */
	public ImageRecord(String title, String description, ImageType type, LocalDate dateTaken, String thumbnail)
			throws Exception {
		String errors = ""; // A String which will contain any error messages
		this.imageId = nextId++; // Incrementing the ID when each ImageRecord is created

		// If the title entered is invalid, an error will be produced
		if (!setTitle(title)) {
			this.title = "No Title";
			errors += "Title Error: " + title + "\n";
		}

		// If the description entered is invalid, an error will be produced
		if (!setDescription(description)) {
			this.description = "No Description";
			errors += "Description Error: " + description + "\n";
		}

		// If the type entered is invalid, an error will be produced
		if (!setType(type)) {
			this.type = null;
			errors += "Image Type Error: " + type + "\n";
		}

		// If the date entered is invalid, an error will be produced
		if (!setDateTaken(dateTaken)) {
			this.dateTaken = null;
			errors += "Date Taken Error: " + dateTaken + "\n";
		}

		// If the thumbnail entered is invalid, an error will be produced
		if (!setThumbnail(thumbnail)) {
			this.thumbnail = "No Thumbnail";
			errors += "Thumbnail Error: " + thumbnail + "\n";
		}

		// If any validation errors occurred, throw an exception containing all errors
		if (errors.length() > 0) {
			throw new Exception(errors);
		}

	}

	/**
	 * This method allows us to access the imageId
	 * 
	 * @return
	 */
	public int getId() {
		return this.imageId;
	}

	/**
	 * This method allows us to access the title of the image
	 * 
	 * @return
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * This method allows us to access the description of the image
	 * 
	 * @return
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * This method allows us to access the type/genre of the image
	 * 
	 * @return
	 */
	public ImageType getType() {
		return type;
	}

	/**
	 * This method allows us to access the date that the photo was taken on
	 * 
	 * @return
	 */
	public LocalDate getDateTaken() {
		return this.dateTaken;
	}

	/**
	 * This method allows us to access the thumbnail of the image
	 * 
	 * @return
	 */
	public String getThumbnail() {
		return this.thumbnail;
	}

	/**
	 * This method allows the user to set the title of the image. It contains
	 * validation to ensure that the data entered is valid
	 * 
	 * @param title - the title that the user will input
	 * @return true if data entered is valid, false otherwise
	 */
	public boolean setTitle(String title) {
		if (title != null && !title.trim().isEmpty()) {
			this.title = title.trim();
			return true;
		}
		return false;
	}

	/**
	 * This method allows the user to set the description of the image. It contains
	 * validation to ensure that the data entered is valid
	 * 
	 * @param description - the description that the user will input
	 * @return true if data entered is valid, false otherwise
	 */
	public boolean setDescription(String description) {
		if (description != null && !description.trim().isEmpty()) {
			this.description = description.trim();
			return true;
		}
		return false;
	}

	/**
	 * This method allows the user to set the ImageType of the image. It contains
	 * validation to ensure that the data entered is valid
	 * 
	 * @param type - the type that the user will input
	 */
	public boolean setType(ImageType type) {
		if (type != null) {
			this.type = type;
			return true;
		}
		return false;

	}

	/**
	 * This is a method which allows the user to set the type of the image. It takes
	 * the user's input and sets the image type based on the input. This is a case
	 * of method overloading where I am overloading the setType() method.
	 * 
	 * @param type - The type the user inputs
	 */
	public boolean setType(String type) {
		if (type.equalsIgnoreCase("Astronomy")) {
			setType(ImageType.ASTRONOMY);
			return true;
		} else if (type.equals("Architecture")) {
			setType(ImageType.ARCHITECTURE);
			return true;
		} else if (type.equals("Sport")) {
			setType(ImageType.SPORT);
			return true;
		} else if (type.equals("Landscape")) {
			setType(ImageType.LANDSCAPE);
			return true;
		} else if (type.equals("Portrait")) {
			setType(ImageType.PORTRAIT);
			return true;
		} else if (type.equals("Nature")) {
			setType(ImageType.NATURE);
			return true;
		} else if (type.equals("Aerial")) {
			setType(ImageType.AERIAL);
			return true;
		} else if (type.equals("Food")) {
			setType(ImageType.FOOD);
			return true;
		} else if (type.equals("Other")) {
			setType(ImageType.OTHER);
			return true;
		}
		return false;
	}

	/**
	 * This method allows the user to set the date the image was taken on. It
	 * contains validation to ensure that the data entered is valid
	 * 
	 * @param dateTaken - the date that the user enters
	 * @return true if data entered is valid, false otherwise
	 */
	public boolean setDateTaken(LocalDate dateTaken) {
		if (dateTaken != null) {
			this.dateTaken = dateTaken;
			return true;
		}
		return false;
	}

	/**
	 * This method allows the user to set the thumbnail of the image. It contains
	 * validation to ensure that the data entered is valid
	 * 
	 * @param thumbnail - the thumbnail that the user will input
	 * @return true if data entered is valid, false otherwise
	 */
	public boolean setThumbnail(String thumbnail) {
		if (thumbnail != null && !thumbnail.trim().isEmpty()) {
			this.thumbnail = thumbnail.trim();
			return true;
		}
		return false;
	}

	/**
	 * This is a toString() method which is used to display all the data related to
	 * an ImageRecord
	 */
	public String toString() {
		String data = "ID: " + getId() + " | Title: " + getTitle() + " | Description: " + getDescription()
				+ " | Genre: " + getType() + " | Date Taken: " + getDateTaken() + " | Thumbnail: " + getThumbnail();
		return data;
	}

}
