/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author angulo.jorge
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Probintziaka {
    public static void main(String[] args) throws IOException {

        String probintzia;
        String lerroa;
        BufferedReader inputStream = null;
        PrintWriter outputStream = null;
        
        //        Key,  Value
        HashMap<String, ArrayList<Mendia>> probintzia_data = new HashMap<>();
        
        try {
            inputStream = new BufferedReader(new FileReader("Mendiak.csv"));            
            
            int linea = 1;
            while ((lerroa = inputStream.readLine()) != null) {
                if(linea != 1){
                    String[] lerroaZatituta = lerroa.split(";");
                        probintzia = lerroaZatituta[2];
                        
                        if (!probintzia_data.containsKey(probintzia)) {
                            probintzia_data.put(probintzia, new ArrayList<>());
                        }
                        
                        //ArrayList<String> rows_probintzia = probintzia_data.get(probintzia);
                        //rows_probintzia.add(lerroa);
                        probintzia_data.get(probintzia).add(new Mendia(lerroa));
           
                }
                linea ++;
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("Zelan ordenatu nahi duzu, Izenetik edo Altuera(izena/altuera)?");
            String zelan = sc.next().toLowerCase();
            
            // guardo la provincia actual en row_... <-  para cada clave de provincias
            // for row_probintzia in probintiza_data.keys():
            for (String probintzia_actual : probintzia_data.keySet()) {
                outputStream = new PrintWriter(new FileWriter(probintzia_actual.toLowerCase() + ".csv"));
                ArrayList<Mendia> rows_probintzia = probintzia_data.get(probintzia_actual);
                
                if(zelan.equals("izena")){
                    Mendia.sortByIzena();
                    Collections.sort(rows_probintzia);
                } else if (zelan.equals("altuera")) {
                    Mendia.sortByAltuera();
                    Collections.sort(rows_probintzia);
                }
                
                for(int i = 0; i < rows_probintzia.size(); i++){
                    outputStream.println(rows_probintzia.get(i).toString());
                }
                outputStream.close();
            }
            
        }catch(FileNotFoundException ex){
            System.out.println("csv fitxategia ez dago bere tokian.");
        }catch(IOException ex){
            System.out.println("Saarrera/irteera erroreeren bat gertatu da.");
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
