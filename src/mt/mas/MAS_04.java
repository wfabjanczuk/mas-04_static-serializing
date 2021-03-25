package mt.mas;

import java.io.*;
import java.util.Date;

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
            testExtentObjectPlus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testExtentObjectPlus() throws Exception {
        final String extentFilePath = "extent-objectplus.bin";   // TODO update the file name

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
}
