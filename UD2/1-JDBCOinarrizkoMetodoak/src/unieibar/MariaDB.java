/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unieibar;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import static unieibar.SQLite.connect;

/**
 *
 * @author maneru.mikel
 */
public class MariaDB {

    public static Connection connect(String db) {
        String server = "localhost";
        String url = "jdbc:mysql://" + server + "/" + db;
        String user = "root";
        String pass = "root";
        Connection conn = null;
        System.out.println("Konektatu zara!");
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " - " + e.getMessage());
        }
        return conn;
    }

    public static void createNewDatabase(String db) {

        try ( Connection conn = connect("");  Statement stmt = conn.createStatement()) {

            DatabaseMetaData meta = conn.getMetaData();
            System.out.println("The driver name is " + meta.getDriverName());
            System.out.println("A new database has been created.");
            String sql = "CREATE DATABASE " + db;
            stmt.execute(sql);
            System.out.println(db + "Sortu da!");

        } catch (SQLException e) {
            System.out.println("Errorea dba sortzerakoan("+e.getMessage()+"-"+e.getErrorCode());
        }
    }
    public static void createNewTable(String database,String table) {
        // SQLite connection string
        
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS "+table+ " (\n"
                + "	id integer PRIMARY KEY AUTO_INCREMENT,\n"
                + "	izena text\n"
                + "                 );";
        
        try (
            Connection conn = connect(database);   
            Statement stmt = conn.createStatement()){
            // create a new table
            stmt.execute(sql);
            System.out.println(table+"taula sortu da.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     public static void insert(String database,String taula) {
        String sql = "INSERT INTO " + taula + " (izena) VALUES(?)";

        try (Connection conn = connect(database);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
         
            pstmt.setString(1,"madariaga");
            pstmt.executeUpdate();
            
           
            pstmt.setString(1,"franco");
            pstmt.executeUpdate();
             pstmt.setString(1,"koke");
            pstmt.executeUpdate();
                     
            System.out.println("Erregistroan ondo sartu dira");
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " ==>" + e.getMessage());
            System.out.println("Ezin dira insertatu");
        }
    }
     public static void selectAll(String databse){
        String sql = "SELECT * FROM irakasleak";
        
        try (Connection conn = connect(databse);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("izena"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     public static void update(String db,String taula){
         String sql = "Select id, izena from " + taula;
        try (Connection conn = connect(db);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String izena = rs.getString("izena");
                int adina = izena.length() * 5;
                String sqlUpdate = "Update " + taula + " set adina = " + adina + " where id = " + id; 
                stmt.execute(sqlUpdate);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
     }
     public static void selectWithParams(String db,String taula){
         Scanner sc = new Scanner(System.in);
         System.out.println("Zein adinetik gorako irakasleak ikusi nahi dituzu??");
         int adina = sc.nextInt();
         String sql = "SELECT * FROM "+taula+ " WHERE adina =?";
         
         Connection conn = null;
         
         try{
             conn = connect(db);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             pstmt.setInt(1, adina);
             ResultSet rs = pstmt.executeQuery(sql);
             while(rs.next()){
                 System.out.println(rs.getString("izena")+rs.getInt("adina"));               
             }
         }catch(Exception e){
             
         }
     }

    public static void main(String[] args) {
        connect("");
        //createNewDatabase("db1");
        //createNewTable("db1", "irakasleak");
        //insert("db1", "irakasleak");
        update("db1", "irakasleak");
        selectAll("db1");
    }
}
