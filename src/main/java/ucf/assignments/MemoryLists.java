package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 first_name last_name
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serial;
import java.io.Serializable;

public class MemoryLists implements Serializable {
    // I would use this class if I needed to
    @Serial
    private static final long serialVersionUID = 1L;

    private ObservableList<Items> Item_List = FXCollections.observableArrayList();
    private String name;
    public ObservableList<Items> getItems() {
        return Item_List;
    }

    public void setItems(ObservableList<Items> items) {
        this.Item_List = items;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

}
