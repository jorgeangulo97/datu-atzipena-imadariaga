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
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author sqlitetutorial.net
 */
public class SQLite {
     /**
     * Connect to a sample database
     */
    static String url ;
    public static Connection connect(String db) {
        Connection conn = null;
        
        try {
            // db parameters
            url= "jdbc:sqlite:" +db;
           
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
          
            System.out.println("Te has conectado a la base de datos!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        
        }
        return conn;
    }
    public static void createNewDatabase(String fileName) {

       Connection conn = connect(fileName);
       
        try  {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            if(conn !=null){
                try{
                    conn.close();
                }catch(SQLException e){
                    System.out.println("Error al cerrar la base de datos!");
                }
            }
        }
    }
     public static void createNewTable(String database,String table) {
        // SQLite connection string
        
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS "+table+ " (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	eremua1 text\n"
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
        String sql = "INSERT INTO " + taula + " (id,eremua1) VALUES(?,?)";

        try (Connection conn = connect(database);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,1);
            pstmt.setString(2,"Mikel");
            pstmt.executeUpdate();
            
            pstmt.setInt(1,2);
            pstmt.setString(2,"Koke");
            pstmt.executeUpdate();
            
            pstmt.setInt(1,3);
            pstmt.setString(2,"Pablo");
            pstmt.executeUpdate();
            
            System.out.println("Erregistroan ondo sartu dira");
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " ==>" + e.getMessage());
            System.out.println("Ezin dira insertatu");
        }
    }
  public static void delete(String database,String taula) {
        String sql = "INSERT INTO " + taula + " (id,eremua1) VALUES(?,?)";

        try (Connection conn = connect(database);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,1);
            pstmt.setString(2,"Mikel");
            pstmt.executeUpdate();
            
            pstmt.setInt(1,2);
            pstmt.setString(2,"Koke");
            pstmt.executeUpdate();
            
            pstmt.setInt(1,3);
            pstmt.setString(2,"Pablo");
            pstmt.executeUpdate();
            
            System.out.println("Erregistroan ondo sartu dira");
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " ==>" + e.getMessage());
            System.out.println("Ezin dira insertatu");
        }
    }
 public static void selectAll(String databse){
        String sql = "SELECT * FROM taula1";
        
        try (Connection conn = connect(databse);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("eremua1"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String dbIzena;
        
        
        dbIzena="E:\\Datu-atzipena\\datu-atzipena-imadariaga\\UD2\\datubaseak\\db1.db";
        createNewDatabase(dbIzena);
       String taulaIzena = "taula1";
        createNewTable(dbIzena, taulaIzena);
        
        
        insert(dbIzena,taulaIzena);
       
        
        selectAll(dbIzena);
    }
}
