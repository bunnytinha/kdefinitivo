import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.text.ParseException;
import java.awt.Font;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

//cria a classe com indicação de JFrame e ActionListener
public class TelaAcesso extends JFrame implements ActionListener{

   private Container painelDeConteudo;   //tem que criar container

   //inicia os privates Jtable, Jlabel, JTextField, Jbutton  
   private JLabel user = new JLabel("usuario:");
   private JTextField tuser = new JTextField("");
   private JLabel senha = new JLabel("senha:");
   private JPasswordField tsenha = new JPasswordField("");
   private JButton login = new JButton("Acessar");
   private ImageIcon icon = new ImageIcon("../Imagens/kimedachi200.png");//aqui coloca a URL da imagem
   private JLabel kimedachi = new JLabel(icon);//coloca a imagem em uma label
   private ImageIcon ibg = new ImageIcon("../Imagens/bg3.png");
   private JLabel bg = new JLabel(ibg);

   public TelaAcesso(){
   
      super ("Kime Dachi - Acesso");
         
      //declara o painel         
      painelDeConteudo = getContentPane(); 
         
      //define o tamanho da tela
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension d = tk.getScreenSize();
      int telaW= d.width;
      int telaH= d.height;
      int centroW = telaW/2;
      int centroH = telaH/2;
         
      //define a posição e tamanho dos itens (posição horizontal,posição vertical,tamanho horizontal, tamanho vertical)
      kimedachi.setBounds(centroW-150,centroH-350,300,300);
      user.setBounds(centroW-120, centroH-70, 120, 30);
      tuser.setBounds(centroW+20, centroH-70, 120, 30);
      senha.setBounds(centroW-120, centroH-20, 120, 30);
      tsenha.setBounds(centroW+20, centroH-20, 120, 30);
      login.setBounds(centroW-100, centroH+20, 200, 100);
      bg.setBounds(0,0,telaW,telaH);
      
      //estilo
      user.setFont(new Font("BEBAS", Font.PLAIN, 36));
      user.setForeground(Color.WHITE);

      senha.setFont(new Font("BEBAS", Font.PLAIN, 36));
      senha.setForeground(Color.WHITE);

      login.setFont(new Font("BEBAS", Font.PLAIN, 42));
      login.setForeground(Color.WHITE);
      login.setOpaque(false);
      login.setBorderPainted(false);
      login.setContentAreaFilled(false);
               
      //coloca os botões pra fazerem algo quando apertados
      login.addActionListener(this);       
  
      //seta o estilo como nulo
      painelDeConteudo.setLayout(null); 
         
      //adiciona as coisas na tela 
      painelDeConteudo.add(kimedachi);
      painelDeConteudo.add(user);
      painelDeConteudo.add(tuser);
      painelDeConteudo.add(senha);
      painelDeConteudo.add(tsenha);
      painelDeConteudo.add(login);
      painelDeConteudo.add(bg);

      //arruma tamanho layout e visibilidade do frame
      setSize(telaW,telaH);//tela do tamanho da tela do usuario
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//fecha a aplicacao
      setExtendedState(JFrame.MAXIMIZED_BOTH);//tela cheia
      setLayout(null);
      setVisible(true);//visivel   
 

      ConnectionDB conexao = new ConnectionDB();
      Connection conn = null;
   }
      
      //define oq os botões fazem 
      public void actionPerformed(ActionEvent e){
         String value1 = tuser.getText();
         String value2 = tsenha.getText();
         String user1 = "";
         String pass1 = "";
         try{
            ConnectionDB conexao= new ConnectionDB();
            Connection conn= null;
            conn = conexao.abrirConnection();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM Professor where matricula_Professor='"+value1+"' && senha_Professor='"+value2+"'");
            while (res.next()) {
            user1 = res.getString("matricula_Professor");
            pass1 = res.getString("senha_Professor");
            }
            if (value1.equals(user1) && value2.equals(pass1)) {
               new TelaMenu();
               dispose();
               System.out.println("Acesso permitido.");
            }else{
               System.out.println("enter the valid username and password");
               JOptionPane.showMessageDialog(this,"Usuário ou senha incorreto(s).",
               "Error",JOptionPane.ERROR_MESSAGE);
            }
            
         }catch(SQLException b){
            b.printStackTrace();
         }finally{
            try{
               
               Connection conn= null;
               if (conn != null){
                  conn.close(); 
               }
               
            }catch(SQLException t){
               t.printStackTrace();
            }
         }
      }
      
      //main para poder ver a tela
      public static void main (String [] args){
         SwingUtilities.invokeLater (new Runnable (){
            public void run (){
               new ConnectionDB();
               new TelaAcesso();//coloca o nome do arquivo no lugar de PadraoTelas
            }
         });
      }

}