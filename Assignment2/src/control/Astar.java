/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage; 
/**
 *
 * @author lars
 * 
 * This is used to only start the javafx application with the fxml document astar
 * The fxml file includes some buttons not being used, i was thinking of making some sort of animation showing the path step by step, but decided against it
 */
public class Astar extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/astar.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("A* larbje" );
        stage.setMaxWidth(1044);        
        stage.setMaxHeight(666);
        stage.setMinHeight(666);
        stage.setMinWidth(1044);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
