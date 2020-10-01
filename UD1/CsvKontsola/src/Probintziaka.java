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

public class Probintziaka {
    public static void main(String[] args) throws IOException {

        String probintzia;
        String lerroa;
        BufferedReader inputStream = null;
        PrintWriter outputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader("Mendiak.csv"));
            outputStream = new PrintWriter(new FileWriter("bizkaia.csv"));
            
            int linea = 1;
            while ((lerroa = inputStream.readLine()) != null) {
                if(linea != 1){
                    String[] lerroaZatituta = lerroa.split(";");
                        probintzia = lerroaZatituta[2];
                        if(probintzia.equals("Bizkaia")){
                            outputStream.println(lerroa);
                        }                    
                }
                linea ++;
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