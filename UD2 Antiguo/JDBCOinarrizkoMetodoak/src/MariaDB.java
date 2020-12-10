
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author angulo.jorge
 */
public class MariaDB {

    public static Connection connect(String db) {
        String server = "localhost";
        String url = "jdbc:mysql://" + server + "/" + db;
        String user = "root";
        String pass = "root";
        
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Ondo konektatu zara");
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "-" + e.getMessage());
        }
        return conn;
    }
    
    public static void main(String[] args) {
        
        connect("test"); // Se puede poner un nombre para conectarse a una base de datos especifica, y en el caso de no ponerlo.
    }
}
