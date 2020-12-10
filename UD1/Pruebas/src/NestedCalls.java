/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author angulo.jorge
 */
public class NestedCalls {
    
    public static void main(String[] args) {
        KlaseBat obj1 = new KlaseBat();
        obj1.gehitu1(4);
        System.out.println(obj1.emaitza);
        
        obj1.gehitu2(5).gehitu2(8).gehitu2(3);
        System.out.println(obj1.emaitza);
    }
}
