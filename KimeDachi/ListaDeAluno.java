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
public class ListaDeAluno extends JFrame implements ActionListener, ItemListener, ListSelectionListener{


      //variaveis privadas 
      private Container painelDeConteudo;  
      private JLabel filtroA = new JLabel("Turma:");   
      private JLabel nomeA= new JLabel("Nome:");
      private JTextField nome= new JTextField("");
      private JTextField data= new JTextField("");
      private JTextField presenca= new JTextField("");
      private JTextField nota= new JTextField("");
      private JLabel dataA= new JLabel("Data:");
      private JLabel presencaA= new JLabel("Presenca:");
      private JLabel notaA= new JLabel("Nota:");
      private JButton alterar= new JButton("alterar");
      private JButton voltar= new JButton("voltar");
      private JButton conferir= new JButton("conferir");
      
      //relacionas ao design
      private ImageIcon ibg = new ImageIcon("../Imagens/bg3.png");
      private JLabel bg = new JLabel(ibg);
      private ImageIcon bm = new ImageIcon("../Imagens/bannerlista.png");
      private JLabel bannerM = new JLabel(bm);
      private ImageIcon bn = new ImageIcon("../Imagens/bannerkd.png");
      private JLabel bannerK = new JLabel(bn);
      private ImageIcon ivoltar = new ImageIcon("../Imagens/voltar1.png");
      private JLabel lbvoltar = new JLabel(ivoltar);
      private ImageIcon ialter = new ImageIcon("../Imagens/alterar1.png");
      private JLabel alter = new JLabel(ialter);
      private ImageIcon iconferir = new ImageIcon("../Imagens/checar1.png");
      private JLabel lbconferir = new JLabel(iconferir);
      
      //relacionados a tabela
      private JScrollPane container;            
      private JTable tabela;
      private JTable tabela1; 
      private JScrollPane container1;
      private String[][] conteudo;
      private String[][] conteudo1;      
      private String colunas[]= {"nome","RA"};
      private String colunasA[]= {"nota","data","presença"};

      //relacionados a combo box
      private JComboBox filtro;
      private String[] vet;  
      private String alunos="ECP3BN";
      
      //relacionados ao tamanho da tela
      private int telaW;
      private int telaH;
      private int centroW;
      private int centroH;
      
      //relacionados a interatividade
      private ListSelectionModel listSelectionModel;  
      private String ra;
      private String n,d,p;


