package mt.mas;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Movie information.
 * @author Mariusz Trzaska
 * Fill free to send me any remarks: mtrzaska@pjwstk.edu.pl
 */
public class Movie implements Serializable {
	/* Class body */

	//	 Business part

	private String title;
	private float price;
	private LocalDate additionDate;  // requires Java 8+

	/**
	 * Constructor.
	 */
	public Movie() {
		// Add to the extent
		addMovie(this);
	}

	/**
	 * Constructor.
	 */
	public Movie(String title, LocalDate additionDate, float price) {
		this.title = title;
		this.additionDate = additionDate;
		this.price = price;

		// Add to the extent
		addMovie(this);
	}

	/**
	 * Gets the movie price
	 *
	 * @return
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Gets the movie price including a VAT rate
	 *
	 * @param vatRate
	 * @return
	 */
	public float getPrice(float vatRate) {
		return price * (1.0f + vatRate / 100.0f);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Movie: " + title + ", id: " + super.toString();
	}

	/**
	 * Save the object state to the given stream.
	 *
	 * @param stream
	 * @throws IOException
	 */
	private void write(DataOutputStream stream) throws IOException {
		stream.writeUTF(title);
		stream.writeFloat(price);
		stream.writeLong(additionDate.toEpochDay());    // count of days where day 0 is 1970-01-01 (ISO)
	}

	/**
	 * Reads an object state from the given stream.
	 *
	 * @param stream
	 * @throws IOException
	 */
	private void read(DataInputStream stream) throws IOException {
		title = stream.readUTF();
		price = stream.readFloat();
		long epochDay = stream.readLong();
		additionDate = LocalDate.ofEpochDay(epochDay);
	}

	//region Implementation of the extent

	/**
	 * The extent. Non-final required because of (de)serialization (persistency).
	 */
	private static List<Movie> extent = new ArrayList<>();

	/**
	 * Adds a movie to the extent.
	 *
	 * @param movie the movie
	 */
	private static void addMovie(Movie movie) {
		extent.add(movie);
	}

	/**
	 * Removes a movie from the extent.
	 *
	 * @param movie the movie
	 */
	private static void removeMovie(Movie movie) {
		extent.remove(movie);
	}

	/**
	 * Shows the extent (utility class method).
	 */
	public static void showExtent() {

		System.out.println("Extent of the class: " + Movie.class.getName());

		for (Movie movie : extent) {
			System.out.println(movie);
		}
	}

	/**
	 * Saves the entire extent to the given stream (the manual approach; utility class method).
	 *
	 * @throws IOException
	 */
	public static void writeExtent(DataOutputStream stream) throws IOException {
		// Number of objects
		stream.writeInt(extent.size());

		for (Movie movie : extent) {
			movie.write(stream);
		}
	}

	/**
	 * Loads the entire extent from the given stream (the manual approach; utility class method).
	 *
	 * @throws IOException
	 */
	public static void readExtent(DataInputStream stream) throws IOException {
		Movie movie = null;

		// Get the number of written objects
		int objectCount = stream.readInt();

		// remove the current extent
		extent.clear();

		for (int i = 0; i < objectCount; i++) {
			movie = new Movie();
			movie.read(stream);
		}
	}

	/**
	 * Saves the entire extent to the given stream (the serialization approach; utility class method).
	 *
	 * @throws IOException
	 */
	public static void writeExtent(ObjectOutputStream stream) throws IOException {
		stream.writeObject(extent);
	}

	/**
	 * Loads the entire extent from the given stream (the serialization approach; utility class method).
	 *
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		extent = (ArrayList<Movie>) stream.readObject();
	}

	//endregion
}
