/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonReader;
import javax.json.JsonStructure;

public class JSONtoCSV {
        
    public static void main(String[] args) throws FileNotFoundException {

        JsonReader reader = Json.createReader(new FileReader("Mendiak.json"));
        JsonStructure jsonst = reader.read();
        
        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (int i = 0; i < jsonst.asJsonArray().size(); i++) {
            jab.add(jsonst.asJsonArray().get(i));
        }        

        JsonArray ar = jab.build();
        
        System.out.println(ar);
        
        String izena;
        int altuera=0;
        String probintzia;
        String linea;
        try {
            FileWriter writer = new FileWriter("mendiak2.csv");

            for (int i = 0; i < ar.size(); i++) {
                
                izena = ar.get(i).asJsonObject().getString("Mendia");
              
                altuera = ar.get(i).asJsonObject().getInt("Altuera");
              
                probintzia = ar.get(i).asJsonObject().getString("Probintzia");
                
                linea = izena + ";" + altuera + ";" + probintzia + "\n";
                writer.write(linea);
                System.out.println("Mendi berria sartu da listan!");

            }
            writer.close();
            System.out.println("Mendi guztiak satu ");
        } catch (Exception e) {

        }
        
    }

}
