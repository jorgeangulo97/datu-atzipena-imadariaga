/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutoriala;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

/**
 *
 * @author maneru.mikel
 */
public class ZerotikSortuV2 {

    public static void main(String[] args) {
        try {
            JsonArray value = Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                            .add("Mendia", "Oiz")
                            .add("Altuera", 915)
                            .add("Probintzia", "Bizkaia"))
                    .add(Json.createObjectBuilder()
                            .add("Mendia", "Gorbea")
                            .add("Altuera", 975)
                            .add("Probintzia", "Gipuzkoa"))
                    .build();

            System.out.println(value);

            //19.3.4 Fitxategian gorde
            try {
                FileOutputStream out = new FileOutputStream("datuberriak.json");
                
                JsonWriter jsonwriter = Json.createWriter(out);
                jsonwriter.writeArray(value);
                jsonwriter.close();
                out.close();
            } catch (FileNotFoundException e) {
                System.out.println("Fitxategia ez da aurkitu!");
            }

        } catch (Exception e) {
            System.out.println("Salbuespen bat gertatu da !");
        }
    }
}
