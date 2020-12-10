package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.KarakFitxategia;

/**
 *
 * @author root
 */
public class MainaDaukanKlasea extends Application {

    @Override
    public void start(Stage stage) throws Exception { 
        // Buscamos el fichero
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage);
        String path = selectedFile.getAbsolutePath();
        
        // Preparamos nuestra escena
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/IkasleenTaula.fxml"));
        Scene scene = new Scene(loader.load());
        
        IkasleenTaulaController ikasleen = loader.getController();
        
        ikasleen.setFitxategia(KarakFitxategia.datuakMemorianKargatu(path));
        ikasleen.setStage(stage);
        
        stage.setScene(scene);
        stage.show();
        /*Parent root = FXMLLoader.load(getClass().getResource("/view/IkasleenTaula.fxml"));//ADI URLarekin: uneko fitxategitik nabigatu behar dugu, eta karpeta erroa src da

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage);
        
        String path = selectedFile.getAbsolutePath();
        System.out.println("Path: " + path);
        
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
