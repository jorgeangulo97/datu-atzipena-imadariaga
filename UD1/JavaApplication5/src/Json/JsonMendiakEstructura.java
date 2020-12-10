/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

/**
 *
 * @author Jorge
 */
public class JsonMendiakEstructura {
        public static void main(String[] args) {
        try{
            JsonParser parser = Json.createParser(new FileReader("Mendiak.json"));
            while(parser.hasNext()) {
                Event event = parser.next();
                if(event == JsonParser.Event.KEY_NAME) {
                    String key = parser.getString();
                        switch (key) {
                            case "Mendia":
                                parser.next();
                                System.out.println("Mendia: " + parser.getString());
                                break;
                            case "Altuera":
                                parser.next();
                                System.out.println("Altuera: " + parser.getString());
                                break;
                            case "Probintzia":
                                parser.next();
                                System.out.println("Probintzia: " + parser.getString());
                                break;
                        }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Fitxaregia ez da aurkitu.");
        }
            
    }
    
}

