package mt.mas;

import java.io.Serializable;
import java.util.Date;

public class Movie2_V2 extends ObjectPlusV2 implements Serializable {
    /* Class body */

    //	 Business implementation

    private static String movieReviewSource = "Filmweb.pl";
    private static Date lastInstanceCreationDate = null;

    private String title;
    private float price;
    private Date additionDate;

    /**
     * The constructor.
     */
    public Movie2_V2(String title, Date additionDate, float price) {
        // Call the constructor from the super class
        super();

        this.title = title;
        this.additionDate = additionDate;
        this.price = price;

        lastInstanceCreationDate = additionDate;
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
     *
     * @throws Exception
     */
    public static void showExtent() throws Exception {
        ObjectPlusV2.showExtent(Movie2_V2.class);
    }

    /**
     * Gets the addition date.
     *
     * @return
     */
    public Date getAdditionDate() {
        return additionDate;
    }

    /**
     * Sets the addition date.
     *
     * @param additionDate
     */
    public void setAdditionDate(Date additionDate) {
        this.additionDate = additionDate;
    }

    public static Date getLastInstanceCreationDate() {
        return lastInstanceCreationDate;
    }

    public static String getMovieReviewSource() {
        return movieReviewSource;
    }

    public static void setMovieReviewSource(String movieReviewSource) {
        Movie2_V2.movieReviewSource = movieReviewSource;
    }

    public static void setLastInstanceCreationDate(Date lastInstanceCreationDate) {
        Movie2_V2.lastInstanceCreationDate = lastInstanceCreationDate;
    }

    public static void showStaticFields() throws Exception {
        ObjectPlusV2.showStaticFields(Movie2_V2.class);
    }
}
