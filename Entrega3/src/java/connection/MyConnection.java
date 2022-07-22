package connection;

import java.sql.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyConnection {
    
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/entrega3";
    public static final String USER = "root";
    public static final String PASS = "";
    
    public static Connection getConnection(){
        
        try {
            
            Class.forName(DRIVER);
            
            return DriverManager.getConnection(URL, USER, PASS);
            
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexao", ex );
        }
    }
    
    public static void closeConnection(Connection con){
        
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println("ERRO"+ex);
            }
        }
        
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt){
        
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("ERRO"+ex);
            }
        }
        
        closeConnection(con);
        
    }
    
    public static void closeConnection(Connection con, PreparedStatement stm, ResultSet rs){
        
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("ERRO"+ex);
            }
        }
        
        closeConnection(con, stm);
        
    }
    
}

