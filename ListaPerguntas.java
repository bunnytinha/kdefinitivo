import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
   
   public class ListaPerguntas{

      ConnectionDB conexao= new ConnectionDB();
      Connection conn= null;
      PreparedStatement x= null;
      ResultSet resp= null; 

    
      String stmt="INSERT INTO Pergunta(pergunta_Pergunta,resposta_Pergunta,tema_Pergunta,id_Pergunta ) VALUES (?,?,?,?);";
      String stmt1="UPDATE Pergunta SET pergunta_Pergunta = ? ,  resposta_Pergunta = ?,tema_pergunta= ?, id_pergunta= ? WHERE id_pergunta = ?;";
      String stmt2="SELECT * FROM Pergunta WHERE tema_Pergunta = ?";
      String stmt3="SELECT * FROM Pergunta ";
      String stmt4="SELECT * FROM Pergunta WHERE tema_Pergunta = ? AND id_Pergunta = ?";
      
      public void addPergunta(Pergunta question){
         String pergunta,resposta,tema,id;
         pergunta= question.getPergunta();
         resposta= question.getResposta();
         tema= question.getTema();
         id= question.getId();
         
         try{
         
           conn= conexao.abrirConnection();
           x=conn.prepareStatement(stmt);
           x.setString(1, pergunta);
           x.setString(2, resposta);
           x.setString(3, tema);
           x.setString(4, id);
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
   
   
   
      public void alterarPergunta(Pergunta p, Pergunta p1){
        String id,perguntaN,respostaN,temaN,idN;
         perguntaN= p1.getPergunta();
         respostaN= p1.getResposta();
         idN= p1.getId();
         temaN= p1.getTema();
         id= p.getId();
         
               
      
         try{
      
            conn= conexao.abrirConnection();
            x=conn.prepareStatement(stmt1);
            x.setString(1, perguntaN);
            x.setString(2, respostaN);
            x.setString(3, temaN);
            x.setString(4, idN);
            x.setString(5, id);            
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
      
   
      public int contarPerguntas(){
         int j=0;  
         try{
            
            conn= conexao.abrirConnection();
            x=conn.prepareStatement(stmt3);
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
   
   
      public void listarPerguntas(String vet[][]){
         int w=0;  
         try{
         
            conn= conexao.abrirConnection();
            x=conn.prepareStatement(stmt3);
            resp= x.executeQuery();
            while (resp.next()) {
               vet[w][0]=resp.getString("id_pergunta");               
               vet[w][1]=resp.getString("tema_pergunta");
               
                           
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
      
      
      
      
      public void getPerguntas(Pergunta perg,String tema, String id){
         int w=0;  
         try{
         
            conn= conexao.abrirConnection();
            x=conn.prepareStatement(stmt4);
            x.setString(1, tema);
            x.setString(2, id);
            resp= x.executeQuery();
            
            
            while (resp.next()) {
               perg.setId(resp.getString("id_Pergunta"));               
               perg.setTema(resp.getString("tema_Pergunta"));
               perg.setPergunta(resp.getString("pergunta_Pergunta"));
               perg.setResposta(resp.getString("resposta_Pergunta"));
                           
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
