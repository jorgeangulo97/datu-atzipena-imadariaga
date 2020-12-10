/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutoriala;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

/**
 *
 * @author angulo.jorge
 */
public class ZerotikSortuEjemplo {
    public static void main(String[] args) throws IOException {
        try{         
            // 19.3.3
            JsonObject model = Json.createObjectBuilder()
               .add("firstName", "Duke")
               .add("lastName", "Java")
               .add("age", 18)
               .add("streetAddress", "100 Internet Dr")
               .add("city", "JavaTown")
               .add("state", "JA")
               .add("postalCode", "12345")
               .add("phoneNumbers", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                            .add("type", "mobile")
                            .add("number", "111-111-1111"))
                        .add(Json.createObjectBuilder()
                            .add("type", "home")
                            .add("number", "222-222-2222")))
               .build();
            System.out.println(model);
            
            // 19.3.4
            FileOutputStream out = new FileOutputStream("datuaberriak.json");
            JsonWriter jsonWriter = Json.createWriter(out);
            jsonWriter.writeObject(model);
            jsonWriter.close();
            out.close();
        }catch(FileNotFoundException ex){
            System.out.println("Fitxategia ez da aurkitu");
        }catch(IOException ex){
            System.out.println("Erroreaaa");
        }
    }
}