      //classe que implementa tudo      
      public ListaDeAluno(){
         
         super("Alunos e notas");
         
         //declara valor ao painel   
         painelDeConteudo= getContentPane(); 
         
            
         //tamanho e centro da tela   
         Toolkit tk = Toolkit.getDefaultToolkit();
         Dimension d = tk.getScreenSize();
         telaW= d.width;
         telaH= d.height;
         //telaW = 1600;
         //telaH = 900;
         centroW = telaW/2;
         centroH = telaH/2;
         
         //estilo
         filtroA.setFont(new Font("BEBAS", Font.PLAIN, 24));
         filtroA.setForeground(Color.WHITE);
         nomeA.setFont(new Font("BEBAS", Font.PLAIN, 24));
         nomeA.setForeground(Color.WHITE);
         dataA.setFont(new Font("BEBAS", Font.PLAIN, 24));
         dataA.setForeground(Color.WHITE);
         presencaA.setFont(new Font("BEBAS", Font.PLAIN, 24));
         presencaA.setForeground(Color.WHITE);
         notaA.setFont(new Font("BEBAS", Font.PLAIN, 24));
         notaA.setForeground(Color.WHITE);
         
         //estilo botao
         voltar.setFont(new Font("BEBAS", Font.PLAIN, 36));
         voltar.setForeground(Color.WHITE);
         voltar.setOpaque(false);
         voltar.setBorderPainted(false);
         voltar.setContentAreaFilled(false);
         conferir.setFont(new Font("BEBAS", Font.PLAIN, 24));
         conferir.setForeground(Color.WHITE);
         conferir.setOpaque(false);
         conferir.setBorderPainted(false);
         conferir.setContentAreaFilled(false);
         alterar.setFont(new Font("BEBAS", Font.PLAIN, 24));
         alterar.setForeground(Color.WHITE);
         alterar.setOpaque(false);
         alterar.setBorderPainted(false);
         alterar.setContentAreaFilled(false);

         //coloca os itens na combo box        
         vet = indicaTurmas();   
         filtro = new JComboBox(vet);
            
         //seleciona oq ta dentro da combo box
         alunos = (String) filtro.getSelectedItem();

         
         //instancia tabela
         conteudo= indicaAlunos();
         container = criaTabela();
         

         //instancia outra tabela   
         conteudo1= tamanhoNotas();
         container1 = criaTabela1();

         
         //painel geral
         painelDeConteudo.setLayout(null); 
         
         
         //posicionamento das coisas   
         filtroA.setBounds(centroW-20,centroH-355,200,100);
         filtro.setBounds(centroW-150,centroH-285,300,19);
         nomeA.setBounds(centroW-20,centroH-290,200,100);
         nome.setBounds(centroW-150,centroH-220,300,20);
         dataA.setBounds(centroW-17,centroH-225,200,100);
         data.setBounds(centroW-150,centroH-155,300,20);
         presencaA.setBounds(centroW-30,centroH-165,200,100);
         presenca.setBounds(centroW-150,centroH-90,300,20);
         notaA.setBounds(centroW-17,centroH-95,200,100);
         nota.setBounds(centroW-150,centroH-25,300,20);
         conferir.setBounds(centroW-150,centroH+30,150,20);
         lbconferir.setBounds(centroW-205,centroH+30,150,20);
         alterar.setBounds(centroW,centroH+30,150,20);
         alter.setBounds(centroW+50,centroH+30,150,20);
         container.setBounds(centroW-310,centroH+100,300,200); 
         container1.setBounds(centroW+10,centroH+100,300,200);
         voltar.setBounds(centroW-150,centroH+330,300,100);
         lbvoltar.setBounds(centroW-250,centroH+330,300,100);
         bg.setBounds(0,0,telaW,telaH);
         bannerK.setBounds(centroW-900,centroH-500,700,300);
         bannerM.setBounds(centroW+200,centroH-500,700,300);
         
         
         //listener nos botões   
         alterar.addActionListener(this);        
         voltar.addActionListener(this);         
         conferir.addActionListener(this);
         
         //listener da combo box   
         filtro.addItemListener(this);
        
         //adiciona as coisas na tela 
         painelDeConteudo.add(filtroA);
         painelDeConteudo.add(filtro);
         painelDeConteudo.add(nomeA);
         painelDeConteudo.add(nome);
         painelDeConteudo.add(dataA);
         painelDeConteudo.add(data);
         painelDeConteudo.add(notaA);
         painelDeConteudo.add(nota);
         painelDeConteudo.add(presencaA);
         painelDeConteudo.add(presenca);
         painelDeConteudo.add(alterar);
         painelDeConteudo.add(alter);         
         painelDeConteudo.add(conferir);
         painelDeConteudo.add(lbconferir);
         painelDeConteudo.add(container);
         painelDeConteudo.add(container1);
         painelDeConteudo.add(voltar);
         painelDeConteudo.add(lbvoltar);
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
      public String[][] indicaAlunos(){
         
         ListaAlunos lista = new ListaAlunos();
         int c= lista.contarAlunos(alunos);
         
         String[][] dados;
         dados =  new String [c][2];
         lista.listarAlunos(alunos,dados);
         
         return dados;
      
      }

      
      
      //gera a matriz da segunda tabela
      public String[][] tamanhoNotas(){
         
         ListaNotas lista =new ListaNotas();
         int c= lista.contarNotas(ra);
         String[][]tam;

         tam =  new String [c][3];
         lista.listarNotas(ra,tam);
         
         return tam;
      
      }
   
   
      // cria uma tabela
      public JScrollPane criaTabela(){
         
         tabela= new JTable(conteudo,colunas);
         tabela.setCellSelectionEnabled(true); 
         listSelectionModel = tabela.getSelectionModel();
         listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         listSelectionModel.addListSelectionListener(this);
         tabela.setSelectionModel(listSelectionModel);

         JScrollPane s = new JScrollPane(tabela);
         return s;  
      }
      
      
      
      //cria outra tabela
      public JScrollPane criaTabela1(){
      
         tabela1 = new JTable(conteudo1, colunasA);
         
         listSelectionModel = tabela1.getSelectionModel();
         listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         listSelectionModel.addListSelectionListener(this);
         tabela1.setSelectionModel(listSelectionModel);


         JScrollPane s = new JScrollPane(tabela1);
         return s;
      }


      //cria array da combo box
      public String[] indicaTurmas(){
      
         ListaTurmas lista =new ListaTurmas();
         int c= lista.contarTurma();
      
         String[] dados;
         dados =  new String [c];
         lista.listarTurma(dados);
         
         return dados;
      
      }
      
      
      
      //funcão dos botões
      public void actionPerformed(ActionEvent c){
         if(c.getSource() == alterar){
            
            Nota notaO= new Nota(0,"","");
            Nota notaN= new Nota(0,"","");
            
            notaO.setValor(Double.parseDouble(n));
            notaO.setData(d);
            notaO.setPresenca(p);
            
            double nN= Double.parseDouble(nota.getText());
            String dN= data.getText();
            String pN= presenca.getText();         
            
            notaN.setValor(nN); 
            notaN.setData(dN);
            notaN.setPresenca(pN);
            
            ListaNotas alterando= new ListaNotas();
            alterando.alterarNota(notaO,ra,notaN);
         
         
            remove(container1);
            remove(bg);
            conteudo1= tamanhoNotas();            
            container1 = criaTabela1();                     
            container1.setBounds(centroW+10,centroH+100,300,200);
            bg.setBounds(0,0,telaW,telaH);
            add(container1);
            add(bg);            
            repaint();  
            
         }else if(c.getSource() == conferir){            
            remove(container1);
            remove(bg);
            conteudo1= tamanhoNotas();            
            container1 = criaTabela1();                      
            container1.setBounds(centroW+10,centroH+100,300,200);
            bg.setBounds(0,0,telaW,telaH);
            add(container1);    
            add(bg);        
            repaint();
         }else if(c.getSource() == voltar){
            new TelaAlunos();
            dispose();
            System.out.println("Voltar para Alunos");
         }
      }
      
      
      
      
      //quando a tabela é selecionada
      public void valueChanged(ListSelectionEvent eve){
            if(eve.getValueIsAdjusting()){
         
                  String s = null;

                  s = ""+ tabela.getValueAt(tabela.getSelectedRow(),0);
                  ra = ""+ tabela.getValueAt(tabela.getSelectedRow(),1);
                  nome.setText(s);  
            
                  n= ""+ tabela1.getValueAt(tabela1.getSelectedRow(),0);
                  d= ""+ tabela1.getValueAt(tabela1.getSelectedRow(),1);
                  p= ""+ tabela1.getValueAt(tabela1.getSelectedRow(),2);
            
                  data.setText(d);
                  nota.setText(n);
                  presenca.setText(p);

            }
      }
    

      
      //quando muda o item da combo box
      public void itemStateChanged(ItemEvent e){
         if(e.getStateChange() == ItemEvent.SELECTED){
          
            alunos = (String) filtro.getSelectedItem();
            conteudo= indicaAlunos();
            remove(container);
            remove(bg);
            container = criaTabela();
            container.setBounds(centroW-310,centroH+100,300,200);
            add(container);
            bg.setBounds(0,0,telaW,telaH);
            add(bg);
            repaint();
              
         }
      }
   
   

   //faz a tela aparecer
   public static void main (String [] args){
      SwingUtilities.invokeLater (new Runnable (){
         public void run (){
            new ListaDeAluno();
         }
      });
   }
   

}