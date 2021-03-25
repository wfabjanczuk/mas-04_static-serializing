package mt.mas;

import java.io.Serializable;
import java.util.Date;

public class Movie2_V2 extends ObjectPlusV2 implements Serializable {
    /* Class body */

    //	 Business implementation

    private static Integer instanceCount = 0;
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

        instanceCount++;
        lastInstanceCreationDate = new Date();
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

    public static Integer getInstanceCount() {
        return instanceCount;
    }

    public static void setInstanceCount(Integer instanceCount) {
        Movie2_V2.instanceCount = instanceCount;
    }

    public static Date getLastInstanceCreationDate() {
        return lastInstanceCreationDate;
    }

    public static void setLastInstanceCreationDate(Date lastInstanceCreationDate) {
        Movie2_V2.lastInstanceCreationDate = lastInstanceCreationDate;
    }

    public static void showStaticFields() throws Exception {
        ObjectPlusV2.showStaticFields(Movie2_V2.class);
    }
}
