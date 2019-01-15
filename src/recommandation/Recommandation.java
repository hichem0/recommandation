/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommandation;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 *
 * @author hichem Bedjaoui
 *
 */

public class Recommandation extends Application {
    private static Stage primaryStage;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    public static void showgoexec(String index) throws IOException{
  FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Recommandation.class.getResource("exec"+index+".fxml"));
   BorderPane   execution =loader.load();
   Stage addDialogStage = new Stage();
   addDialogStage.setTitle("Execution");
   addDialogStage.initModality(Modality.WINDOW_MODAL);
   addDialogStage.initOwner(primaryStage);
   Scene scene = new Scene(execution);
   addDialogStage.setScene(scene);
   addDialogStage.showAndWait();}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
