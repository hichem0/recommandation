/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommandation;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.apache.mahout.cf.taste.common.TasteException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.apache.mahout.cf.taste.impl.similarity.CityBlockSimilarity;

/**
 * FXML Controller class
 *
 * @author hichem Bedjaoui
 */

public class FXMLDocumentController implements Initializable {

    public static String pathh;
    @FXML
    private Button btnChemin;
    @FXML
    private Label chemin;
    @FXML
    private Label type;
    @FXML
    private Button btnExecuter;
    @FXML
    private Button btnExecuter1;
    @FXML
    private Button btnExecuter11;
    @FXML
    private TableView<dataInfo> tableAffichage;
    @FXML
    private TableColumn<dataInfo, String> userAffichage;
    @FXML
     public ComboBox<String> ComboBoxAlgo;
    public  static ComboBox<String> ComboboxAlgoFx;
    @FXML
    public ComboBox<String> ComboBoxAlgo1;
    public  static ComboBox<String> ComboboxAlgoFx1;
    @FXML
    public ComboBox<String> ComboBoxAlgo11;
    public  static ComboBox<String> ComboboxAlgoFx11;
    @FXML
    public ComboBox<String> ComboBox111;
    public  static ComboBox<String> ComboboxAlgoFx111;
    @FXML
    public ComboBox<String> ComboBox1111;
    public  static ComboBox<String> ComboboxAlgoFx1111;
    @FXML
    public ComboBox<String> ComboBox2;
    public  static ComboBox<String> ComboboxAlgoFx2;
    @FXML
    private TableColumn<dataInfo, String> sujetAffichage;
    @FXML
    private TableColumn<dataInfo, String> ratingAffichage;
    @FXML
    public   ComboBox<String> ComboBoxAlg;

    public  static ComboBox<String> ComboBoxAlgFx;

    @FXML
    public   ComboBox<String> ComboBoxAlg1;
    public  static ComboBox<String> ComboBoxAlgFx1;
    @FXML
    public   ComboBox<String> ComboBoxAlg11;
    public  static ComboBox<String> ComboBoxAlgFx11;
    @FXML
    public  ComboBox<String> comboboxneighber;
    public  static ComboBox<String> comboboxneighberFx;



     @FXML
   public Slider slider1;
    public static Slider sliderFx1;
    @FXML
   public Slider slider11;
    public static Slider sliderFx11;
    @FXML
   public Slider slider;
    public static Slider sliderFx;
    @FXML
    public TextField nbrRecom ;
    public static TextField nbrRecomFx ;
    @FXML
    public TextField nbrRecom1 ;
    public static TextField nbrRecomFx1 ;
    @FXML
    public TextField nbrRecom11 ;
    public static TextField nbrRecomFx11 ;
    @FXML
    public TextField nbrRecom111 ;
    public static TextField nbrRecomFx111 ;

    @FXML
    public  ComboBox<String> nbrtest;
    public  static ComboBox<String> nbrtest0;
    @FXML
    public  ComboBox<String> nbrtest1;
    public  static ComboBox<String> nbrtest10;
    @FXML
    public  ComboBox<String> nbrtest11;
    public  static ComboBox<String> nbrtest110;

  
    

     public ObservableList<dataInfo> data = FXCollections.observableArrayList();
    
    @FXML

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ComboBoxAlgo.getItems().addAll("PearsonCorrelationSimilarity","EuclideanDistanceSimilarity","TanimotoCoefficientSimilarity","LogLikelihoodSimilarity","SpearmanCorrelationSimilarity","CityBlockSimilarity");
        ComboBoxAlgo11.getItems().addAll("ALSWRFactorizer","ParallelSGDFactorizer","SVDPlusPlusFactorizer");
        ComboBoxAlgo1.getItems().addAll("PearsonCorrelation","EuclideanDistanceSimilarity","TanimotoCoefficientSimilarity","LogLikelihoodSimilarity","CityBlockSimilarity");
        comboboxneighber.getItems().addAll("NearestNUserNeighborhood","ThresholdUserNeighborhood");

        ComboBoxAlg.getItems().addAll("Tout","5","10" , "20","26","100","153","350","276788","277965");
        ComboBoxAlg1.getItems().addAll("Tout","5","10" , "20","26","100","153","350");
        ComboBoxAlg11.getItems().addAll("Tout","5","10" , "20","26","100","153","350");


        ComboBox111.getItems().addAll("0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9","1.0");
        ComboBox1111.getItems().addAll("0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9","1.0");
        ComboBox2.getItems().addAll("0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9","1.0");
        nbrtest.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
        nbrtest1.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
        nbrtest11.getItems().addAll("1","2","3","4","5","6","7","8","9","10");

