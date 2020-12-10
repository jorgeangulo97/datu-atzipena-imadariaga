/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;

/**
 *
 * @author Jorge
 */
public class JsonMendiakProbintziakaBilatu {
        public static void main(String[] args) throws IOException {
        JsonParser parser;
        String azkenIzena = "";
        int azkenAltuera = 0;
        String azkenProbintzia = "";
        try {
            JsonReader reader = Json.createReader(new FileReader("MendiakProbintziaka.json"));
            JsonStructure jsonst = reader.read();
            JsonArray jsonArray = jsonst.asJsonArray();
            
            // Array builder donde montamos la estructura de salida
            JsonArrayBuilder jab = Json.createArrayBuilder();
            
            // SOLUCION CON FOR NORMAL
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject item = jsonArray.get(i).asJsonObject();
                JsonArray montes = item.get("mendiak").asJsonArray();
                
                for (int j = 0; j < montes.size(); j++) {
                    JsonObject monte = montes.get(j).asJsonObject();
                    azkenAltuera = monte.getInt("altuera");
                    
                    if (azkenAltuera > 1000) {
                        azkenIzena = monte.getString("izena");
                        azkenProbintzia = item.getString("probintziaIzena");
                        System.out.println("Mendia:" + azkenIzena + " / " + "Altuera:" + azkenAltuera + " / " + "Probintzia:" + azkenProbintzia);

                        //Creamos cada uno de los objetos que añadimos a la lista de salida
                        JsonObject model = Json.createObjectBuilder()
                            .add("Mendia", azkenIzena)
                            .add("Altura", azkenAltuera)
                            .add("Probintzia", azkenProbintzia)
                            .build();
                        jab.add(model);  
                    }
                }
            }
            
            JsonArray outputArray = jab.build();
            FileWriter writer = new FileWriter("jsonMendiakProbintzia1000.json");
            JsonWriter jsonwriter = Json.createWriter(writer);
            jsonwriter.writeArray(outputArray);
            jsonwriter.close();
            writer.close();
            
        } catch (IOException ex) {
            System.out.println("errorea");
        }
    }
}

