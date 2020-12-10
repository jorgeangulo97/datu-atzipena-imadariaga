package Csv;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileWriter;
import java.util.ArrayList;

public class ZerotikSortu {

    public static void main(String[] args) {

        ArrayList<Mendia> mendiak = new ArrayList<>();

        Mendia m1 = new Mendia("Mirutegi", 1166, "Araba");
        Mendia m2 = new Mendia("Mugarra", 964, "Bizkaia");
        Mendia m3 = new Mendia("Oiz", 345, "Araba");
        Mendia m4 = new Mendia("Soila", 257, "Gipuzkoa");
        Mendia m5 = new Mendia("Toloño", 1577, "Araba");
        Mendia m6 = new Mendia("Txamantxoia", 1234, "Bizkaia");

        mendiak.add(m1);
        mendiak.add(m2);
        mendiak.add(m3);
        mendiak.add(m4);
        mendiak.add(m5);
        mendiak.add(m6);

        String izena = "";
        int altuera = 0;
        String probintzia = "";
        String linea = "";

        try {
            FileWriter writer = new FileWriter("mendiak2.csv");

            for (int i = 0; i < mendiak.size(); i++) {
                izena = mendiak.get(i).getMendia();
                altuera = mendiak.get(i).getAltuera();
                probintzia = mendiak.get(i).getProbintzia();
                linea = izena + ";" + altuera + ";" + probintzia + "\n";
                writer.write(linea);
                System.out.println("Mendi berria sartu da listan!");

            }
            writer.close();
            System.out.println("Mendi guztiak satu ");
        } catch (Exception e) {

        }

    }

}