        slider.setMin(0);
        
        slider.setMax(1);
        slider.setValue(0.5);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(0.1);
        slider.setMinorTickCount(1);
        slider.setBlockIncrement(0.1);
        slider.setMin(0);

        slider1.setMax(1);
        slider1.setValue(0.5);
        slider1.setShowTickLabels(true);
        slider1.setShowTickMarks(true);
        slider1.setMajorTickUnit(0.1);
        slider1.setMinorTickCount(1);
        slider1.setBlockIncrement(0.1);
        slider1.setMin(0);

        slider11.setMax(1);
        slider11.setValue(0.5);
        slider11.setShowTickLabels(true);
        slider11.setShowTickMarks(true);
        slider11.setMajorTickUnit(0.1);
        slider11.setMinorTickCount(1);
        slider11.setBlockIncrement(0.1);

    }
    

    @FXML
    private void importData(ActionEvent event) {
        Window primaryStage = null;
        FileChooser Chooser = new FileChooser();
        File selectedDirectory = Chooser.showOpenDialog(primaryStage);

        if (selectedDirectory == null) {
            chemin.setText("Aucun fichier n'a été selectionner ");
        } else {
            chemin.setText(selectedDirectory.getAbsolutePath());
            //traitement 
            pathh = selectedDirectory.getAbsolutePath();
        }
    }
    @FXML
    private void goexec() throws IOException{
        ComboBoxAlgFx=ComboBoxAlg;
        comboboxneighberFx=comboboxneighber;
        nbrRecomFx=nbrRecom;
        ComboboxAlgoFx=ComboBoxAlgo;
        sliderFx=slider;
        ComboboxAlgoFx111=ComboBox111;
        nbrRecomFx111=nbrRecom111;
        nbrtest0=nbrtest;
        Recommandation.showgoexec("");


    }
    @FXML
    private void change(){
        if(comboboxneighber.getValue()=="NearestNUserNeighborhood")
            type.setText("Le Nombre De Voisin ");
        else
            type.setText("le seuil");




    }
    @FXML
    private void goexec1() throws IOException{

        ComboBoxAlgFx1=ComboBoxAlg1;
        nbrRecomFx1=nbrRecom1;
        ComboboxAlgoFx1=ComboBoxAlgo1;
        sliderFx1=slider1;

        ComboboxAlgoFx1111=ComboBox1111;
        nbrtest10=nbrtest1;


        Recommandation.showgoexec("1");


    }
    @FXML
    private void goexec11() throws IOException{


        sliderFx11=slider11;
        ComboBoxAlgFx11=ComboBoxAlg11;
        nbrRecomFx11=nbrRecom11;
        ComboboxAlgoFx11=ComboBoxAlgo11;
        ComboboxAlgoFx2=ComboBox2;

        nbrtest110=nbrtest11;

        Recommandation.showgoexec("2");


    }
    @FXML
    private void executer(ActionEvent event) throws IOException, TasteException {
// relier les columns
        userAffichage.setCellValueFactory(new PropertyValueFactory<dataInfo,String>("user"));
        sujetAffichage.setCellValueFactory(new PropertyValueFactory<dataInfo,String>("sujet"));
        ratingAffichage.setCellValueFactory(new PropertyValueFactory<dataInfo,String>("rating"));
        String csvFile = pathh;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] ligne = line.split(cvsSplitBy);
                // ComboBoxAlg.getItems().addAll(ligne[0]);
                //le nom du film
             if(!ligne[1].equals("51"))
                data.add(new dataInfo(ligne[0],ligne[1],ligne[2]));
            }
            
            //Callback<TableColumn<dataInfo, String>, TableCell<dataInfo, String>> value = tableAffichage.g  ;
            //userAffichage.setCellFactory(value);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        br.close();
         csvFile = "data/datamovies.csv";
         br = null;
         line = "";
         cvsSplitBy = ",";

        try {

            for (int i = 0; i <10 ; i++) {
                br = new BufferedReader(new FileReader(csvFile));

                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    String[] ligne = line.split(cvsSplitBy);
                    // ComboBoxAlg.getItems().addAll(ligne[0]);
                    //le nom du film
                    if(data.get(i).getSujet().equals(ligne[0])){
                        String info=data.get(i).getSujet()+":"+ligne[1];
                        data.get(i).setSujet(info)   ;
                        break;
                    }

                }

            }

            //Callback<TableColumn<dataInfo, String>, TableCell<dataInfo, String>> value = tableAffichage.g  ;
            //userAffichage.setCellFactory(value);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        tableAffichage.setItems(data);
    }

}
