import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
   
   public class ListaNotas{

      ConnectionDB conexao= new ConnectionDB();
      Connection conn= null;
      PreparedStatement x= null;
      
      ResultSet resp= null; 
      
    
      String stmt="INSERT INTO Nota(valor_Nota, data_Teste, presenca_dia, fk_matricula_Aluno ) VALUES (?,?,?,?);"; 
      String stmt1="UPDATE Nota SET valor_Nota = ? , data_Teste = ? , presenca_dia = ? WHERE fk_matricula_Aluno = ? AND data_Teste= ? ;";       
      String stmt2="SELECT * FROM Nota WHERE fk_matricula_Aluno= ?;";

      
      public void addNota(Nota nota, String ra){
         String valor,data,presenca,matricula;
         valor= Double.toString(nota.getValor());
         data=  nota.getData();
         presenca= nota.getPresenca(); 
         matricula =  ra;
         
         try{
         
           conn= conexao.abrirConnection();
           x=conn.prepareStatement(stmt);
           x.setString(1, valor);
           x.setString(2, data);
           x.setString(3, presenca);
           x.setString(4, matricula);
           x.execute();
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
   
   
   
      public void alterarNota(Nota nota,String ra, Nota notaNew){
         String data,matricula,valorN,dataN,presencaN;
         data=  nota.getData(); 
         matricula =  ra;
         valorN=  Double.toString(notaNew.getValor());;
         dataN=  notaNew.getData();
         presencaN=  notaNew.getPresenca();
         
      
         try{
      
            conn= conexao.abrirConnection();
            x=conn.prepareStatement(stmt1);
            x.setString(1, valorN);
            x.setString(2, dataN);
            x.setString(3, presencaN);
            x.setString(4, matricula);
            x.setString(5, data);
            x.execute();
            x.close();
            
            
           
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
      
   
      public int contarNotas(String ra){
      
         String matricula= ra;
            
         int j=0;  
         try{
            
            conn= conexao.abrirConnection();
            x=conn.prepareStatement(stmt2);
            x.setString(1, matricula);
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
   
   
      public void listarNotas(String ra,String vet[][]){
         int w=0;  
         try{
         
            conn= conexao.abrirConnection();
            x=conn.prepareStatement(stmt2);
            x.setString(1, ra);
            resp= x.executeQuery();
            while (resp.next()) {
               vet[w][0]=resp.getString("valor_Nota");
               vet[w][1]=resp.getString("data_Teste");
               vet[w][2]=resp.getString("presenca_dia");
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