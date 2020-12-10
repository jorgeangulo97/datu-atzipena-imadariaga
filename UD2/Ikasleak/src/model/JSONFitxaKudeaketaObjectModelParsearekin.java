/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.stream.JsonParser;
/**
 *
 * @author maneru.mikel
 */
public class JSONFitxaKudeaketaObjectModelParsearekin {
     public static ObservableList<Ikaslea> datuakMemorianKargatu(String fitxategia) throws FileNotFoundException{
        ObservableList<Ikaslea> data;
        
        JsonReader reader = Json.createReader(new FileReader(fitxategia));
        JsonStructure hasierakoEreduaStruct = reader.read();
        
        
        data = jsonOMLista(hasierakoEreduaStruct.asJsonArray());
            
        return data;
     }
      public static boolean datuakFitxategianGorde(ObservableList<Ikaslea> lista ,String fitxategia) throws FileNotFoundException{
         JsonArray model = listaJsonOM(lista);
         
         FileOutputStream out = new FileOutputStream(fitxategia);
         JsonWriter jsonWriter = Json.createWriter(out);
         jsonWriter.writeArray(model);
         jsonWriter.close();
         return true;
         
     }
     public static ObservableList<Ikaslea> jsonOMLista(JsonArray a) throws FileNotFoundException{
         ObservableList<Ikaslea> data = FXCollections.observableArrayList();
       
          
        JsonArrayBuilder jab = Json.createArrayBuilder();//1.
                       
        for (JsonValue jvdatos : a){//2.-
            jab.add(jvdatos);
            
        }
                         
           
       JsonArray b = jab.build();//4.  
        
        

        return data;
     }
     
     public static JsonArray listaJsonOM(ObservableList<Ikaslea> lista){
         JsonArrayBuilder arraBuilder = Json.createArrayBuilder();
         JsonObjectBuilder objBuilder = Json.createObjectBuilder();
         
         for(Ikaslea ikasle :lista){
             objBuilder.add("zenbakia", ikasle.getZenbakia());
             objBuilder.add("izena", ikasle.getIzena());
             objBuilder.add("abizena1", ikasle.getAbizena1());
             JsonObject jsonObjectIkasle=objBuilder.build();
             arraBuilder.add(jsonObjectIkasle);
         }
         
         JsonArray jsonArraya = arraBuilder.build();
         return jsonArraya;
     }
}
