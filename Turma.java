public class Turma{
   private static String codigo;
   
   public Turma(String codigo){    
      this.codigo= codigo;
   }
   public void setCodigo(String codigo){    
      this.codigo= codigo;
   }
   public static String getCodigo(){    
      return codigo;
   }
}