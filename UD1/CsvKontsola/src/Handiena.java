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

public class Handiena {
    public static void main(String[] args) throws IOException {

        int altuera = 0;
        String izena = "";
        String lerroa;
        BufferedReader inputStream = null;
        PrintWriter outputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader("Mendiak.csv"));
            
            int linea = 1;
            while ((lerroa = inputStream.readLine()) != null) {
                if(linea != 1){
                    String[] lerroaZatituta = lerroa.split(";");
                    if(Integer.parseInt(lerroaZatituta[1]) > altuera){
                        altuera = Integer.parseInt(lerroaZatituta[1]);
                        izena = lerroaZatituta[0];
                    }
                }
                linea ++;
            }
            System.out.println("Mendi altuena: " + izena + " da. " + altuera + " metro ditu");
            
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