/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author angulo.jorge
 */
public class KarakFitxategia {
    
    public static ObservableList<Ikaslea> datuakMemorianKargatu(String fitxategia) {
        ObservableList<Ikaslea> data = FXCollections.observableArrayList();
        BufferedReader inputStream;
        try{
            inputStream = new BufferedReader(new FileReader(fitxategia));
            
            String lerro;
            while((lerro = inputStream.readLine()) != null){
                Scanner sc = new Scanner(lerro).useDelimiter(",");
                Ikaslea ikasle = new Ikaslea(sc.nextInt(), sc.next(), sc.next());
                data.add(ikasle);
            }
            
        }catch (IOException e){
            return FXCollections.observableArrayList();
        }
        return data;
    }
    
    public static boolean datuakFitxategianGorde(ObservableList<Ikaslea> lista, String fitxategia) {
        try {
            PrintWriter outputStream = new PrintWriter(new FileWriter(fitxategia));
            for(int i = 0; i < lista.size(); i++){
                outputStream.println(lista.get(i).toString());
            }
            outputStream.close();
        } catch(IOException e) {
            return false;
        }
        return true;
    }
}
