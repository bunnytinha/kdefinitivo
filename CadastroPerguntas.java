//imports
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


//classe do tipo tela
public class CadastroPerguntas extends JFrame implements ActionListener{


      //variaveis privadas 
      private Container painelDeConteudo;
      private JTextField perguntaT= new JTextField("");
      private JTextField respostaT= new JTextField("");
      private JTextField temaT= new JTextField("");
      private JTextField idT= new JTextField("pergunta em 2 palavras ou menos");
      private JLabel pergunta= new JLabel("pergunta:");
      private JLabel resposta= new JLabel("resposta:");
      private JLabel tema= new JLabel("tema:");
      private JLabel id= new JLabel("resumo:");
      private JButton adicionar= new JButton("adicionar");
      private JButton voltar= new JButton("voltar");
 
      //relacionas ao design
      private ImageIcon ibg = new ImageIcon("C:/Dojo/Imagens/bg3.png");
      private JLabel bg = new JLabel(ibg);
      private ImageIcon bm = new ImageIcon("C:/Dojo/Imagens/bannernovasatividades.png");
      private JLabel bannerM = new JLabel(bm);
      private ImageIcon bn = new ImageIcon("C:/Dojo/Imagens/bannerkd.png");
      private JLabel bannerK = new JLabel(bn);
      
      //relacionados a tabela
      private JScrollPane container; 
      private JScrollPane pp;
      private JScrollPane rr;           
      private JTable tabela;
      private String[][] conteudo;     
      private String colunas[]= {"pergunta","tema"};
      
      
      //relacionados ao tamanho da tela
      private int telaW;
      private int telaH;
      private int centroW;
      private int centroH;
      
      //relacionados a interatividade
      private String p,r,t,re;
      

      //classe que implementa tudo      
      public CadastroPerguntas(){
         
         super("Perguntas");
         
         //declara valor ao painel   
         painelDeConteudo= getContentPane(); 
         
            
         //tamanho e centro da tela   
         Toolkit tk = Toolkit.getDefaultToolkit();
         Dimension d = tk.getScreenSize();
         telaW= d.width;
         telaH= d.height;
         centroW = telaW/2;
         centroH = telaH/2;
         

         
         //instancia tabela
         conteudo= indicaPerguntas();
         container = criaTabela();
         
         //estilo
         pergunta.setFont(new Font("BEBAS", Font.PLAIN, 24));
         pergunta.setForeground(Color.WHITE);
         resposta.setFont(new Font("BEBAS", Font.PLAIN, 24));
         resposta.setForeground(Color.WHITE);
         tema.setFont(new Font("BEBAS", Font.PLAIN, 24));
         tema.setForeground(Color.WHITE);
         id.setFont(new Font("BEBAS", Font.PLAIN, 24));
         id.setForeground(Color.WHITE);
         
         //estilos botoes
         voltar.setFont(new Font("BEBAS", Font.PLAIN, 36));
         voltar.setForeground(Color.WHITE);
         voltar.setOpaque(false);
         voltar.setBorderPainted(false);
         voltar.setContentAreaFilled(false);
         adicionar.setFont(new Font("BEBAS", Font.PLAIN, 30));
         adicionar.setForeground(Color.WHITE);
         adicionar.setOpaque(false);
         adicionar.setBorderPainted(false);
         adicionar.setContentAreaFilled(false);

         


         //painel geral
         painelDeConteudo.setLayout(null); 
         
         
         
         pp = new JScrollPane(perguntaT);
         rr = new JScrollPane(respostaT);

         
         
         //posicionamento das coisas   
         
         pergunta.setBounds(centroW-33,centroH-300,200,100);
         pp.setBounds(centroW-300,centroH-220,600,60);
         resposta.setBounds(centroW-33,centroH-180,200,100);
         rr.setBounds(centroW-300,centroH-100,600,60);
         tema.setBounds(centroW+120,centroH-60,200,100);
         temaT.setBounds(centroW+5,centroH+20,295,19);
         id.setBounds(centroW-180,centroH-60,200,100);
         idT.setBounds(centroW-300,centroH+20,295,19);
         adicionar.setBounds(centroW-100,centroH+40,200,100);
         container.setBounds(centroW-300,centroH+130,600,200); 
         voltar.setBounds(centroW-100,centroH+360,200,100);
         bg.setBounds(0,0,telaW,telaH);
         bannerK.setBounds(-700,-400,telaW,telaH);
         bannerM.setBounds(+700,-400,telaW,telaH);
         
         //listener nos botões   
         adicionar.addActionListener(this);        
         voltar.addActionListener(this);         


               
         
         //adiciona as coisas na tela
         painelDeConteudo.add(id);
         painelDeConteudo.add(idT);
         painelDeConteudo.add(pergunta);
         painelDeConteudo.add(pp);
         painelDeConteudo.add(resposta);
         painelDeConteudo.add(rr);
         painelDeConteudo.add(tema);
         painelDeConteudo.add(temaT);
         painelDeConteudo.add(adicionar);         
         painelDeConteudo.add(container);
         painelDeConteudo.add(voltar);
         painelDeConteudo.add(bannerK);
         painelDeConteudo.add(bannerM);
         painelDeConteudo.add(bg);
            
            
         
         //arruma tamanho layout e visibilidade do frame
         setSize(telaW,telaH);
         setExtendedState(JFrame.MAXIMIZED_BOTH);//tela cheia
         setLayout(null);
         setVisible(true);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//fecha a aplicacao
      }
      
      
      

