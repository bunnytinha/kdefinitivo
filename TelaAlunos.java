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
public class TelaAlunos extends JFrame implements ActionListener{

   private Container painelDeConteudo;   //tem que criar container

   //inicia os privates Jtable, Jlabel, JTextField, Jbutton  
   private ImageIcon ibg = new ImageIcon("C:/Dojo/Imagens/bg3.png");
   private JLabel bg = new JLabel(ibg);
   private ImageIcon ialunos = new ImageIcon("C:/Dojo/Imagens/listaAlunos1.png");    
   private JLabel lbalunos = new JLabel(ialunos);
   private ImageIcon iperguntas = new ImageIcon("C:/Dojo/Imagens/cadastrarAlunos.png");
   private JLabel lbperguntas = new JLabel(iperguntas);
   private ImageIcon idojo = new ImageIcon("C:/Dojo/Imagens/alterarAlunos1.png");
   private JLabel lbdojo = new JLabel(idojo);
   private ImageIcon ivoltar = new ImageIcon("C:/Dojo/Imagens/voltar1.png");
   private JLabel lbvoltar = new JLabel(ivoltar);
   private JButton listaAlunos = new JButton("Lista de Alunos");
   private JButton cadastroAlunos = new JButton("Cadastro de Alunos");
   private JButton alterarAlunos = new JButton("Alterar Cadastro");
   private JButton voltar = new JButton("Voltar");
   private ImageIcon icon = new ImageIcon("C:/Dojo/Imagens/kimedachi200.png");//aqui coloca a URL da imagem
   private JLabel kimedachi = new JLabel(icon);//coloca a imagem em uma label
   private ImageIcon bm = new ImageIcon("C:/Dojo/Imagens/banneralunos.png");
   private JLabel bannerM = new JLabel(bm);
   private ImageIcon bn = new ImageIcon("C:/Dojo/Imagens/bannerkd.png");
   private JLabel bannerK = new JLabel(bn);

   public TelaAlunos(){
   
      super ("Kime Dachi - Alunos");
         
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
      listaAlunos.setBounds(centroW-250,centroH-200,500,100);
      lbalunos.setBounds(centroW-230,centroH-200,100,100);
      cadastroAlunos.setBounds(centroW-250,centroH-100,500,100);
      lbperguntas.setBounds(centroW-230,centroH-100,100,100);
      alterarAlunos.setBounds(centroW-250,centroH,500,100);
      voltar.setBounds(centroW-250,centroH+100,500,100);
      lbvoltar.setBounds(centroW-230,centroH+100,100,100);
      lbdojo.setBounds(centroW-230,centroH,100,100);
      bannerK.setBounds(-700,-400,telaW,telaH);
      bannerM.setBounds(+700,-400,telaW,telaH);

      //estio dos botoes
      listaAlunos.setFont(new Font("BEBAS", Font.PLAIN, 42));
      cadastroAlunos.setFont(new Font("BEBAS", Font.PLAIN, 42));
      alterarAlunos.setFont(new Font("BEBAS", Font.PLAIN, 42));
      voltar.setFont(new Font("BEBAS", Font.PLAIN, 42));
      listaAlunos.setForeground(Color.WHITE);
      cadastroAlunos.setForeground(Color.WHITE);
      alterarAlunos.setForeground(Color.WHITE);
      voltar.setForeground(Color.WHITE);
      listaAlunos.setOpaque(false);
      listaAlunos.setContentAreaFilled(false);
      listaAlunos.setBorderPainted(false);
      alterarAlunos.setOpaque(false);
      alterarAlunos.setContentAreaFilled(false);
      alterarAlunos.setBorderPainted(false);
      cadastroAlunos.setOpaque(false);
      cadastroAlunos.setContentAreaFilled(false);
      cadastroAlunos.setBorderPainted(false);
      voltar.setOpaque(false);
      voltar.setContentAreaFilled(false);
      voltar.setBorderPainted(false);

      //coloca os botões pra fazerem algo quando apertados
      listaAlunos.addActionListener(this); 
      cadastroAlunos.addActionListener(this);
      alterarAlunos.addActionListener(this);
      voltar.addActionListener(this);      
  
      //seta o estilo como nulo
      painelDeConteudo.setLayout(null); 
         
      //adiciona as coisas na tela 
      painelDeConteudo.add(listaAlunos);
      painelDeConteudo.add(lbalunos);
      painelDeConteudo.add(cadastroAlunos);
      painelDeConteudo.add(lbperguntas);
      painelDeConteudo.add(alterarAlunos);
      painelDeConteudo.add(lbdojo);
      painelDeConteudo.add(lbvoltar);
      painelDeConteudo.add(voltar);
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
         if(e.getSource() == listaAlunos){
            new ListaDeAluno();
            dispose();
            System.out.println("Lista de Alunos");
         }
         else if(e.getSource() == cadastroAlunos){
            new CadastroAluno();
            dispose();
            System.out.println("Cadastrar Aluno");
         }
         else if(e.getSource() == alterarAlunos){
            new AlterarCadastroAluno();
            dispose();
            System.out.println("Alterar cadastro.");
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
               new TelaAlunos();//coloca o nome do arquivo no lugar de PadraoTelas
            }
         });
      }

}