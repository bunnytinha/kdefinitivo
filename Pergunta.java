public class Pergunta{
   private String id;
   private String pergunta;
   private String resposta;
   private String tema;
   
   
   public Pergunta(String pergunta, String resposta, String tema, String id){
      
      this.pergunta= pergunta;
      this.resposta= resposta;
      this.tema= tema;
      this.id= id;
   }
   
   
   public void setPergunta(String pergunta){
      this.pergunta= pergunta;
   }
   public void setResposta(String resposta){
      this.resposta= resposta;
   }
   public void setTema(String tema){
      this.tema= tema;
   }
   public void setId(String id){
      this.id= id;
   }
   
   
   public String getPergunta(){
      return pergunta;
   }
   public String getResposta(){
      return resposta;
   }
   public String getTema(){
      return tema;
   }
   public String getId(){
      return id;
   }
   
}