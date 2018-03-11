package cs2410.assn4.Model;

/**
 * A custom data type that stores two strings, one for urls and one
 * for the picture names
 *
 * @author Amitesh
 * @version 1.0
 */
public class Pictures
{
    /**
     * String that stores the url
     */
    private String url;
    /**
     * String that stores the picture name
     */
    private String name;

    /**
     * Getter for url
     * @return the picture's url
     */
    public String getURL()
    {
        return url;
    }

    /**
     * Getter for picture name
     * @return the picture's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Constructor that initialized both Strings
     * @param theUrl string containing picture url
     * @param theName string containing picture name
     */
    Pictures(String theUrl, String theName)
    {
        url = theUrl;
        name = theName;
    }
}
