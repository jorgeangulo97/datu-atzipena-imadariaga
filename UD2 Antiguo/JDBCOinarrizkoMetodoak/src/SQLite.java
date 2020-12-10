

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author angulo.jorge
 */
public class SQLite {
    
     public static Connection connect(String db) {
        // db parameters
        String url = "";

        url = "jdbc:sqlite:" + db;

        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection(url);
            System.out.println("Ondo konektatu gara");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
     
    public static void createDatabase(String fileName) {

        Connection conn = connect(fileName);
        try {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Driver hau erabili dugu: " + meta.getDriverName());
                System.out.println(fileName + " datu-basea sortua izan da.");
            }

        } catch (SQLException e) {
            System.out.println("Ezin konektatu. Ezin datubasea sortu");
        } finally {
            if (conn != null) {
                try{
                    conn.close();
                } catch (SQLException e){
                    System.out.println("Errorea konexioa ixterakoan.");
                }
            }
        }
    }
    
    public static void createTable(String database, String table) {

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + table + "(\n"
                + "	id integer PRIMARY KEY,\n"
                + "	eremu1 text,\n"
                + "	eremu2 text)";
        
        try (
            Connection conn = connect(database);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println(table + " taula sortua");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     
    public static void insert(String db, String taula) {
        String sql = "INSERT INTO " + taula + "(id,eremu1) VALUES(?,?)";

        try (
            Connection conn = connect(db);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, 1);
            pstmt.setString(2, "Juanito");
            pstmt.executeUpdate();
            
             pstmt.setInt(1, 2);
            pstmt.setString(2, "Jorgito");
            pstmt.executeUpdate();
            
            pstmt.setInt(1, 3);
            pstmt.setString(2, "Jaimito");
            pstmt.executeUpdate();
            
            System.out.println("Erregistro batzuk txertatu dira.");
        } catch (SQLException e) {
            //System.out.println(e.getErrorCode() + " ==> " +e.getMessage());
            if(19 == e.getErrorCode()){
                System.out.println("Txertatu nahi duzun erregistroaren id-a dagoeneko existitzen da.");
            }           
        }
    }
    
    public static void selectAll(String database){
        String sql = "SELECT * FROM taula1";

        try (Connection conn = connect(database);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("eremu1"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void selectWithParams(String database){
        String sql = "SELECT eremu1 FROM taula1";        
    
        try (Connection conn = connect(database);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("eremu1"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
   /* public static void update(String database){ //TODO
        String sql = "UPDATE taula1 SET eremu1 = ?, "
                + "eremu2 = ?"
                + "WHERE id = ?";
        try (
            Connection conn = connect(database);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, 1);
            pstmt.setString(2, "Juanito");
            pstmt.executeUpdate();
            
             pstmt.setInt(1, 2);
            pstmt.setString(2, "Jorgito");
            pstmt.executeUpdate();
            
            pstmt.setInt(1, 3);
            pstmt.setString(2, "Jaimito");
            pstmt.executeUpdate();
            
            System.out.println("Erregistro batzuk txertatu dira.");
        } catch (SQLException e) {
            //System.out.println(e.getErrorCode() + " ==> " +e.getMessage());
        }
    }*/
    
     
    public static void main(String[] args) {
        String dbIzena;
        
        dbIzena = "datubaseak/db1.db;";
        connect(dbIzena);
        
        createDatabase(dbIzena);
        String taulaIzena = "taula1";
        
        createTable(dbIzena, taulaIzena);
        
        insert(dbIzena, taulaIzena);
        selectAll(dbIzena);
        
        System.out.println("Select parametrorekin");
        selectWithParams(dbIzena);
    }
}
