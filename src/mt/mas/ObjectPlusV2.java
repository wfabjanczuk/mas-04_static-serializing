package mt.mas;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public abstract class ObjectPlusV2 implements Serializable {
    private static Map<Class, List<ObjectPlusV2>> allExtents = new Hashtable<>();
    private static Map<Class, Map<String, Object>> allStaticFields = new Hashtable<>();

    /**
     * Constructor.
     */
    public ObjectPlusV2() {
        List extent = null;
        Class theClass = this.getClass();

        if (allExtents.containsKey(theClass)) {
            // An extent of this class already exist
            extent = allExtents.get(theClass);
        } else {
            // An extent does not exist - create a new one
            extent = new ArrayList();
            allExtents.put(theClass, extent);
        }

        extent.add(this);
    }

    /**
     * Writes all extents to the given stream (a class method).
     *
     * @throws IOException
     */
    public static void writeSerializedSubclasses(ObjectOutputStream stream) throws IOException, IllegalAccessException {
        readAllStaticFieldsFromSubclasses();
        stream.writeObject(allExtents);
        stream.writeObject(allStaticFields);
    }

    /**
     * Reads all extents from the given stream (a utility class method).
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void readSerializedSubclasses(ObjectInputStream stream)
            throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        allExtents = (Hashtable) stream.readObject();
        allStaticFields = (Hashtable) stream.readObject();
        writeAllStaticFieldsToSubclasses();
    }

    /**
     * Shows an extent of the given class (a utility class method)
     *
     * @throws Exception
     */
    public static void showExtent(Class theClass) throws Exception {
        List extent = null;

        if (allExtents.containsKey(theClass)) {
            // Extent of this class already exist
            extent = allExtents.get(theClass);
        } else {
            throw new Exception("Unknown class " + theClass);
        }

        System.out.println("---- Extent of the class: " + theClass.getSimpleName() + " ----");

        for (Object obj : extent) {
            System.out.println(obj);
        }
    }

    /**
     * Gets the extent of the given class.
     *
     * @param type
     * @param <T>
     * @return
     * @throws ClassNotFoundException
     */
    public static <T> Iterable<T> getExtent(Class<T> type) throws ClassNotFoundException {
        if (allExtents.containsKey(type)) {
            return (Iterable<T>) allExtents.get(type);
        }

        throw new ClassNotFoundException(String.format("%s. Stored extents: %s", type.toString(), allExtents.keySet()));
    }

    private static void readAllStaticFieldsFromSubclasses() throws IllegalAccessException {
        for (Class<? extends ObjectPlusV2> subclass : getSubclasses()) {
            readAllStaticFieldsFromSubclass(subclass);
        }
    }

    private static void readAllStaticFieldsFromSubclass(Class<? extends ObjectPlusV2> subclass) throws IllegalAccessException {
        LinkedHashMap<String, Object> staticFieldMap = new LinkedHashMap<>();

        for (Field field : subclass.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                staticFieldMap.put(field.getName(), field.get(null));
            }
        }

        allStaticFields.put(subclass, staticFieldMap);
    }

    private static void writeAllStaticFieldsToSubclasses() throws IllegalAccessException, NoSuchFieldException {
        for (Class subclass : allStaticFields.keySet()) {
            for (String fieldName : allStaticFields.get(subclass).keySet()) {
                Field field = subclass.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(null, allStaticFields.get(subclass).get(fieldName));
            }
        }
    }

    private static Set<Class<? extends ObjectPlusV2>> getSubclasses() {
        Reflections reflections = new Reflections(
                "", new SubTypesScanner());
        return reflections.getSubTypesOf(ObjectPlusV2.class);
    }

    public static void printSubclasses() {
        for (Class<? extends ObjectPlusV2> subclass : getSubclasses()) {
            System.out.println(subclass.getSimpleName());
        }
    }

    public static void showStaticFields(Class theClass) throws Exception {
        readAllStaticFieldsFromSubclass(theClass);
        Map<String, Object> staticFields = allStaticFields.get(theClass);

        System.out.println("---- Static fields of the class: " + theClass.getSimpleName() + " ----");

        for (Map.Entry entry : staticFields.entrySet()) {
            System.out.println(entry.getKey() + ": " + (entry.getValue() == null
                    ? "(null)"
                    : entry.getValue().toString()
            ));
        }
    }
}
