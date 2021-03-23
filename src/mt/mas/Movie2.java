package mt.mas;

import java.io.Serializable;
import java.util.Date;

/**
 * Information about a movie using the ObjectPlus.
 *
 * @author Mariusz Trzaska
 * Fill free to send me any remarks: mtrzaska@pjwstk.edu.pl
 */
public class Movie2 extends ObjectPlus implements Serializable {
	/* Class body */

	//	 Business implementation

	private String title;
	private float price;
	private Date additionDate;
	
	/**
	 * The constructor.
	 */
	public Movie2(String title, Date additionDate, float price) {
		// Call the constructor from the super class
		super();

		this.title = title;
		this.additionDate = additionDate;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	 * Shows the extent of the Movie class.
	 * Metoda klasowa.
	 * @throws Exception 
	 */
	public static void showExtent() throws Exception {
		ObjectPlus.showExtent(Movie2.class);
	}

	/**
	 * Gets the addition date.
	 * @return
	 */
	public Date getAdditionDate() {
		return additionDate;
	}

	/**
	 * Sets the addition date.
	 * @param additionDate
	 */
	public void setAdditionDate(Date additionDate) {
		this.additionDate = additionDate;
	}
}
