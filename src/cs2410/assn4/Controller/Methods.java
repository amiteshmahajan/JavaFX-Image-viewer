package cs2410.assn4.Controller;

import cs2410.assn4.Model.ImageManagement;
import javafx.scene.image.Image;


import java.awt.*;

/**
 * A set of Methods used by the UserInterface to view, add, and delete pictures
 *
 * @author Amitesh
 * @version 1.0
 */
public class Methods
{
    /**
     * An image manager that reads image URLs and Titles in from a file,
     * stores them in an ArrayList, and prints the new URLs and Titles
     * when the program is closed
     */
    private ImageManagement imageManagement = new ImageManagement();
    /**
     * Current index in the ArrayList of the current image
     */
    private int index;
    /**
     * The URL of an image displayed when the ArrayList is empty
     */
    private String noImageFound = new String("https://www.jordans.com/~/media/jordans%20redesign/no-image-found.ashx?h=275&la=en&w=275&hash=F87BC23F17E37D57E2A0B1CC6E2E3EEE312AAD5B");

    /**
     * A constructor that initializes the index of the ArrayList to -1
     * so that it will be increased to 0 when the first image is viewed
     */
    public Methods()
    {
        index = -1;
    }

    /**
     * Returns the next image in the ArrayList, generated from the next
     * URL stored in the ArrayList
     * @return The next image
     */
    public Image nextImage()
    {
        index = (index + 1) % imageManagement.getSize();
        Image image = new Image(imageManagement.getURL(index));
        return image;
    }

    /**
     * Creates the image found at the previous index in the ArrayList,
     * generated from the URL stored in the ArrayList
     * @return The previous image
     */
    public Image prevImage()
    {
        if(index > 0)
        {
            index = (index - 1);
        }
        else
        {
            index = imageManagement.getSize() - 1;
        }
        Image image = new Image(imageManagement.getURL(index));
        return image;
    }

    /**
     * Adds an image at the current index and returns it to the UserInterface
     * @param URL URL of new image
     * @param title Title of new image
     * @return The new image
     */
    public Image addImage(String URL, String title)
    {
        imageManagement.insert(index, URL, title);
        Image image = new Image(imageManagement.getURL(index));
        return image;
    }

    /**
     * Removes the image stored at the current index and displays the next image.
     * If no images are found, an alternative image is displayed, indicating to the
     * user that there are no images
     * @return The next image or one indicating no images are left in the ArrayList
     */
    public Image delImage()
    {
        imageManagement.delete(index);
        if(imageManagement.getSize() == 0)
        {
            Image image = new Image(noImageFound);
            return image;
        }
        else
        {
            if (index == imageManagement.getSize()) index = 0;
            Image image = new Image(imageManagement.getURL(index));
            return image;
        }
    }

    /**
     * Calls printFile when the program closes to update the new URLs and Titles
     */
    public void quit()
    {
        imageManagement.printFile();
    }

    /**
     * Gets the title of an image from the ArrayList
     * @return title of image at current index
     */
    public String getTitle()
    {
        return imageManagement.getTitle(index);
    }

    /**
     * A bool that checks if there are images in the ArrayList
     * @return a true or false value
     */
    public boolean noImages() {return (imageManagement.getSize() == 0);}
}
