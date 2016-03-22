/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydiary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

/**
 *
 * @author Adarsh
 */
public class MyDiary extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setTitle("My Diary");
        
        FXMLLoader root = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
        
        Pane pane = (Pane) root.load();
        
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        LoginScreenController controller = (LoginScreenController) root.getController();
        controller.setPreviousStage(stage);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}