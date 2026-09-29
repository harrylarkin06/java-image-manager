package part01;

/**
 * This enumeration contains all of the different types an image can be
 */
public enum ImageType {
	// All possible types, along with the String version, which can be accessed
	// using the toString()
	ASTRONOMY("Astronomy"), ARCHITECTURE("Architecture"), SPORT("Sport"), LANDSCAPE("Landscape"), PORTRAIT("Portrait"),
	NATURE("Nature"), AERIAL("Aerial"), FOOD("Food"), OTHER("Other");

	private String imgStr;

	/**
	 * This is the constructor for ImageType
	 * 
	 * @param img
	 */
	private ImageType(String img) {
		imgStr = img;
	}

	/**
	 * A toString() method which returns the String version of the type
	 */
	public String toString() {
		return imgStr;
	}
}
