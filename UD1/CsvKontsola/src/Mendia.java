/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jorge
 */

public class Mendia implements Comparable<Mendia>{
        
        private String mendia;
        private int altuera;
        private String probintzia;
        private static boolean sortbyaltuera = false;

    public Mendia(String mendia, int altuera, String probintzia) {
        this.mendia = mendia;
        this.altuera = altuera;
        this.probintzia = probintzia;
    }
    
    public Mendia(String line) {
        String[] values = line.split(";");
        this.mendia = values[0];
        this.altuera = Integer.parseInt(values[1]);
        this.probintzia = values[2];
    }

    public String getMendia() {
        return mendia;
    }

    public void setMendia(String mendia) {
        this.mendia = mendia;
    }

    public int getAltuera() {
        return altuera;
    }

    public void setAltuera(int altuera) {
        this.altuera = altuera;
    }

    public String getProbintzia() {
        return probintzia;
    }

    public void setProbintzia(String probintzia) {
        this.probintzia = probintzia;
    }   
    
    public static void sortByAltuera(){
        Mendia.sortbyaltuera = true;
    }
    
    public static void sortByIzena(){
        Mendia.sortbyaltuera = false;
    }

    @Override
    public int compareTo(Mendia o) {
        if(Mendia.sortbyaltuera){
            return Integer.valueOf(this.altuera).compareTo(o.getAltuera());
        }else{
            return this.mendia.compareTo(o.getMendia());
        }
    }

    @Override
    public String toString() {
        return mendia + "," + altuera + "," + probintzia;
    }
    
}
