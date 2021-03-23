package mt.mas;

import java.io.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

/**
 * @author Mariusz Trzaska
 * Fill free to send me any remarks: mtrzaska@pjwstk.edu.pl
 */
public class MAS_04 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
//			testInternalExtent();
//			testClassMethod();
			testOptionalAttributeMethod();
//			 testExtentManual();
//			 testExtentSerialization();
//			 testExtentObjectPlus();
			// testExternalExtent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testExtentManual() {
		final String extentFile = "d:\\temp\\mas-extent.bin"; // TODO update the file location
		testInternalExtent();

		// A test: persistency of the extent (manual impl.)
		try {
			// Write the extent to the given stream
			var out2 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(extentFile)));
			Movie.writeExtent(out2);
			out2.close();

			// Read the extent from the given stream
			var in2 = new DataInputStream(new BufferedInputStream(new FileInputStream(extentFile)));
			Movie.readExtent(in2);
			in2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Movie.showExtent();
	}

	private static void testExtentSerialization() {
		final String extentFile = "d:\\temp\\mas-extent.ser"; // TODO update the file location
		testInternalExtent();

		// A test: persistency of the extent (impl. using serialization)
		try {
			// Write the extent to the given stream
			var out = new ObjectOutputStream(new FileOutputStream(extentFile));
			Movie.writeExtent(out);
			out.close();

			// Read the extent from the given stream
			var in = new ObjectInputStream(new FileInputStream(extentFile));
			Movie.readExtent(in);
			in.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		Movie.showExtent();
	}

	private static void testInternalExtent() {
		// A test: Class extent implemented in the same class
		var movie1 = new Movie("Terminator 1", LocalDate.now(), 29.90f);
		var movie2 = new Movie("Terminator 2", LocalDate.now(), 34.90f);

		Movie.showExtent();
	}

	private static void testExternalExtent() {
		// A test: Class extent implemented using an external class
		var movieExtent = new MovieExtent();

		var movie1 = new Movie("Terminator 1", LocalDate.now(), 29.90f);
		movieExtent.addMovie(movie1);

		var movie2 = new Movie("Terminator 2", LocalDate.now(), 34.90f);
		movieExtent.addMovie(movie2);

		movieExtent.showExtent();
	}

	private static void testExtentObjectPlus() throws Exception {
		final String extentFilePath = "d:\\temp\\extent-objectplus.bin";   // TODO update the file name

		var film1 = new Movie2("Terminator 1", new Date(), 29.90f);
		var film2 = new Movie2("Terminator 2", new Date(), 34.90f);
		
		Movie2.showExtent();

	    try {
			// Write the extent to the stream
		    var out = new ObjectOutputStream(new FileOutputStream(extentFilePath));
			ObjectPlus.writeExtents(out);
			out.close();

		    // Read the extent from the stream
		    var in = new ObjectInputStream(new FileInputStream(extentFilePath));
			ObjectPlus.readExtents(in);
			in.close();

			// Gets the extent and show the content of it
		    Iterable<Movie2> movieExtent = ObjectPlus.getExtent(Movie2.class);
		    for (var movie : movieExtent) {
			    System.out.println(movie.getTitle());
		    }
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		Movie2.showExtent();
	}

	private static void testClassMethod() {
		var emp1 = new Employee("Emp 1", 4000);
		var emp2 = new Employee("Emp 2", 2000);
		var emp3 = new Employee("Emp 3", 1000);
		var emp4 = new Employee("Emp 4", 3500);

		System.out.println(Employee.findEmployeesWithMinSalary(3000));
	}

	private static void testOptionalAttributeMethod() {
		var emp1 = new Employee("Emp 1", 4000);
		var emp2 = new Employee("Emp 2", 2000);
		emp2.setExtraBonus(Optional.of(999d));

		System.out.println(emp1);
		System.out.println(emp2);

		System.out.printf("Income emp1: %s%n", emp1.getIncome());
		System.out.printf("Income emp2: %s%n", emp2.getIncome());
	}
}
