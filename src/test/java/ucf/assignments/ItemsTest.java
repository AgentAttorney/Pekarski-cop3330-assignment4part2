package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemsTest {

    @Test // 1
    void items_101(){
        // You wanted 100 items, you got 100 items
        // Create an observable List with 100 items
        // add an item to it, then check if the size is 101 (also y'know that it doesn't explode)
        Items Item1 = new Items(null,null,null,null);
        ObservableList<Items> item_list_100 = FXCollections.observableArrayList(Item1,Item1,Item1,Item1,Item1,Item1
                ,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,
                Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,
                Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,
                Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,
                Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,
                Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,
                Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1,Item1);

        Items Item2 = new Items(null,null,null,null);
        Items.addItem(item_list_100,Item2);
        assertEquals(101,item_list_100.size());
    }
    @Test // 5, 6
    void removeItems_1Item_AllItems() {
        // Create two observable lists of items, which will be populated with the 5 items
        // Our 'Large' list will need 3 copies to perform the unit tests required
        // The first copy will remove the items specified in Remove using the Items method
        // assert that the size of our first copy is two less (i.e. 3)
        // Repeat this with our second and third copies (Items.removeItems(Third_Copy,Second_Copy)
        // assert that the size of our Second_Copy is 0

        Items Item1 = new Items("Item4", null,"Description4","Complete");
        Items Item2 = new Items("Item2", null,"Description2","Complete");
        Items Item3 = new Items("Item3", null,"Description3","Complete");
        Items Item4 = new Items("Item4", null,"Description4","Complete");
        Items Item5 = new Items("Item5",null,"description5","Incomplete");

        ObservableList<Items> Large = FXCollections.observableArrayList(Item1,Item2,Item3,Item4,Item5);
        ObservableList<Items> Large_Copy = FXCollections.observableArrayList(Item1,Item2,Item3,Item4,Item5);
        ObservableList<Items> Large_Copy2 = FXCollections.observableArrayList(Item1,Item2,Item3,Item4,Item5);

        ObservableList<Items> Removed = FXCollections.observableArrayList(Item4);
        Items.removeItems(Removed,Large);
        assertEquals(4,Large.size());
        Items.removeItems(Large_Copy2,Large_Copy);
        assertEquals(0,Large_Copy.size());

    }

    @Test // 2
    void validItem_Small_Description() {
        // Make an item and set the item's properties
        // assert that Items.validItem(our Created Item) is true
        Items Item1 = new Items("Title","2000-04-20","E","Incomplete");
        assertTrue(Items.validItem((Item1.getDescription().length()),Item1.getName().length(),Item1.getDue_Date()));
    }
    @Test // 2
    void validItem_Large_Description() {
        // Make an item and set the item's properties
        // assert that Items.validItem(our Created Item) is true

        // Note: That description is just every character of the alphabet 10 times - 4 chars (256)
        Items Item1 = new Items("Title","2016-11-01","abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuv","Incomplete");
        assertTrue(Items.validItem((Item1.getDescription().length()),Item1.getName().length(),Item1.getDue_Date()));
    }
    @Test // 3
    void validItem_Bad_Due_Date() {
        // Make an item and set the item's properties
        // assert that Items.validItem(our Created Item) is true

        // Note: That description is just every character of the alphabet 10 times - 4 chars (256)
        Items Item1 = new Items("Title","01-11-2020","abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuv","Incomplete");
        assertFalse(Items.validItem((Item1.getDescription().length()),Item1.getName().length(),Item1.getDue_Date()));
    }

    @Test // 4
    void addItem_and_check_properties_of_new_observable_list() {
        // Populate a couple of pre-made items to an observablelist
        // add a new Item to that observableList using Items.addItem
        // assert that the size of our list is 4, and that the values of the item are held when adding the item

        Items Item1 = new Items("Name1",null,"Description1","Incomplete");
        Items Item2 = new Items("Name2",null,"Description2","Incomplete");
        Items Item3 = new Items("Name3",null,"Description3","Complete");
        ObservableList<Items> item_list = FXCollections.observableArrayList(Item1,Item2,Item3);
        Items Item4 = new Items("Name4",null,"Description4","Incomplete");
        Items.addItem(item_list,Item4);
        assertEquals(4,item_list.size());
        assertEquals("Description4",item_list.get(3).getDescription());
        assertEquals("Name4",item_list.get(3).getName());
        assertEquals("Incomplete",item_list.get(3).getComplete());

    }

    @Test // 7,9
    void changedStrings() {
        // Makes an item with specified properties
        // Set the Name,Description, and Complete Status of the item
        // Pass in Item1.changedStrings(Item1 Old Value, Constant new Value)
        // assert that the new values of our Item match the constant new values provided

        Items Item1 = new Items("Title",null,"Description","Incomplete");
        String not_change = Item1.getComplete();
        Item1.setComplete(Item1.ChangedStrings(not_change,"Complete"));
        assertEquals("Complete",Item1.getComplete());
        Item1.setName(Item1.ChangedStrings(Item1.getName(),"NewTitle"));
        assertEquals("NewTitle",Item1.getName());
        Item1.setDescription(Item1.ChangedStrings(Item1.getName(),"Descriptor"));
        assertEquals("Descriptor",Item1.getDescription());
    }

    @Test // Useful for 10,11,and 12
    void getTheOldDisplayToRemove() {

        // Make an observable list of Strings that add Item Names from 5 Items (like The Listview)
        // Have a new observable list of Strings that calls getTheOldDisplay with our previous list
        // Check if the objects are equal to one another

        ObservableList<String> the_items = FXCollections.observableArrayList();

        Items Item1 = new Items("name1",null,"description1","Incomplete");
        Items Item2 = new Items("name2",null,"description2","Incomplete");
        Items Item3 = new Items("name3",null,"description3","Complete");
        Items Item4 = new Items("name4",null,"description4","Incomplete");
        Items Item5 = new Items("name5",null,"description5","Incomplete");

        the_items.addAll(Item1.getName(),Item2.getName(),Item3.getName(),Item4.getName(),Item5.getName());
        ObservableList<String> to_Remove = Items.GetTheOldDisplay(the_items);
        assertEquals(to_Remove,the_items);

    }

    @Test // 11
    void displayIncompleteItemNames() {

        // Make an Observable List and add 5 items to it (all_items)
        // Have an empty Observable List of Strings and pass that along with all_items
        // Have that value return to an ObservableList of Strings
        // assert that the size is one less (Item3 is gone) and that the Object
        // equals another object that has the names of the incomplete items

        Items Item1 = new Items("name1",null,"description1","Incomplete");
        Items Item2 = new Items("name2",null,"description2","Incomplete");
        Items Item3 = new Items("name3",null,"description3","Complete");
        Items Item4 = new Items("name4",null,"description4","Incomplete");
        Items Item5 = new Items("name5",null,"description5","Incomplete");

        ObservableList<Items> all_items = FXCollections.observableArrayList();
        all_items.addAll(Item1,Item2,Item3,Item4,Item5);

        ObservableList<String> all_itemCompleteStatus = FXCollections.observableArrayList
                (Item1.getName(),Item2.getName(),Item4.getName(),Item5.getName());

        ObservableList<String> empty_list = FXCollections.observableArrayList();

        ObservableList<String> The_Incomplete_Items = Items.displayIncomplete(all_items,empty_list);
        assertEquals(4,The_Incomplete_Items.size());
        assertEquals(The_Incomplete_Items,all_itemCompleteStatus);
    }

    @Test // 12
    void displayCompleteItemNames() {
        Items Item1 = new Items("name1",null,"description1","Incomplete");
        Items Item2 = new Items("name2",null,"description2","Incomplete");
        Items Item3 = new Items("name3",null,"description3","Complete");
        Items Item4 = new Items("name4",null,"description4","Incomplete");
        Items Item5 = new Items("name5",null,"description5","Incomplete");

        ObservableList<Items> all_items = FXCollections.observableArrayList();
        all_items.addAll(Item1,Item2,Item3,Item4,Item5);

        ObservableList<String> all_itemCompleteStatus = FXCollections.observableArrayList
                (Item3.getName());

        ObservableList<String> empty_list = FXCollections.observableArrayList();

        ObservableList<String> The_Incomplete_Items = Items.displayComplete(all_items,empty_list);
        assertEquals(1,The_Incomplete_Items.size());
        assertEquals(The_Incomplete_Items,all_itemCompleteStatus);
    }

    @Test
    void changeDueDate_Good_Date_Format() {

        // Make a new item with a valid due date
        // Try to edit the due date with a good format
        // see if the final date is the name as the edit date
        // The Controller then actually sets the Item property

        Items Item1 = new Items("name1","2000-04-03","description","Incomplete");
        String edit_date = "2000-04-10";
        String final_date = Item1.ChangeDueDate(Item1,edit_date);
        assertEquals(edit_date,final_date);
    }
    @Test
    void changeDueDate_Bad_Date_Format() {
        // There is an issue here with making sure that the new due-date is even valid

        // Make a new item with a valid due date
        // Try to edit the due date with a bad format
        // see if the final date is the name as the original date
        // The Controller then actually sets the Item property

        Items Item1 = new Items("name1","2000-04-03","description","Incomplete");
        String initial_date = Item1.getDue_Date();
        String edit_date = "01-2000-20";
        String final_date = Item1.ChangeDueDate(Item1,edit_date);
        assertEquals(Item1.getDue_Date(),final_date);
    }
}