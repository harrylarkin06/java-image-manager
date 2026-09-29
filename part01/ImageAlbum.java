package part01;

import java.util.ArrayList;

/**
 * This is a class which will contain images after a search has been carried out
 */
public class ImageAlbum {
	private ArrayList<ImageRecord> images; // An ArrayList which will store all the images within the album
	private int index; // The current index when searching through the images

	/**
	 * This is the constructor for ImageAlbum
	 */
	public ImageAlbum() {
		this.images = new ArrayList<ImageRecord>(); // Initialising the ArrayList
		this.index = 0; // Initialised to the first image in the ArrayList
	}

	/**
	 * This method returns the first image in the ImageAlbum based on a search
	 * 
	 * @return the first ImageRecord, or null if no images match the search criteria
	 */
	public ImageRecord getFirst() {

		// Check if there are no images, if so, return null
		if (images.size() == 0) {
			return null;
		}

		// Go to the first image in the ArrayList
		index = 0;

		// Return the image at the first position
		return images.get(index);
	}

	/**
	 * This method returns the next image in the ImageAlbum
	 * 
	 * @return the next ImageRecord, or null if there aren't any more images to
	 *         display
	 */
	public ImageRecord getNext() {

		// Check if moving to the next index would go beyond the end of the ArrayList,
		// if so, return null
		if (index + 1 >= images.size()) {
			return null;
		}

		// Go to the next image
		index++;

		// Return the image at the new index
		return images.get(index);
	}

	/**
	 * This method returns the previous image in the ImageAlbum
	 * 
	 * @return the previous ImageRecord in the album, or null if there is no
	 *         previous image
	 */
	public ImageRecord getPrevious() {

		// Check if going to the previous index would go before the first image in the
		// ArrayList, if so,
		// return null
		if (index - 1 < 0) {
			return null;
		}

		// Go to the previous image
		index--;

		// Return the image at this index
		return images.get(index);
	}

	/**
	 * This image adds an ImageRecord to the ImageAlbum
	 * 
	 * @param image - the ImageRecord which the user wants to add
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
		images.add(image); // else add the ImageRecord to the ImageAlbum
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