      //metodo gera matriz tabela
      public String[][] indicaPerguntas(){
         
         ListaPerguntas lista =new ListaPerguntas();
         int c= lista.contarPerguntas();
         
         String[][] dados;
         dados =  new String [c][2];
         lista.listarPerguntas(dados);
         
         return dados;
      
      }
   
   
      // cria uma tabela
      public JScrollPane criaTabela(){
         
         tabela= new JTable(conteudo,colunas);
 

         JScrollPane s = new JScrollPane(tabela);
         return s;  
      }
     


      /*//cria array da combo box
      public String[] indicaTurmas(){
      
         ListaTurmas lista =new ListaTurmas();
         int c= lista.contarTurma();
      
         String[] dados;
         dados =  new String [c];
         lista.listarTurma(dados);
         
         return dados;
      
      }*/
      
      
      
      //funcão dos botões
      public void actionPerformed(ActionEvent c){
         if(c.getSource() == adicionar){
            
            t=temaT.getText();
            p=perguntaT.getText();
            r=respostaT.getText();
            re=idT.getText();
            
            Pergunta perg= new Pergunta("","","","");
            perg.setPergunta(p);
            perg.setResposta(r);
            perg.setTema(t);
            perg.setId(re);
            
            ListaPerguntas adicionando= new ListaPerguntas();
            adicionando.addPergunta(perg);
            
            
            
             
            remove(container);
            remove(bg);
            conteudo= indicaPerguntas();            
            container = criaTabela();                  
            container.setBounds(centroW-300,centroH+130,600,200);
            bg.setBounds(0,0,telaW,telaH);
            add(container);
            add(bg);             
            repaint(); 
            
            perguntaT.setText("");
            respostaT.setText("");
            temaT.setText("");
            idT.setText("");
            
         }else if(c.getSource() == voltar){
            new TelaAtividades();
            dispose();
            System.out.println("Volta para a Tela de Atividades");
         }
      }
      
      
      
      
      //quando a tabela é selecionada
      

      
      /*/quando muda o item da combo box
      public void itemStateChanged(ItemEvent e){
         if(e.getStateChange() == ItemEvent.SELECTED){
          
            alunos = (String) filtro.getSelectedItem();
            conteudo= indicaAlunos();
            remove(container);
            container = criaTabela();
            add(container);
            container.setBounds(centroW-300,centroH,600,200); 
            repaint();
              
         }
      }*/   
   

   //faz a tela aparecer
   public static void main (String [] args){
      SwingUtilities.invokeLater (new Runnable (){
         public void run (){
            new CadastroPerguntas();
         }
      });
   }
   

}