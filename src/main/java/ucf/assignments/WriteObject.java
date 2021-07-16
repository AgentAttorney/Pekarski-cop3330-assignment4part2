package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 first_name last_name
 */
import javafx.collections.ObservableList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class WriteObject {
    public void serializeLists(ObservableList<Items> save_the_list) {
        // Create a FileOutputStream and ObjectOutPutStream object
        // Initialize the Fout object with a .ser file name and initialize objout with our Fout object
        // Call the writeObject method of objout making our Observable List an ArrayList of the Items
        // close both streams
        // Issue here is that Items (SimpleStringProperty?) isn't serializable
        FileOutputStream fout = null;
        ObjectOutputStream objout = null;
        try{
            fout = new FileOutputStream("Objectsavefile.ser");
            objout = new ObjectOutputStream(fout);
            objout.writeObject(new ArrayList<Items> (save_the_list));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            if(fout != null){
                try{
                    fout.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(objout != null){
                try{
                    objout.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
