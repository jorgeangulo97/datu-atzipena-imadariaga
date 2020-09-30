
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author angulo.jorge
 */
public class CopyCharactersMaiuskulaz {
        public static void main(String[] args) throws FileNotFoundException, IOException {
                
        FileInputStream in = null;
        FileOutputStream out = null;        

        try {
            in = new FileInputStream("xanadu.txt");
            out = new FileOutputStream("minusmayusoutputaldatuta.txt");
            int c;

            while ((c = in.read()) != -1) {
                    out.write(Character.toUpperCase(c));                             
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
                
            }
        }
    
    }
}
