import java.util.Date;

public class Nota{
   double valor;
   String data, presenca;
   
   public Nota(double nota, String data, String presenca){
      
      this.valor= nota;
      this.data= data;
      this.presenca= presenca;
   }
   
   public double getValor(){
      return valor;
   }
   public String getData(){
      return data;
   }
   public String getPresenca(){
      return presenca;
   }
   public void setValor(double valor){
      this.valor= valor;
   }
   public void setData(String data){
      this.data= data;
   }
   public void setPresenca(String presenca){
      this.presenca= presenca;
   }

}
