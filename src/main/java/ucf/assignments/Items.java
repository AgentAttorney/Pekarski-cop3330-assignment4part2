package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 first_name last_name
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Items implements Serializable {
    private SimpleStringProperty name;
    private SimpleStringProperty Description;
    private String Due_Date;
    private SimpleStringProperty Complete;
    //
    public static ObservableList<Items> removeItems(ObservableList<Items> selected, ObservableList<Items> all) {
        // if selected has items, remove the selected items
        if(selected != null) {
            all.removeAll(selected);
            return all;
        }
        return all;
    }

    public static ObservableList<String> GetTheOldDisplay(ObservableList<String> items) {
        // Take the items specified and add them to an observable List detailing the previous Strings
        // from the listview and return it
        ObservableList<String> to_Remove = FXCollections.observableArrayList();
        for(String display: items ){
            to_Remove.add(display);
        }
        return to_Remove;
    }

    public static ObservableList<String> displayIncomplete(ObservableList<Items> all_items, ObservableList<String> name_items) {
        // loop through all the items in our table view and check if the complete status of the item is incomplete
        // if it is, we add it to our observable list and return it
        for(Items item: all_items){
            if(item.getComplete().equalsIgnoreCase("Incomplete"))
                name_items.add(item.getName());
        }
        return name_items;
    }

    public static ObservableList<String> displayComplete(ObservableList<Items> all_items, ObservableList<String> name_items) {
        // loop through all the items in our table view and check if the complete status of the item is Complete
        // if it is, we add it to our observable list and return that list
        for(Items item: all_items){
            if(item.getComplete().equalsIgnoreCase("Complete"))
                name_items.add(item.getName());
        }
        return name_items;
    }


    public String getComplete() {
        return Complete.get();
    }

    public SimpleStringProperty completeProperty() {
        return Complete;
    }

    public void setComplete(String complete) {
        this.Complete.set(complete);
    }

    // Items Constructor
    public Items(String name,String due_Date,String description, String Complete) {
        this.name = new SimpleStringProperty(name);
        Due_Date = due_Date;
        Description =new SimpleStringProperty(description);
        this.Complete = new SimpleStringProperty(Complete);
    }

    public static boolean validItem(int desc_length, int title_length, String due_date) {
        // Create a new LocalDateTime and DateTimeFormatter object
        // Initialize the pattern
        // parse the String to our local date object. If there is no exception, set a boolean to true
        // Otherwise, set it to false then check if the description and title meet the length requirements
        // and if the due-date is valid. If the conditions are met, return true, otherwise return false
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.ENGLISH);
        boolean valid_date = true;
        try{
            LocalDate date = LocalDate.parse(due_date,formatter);
        } catch (DateTimeParseException e) {
            valid_date = false;
        }
        if((desc_length <= 256 && title_length <= 10)&&((desc_length >= 1 && title_length>= 1))&& valid_date)
            return true;
        else
            return false;
    }

    public static ObservableList<Items> addItem(ObservableList<Items> items, Items newItem) {
        items.add(newItem);
        return items;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return Description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description.set(description);
    }

    public String getDue_Date() {
        return Due_Date;
    }

    public void setDue_Date(String due_Date) {
        Due_Date = due_Date;
    }

    public String ChangedStrings(String initial_complete, String final_complete) {
        // check if our new hypothetical value meets the title+description requirements
        // if it does, return the new value, otherwise return the old value
        if(final_complete.length() > 0 && final_complete.length() <= 256)
       return final_complete;
        else {
            return initial_complete;
        }
    }


    public String ChangeDueDate(Items itemSelected, String new_Date) {
        // Make a copy of our original ItemSelected
        // Set the Due_Date of our item to be the new due date
        // Check if that new item is valid with validItem
        // if it is, return the new item
        // otherwise, return the copy of our original item
        Items copy_ItemSelected = itemSelected;
        itemSelected.setDue_Date(new_Date);
        boolean new_item_valid = validItem(itemSelected.getDescription().length(), itemSelected.getName().length(), itemSelected.getDue_Date());
        if(new_item_valid){
            return itemSelected.getDue_Date();
        }
        else{
            return copy_ItemSelected.getDue_Date();
        }
    }
}
