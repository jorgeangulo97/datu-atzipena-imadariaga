/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package global;

/**
 *
 * @author maneru.mikel
 */
public class Global {
    public static String ZERBITZARIA="localhost";
    public static String DATUBASEA="ikasleenDBa";
    
    public static String erabiltzailea ="root";
    public static String pasahitza="root";

    public static String getPasahitza() {
        return pasahitza;
    }

    public  void setPasahitza(String pasahitza) {
        this.pasahitza = pasahitza;
    }

    public static String getErabiltzailea() {
        return erabiltzailea;
    }

    public static void setErabiltzailea(String erabiltzailea) {
        Global.erabiltzailea = erabiltzailea;
    }
    
    
}
