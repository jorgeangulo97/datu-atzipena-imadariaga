/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutoriala;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonWriter;

/**
 *
 * @author maneru.mikel
 */
public class MendiakGehituJson {

    public static void main(String[] args) throws FileNotFoundException {
        String mendia = "AÑADEMEESTE";
        int altuera = 3467;
        String probintzia = "Bizkaia";

        String filename = "datuberriak.json";
        try {
            JsonReader reader = Json.createReader(new FileReader(filename));
            JsonStructure hasierakoEreduaStruct = reader.read();
            System.out.println(filename + " ondo kargatu dira!");
            System.out.println(hasierakoEreduaStruct);
            
            JsonObject model = Json.createObjectBuilder()
                    .add("Mendia", mendia)
                    .add("Altura", altuera)
                    .add("Probintzia", probintzia)
                    .build();

           //1.-CreateBuilder
           //2.-Add old elements
           //3.-Add new elements
           //4.-Build
            JsonArrayBuilder jab = Json.createArrayBuilder();//1.
                       
           for(JsonValue jvdatos : hasierakoEreduaStruct.asJsonArray()){//2.-
               jab.add(jvdatos);
           }
                        
            jab.add(model);//3.-
           
            System.out.println(jab.toString());
                                     
            JsonArray model2 = jab.build();//4.          
            
            System.out.println(model2);
            FileWriter fw = new FileWriter(filename);
            JsonWriter jsonwriter = Json.createWriter(fw);
            jsonwriter.writeArray(model2);
            jsonwriter.close();
            fw.close();
            
            
        } catch (Exception e) {

        }

    }
}
