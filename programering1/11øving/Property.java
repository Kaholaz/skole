/**
 * The Property-class holds information and processes information related to a
 * specific property.
 */
public class Property {
    private String muniName, name, owner;
    private int muniNum, lotNum, secNum;
    private float area;

    /**
     * Constructor for a property object. Takes in all the properties of the
     * Property class except the common name, since the name is optional.
     * 
     * @param muniName The name of the municipality the property is located in.
     * @param muniNum  The number corresponding to the municipality the property is
     *                 located in.
     * @param lotNum   The lot number (gårdsnummer) of the property.
     * @param secNum   The sector number (bruksnummer) of the property.
     * @param area     The area in square meters of the property.
     * @param owner    The name of the current owner of the property.
     */
    public Property(String muniName, int muniNum, int lotNum, int secNum, float area, String owner) {
        this(muniName, muniNum, lotNum, secNum, null, area, owner);
    }

    /**
     * Constructor for a property object. Takes in all the properties of the
     * Property class.
     * 
     * @param muniName The name of the municipality the property is located in.
     * @param muniNum  The number corresponding to the municipality the property is
     *                 located in.
     * @param lotNum   The lot number (gårdsnummer) of the property.
     * @param secNum   The sector number (bruksnummer) of the property.
     * @param name     The common name (bruksnavn) of the property.
     * @param area     The area in square meters of the property.
     * @param owner    The name of the current owner of the property.
     */
    public Property(String muniName, int muniNum, int lotNum, int secNum, String name, float area, String owner) {
        this.muniName = muniName;
        this.muniNum = muniNum;
        this.lotNum = lotNum;
        this.secNum = secNum;
        this.name = name;
        this.area = area;
        this.owner = owner;
    }

    /**
     * Gets the name of the municipality.<br>
     * <br>
     * Since changes to a municipality name normaly happends when municipalities
     * fuseion, fission, or other changes to the municipality borders (changes that
     * would require a change in the lot numbering schema), changeing the
     * municipality name is not supported.
     * 
     * @return The name of the municipality
     * 
     */
    public String getMuniName() {
        return muniName;
    }

    /**
     * @return The common name (bruksnavn) of a property.
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the common name (bruksnavn) of a property. The name of the property
     * is is not a defining characteristic of the property, and changes to it will
     * therefore be premitted. This could be usefull if one name falls out of favor
     * over another more popular name.
     * 
     * @param name The new name of the property.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The name of the owner of the property.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Changes the name of the owner of the property. The owner of the property is
     * not a defining characteristic of the property, and changes to it will
     * therefore be premitted. This is usefull in cases where the property changes
     * owners, fore example by property sale.
     * 
     * @param owner The name of the new owner of the property.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Gets the number associated with the municipality.<br>
     * <br>
     * Since changes to a municipality number normaly happends when municipalities
     * fuseion, fission, or other changes to the municipality borders (changes that
     * would require a change in the lot numbering schema), changeing the
     * municipality name is not supported.
     * 
     * @return The name of the municipality
     * 
     */
    public int getMuniNum() {
        return muniNum;
    }

    /**
     * Gets the lot number (gårdsnummer)<br>
     * <br>
     * Since the lot number is a defining characteristic of a property, changeing
     * the lot number is not supported.
     * 
     * @return The lot number.
     */
    public int getLotNum() {
        return lotNum;
    }

    /**
     * Gets the section number (bruksnummer)<br>
     * <br>
     * Since the section number is a defining characteristic of a property,
     * changeing the section number is not supported.
     * 
     * @return The section number.
     */
    public int getSecNum() {
        return secNum;
    }

    /**
     * Gets the area of the property (in square meters)<br>
     * <br>
     * Scince the area is a defining characteristic of a property, changeing the
     * area is not supported.
     * 
     * @return The area of the property.
     */
    public float getArea() {
        return area;
    }

    /**
     * Creates a unique identifyer of a property specified by the municipality
     * number, lot number, and sector number.<br>
     * <br>
     * The string is formated this way:
     * {@code muniNumber}-{@code lotNumber}/{@code sectorNumber}
     * 
     * @param muniNum The municipality number corresponding to the municipality in
     *                which the property is located in.
     * @param lotNum  The lot number the property is located in.
     * @param secNum  The sector number the property is located in.
     * @return A unique identificator string that identifies a property.
     */
    public static String makeIdentificatorString(int muniNum, int lotNum, int secNum) {
        return String.format("%d-%d/%d", muniNum, lotNum, secNum);
    }

    /**
     * Creates a unique identifyer of the property on the form:
     * {@code %municipalityNumber%}-{@code %lotNumber%}/{@code %sectionNumber%} <br>
     * (ex. 1504-54/73)
     * 
     * @return A unique identifyer of the property.
     */
    public String identificatorString() {
        return Property.makeIdentificatorString(muniNum, lotNum, secNum);
    }

    /**
     * Returns a string represetation of the object where all the properties are
     * listed and separated by semicolons.
     */
    @Override
    public String toString() {
        return String.format(
                "Municipality name: %s; Muni. nr. %d; Lot nr.: %d; Section nr.: %d; Name: %s; Area: %.2f square meters; Owner: %s",
                muniName, muniNum, lotNum, secNum, name, area, owner);
    }
}