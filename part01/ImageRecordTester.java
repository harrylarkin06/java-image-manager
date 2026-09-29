package part01;

import java.time.LocalDate;

public class ImageRecordTester {

	public static void main(String[] args) {

		LocalDate date1 = LocalDate.of(2023, 1, 1);
		/* String date1Formatted = String.format("%02d-%02d-%d", date1.getDayOfMonth(), date1.getMonthValue(),
				date1.getYear());
		*/

		try {
			ImageRecord ir1 = new ImageRecord("Andromeda Galaxy", "Image of the Andromeda Galaxy", ImageType.ASTRONOMY,
					date1, "Andromeda.png");
			System.out.println(ir1);
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

}
