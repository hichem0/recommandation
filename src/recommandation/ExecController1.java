/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommandation;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.mahout.cf.taste.common.TasteException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author hichem Bedjaoui
 */
public class ExecController1 implements Initializable {

    @FXML
    private Label eval;
    @FXML
    private Label rmseval;
    @FXML
    private Label time;
    
    @FXML
    private TableView<dataInfo> tableAffichage;
    @FXML
    private TableColumn<dataInfo, String> userAffichage;
  
    @FXML
    private TableColumn<dataInfo, String> sujetAffichage;
    @FXML
    private TableColumn<dataInfo, String> ratingAffichage;
     
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userAffichage.setCellValueFactory(new PropertyValueFactory<dataInfo,String>("user"));
        sujetAffichage.setCellValueFactory(new PropertyValueFactory<dataInfo,String>("sujet"));
        ratingAffichage.setCellValueFactory(new PropertyValueFactory<dataInfo,String>("rating"));
        ItemBased liste=new ItemBased();
        try {
            liste.test();
            ObservableList<dataInfo> data=(liste.getItem());
            tableAffichage.setItems(data);
            eval.setText(liste.getEval()+"");
            rmseval.setText(liste.getrmsEval()+"");
           time.setText(liste.getTime()+"MS");
        } catch (IOException ex) {
            Logger.getLogger(ExecController1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TasteException ex) {
            Logger.getLogger(ExecController1.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(data.get(0).getUser());
    }    
  
    
}
