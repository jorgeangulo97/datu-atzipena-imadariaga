/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author angulo.jorge
 */
public class KlaseBat {
    int emaitza;
    
    public KlaseBat(){
        emaitza = 0;
    }
    
    public void gehitu1(int balioa){
        emaitza = emaitza + balioa;
    }
    
    public KlaseBat gehitu2(int balioa){
        emaitza = emaitza + balioa;
        return this;
    }
}
