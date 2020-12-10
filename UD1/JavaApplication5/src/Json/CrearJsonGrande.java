/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonWriter;

/**
 *
 * @author Jorge
 */
public class CrearJsonGrande {
    public static void main(String[] args) {
        try {
            JsonArray value = Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                            .add("probintziaIzena", "Araba")
                            .add("Mendiak", Json.createArrayBuilder()
                                .add(Json.createObjectBuilder()
                                    .add("Izena", "Albertia")
                                    .add("Altuera", 868))
                                .add(Json.createObjectBuilder()
                                    .add("Izena", "Aratz")
                                    .add("Altuera", 1443))))
                                
                    .add(Json.createObjectBuilder()
                            .add("probintziaIzena", "Bizkaia")
                            .add("Mendiak", Json.createArrayBuilder()))
                    .build();

            System.out.println(value);

            //19.3.4 Fitxategian gorde
            try {
                FileOutputStream out = new FileOutputStream("jsonGrande.json");
                
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

