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
//            testExtentObjectPlus();
            testExtentAndStaticFieldsOfObjectPlusV2();
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

    private static void testExtentAndStaticFieldsOfObjectPlusV2() throws Exception {
        final String extentFilePath = "extent-objectplusv2.bin";   // TODO update the file name

        var film1 = new Movie2_V2("Terminator 1", new Date(), 29.90f);
        var film2 = new Movie2_V2("Terminator 2", new Date(), 34.90f);

        try {
            // Write the extent to the stream
            var out = new ObjectOutputStream(new FileOutputStream(extentFilePath));
            ObjectPlusV2.writeSerializedSubclasses(out);
            out.close();

            System.out.println("\n1. Movie2_V2 Just after serializing: \n");
            Movie2_V2.showExtent();
            Movie2_V2.showStaticFields();

            // Set static fields to null before reading extent from the stream
            Movie2_V2.setInstanceCount(null);
            Movie2_V2.setLastInstanceCreationDate(null);

            System.out.println("\n2. Movie2_V2 After temporary setting static fields to null: \n");
            Movie2_V2.showExtent();
            Movie2_V2.showStaticFields();

            // Read the extent from the stream
            var in = new ObjectInputStream(new FileInputStream(extentFilePath));
            ObjectPlusV2.readSerializedSubclasses(in);
            in.close();

            System.out.println("\n3. Movie2_V2 After reading serialized subclasses: \n");
            Movie2_V2.showExtent();
            Movie2_V2.showStaticFields();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
