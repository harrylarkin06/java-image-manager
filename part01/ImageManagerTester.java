package part01;

import java.time.LocalDate;

public class ImageManagerTester {

	public static void main(String[] args) {
		ImageManager imgManager = new ImageManager();
		LocalDate date1 = LocalDate.of(2023, 1, 1);
		
		try {
			ImageRecord img1 = new ImageRecord("Andromeda Galaxy", "Image of the Andromeda galaxy", ImageType.ASTRONOMY,
					date1, "Andromeda.png");
			imgManager.addImage(img1);
			imgManager.getAllImages();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
