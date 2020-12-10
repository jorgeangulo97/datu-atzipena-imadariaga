/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import global.Global;
import java.sql.*;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author maneru.mikel
 */
public class MariaDB {

    public static Connection konektatu() {
        Connection konekzioa = null;
        String url = "";
        try {
            url = "jdbc:mysql://" + Global.ZERBITZARIA + "/" + Global.DATUBASEA;
            konekzioa = DriverManager.getConnection(url, Global.erabiltzailea, Global.pasahitza);
            System.out.println("KONEKTATU ZARA");
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " - " + e.getMessage());
        }

        return konekzioa;
    }

    public static ObservableList datuakMemorianKargatu() {
        ObservableList<Ikaslea> data = FXCollections.observableArrayList();
        Statement a = null;
        ResultSet rs = null;
        Connection konekzioa = konektatu();
        try {
            a = konekzioa.createStatement();
            rs = a.executeQuery("SELECT * FROM ikaslea");
            while (rs.next()) {
                int zenbakia = rs.getInt("zenbakia");
                String izena = rs.getString("izena");
                String abizena = rs.getString("abizena1");
                Ikaslea ikasle = new Ikaslea(zenbakia, izena, abizena);
                data.add(ikasle);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    public static boolean gehitu(Ikaslea ika) {
        String sql = "INSERT INTO ikaslea (zenbakia,izena,abizena1) VALUES(?,?,?)";

        try ( Connection conn = konektatu();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ika.getZenbakia());
            pstmt.setString(2, ika.getIzena());
            pstmt.setString(3, ika.getAbizena1());
            pstmt.executeUpdate();
            eventoRegister(pstmt.toString());
            System.out.println("Erregistroan ondo sartu dira");
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " ==>" + e.getMessage());
            System.out.println("Ezin dira insertatu");
            return false;
        }

        return true;
    }

    public static void eventoRegister(String deskripzioa) {
        String sql = "INSERT INTO log (user,Description) VALUES(?,?)";

        try ( Connection conn = konektatu();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, Global.getErabiltzailea());
            pstmt.setString(2, deskripzioa);
            pstmt.executeUpdate();

            System.out.println("Erregistroan ondo sartu dira");
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " ==>" + e.getMessage());
            System.out.println("Ezin dira insertatu");

        }
    }

    public static boolean ezabatu(Ikaslea ika) {
        String sql = "DELETE FROM ikaslea WHERE zenbakia =?";

        try ( Connection conn = konektatu();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ika.getZenbakia());

            pstmt.executeUpdate();
            eventoRegister(pstmt.toString());
            System.out.println(ika.getZenbakia() + " ikaslea datu-basetik ezabatu da.");
            System.out.println("Erregistroan ondo sartu dira");
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " ==>" + e.getMessage());
            System.out.println("Ezin dira ezabatu");
            return false;
        }

        return true;
    }

    public static int update(int zein, String zutabea, String balioberria) {
        String sqlUpdate = "UPDATE ikaslea SET " + zutabea + " = ? where zenbakia = ?";
        int aldatuak = 0;
        try ( Connection conn = konektatu();  PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {

            stmt.setString(1, balioberria);
            stmt.setInt(2, zein);
            stmt.executeUpdate();
            eventoRegister(stmt.toString());
            System.out.println(zein + "zenbakiaren " + zutabea + " aldatu da datu basean");
            return aldatuak;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }
    

}
