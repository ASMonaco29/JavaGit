package tests;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import cda.Question;

@SuppressWarnings("serial")
public class WindowCreerQuestionaire extends JDialog implements ActionListener {
  
  // Attributs à ajouter :
  private String titre;
  private String stitre;
  private String msgfin;
  private Date dateD;
  private Date dateF;
  private JTable tableauQn;
  private ModelTableauQn modeleQn;
  private JButton bAnnulerQn;
  private JButton bCreerQna;
  private JButton bCreerQn;
  private JButton bSupprQn;
  private JLabel lTitreQn; 
  private JLabel lStitreQn; 
  private JLabel lDatedQn; 
  private JLabel lDatefQn; 
  private JLabel lMesgQn; 
  private JLabel lTitreQn1; 
  private JLabel lStitreQn1; 
  private JLabel lDatedQn1; 
  private JLabel lDatefQn1; 
  private JLabel lMesgQn1; 
  private JLabel lquestion;
  private JLabel tricheQna;
  private JLabel tricheQnb;
  private JLabel tricheQnc;
  private DateFormat dateFormat;
  private JPanel pWestQst;
  private JPanel pEastQst;
  private JPanel pGlobBtn;
  private JPanel panelQnN;
  private JPanel panelQnN1;
  private JPanel panelQnN2 = null;
  private JPanel panelQnN3;
  private JPanel pFinalQn;
  private ModelTableauQa modeleQa;
  private ArrayList<Question> questions;
  
  
  // CONSTRUCTEUR :
  public WindowCreerQuestionaire(Component compo, ModelTableauQa modeleQ, String titre, String stitre, String msgfin,
      Date dateD, Date dateF){
  
    super((Frame) compo, "Questions", true);
    
    // Initilisation : 
    dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
    this.titre = titre;
    this.stitre = stitre;
    this.msgfin = msgfin;
    this.dateD = dateD;
    this.dateF = dateF;
    this.modeleQa = modeleQ;
    modeleQn = new ModelTableauQn();
    tableauQn = new JTable(modeleQn);
    bAnnulerQn = new JButton("Annuler");
    bAnnulerQn.addActionListener(this);
    bCreerQna = new JButton("Créer questionnaire");
    bCreerQna.addActionListener(this);
    bCreerQn = new JButton("Créer une nouvelle question");
    bCreerQn.addActionListener(this);
    bSupprQn = new JButton("Supprimer question");
    bSupprQn.addActionListener(this);
    bSupprQn.setEnabled(false);
    lTitreQn = new JLabel("Titre : "); 
    lStitreQn = new JLabel("Sous-titre : "); 
    lDatedQn = new JLabel("Date début : "); 
    lDatefQn = new JLabel("Date fin : "); 
    lMesgQn = new JLabel("Message de fin : "); 
    lquestion = new JLabel("Veuillez sélectionner vos questions pour ce questionnaire : "); 
    tricheQna = new JLabel("     ");
    tricheQnb = new JLabel("     ");
    tricheQnc = new JLabel("     ");
    lTitreQn1 = new JLabel(this.titre); 
    lStitreQn1 = new JLabel(this.stitre); 
  
    lMesgQn1 = new JLabel(this.msgfin); 
    panelQnN = new JPanel(new FlowLayout());
    panelQnN.add(lTitreQn);
    panelQnN.add(lTitreQn1);
    panelQnN.add(lStitreQn);
    panelQnN.add(lStitreQn1);
    if(this.dateD != null && this.dateD != null){
      lDatedQn1 = new JLabel(dateFormat.format(this.dateD)); 
      lDatefQn1 = new JLabel(dateFormat.format(this.dateF)); 
    
      panelQnN2 = new JPanel(new FlowLayout());
      panelQnN2.add(lDatedQn);
      panelQnN2.add(lDatedQn1);
      panelQnN2.add(lDatefQn);
      panelQnN2.add(lDatefQn1);
    }
    panelQnN.add(lMesgQn);
    panelQnN.add(lMesgQn1);
    
    panelQnN1 = new JPanel();
    panelQnN1.setLayout(new BoxLayout(panelQnN1, BoxLayout.Y_AXIS));
    panelQnN1.add(panelQnN);
    panelQnN1.add(Box.createRigidArea(new Dimension(0,5)));
    if(this.dateD != null && this.dateD != null){
      panelQnN1.add(panelQnN2);
      panelQnN1.add(Box.createRigidArea(new Dimension(0,5)));
    }
    panelQnN3 = new JPanel();
    panelQnN3.add(lquestion);
    panelQnN1.add(panelQnN3);
    panelQnN1.add(Box.createRigidArea(new Dimension(0,10)));
   
    
    
    // Tests sur les attributs :
    
    
    // Nouvelle fenêtre (questions) :
    pFinalQn = new JPanel(new BorderLayout());
    pFinalQn.setSize(670, 540);
    pFinalQn.setVisible(true);
    pFinalQn.setLayout(new BorderLayout());
    
    tableauQn.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    tableauQn.setRowHeight(30);
    tableauQn.getTableHeader().setReorderingAllowed(false);
    tableauQn.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
          jTableMouseClicked(evt);
      }
    });  
    
    
    
    pWestQst = new JPanel();
    pWestQst.setLayout(new BorderLayout());
    pWestQst.add(bCreerQn, BorderLayout.WEST);
    pWestQst.add(tricheQna, BorderLayout.CENTER);
    pWestQst.add(bSupprQn, BorderLayout.EAST);
    pEastQst = new JPanel();
    pEastQst.setLayout(new BorderLayout());
    pEastQst.add(bCreerQna, BorderLayout.WEST);
    pEastQst.add(tricheQnb, BorderLayout.CENTER);
    pEastQst.add(bAnnulerQn, BorderLayout.EAST);
    pGlobBtn = new JPanel();
    pGlobBtn.setLayout(new BorderLayout());
    pGlobBtn.add(pWestQst, BorderLayout.WEST);
    pGlobBtn.add(tricheQnc, BorderLayout.CENTER);
    pGlobBtn.add(pEastQst, BorderLayout.EAST);
    
    pFinalQn.add(panelQnN1, BorderLayout.NORTH);
    pFinalQn.add(new JScrollPane(tableauQn), BorderLayout.CENTER); 
    pFinalQn.add(pGlobBtn, BorderLayout.SOUTH);
    
    ImageIcon img = new ImageIcon("logo-sportif.jpg");
    this.setIconImage(img.getImage());
    
    
    this.getContentPane().add(pFinalQn);
    this.pack();
    this.setLocationRelativeTo(this);
    this.setMinimumSize(new Dimension(630, 500));
    this.setVisible(true);
  
    
    
    
  }


  protected void jTableMouseClicked(MouseEvent evt) {
    // get the selected row index
    int modelRow = tableauQn.getSelectedRow();
    if(modelRow != -1){          
      this.bSupprQn.setEnabled(true);
    } else {
      this.bSupprQn.setEnabled(false);
    }    
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    
    /**************************** QUITTER ********************************/
    
    if(source == bAnnulerQn){
      this.setVisible(false);
      this.dispose();
    }
    
    /**************************** CREER QUESTIONNAIRE ********************************/
    
    else if(source == bCreerQna){
      this.questions = new ArrayList<Question>();
      
      for(int i = 0; i < modeleQn.getRowCount(); i++){
        if((Boolean)modeleQn.getValueAt(i, 0) == true){
          questions.add(new Question((String)modeleQn.getValueAt(i, 1), (Boolean)modeleQn.getValueAt(i, 2)));
        }
      }
      
      // Ajout du questionnaire :
      this.modeleQa.addQuestionnaire(new cda.Questionnaire(titre, stitre, dateD, 
          dateF, msgfin, this.questions));
    
      this.setVisible(false);
      this.dispose();
    }
    
    /**************************** SUPPRIMER QUESTION ********************************/
    
    else if(source == bSupprQn){
        
      int[] selection = tableauQn.getSelectedRows();
      
      String messageSupQn = "Etes-vous sur de vouloir supprimer cette question définitivement ?";
      int replyQn = JOptionPane.showConfirmDialog(null, messageSupQn, "Confirmation de la suppression",
          JOptionPane.YES_NO_OPTION);
      
      if (replyQn == JOptionPane.YES_OPTION) {
        for(int i = selection.length - 1; i >= 0; i--){
            modeleQn.removeQuestion(selection[i]);
        }
      }
        
    }
    
    /**************************** CREER QUESTION ********************************/
    
    else if(source == bCreerQn){
      String titreDialogQn = "Ajout d'une question";
      String messageDialogQn = "Veuillez taper l'intitulé de la question :     ";
      String newQn = "";
      
      
      newQn = JOptionPane.showInputDialog(
          this, 
          messageDialogQn, 
          titreDialogQn, 
          JOptionPane.INFORMATION_MESSAGE
      );

      if ((newQn != null) && (newQn.length() > 0)) {
        modeleQn.addQuestion(new Question(newQn, true));
        
      } 

    }
    
  }
}