/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

public class CSVtoJSON {

    public static void main(String[] args) {

        BufferedReader br;
        String linea;
        JsonArrayBuilder jab = Json.createArrayBuilder();//1.
        try {
            br = new BufferedReader(new FileReader("mendiak2.csv"));
            while ((linea = br.readLine()) != null) {               
                String[] mendiak  = linea.split(";");   
               
                /*JsonObject mendiaModel = Json.createObjectBuilder()
                        .add("a", 1)
                        .add("b",2)
                        .build();
                */
                JsonObject model = Json.createObjectBuilder()
                    .add("Mendia", mendiak[0])
                    //.add("Mendia", mendiaModel)
                    .add("Altura", mendiak[1])
                    .add("Probintzia",mendiak[2])
                    /*.add("Fer", Json.createObjectBuilder()
                        .add("a", 1)
                        .add("b",2)
                        .build())*/
                    .build();
                    jab.add(model);  
            }
            JsonArray model2 = jab.build();
            FileWriter fw = new FileWriter("mendiak2.json");
            JsonWriter jsonwriter = Json.createWriter(fw);
            jsonwriter.writeArray(model2);
            jsonwriter.close();
            fw.close();
          
        } catch (EnumConstantNotPresentException | IOException ex) {
            System.err.println("Errore bat gertatu da");
        }
    }

}
