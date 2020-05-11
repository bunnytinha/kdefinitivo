import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionDB{

   private static final String URL= "jdbc:mysql://localhost:3306/Sistema_Dojo?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT&useSSL=false";
   private static final String USER= "root";
// private static final String PASS="vi1429peso";
   private static final String PASS = "p00taQUEpariu";
   public static Connection abrirConnection(){
      try {
         return DriverManager.getConnection(URL, USER, PASS);
      }catch(SQLException e){
         throw new RuntimeException("erro: ",e); 
      }
      
   }
   public static void fecharConnection(Connection w){
      
      try {
         if(w != null){
            w.close();
         }
      }catch(SQLException e){
         e.printStackTrace();
      }
      
      
   }
   public static void fecharConnection(Connection w, PreparedStatement x){
      
      fecharConnection(w);
      
      try {
         if(x != null){
            x.close();
         }
      }catch(SQLException e){
         e.printStackTrace();
      }
      
      
   }
   public static void fecharConnection(Connection w, PreparedStatement x, ResultSet y){
      
      fecharConnection(w,x);
      
      try {
         if(y != null){
            y.close();
         }
      }catch(SQLException e){
         e.printStackTrace();
      }
      
      
      
   }
}
