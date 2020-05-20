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
import javax.swing.JOptionPane;
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
import java.util.ArrayList;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.text.ParseException;


public class AulaDojo extends JFrame implements ActionListener, ItemListener{

      
      private Container tela;
      private JLabel data= new JLabel("DATA");
      private JLabel tema = new JLabel("TEMA");
      private JLabel atividade = new JLabel("ATIVIDADE");
      private JButton voltar =new JButton("VOLTAR");
      private JButton conferirP= new JButton("CONFERIR");
      private JButton escolherA= new JButton("CHAMADA"); 
      private JButton atribuirP = new JButton("ATRIBUIR");
      private JButton atribuirC = new JButton("ATRIBUIR");
      private JTextField copiloto= new JTextField("Copiloto");
      private JTextField piloto= new JTextField("Piloto");
      private JTextArea apresentaP= new JTextArea("");//
      private JTextField notaP= new JTextField("nota");
      private JTextField notaC= new JTextField("nota");
      private JTextField ano= new JTextField("2020");
      private JComboBox temas;
      private JComboBox perguntas;
      private JComboBox dd;
      private JComboBox mm;        
      private JComboBox presenca;
      private JComboBox presenca1;
      
      private JComboBox filtro;
      private String[] vet;  
      private String alunos="ECP3BN";
      
      private JScrollPane rolagem;        
      
      private ImageIcon ibg = new ImageIcon("../Imagens/bg3.png");
      private JLabel bg = new JLabel(ibg);
      private ImageIcon bm = new ImageIcon("../Imagens/bannerdojo.png");
      private JLabel bannerM = new JLabel(bm);
      private ImageIcon bn = new ImageIcon("../Imagens/bannerkd.png");
      private JLabel bannerK = new JLabel(bn);
      private ImageIcon ivoltar = new ImageIcon("../Imagens/voltar1.png");
      private JLabel lbvoltar = new JLabel(ivoltar);
      private ImageIcon iconferir = new ImageIcon("../Imagens/checar1.png");
      private JLabel lbconferir = new JLabel(iconferir);
      private ImageIcon isort = new ImageIcon("../Imagens/sorteio.png");
      private JLabel sorteio = new JLabel(isort);
      private ImageIcon iind1 = new ImageIcon("../Imagens/chamada.png");
      private JLabel ind1 = new JLabel(iind1);

      
      private String[][] estudantes;
      private ArrayList<Aluno> sorteados;
      
