import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaTurmas{

   ConnectionDB conexao= new ConnectionDB();
   Connection conn= null;
   PreparedStatement x= null;
   ResultSet resp= null; 
   ResultSet resp1= null;
    
   String stmt="SELECT * FROM Turma";
   
      
   
   public int contarTurma(){
      int j=0;  
      try{
      
         conn= conexao.abrirConnection();
         x=conn.prepareStatement(stmt);
         resp= x.executeQuery();
         while (resp.next()) {
            j++;      
         }
         x.close();
         
         

              
      }catch(SQLException e){
         e.printStackTrace();
         j=-1;
      }finally{
         try{
            
            if (conn != null){
               conn.close(); 
            }
            
         }catch(SQLException t){
            t.printStackTrace();
         }
      }
      return j;
   }
   
   
   public void listarTurma(String vet[]){
      int w=0;  
      try{
      
         conn= conexao.abrirConnection();
         x=conn.prepareStatement(stmt);
         resp= x.executeQuery();
         while (resp.next()) {
            vet[w]=resp.getString("codigo_Turma");
            w++;          
         }
         x.close();   
              
      }catch(SQLException e){
         e.printStackTrace();
      }finally{
         try{
            
            if (conn != null){
               conn.close(); 
            }
            
         }catch(SQLException t){
            t.printStackTrace();
         }
      }
   }
   


   
}