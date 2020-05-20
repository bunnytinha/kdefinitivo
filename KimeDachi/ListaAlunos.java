import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class ListaAlunos{

   ConnectionDB conexao= new ConnectionDB();
   Connection conn= null;
   PreparedStatement x= null;
   PreparedStatement x1= null;
   PreparedStatement x2= null;
   ResultSet resp= null; 
   ResultSet resp1= null;
   ResultSet resp2= null;
    
   String stmt="INSERT INTO Pessoa(nome_Pessoa, email_Pessoa) VALUES (?,?);";
   String stmt1="INSERT INTO Aluno (matricula_Aluno, fk_matricula_Pessoa, fk_codigo_Turma) VALUE (?,?,?);";  
   String stmt2="SELECT * FROM Pessoa WHERE email_Pessoa= ?;";
   String stmt3="UPDATE Aluno SET matricula_Aluno = ? ,  fk_codigo_Turma = ? WHERE matricula_Aluno = ?;";
   String stmt4="UPDATE Pessoa SET nome_Pessoa = ? ,  email_Pessoa = ? WHERE matricula_Pessoa = ?;";
   String stmt5="SELECT * FROM Aluno WHERE fk_codigo_Turma= ?";
   String stmt6="SELECT * FROM Pessoa WHERE matricula_Pessoa= ?";
   String stmt7="SELECT * FROM Aluno WHERE fk_matricula_Pessoa= ?";
   String stmt8="SELECT * FROM Pessoa WHERE nome_Pessoa= ?";
   String stmt9="SELECT * FROM Aluno WHERE matricula_Aluno= ?";
   
   public void addAluno(Aluno aluno){
      String nome,email,matricula,turma;
      nome=  aluno.getNomeA();
      email=  aluno.getEmailA(); 
      matricula=  aluno.getMatriculaA();
      turma=  aluno.getTurmaA();
      try{
      
         conn= conexao.abrirConnection();
         x=conn.prepareStatement(stmt);
         x.setString(1, nome);
         x.setString(2, email);
         x.execute();
         x.close();
         
         
         
         x=conn.prepareStatement(stmt2);
         x.setString(1,email);
         resp = x.executeQuery();
         int y = 0;
         while (resp.next()) {
             y = resp.getInt("matricula_Pessoa");
         }
         String w = ""+y; 
         x.close();
         
         
         x=conn.prepareStatement(stmt1);
         x.setString(1, matricula);
         x.setString(2, w);
         x.setString(3, turma);
         x.execute();
         x.close();
         
         
         
         
         resp.close();
         
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
   
   
   
   public void alterarAluno(String matricula, Aluno alunoNew){
      
      String id="0";
      
      String nomeN,emailN,matriculaN,turmaN;
      nomeN=  alunoNew.getNomeA();
      emailN=  alunoNew.getEmailA(); 
      matriculaN=  alunoNew.getMatriculaA();
      turmaN=  alunoNew.getTurmaA();
      
      
      try{
      
         conn= conexao.abrirConnection();
         x=conn.prepareStatement(stmt3);
         x.setString(1, matriculaN);
         x.setString(2, turmaN);
         x.setString(3, matricula);
         x.execute();
         x.close();
         
         conn= conexao.abrirConnection();
         x2=conn.prepareStatement(stmt9);
         x2.setString(1, matricula);
         resp= x2.executeQuery();
         while (resp.next()) {
            id =Integer.toString(resp.getInt("fk_matricula_Pessoa"));        
         }
         x2.close();
         
         x=conn.prepareStatement(stmt4);
         x.setString(1, nomeN);
         x.setString(2, emailN);
         x.setString(3, id);
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
   
   
   public int contarAlunos(String turma){
      int j=0;  
      try{
      
         conn= conexao.abrirConnection();
         x=conn.prepareStatement(stmt5);
         x.setString(1, turma);
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
   
   
   public void listarAlunos(String turma,String vet[][]){
      int w=0;  
      try{
      
         conn= conexao.abrirConnection();
         x=conn.prepareStatement(stmt5);
         x.setString(1, turma);
         resp= x.executeQuery();
         while (resp.next()) {
            vet[w][1]=resp.getString("matricula_Aluno");
            x1=conn.prepareStatement(stmt6);
            String numero= Integer.toString(resp.getInt("fk_matricula_Pessoa"));
            x1.setString(1,numero);
            resp1=x1.executeQuery();
            while(resp1.next()){
               vet[w][0]=resp1.getString("nome_Pessoa");
            }   
            x1.close();
            resp1.close();
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
   
   
   
   
  public Pilha listaSorteio(ArrayList<Aluno> nomes){
      
      Pilha p= new Pilha(nomes.size());
      Collections.shuffle(nomes);
      Collections.shuffle(nomes);
      for(int i=0; i<nomes.size();i++){
         String atributo = nomes.get(i).getMatriculaA();
         p.push(atributo);
      }
      return p;
      
      
      
  }
   
   
   public void listarAlunosCompleto(String turma,String vet[][]){
      int w=0;  
      try{
      
         conn= conexao.abrirConnection();
         x=conn.prepareStatement(stmt5);
         x.setString(1, turma);
         resp= x.executeQuery();
         while (resp.next()) {
            vet[w][1]=resp.getString("matricula_Aluno");            
            x1=conn.prepareStatement(stmt6);
            String numero= Integer.toString(resp.getInt("fk_matricula_Pessoa"));
            x1.setString(1,numero);
            resp1=x1.executeQuery();
            while(resp1.next()){
               vet[w][0]=resp1.getString("nome_Pessoa");
               vet[w][2]=resp1.getString("email_Pessoa");
            }   
            x1.close();
            resp1.close();
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

   
   public Aluno buscarAluno(ArrayList<Aluno> nomes,String ra){
      int j=0;
      for(int i=0; i < nomes.size(); i++){
         if(nomes.get(i).getMatriculaA().equals(ra)){
           j= i; 
         }             
      }
      Aluno estudante= nomes.get(j);
      return estudante;
 
   }
      

   
   



   
}