      private String day;
      
      
      
      
      // private String[] tema;
      //private String[] pergunta;
      private String falta[]={"Presente","Faltou"};
      private String dias[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
      private String meses[]={"01","02","03","04","05","06","07","08","09","10","11","12"};
      
      private String[] contT;
      private String[] contP={"0","0","0"};
      private String listaT;
      private String listaP; 
      private String listaF; 
      
      private JScrollPane painel;
      private ListaNotas atribuindo= new ListaNotas(); 
      private Pilha p; 
      private ListaAlunos list = new ListaAlunos();
      private Aluno pi= new Aluno("","","","");
      private Aluno co= new Aluno("","","","");      
      
      private int telaW;
      private int telaH;
      private int centroW;
      private int centroH;
      
      
      
      public AulaDojo(){
            
            
            Toolkit tk = Toolkit.getDefaultToolkit();
            Dimension d = tk.getScreenSize();
            telaW= d.width;
            telaH= d.height;
            centroW = telaW/2;
            centroH = telaH/2;
            
            vet = indicaTurmas();   
            filtro = new JComboBox(vet);
            

            dd= new JComboBox(dias);
            mm= new JComboBox(meses);        
            presenca= new JComboBox(falta);
            presenca1= new JComboBox(falta);
            
            
            indicaTemas();
            temas= new JComboBox(contT);
            listaT = (String) temas.getSelectedItem();
                       
            indicaPergunta();
            perguntas= new JComboBox(contP);
            listaP = (String) perguntas.getSelectedItem(); 
           
            //listaP = (String) perguntas.getSelectedItem(); 
            
            conferirP.addActionListener(this);
            escolherA.addActionListener(this);
            atribuirP.addActionListener(this);
            atribuirC.addActionListener(this);
            voltar.addActionListener(this);
          
          
            temas.addItemListener(this);
            //filtro.addItemListener(this);
         
            
            
            tela= getContentPane();
            tela.setLayout(null);
            
            apresentaP.setLineWrap(true);
            
            painel = new JScrollPane(apresentaP);
            
            
            data.setBounds(centroW+180,centroH-280,40,25);
            tema.setBounds(centroW-230,centroH-280,40,25);
            atividade.setBounds(centroW-30,centroH-280,100,25);
            dd.setBounds(centroW+110,centroH-250,66,24);
            mm.setBounds(centroW+176,centroH-250,66,24);
            ano.setBounds(centroW+242,centroH-250,67,25);
            temas.setBounds(centroW-310,centroH-250,200,24);
            perguntas.setBounds(centroW-100,centroH-250,200,24);
            conferirP.setBounds(centroW-100,centroH-200,200,25);
            lbconferir.setBounds(centroW-150,centroH-201,200,30);
            painel.setBounds(centroW-telaW/4,centroH-150,telaW/2,100);
            escolherA.setBounds(centroW-100,centroH,200,25);
            ind1.setBounds(centroW-150,centroH,200,25);
            piloto.setBounds(centroW-310,centroH+50,200,25);
            presenca1.setBounds(centroW+50,centroH+50,100,24);
            atribuirP.setBounds(centroW+110,centroH+50,200,25);
            notaP.setBounds(centroW-80, centroH+50,100,25);
            copiloto.setBounds(centroW-310,centroH+85,200,25);
            presenca.setBounds(centroW+50,centroH+85,100,24);
            notaC.setBounds(centroW-80, centroH+85,100,25);
            atribuirC.setBounds(centroW+110,centroH+85,200,25);
            voltar.setBounds(centroW-100,centroH+165,200,30);
            lbvoltar.setBounds(centroW-200,centroH+165,200,30);
            filtro.setBounds(centroW-100,centroH-45,200,20);
            bg.setBounds(0,0,telaW,telaH);
            bannerK.setBounds(centroW-900,centroH-500,700,300);
            bannerM.setBounds(centroW+280,centroH-500,700,300);
            
            
            //estilo botao
            voltar.setFont(new Font("AlternateGothic2 BT", Font.PLAIN, 36));
            voltar.setForeground(Color.WHITE);
            voltar.setOpaque(false);
            voltar.setBorderPainted(false);
            voltar.setContentAreaFilled(false);
            conferirP.setFont(new Font("AlternateGothic2 BT", Font.PLAIN, 24));
            conferirP.setForeground(Color.WHITE);
            conferirP.setOpaque(false);
            conferirP.setBorderPainted(false);
            conferirP.setContentAreaFilled(false);
            escolherA.setFont(new Font("AlternateGothic2 BT", Font.PLAIN, 24));
            escolherA.setForeground(Color.WHITE);
            escolherA.setOpaque(false);
            escolherA.setBorderPainted(false);
            escolherA.setContentAreaFilled(false);
            atribuirP.setFont(new Font("AlternateGothic2 BT", Font.PLAIN, 24));
            atribuirP.setForeground(Color.WHITE);
            atribuirP.setOpaque(false);
            atribuirP.setBorderPainted(false);
            atribuirP.setContentAreaFilled(false);
            atribuirC.setFont(new Font("AlternateGothic2 BT", Font.PLAIN, 24));
            atribuirC.setForeground(Color.WHITE);
            atribuirC.setOpaque(false);
            atribuirC.setBorderPainted(false);
            atribuirC.setContentAreaFilled(false);
            
            //estilo
            data.setFont(new Font("AlternateGothic2 BT", Font.PLAIN, 20));
            data.setForeground(Color.WHITE);
            tema.setFont(new Font("AlternateGothic2 BT", Font.PLAIN, 20));
            tema.setForeground(Color.WHITE);
            atividade.setFont(new Font("AlternateGothic2 BT", Font.PLAIN, 20));
            atividade.setForeground(Color.WHITE);
            
            tela.add(filtro);
            tela.add(temas);
            add(perguntas);
            tela.add(dd);
            tela.add(mm);
            tela.add(ano);
            tela.add(data);
            tela.add(tema);
            tela.add(atividade);
            tela.add(conferirP);
            tela.add(lbconferir);
            tela.add(painel);
            tela.add(escolherA);
            tela.add(ind1);
            tela.add(presenca);
            tela.add(presenca1);
            tela.add(piloto);
            tela.add(copiloto);
            tela.add(atribuirP);
            tela.add(atribuirC);
            tela.add(notaP);
            tela.add(notaC);
            tela.add(voltar);
            tela.add(lbvoltar);
            setSize(telaW,telaH);
            tela.add(bannerK);
            tela.add(bannerM);
            tela.add(bg);
            
            setExtendedState(JFrame.MAXIMIZED_BOTH);//tela cheia
            setLayout(null);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//fecha a aplicacao

            
      }
      
      
      public void indicaTemas(){
         
         
         ListaPerguntas tem= new ListaPerguntas();
         int n= tem.contarId();
         contT= new String[n];
         tem.listarId(contT);
        
      
      }
      
      public void actionPerformed(ActionEvent f){
         int verific=0;
         boolean veri= true ;
         try{
           verific = Integer.parseInt(ano.getText());
         }catch(Exception e){
             veri = false;
         }
                  
         if(veri==true && verific>=2020){   
            if(f.getSource() == conferirP){
               
               listaP =""+perguntas.getSelectedItem();
               
               ListaPerguntas tem= new ListaPerguntas();
               Pergunta perg= new Pergunta("","","","");
               tem.getPerguntas(perg,listaT,listaP);
               
               
               apresentaP.setText(""+perg.getPergunta());
              
               
            }
            if(f.getSource() == escolherA){
               listaF = (String) filtro.getSelectedItem();
               String a =escolherA.getText();
               if(a.compareTo("CHAMADA")==0){
                  day=(dd.getSelectedItem() + "/" + mm.getSelectedItem() + "/" + ano.getText());
                  int w= list.contarAlunos(listaF);
                  sorteados= new ArrayList<Aluno>();
                  estudantes= new String[w][2];
                  list.listarAlunos(listaF, estudantes);
                  for(int i=0;w>i;i++){
                     String c = JOptionPane.showInputDialog(estudantes[i][0]+" estudante de RA: "+estudantes[i][1]+"\n está presente? (digite 'F' para falta ou 'P' para presente)"); 
                     if(c.compareToIgnoreCase("F")== 0){
                        Nota nn= new Nota(0,day,"F");
                        atribuindo.addNota(nn,estudantes[i][1]);
                        
                     }else if(c.compareToIgnoreCase("P")== 0){
                        Aluno x= new Aluno("","","","");
                        x.setNomeA(estudantes[i][0]);
                        x.setMatriculaA(estudantes[i][1]);
                        sorteados.add(x);
                     }
                  }
                  remove(ind1);
                  remove(bg);
                  sorteio.setBounds(centroW-150,centroH,200,25);
                  bg.setBounds(0,0,telaW,telaH);
                  add(sorteio);
                  add(bg);
                  escolherA.setText("SORTEIO");
                  p= list.listaSorteio(sorteados);
               }else{
                  day=(dd.getSelectedItem() + "/" + mm.getSelectedItem() + "/" + ano.getText());
                  String ra,ra1;
                  if(p.isEmpty() == false){
                     ra=p.pop();
                     pi= list.buscarAluno(sorteados, ra);
                     piloto.setText(pi.getNomeA());
                     if(p.isEmpty() == false){
                        ra1=p.pop();
                        co= list.buscarAluno(sorteados, ra1);
                        copiloto.setText(co.getNomeA());
                     }
                  }else{
                     JOptionPane.showMessageDialog(null,"Todos os Alunos já participaram");
                  }
                  
                  
               }
               
            }
            if(f.getSource() == atribuirP){
               double verificP=0;
               boolean veriP= true ;
               try{
                 verificP = Double.parseDouble(notaP.getText());
               }catch(Exception e){
                   veriP = false;
               }
               if(veriP== true && verificP<=10 && verificP>=0 ){
                  double j= Double.parseDouble(notaP.getText());
                  String pp= (String) presenca.getSelectedItem();
                  if(pp.compareTo("Presente")==0){
                     pp="P";
                  }else{
                     pp="F";
                  }
                  Nota nn = new Nota(j,day,pp);
                  String rr = pi.getMatriculaA();
                  atribuindo.addNota(nn,rr);
               }else{
                  JOptionPane.showMessageDialog(null,"Ajuste a nota");
               }   
            }
            if(f.getSource() == atribuirC){
               double verificC=0;
               boolean veriC= true ;
               try{
                 verificC = Double.parseDouble(notaC.getText());
               }catch(Exception e){
                   veriC = false;
               }
               if(veriC== true && verificC<=10 && verificC>=0 ){
               
                  double j= Double.parseDouble(notaC.getText());
                  String pp= (String) presenca1.getSelectedItem();
                  if(pp.compareTo("Presente")==0){
                     pp="P";
                  }else{
                     pp="F";
                  }
                  Nota nn = new Nota(j,day,pp);
                  String rr = co.getMatriculaA();
                  atribuindo.addNota(nn,rr); 
               }else{
                  JOptionPane.showMessageDialog(null,"Ajuste a nota");
               }             
            }  
            if(f.getSource() == voltar){
               //encerra a aula de dojo caso não de tempo de mais alunos responderem
               new TelaMenu();
               dispose(); 
               if(p.isEmpty()== false){
                  String pp="P";
                  double j=10;
                  do{
                     String ra=p.pop();
                     Nota nn= new Nota(j,day,pp);
                     atribuindo.addNota(nn,ra);
                  }while(p.isEmpty()== false);
   
               }
                new TelaMenu();
                dispose(); 
            }
         }else{
            JOptionPane.showMessageDialog(null ,"ajuste a data primeiro");
         }
      }


      public void indicaPergunta(){
         
         ListaPerguntas tem= new ListaPerguntas();
         int n= tem.contarPerguntasTema(listaT);
         contP= new String[n];
         tem.listarPerguntasTema(contP,listaT);
      
      }
      
      public void itemStateChanged(ItemEvent e){
         if(e.getStateChange() == ItemEvent.SELECTED){
            
            remove(perguntas);
            remove(bg);
            listaT = (String) temas.getSelectedItem();
            indicaPergunta();
            perguntas= new JComboBox(contP);
            perguntas.setBounds(centroW-100,centroH-250,200,24);
            listaP = (String) perguntas.getSelectedItem(); 
            add(perguntas);
            bg.setBounds(0,0,telaW,telaH);
            add(bg);
            validate();
            repaint();

         }
      }
      
      
      public String[] indicaTurmas(){
      
         ListaTurmas lista =new ListaTurmas();
         int c= lista.contarTurma();
      
         String[] dados;
         dados =  new String [c];
         lista.listarTurma(dados);
         
         return dados;
      
      }
      
      
      
      //faz a tela aparecer
      public static void main (String [] args){
         SwingUtilities.invokeLater (new Runnable (){
            public void run (){
               new AulaDojo();
            }
         });
      }
      
      
      
      
      
      
      


}
 