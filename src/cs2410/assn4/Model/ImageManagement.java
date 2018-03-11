package cs2410.assn4.Model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * A class that handles the reading in and printing out of image URLs and
 * Titles to a file. It also stores these URLs and Titles in an ArraryList
 * to manage them
 *
 * @author Amitesh
 * @version 1.0
 */
public class ImageManagement
{
    /**
     * The constructor calls the readFile method
     */
    public ImageManagement()
    {
        readFile();
    }

    /**
     * An ArrayList that stores the URLs and Titles of the pictures displayed
     * in the image viewer
     */
    private ArrayList<Pictures> pictures = new ArrayList<Pictures>();
    /**
     * A string that stores the name and location of the file
     */
    private static final String fileName = "data/images.data";

    /**
     * A method that creates a file scanner and stores the image URLs
     * and Titles in an ArrayList
     */
    public void readFile()
    {
        Scanner fileInput = null;
        try
        {
            fileInput = new Scanner(new FileReader(fileName));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        while(fileInput.hasNext())
        {
            String input = fileInput.nextLine();
            String[] splitString = input.split(" ", 2);
            Pictures picture = new Pictures(splitString[0], splitString[1]);
            pictures.add(picture);
        }
        fileInput.close();
    }

    /**
     * Upon ending the program, printFile overwrites the existing file and
     * replaces it with the URLs and Titles of the images currently in the
     * image viewer
     */
    public void printFile()
    {
        PrintWriter fileOutput = null;

        try
        {
            fileOutput = new PrintWriter(new FileOutputStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < pictures.size(); i++)
        {
            fileOutput.print(pictures.get(i).getURL() + " ");
            fileOutput.println(pictures.get(i).getName());
        }
        fileOutput.close();
    }

    /**
     * Creates a Picture and inserts it into the ArrayList at the
     * proper index
     * @param index The current index of the ArrayList
     * @param URL The new image URL
     * @param title The new image Title
     */
    public void insert(int index, String URL, String title)
    {
        Pictures aPicture = new Pictures(URL, title);
        pictures.add(index, aPicture);
    }

    /**
     * Removes the Pictures stored at the index from the ArrayList
     * @param index The index of the Picture to be removed
     */
    public void delete(int index)
    {
        pictures.remove(index);
    }

    /**
     * A getter that returns the String of the URL
     * @param index index of desired URL
     * @return URL of desired Picture
     */
    public String getURL(int index)
    {
        return pictures.get(index).getURL();
    }

    /**
     * A getter that returns the title of the picture
     * @param index index of desired title
     * @return Title of desired picture
     */
    public String getTitle(int index) {
        return pictures.get(index).getName();
    }

    /**
     * A getter that returns the size of the ArrayList
     * @return size of pictures ArrayList
     */
    public int getSize()
    {
        return pictures.size();
    }
}
