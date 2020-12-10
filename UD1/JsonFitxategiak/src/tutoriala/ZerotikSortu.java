/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutoriala;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

/**
 *
 * @author maneru.mikel
 */
public class ZerotikSortu {

    public static void main(String[] args) {
        try {
            JsonObject model = Json.createObjectBuilder()
                    .add("Mendiak", Json.createArrayBuilder()
                            .add(Json.createObjectBuilder()
                                    .add("Mendiak", "Oiz")
                                    .add("Altura", 987)
                                    .add("Probintzia", "Bizkaia"))
                            .add(Json.createObjectBuilder()
                                    .add("Mendiak", "Gorbea")
                                    .add("Altura", 787)
                                    .add("Probintzia", "Gipuzkoa")))
                    .build();

            System.out.println(model);

            //19.3.4 Fitxategian gorde
            try {
                FileOutputStream out = new FileOutputStream("datuberriak.json");
                JsonWriter jsonwriter = Json.createWriter(out);
                jsonwriter.writeObject(model);
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
