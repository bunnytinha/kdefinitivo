public class Aluno{
   private String matriculaA;
   private String nomeA;
   private String emailA;
   private String turmaA;
   
   public Aluno(){
   }
   
   public Aluno(String MatriculaA, String nomeA, String emailA, String turmaA){
      
      this.matriculaA= matriculaA;
      this.nomeA= nomeA;
      this.emailA= emailA;
      this.turmaA= turmaA;
   }
   
  
   public void setMatriculaA(String matriculaA){
      this.matriculaA=matriculaA;
   }
   public void setNomeA(String nomeA){
      this.nomeA=nomeA;
   }
   public void setEmailA(String emailA){
      this.emailA=emailA;
   }
   public void setTurmaA(String turmaA){
      this.turmaA=turmaA;
   }
   public String getMatriculaA(){
      return matriculaA;
   }
   public String getNomeA(){
      return nomeA;
   }
   public String getEmailA(){
      return emailA;
   }   
   public String getTurmaA(){
      return turmaA;
   }   
   
}
