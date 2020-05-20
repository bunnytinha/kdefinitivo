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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

//cria a classe com indicação de JFrame e ActionListener
public class TelaMenuTeste extends JFrame implements ActionListener{

   private Container painelDeConteudo;   //tem que criar container

   //inicia os privates Jtable, Jlabel, JTextField, Jbutton  
   private ImageIcon ibg = new ImageIcon("../Imagens/bg3.png");
   private JLabel bg = new JLabel(ibg);
   private ImageIcon ialunos = new ImageIcon("../Imagens/alunos1.png");    
   private JLabel lbalunos = new JLabel(ialunos);
   private ImageIcon iperguntas = new ImageIcon("../Imagens/atividades1.png");
   private JLabel lbperguntas = new JLabel(iperguntas);
   private ImageIcon idojo = new ImageIcon("../Imagens/dojo1.png");
   private JLabel lbdojo = new JLabel(idojo);
   private JButton alunos = new JButton("Alunos");
   private JButton perguntas = new JButton("Atividades");
   private JButton dojo = new JButton("Dojo");
   private ImageIcon icon = new ImageIcon("../Imagens/kimedachi200.png");//aqui coloca a URL da imagem
   private JLabel kimedachi = new JLabel(icon);//coloca a imagem em uma label
   private ImageIcon bm = new ImageIcon("../Imagens/bannermenu.png");
   private JLabel bannerM = new JLabel(bm);
   private ImageIcon bn = new ImageIcon("../Imagens/bannerkd.png");
   private JLabel bannerK = new JLabel(bn);

   public TelaMenuTeste(){
   
      super ("Kime Dachi - Menu");
         
      //declara o painel         
      painelDeConteudo = getContentPane();
  
      //define o tamanho da tela
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension d = tk.getScreenSize();
      int telaW= d.width;
      int telaH= d.height;
      //int telaW = 1600;
      //int telaH = 900;
      int centroW = telaW/2;
      int centroH = telaH/2;
         
      //define a posição e tamanho dos itens (posição horizontal,posição vertical,tamanho horizontal, tamanho vertical)
      //bg.setBounds(0,0,telaW,telaH);
      alunos.setBounds(centroW-100,centroH-200,200,100);
      lbalunos.setBounds(centroW-220,centroH-200,100,100);
      perguntas.setBounds(centroW-100,centroH-100,200,100);
      lbperguntas.setBounds(centroW-220,centroH-100,100,100);
      dojo.setBounds(centroW-100,centroH,200,100);
      lbdojo.setBounds(centroW-220,centroH,100,100);
      bannerK.setBounds(centroW-900,centroH-500,700,300);
      bannerM.setBounds(centroW+295,centroH-500,700,300);
      
      //estio dos botoes
      alunos.setFont(new Font("BEBAS", Font.PLAIN, 42));
      perguntas.setFont(new Font("BEBAS", Font.PLAIN, 42));
      dojo.setFont(new Font("BEBAS", Font.PLAIN, 42));
      alunos.setForeground(Color.WHITE);
      perguntas.setForeground(Color.WHITE);
      dojo.setForeground(Color.WHITE);
      alunos.setOpaque(false);
      alunos.setContentAreaFilled(false);
      alunos.setBorderPainted(false);
      perguntas.setOpaque(false);
      perguntas.setContentAreaFilled(false);
      perguntas.setBorderPainted(false);
      dojo.setOpaque(false);
      dojo.setContentAreaFilled(false);
      dojo.setBorderPainted(false);
               
      //coloca os botões pra fazerem algo quando apertados
      alunos.addActionListener(this); 
      perguntas.addActionListener(this);
      dojo.addActionListener(this);      
  
      //seta o estilo como nulo
      painelDeConteudo.setLayout(null); 
         
      //adiciona as coisas na tela 
      painelDeConteudo.add(alunos);
      painelDeConteudo.add(lbalunos);
      painelDeConteudo.add(perguntas);
      painelDeConteudo.add(lbperguntas);
      painelDeConteudo.add(dojo);
      painelDeConteudo.add(lbdojo);
      painelDeConteudo.add(bannerK);
      painelDeConteudo.add(bannerM);
      painelDeConteudo.add(bg);
      //add(bg, BorderLayout.CENTER);

      //arruma tamanho layout e visibilidade do frame
      setSize(telaW,telaH);//tela do tamanho da tela do usuario
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//fecha a aplicacao
      setExtendedState(JFrame.MAXIMIZED_BOTH);//tela cheia
      setLayout(new BoxLayout(painelDeConteudo,BoxLayout.Y_AXIS));
      setVisible(true);//visivel    
         
   }
      
      //define oq os botões fazem 
      public void actionPerformed(ActionEvent e){
         if(e.getSource() == alunos){
            new TelaAlunos();
            dispose();
            System.out.println("Tela Alunos");
         }
         else if(e.getSource() == perguntas){
            new TelaAtividades();
            dispose();
            System.out.println("Tela Atividades");
         }
         else if(e.getSource() == dojo){
            new AulaDojo();
            dispose();
            System.out.println("Tela Dojo");
         }
      }
      
      //main para poder ver a tela
      public static void main (String [] args){
         SwingUtilities.invokeLater (new Runnable (){
            public void run (){
               new TelaMenu();//coloca o nome do arquivo no lugar de PadraoTelas
            }
         });
      }

}