
import java.awt.BorderLayout;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author angulo.jorge
 */
public class SQLInjectionAdibidea {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Nor nahi duzu ezabatu");
        String izena = sc.next();
        
        String sql = "DELETE FROM taula WHERE eremu1='" + izena + "'";
        
        /**
         * Zer gertatuko litzakete erabiltzaileak horrela erantzungo balio galderari?
         * Jorgito' OR true OR ' // En consola, a la hora de pedir el nombre, si escribimos esto borra todo.
         * Erregistro danak ezabatuko lirateke
         * Beraz, ahal den guztietan, Statement barik Prepared Statement erabili
         */
        
        
    }
}
