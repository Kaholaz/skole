import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * PropertyRecord is a record over a set of properties. The class provides
 * methods to add, remove and manipulate the record.
 */
public class PropertyRecord {
    private Map<String, Property> propertyRecord;

    /**
     * Constructor for the PropertyRecord-class. Initializes a new empty property
     * record.
     */
    public PropertyRecord() {
        this(new Property[0]);
    }

    /**
     * Constructor for the PropertyRecord-class. Initializes a new property record
     * that holds given properties.
     * 
     * @param properties The properties to store in the property record.
     */
    public PropertyRecord(Property... properties) {
        propertyRecord = new HashMap<String, Property>();

        for (Property property : properties) {
            addProperty(property);
        }
    }

    /**
     * Returs the number of properties in the property record.
     * 
     * @return The number of properties in the property record.
     */
    public int size() {
        return propertyRecord.size();
    }

    /**
     * Returs a specified property that matches a given identificator. The
     * identificator is a string consisting og the municipality number, lot number,
     * and sector number in this fashion:
     * {@code muniNumber}-{@code lotNumber}/{@code sectorNumber}
     * 
     * @param identificator A unique string that maps to a singe property.
     * @return Returns the property corresponding with the given identificator
     * 
     * @throws IllegalArgumentException Throws exception if the property is not in
     *                                  the property record.
     */
    public Property getProperty(String identificator) {
        if (!propertyRecord.containsKey(identificator)) {
            throw new IllegalArgumentException("The given property is not in the property record");
        }
        return propertyRecord.get(identificator);
    }

    /**
     * Returs a specified property that mathces the specified municipality number,
     * lot number, and sector number.
     * 
     * @param municipalityNumber The number corresponding to the municipality the
     *                           property is located in.
     * @param lotNubmer          The number corresponding to the lot the property is
     *                           located in.
     * @param sectorNumber       The number corresponding to the sector the property
     *                           is located in.
     * @return Returns the property corresponding with the given input.
     * 
     * @throws IllegalArgumentException Throws exception if the property is not in
     *                                  the property record.
     */
    public Property getProperty(int municipalityNumber, int lotNumber, int sectorNumber) {
        // Uniqe identification string for the given input.
        String key = Property.makeIdentificatorString(municipalityNumber, lotNumber, sectorNumber);

        return getProperty(key);
    }

    /**
     * Adds a property to the property record.
     * 
     * @param property The property that gets added to the record.
     */
    public void addProperty(Property property) {
        String key = property.identificatorString();

        // If the property record already contains a property, an exception is thrown.
        if (propertyRecord.containsKey(key)) {
            throw new IllegalArgumentException(
                    "A property with the same idetifyer is already in the property register.");
        }

        // Inserts the property into the property record.
        propertyRecord.put(property.identificatorString(), property);
    }

    /**
     * Removes a given property from the property record.
     * 
     * @param property The property that you want removed.
     */
    public void removeProperty(Property property) {
        String key = property.identificatorString();
        removeProperty(key);
    }

    /**
     * Removes a property from the property record given the unique identifyer.<br>
     * <br>
     * The identifyer of a property can be retrived by calling the
     * identificatorString property on a given property instance.
     * 
     * @param key A unique string identifyer of a property.
     */
    public void removeProperty(String key) {
        propertyRecord.remove(key);
    }

    /**
     * Calcutates the avrage property area by taking the sum of all the areas and
     * deviding by the number of properties.
     * 
     * @return The avrage property area.
     */
    public float calcAvgPropertyArea() {
        // Takes area of each property and sums them using the reduce function. The
        // reduce function iterates over the elements and runs a function over each
        // element, where b is the next element and a is the result of the previous
        // iterations. In this case reduce gets the sum of all the elements.
        float areaSum = propertyRecord.values().stream().map(p -> p.getArea()).reduce(0f, (a, b) -> a + b);
        return areaSum / size();
    }

    public String listAllProperties() {
        StringBuilder s = new StringBuilder();

        for (Property property : propertyRecord.values()) {
            s.append(property.toString());
            s.append("\n");
        }

        s.delete(s.length() - 1, s.length());
        return s.toString();
    }

    /**
     * Finds every property where the lot number is equal to the given lot number.
     * 
     * @param lotNumber The lot number you are searching for.
     * @return An array of properties where the lot number is equal to the given lot
     *         number.
     */
    public Property[] findPropertyByLotNum(int lotNumber) {
        // Creates a stream of every property where the lot number is correct.
        Stream<Property> properties = propertyRecord.values().stream().filter(p -> (p.getLotNum() == lotNumber));

        // Converts stream to array.
        return properties.toArray(Property[]::new);
    }
}
