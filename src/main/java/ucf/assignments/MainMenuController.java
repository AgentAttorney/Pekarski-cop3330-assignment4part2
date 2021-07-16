package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 first_name last_name
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML private Button validateButton;
    @FXML private Button AddItemButton;
    @FXML private Button DeleteButton;
    @FXML private Button SaveButton;
    @FXML private Button LoadButton;

    @FXML private TextArea UserDescription;
    @FXML private TextField UserTitle;
    @FXML private TextField UserDate;

    @FXML private ListView<String> names;
    @FXML private TableView<Items> listItems;
    @FXML private TableColumn<Items,String> nameList;
    @FXML private TableColumn<Items,String> DescList;
    @FXML private TableColumn<Items, String> DueDateList;
    @FXML private TableColumn<Items, String> CheckMarkList;

    final FileChooser fc = new FileChooser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Disable the add button until the item is valid
        AddItemButton.setDisable(true);
        // Make the tableview with our Items Parameters as the columns
        nameList.setCellValueFactory(new PropertyValueFactory<Items,String>("name"));
        DescList.setCellValueFactory(new PropertyValueFactory<Items,String>("Description"));
        DueDateList.setCellValueFactory(new PropertyValueFactory<Items,String>("Due_Date"));
        CheckMarkList.setCellValueFactory(new PropertyValueFactory<Items,String>("Complete"));

        // Set all the columns in the tableView and the tableView Itself to be editable
        // Make the Due-Date a String so we can use TextFieldTableCell.forTableColumn()

        listItems.setEditable(true);
        nameList.setCellFactory(TextFieldTableCell.forTableColumn());
        DescList.setCellFactory(TextFieldTableCell.forTableColumn());
        DueDateList.setCellFactory(TextFieldTableCell.forTableColumn());
        CheckMarkList.setCellFactory(TextFieldTableCell.forTableColumn());

        // Set the selection model to multiple so we can delete multiple items
        // populate the table with the Items
        listItems.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listItems.setItems(getTheItems());
    }
    public ObservableList<Items> getTheItems(){
        // initialize the items as an empty ObservableArrayList at the start of the program
        return FXCollections.observableArrayList();
    }
    public void ValidateButtonPushed(ActionEvent event){
        // call the validItem method with our lengths of the title, description
        // Also include the due date as a string
        // Enable the AddItem Button
        int desc_length = UserDescription.getLength();
        int title_length = UserTitle.getLength();
        String due_date = UserDate.getText();
        boolean valid_item =  Items.validItem(desc_length,title_length,due_date);
        if(valid_item){
            AddItemButton.setDisable(false);
        }
    }
    public void addItemButtonPushed(ActionEvent event){
        // Make a new Item with our User textFields & Areas
        // Add the list of items, and disable the add button
        Items newItem = new Items(UserTitle.getText(),UserDate.getText(),UserDescription.getText(),"Incomplete");
        ObservableList<Items> newList = Items.addItem(listItems.getItems(),newItem);
        listItems.setItems(newList);
        AddItemButton.setDisable(true);
    }
    public void DeleteButtonPushed(ActionEvent event){
        // make two observable lists of items
        // set one to get all the items, and the other to get the selected items
        // Call the removeItems method
        ObservableList<Items> all, selected;
        all = listItems.getItems();
        selected = listItems.getSelectionModel().getSelectedItems();
        ObservableList<Items> items_not_removed = Items.removeItems(selected,all);
        listItems.setItems(items_not_removed);
    }
    // Modified these for unit tests
    public void changeCompleteStatusCellEvent(TableColumn.CellEditEvent editedCell){
        // May be some logic errors as to when this is occurring
        // Get the selected item, and set a string to get the complete status of the selected item
        // Set another string to the value you get when calling ChangedStrings with our two values
        // Set the Complete status of our selected Item
        Items ItemSelected = listItems.getSelectionModel().getSelectedItem();
        String initial = ItemSelected.getComplete();
        String final_string = ItemSelected.ChangedStrings(initial,editedCell.getNewValue().toString());
        ItemSelected.setComplete(final_string);
    }
    public void changeItemNameCellEvent(TableColumn.CellEditEvent editedCell){
        // Same structure as changing the complete status
        // Get selected item, its name, and get the editedCell value in string form
        // Set the new Name for the item
        Items ItemSelected = listItems.getSelectionModel().getSelectedItem();
        String initial = ItemSelected.getName();
        String final_Name = ItemSelected.ChangedStrings(initial,editedCell.getNewValue().toString());
        ItemSelected.setName(final_Name);
    }
    public void changeDueDateCellEvent(TableColumn.CellEditEvent editedCell){
        // Same structure as changing the complete status or item name
        // Calls the ChangeDueDate function, which works with LocalDate Objects
        // to check if the new String is a valid date
        Items ItemSelected = listItems.getSelectionModel().getSelectedItem();
        String initial_Date = listItems.getSelectionModel().getSelectedItem().getDue_Date();
        String final_Date = ItemSelected.ChangeDueDate(ItemSelected,editedCell.getNewValue().toString());
        ItemSelected.setDue_Date(final_Date);
    }
    public void changeDescriptionCellEvent(TableColumn.CellEditEvent editedCell){
        // Effectively the same process for changing the name, or complete status
        Items ItemSelected = listItems.getSelectionModel().getSelectedItem();
        String initial = ItemSelected.getDescription();
        String final_Description = ItemSelected.ChangedStrings(initial,editedCell.getNewValue().toString());
        ItemSelected.setDescription(final_Description);
    }

    public void displayIncompleteButtonPushed(ActionEvent event){
        // Create an observable list that calls GetTheOldDisplay with our Strings in our List View
        // Remove all the Items on that to_Remove ObList from our names
        // call displayIncomplete with our items in the table and the Strings in our names List view
        // Set the value of that method to be the Items of the names Listview
        ObservableList<String> to_Remove = Items.GetTheOldDisplay(names.getItems());
        names.getItems().removeAll(to_Remove);
        ObservableList<String> new_Items = Items.displayIncomplete(listItems.getItems(),names.getItems());
        names.setItems(new_Items);
    }
    public void displayCompleteButtonPushed(ActionEvent event){
        // Create an observable list that calls GetTheOldDisplay with our Strings in our List View
        // Remove all the Items on that to_Remove ObList from our names
        // call displayComplete with our items in the table and the Strings in our names List view
        // Set the value of that method to be the Items of the names Listview
        ObservableList<String> to_Remove = Items.GetTheOldDisplay(names.getItems());
        names.getItems().removeAll(to_Remove);
        ObservableList<String> new_Items = Items.displayComplete(listItems.getItems(),names.getItems());
        names.setItems(new_Items);
    }
    public void displayAllButtonPushed(ActionEvent event){
        // Create an observable lsit that calls GetTheOldDisplay with our Strings in the List View
        // Remove the item names from the List View
        // Add all the item names into our list view taking the data from the items on our tableview
        ObservableList<String> to_Remove = Items.GetTheOldDisplay(names.getItems());
        names.getItems().removeAll(to_Remove);

        names.getItems().removeAll(to_Remove);
        for(Items item: listItems.getItems()){
            names.getItems().add(item.getName());
        }
    }
    public void SaveButtonPushed(ActionEvent event){
        // Create a new instance of our WriteObject
        // call the serialize list method with our List of Items from the table
        // Ideally I would use a FileChooser over direct serialization
        WriteObject writeObj = new WriteObject();
        writeObj.serializeLists(listItems.getItems());
    }
    public void LoadButtonPushed(ActionEvent event){
        // Create a new instance of our WriteObject
        // call a method that sets the default directory of what to load being a previously Saved List
        // Populate the tableview with our loaded file information
    }
}

