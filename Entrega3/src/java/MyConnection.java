import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
   private Connection connection;
   public MyConnection()throws ClassNotFoundException, SQLException{
       Class.forName("com.mysql.jdbc.Driver");
       connection = DriverManager.getConnection("jdbc:mysql://localhost/entrega3", "root", "");
       System.out.println("Conectado com sucesso");
      
        
    }
    
    public static void main(String[] args) {
        try{
            new MyConnection();
        }catch (Exception e){
            System.out.println("NOT CONNECT");
            
        }
    }
    
}
