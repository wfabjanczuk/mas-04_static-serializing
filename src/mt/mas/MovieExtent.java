package mt.mas;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the extent of the class Movie.
 *
 * @author Mariusz Trzaska
 * Fill free to send me any remarks: mtrzaska@pjwstk.edu.pl
 */
public class MovieExtent {

	// Extent implementation
	
	/** The extent. */
	private List<Movie> extent = new ArrayList<>();

	/**
	 * Adds a movie to the extent.
	 *
	 * @param movie the movie
	 */
	public void addMovie(Movie movie) {
		extent.add(movie);
	}

	/**
	 * Removes a movie from the extent.
	 *
	 * @param movie the movie
	 */
	public void removeMovie(Movie movie) {
		extent.remove(movie);
	}

	/**
	 * Shows the extent (utility class method).
	 */
	public void showExtent() {

		System.out.println("Extent of the class: " + Movie.class.getName());

		for (Movie movie : extent) {
			System.out.println(movie);
		}
	}
}
