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

//cria a classe com indicação de JFrame e ActionListener
public class TelaAtividades extends JFrame implements ActionListener{

   private Container painelDeConteudo;   //tem que criar container

   //inicia os privates Jtable, Jlabel, JTextField, Jbutton  
   private ImageIcon ibg = new ImageIcon("C:/Dojo/Imagens/bg3.png");
   private JLabel bg = new JLabel(ibg);
   private ImageIcon ialunos = new ImageIcon("C:/Dojo/Imagens/atividades1.png");    
   private JLabel lbalunos = new JLabel(ialunos);
   private ImageIcon iperguntas = new ImageIcon("C:/Dojo/Imagens/novasAtividades1.png");
   private JLabel lbperguntas = new JLabel(iperguntas);
   private ImageIcon ivoltar = new ImageIcon("C:/Dojo/Imagens/voltar1.png");
   private JLabel lbvoltar = new JLabel(ivoltar);
   private JButton novasAtividades = new JButton("Novas Atividades");
   private JButton atividades = new JButton("Atividades");
   private JButton voltar = new JButton("Voltar");
   private ImageIcon icon = new ImageIcon("C:/Dojo/Imagens/kimedachi200.png");//aqui coloca a URL da imagem
   private JLabel kimedachi = new JLabel(icon);//coloca a imagem em uma label
   private ImageIcon bm = new ImageIcon("C:/Dojo/Imagens/banneratividades.png");
   private JLabel bannerM = new JLabel(bm);
   private ImageIcon bn = new ImageIcon("C:/Dojo/Imagens/bannerkd.png");
   private JLabel bannerK = new JLabel(bn);

   public TelaAtividades(){
   
      super ("Kime Dachi - Atividades");
         
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
      bg.setBounds(0,0,telaW,telaH);
      atividades.setBounds(centroW-250,centroH-200,500,100);
      lbalunos.setBounds(centroW-230,centroH-200,100,100);
      novasAtividades.setBounds(centroW-250,centroH-100,500,100);
      lbperguntas.setBounds(centroW-230,centroH-100,100,100);
      voltar.setBounds(centroW-250,centroH+100,500,100);
      lbvoltar.setBounds(centroW-230,centroH+100,100,100);
      bannerK.setBounds(-700,-400,telaW,telaH);
      bannerM.setBounds(+700,-400,telaW,telaH);
      
      //estio dos botoes
      atividades.setFont(new Font("BEBAS", Font.PLAIN, 42));
      novasAtividades.setFont(new Font("BEBAS", Font.PLAIN, 42));
      voltar.setFont(new Font("BEBAS", Font.PLAIN, 42));
      atividades.setForeground(Color.WHITE);
      novasAtividades.setForeground(Color.WHITE);
      voltar.setForeground(Color.WHITE);
      atividades.setOpaque(false);
      atividades.setContentAreaFilled(false);
      atividades.setBorderPainted(false);
      novasAtividades.setOpaque(false);
      novasAtividades.setContentAreaFilled(false);
      novasAtividades.setBorderPainted(false);
      voltar.setOpaque(false);
      voltar.setContentAreaFilled(false);
      voltar.setBorderPainted(false);
     
      //coloca os botões pra fazerem algo quando apertados
      atividades.addActionListener(this); 
      novasAtividades.addActionListener(this);
      voltar.addActionListener(this);      
  
      //seta o estilo como nulo
      painelDeConteudo.setLayout(null); 
         
      //adiciona as coisas na tela 
      painelDeConteudo.add(atividades);
      painelDeConteudo.add(lbalunos);
      painelDeConteudo.add(novasAtividades);
      painelDeConteudo.add(lbperguntas);
      painelDeConteudo.add(voltar);
      painelDeConteudo.add(lbvoltar);
      painelDeConteudo.add(bannerK);
      painelDeConteudo.add(bannerM);
      painelDeConteudo.add(bg);

      //arruma tamanho layout e visibilidade do frame
      setSize(telaW,telaH);//tela do tamanho da tela do usuario
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//fecha a aplicacao
      setExtendedState(JFrame.MAXIMIZED_BOTH);//tela cheia
      setLayout(null);
      setVisible(true);//visivel    
         
   }
      
      //define oq os botões fazem 
      public void actionPerformed(ActionEvent e){
         if(e.getSource() == atividades){
            new BancoDePerguntas();
            dispose();
            System.out.println("Lista de Atividades");
         }
         else if(e.getSource() == novasAtividades){
            new CadastroPerguntas();
            dispose();
            System.out.println("Novas Atividades");
         }
         else if(e.getSource() == voltar){
            new TelaMenu();
            dispose();
            System.out.println("Voltar para menu");
         }
      }
      
      //main para poder ver a tela
      public static void main (String [] args){
         SwingUtilities.invokeLater (new Runnable (){
            public void run (){
               new TelaAtividades();//coloca o nome do arquivo no lugar de PadraoTelas
            }
         });
      }

}