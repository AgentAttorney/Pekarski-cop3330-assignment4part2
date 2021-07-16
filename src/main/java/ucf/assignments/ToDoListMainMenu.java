package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Richard Pekarski
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ToDoListMainMenu extends Application { // This functions as our 'main' class
    public static void main(String[] args) {
        launch(args);
    }
     public void start(Stage primaryStage){
         try{
             // set the parent to the Menu file, then set the scene with that parent and title
             // Have the window not be resizable
             Parent root = FXMLLoader.load(getClass().getResource("Menu.FXML"));
             Scene scene = new Scene(root);
             primaryStage.setScene(scene);
             primaryStage.setTitle("To-do List Manager");
             primaryStage.show();
             primaryStage.setResizable(false);
         }
         catch(IOException e){
             e.printStackTrace();
         }
    // Set the parent using the associated FXML file
    // Make a new scene using the parent as the template window
    // Set the scene to the stage
    // Set the title of the stage
    // show the stage/window
    // The above is an example of what looks like. the Format is used plenty of times
    }
}
