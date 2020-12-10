/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

/**
 *
 * @author Jorge
 */
public class JsonMendiakBilatu {
    public static void main(String[] args) {
        try{
                JsonParser parser = Json.createParser(new FileReader("Mendiak.json"));
                while(parser.hasNext()) {
                    Event event = parser.next();
                    if(event == JsonParser.Event.KEY_NAME){
                        String key = parser.getString();
                        if (key.equals("Mendia")){
                            parser.next();
                            System.out.println(parser.getString());
                        }
                    }
                }
        } catch (FileNotFoundException ex) {
            System.out.println("Fitxaregia ez da aurkitu.");
        }
            
    }
    
}
