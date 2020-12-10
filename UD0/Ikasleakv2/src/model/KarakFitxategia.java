/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author angulo.jorge
 */
public class KarakFitxategia {
    
    public static ObservableList<Ikaslea> datuakMemorianKargatu(String fitxategia) throws IOException{
        ObservableList<Ikaslea> data = FXCollections.observableArrayList();
        BufferedReader inputStream = null;
        
        try{
            inputStream = new BufferedReader(new FileReader(fitxategia));
            
            String lerro;
            while((lerro = inputStream.readLine()) != null){
                Scanner sc = new Scanner(lerro).useDelimiter(",");
                Ikaslea ikasle = new Ikaslea(sc.nextInt(), sc.next(), sc.next());
                data.add(ikasle);
            }
            
        }catch (Exception e){
            
        }
        return null;
    }
}
