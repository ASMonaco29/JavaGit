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
import javax.swing.table.TableRowSorter;


@SuppressWarnings("serial")
public class WindowListeQuestionaireSp extends JDialog implements ActionListener {
  
  // Attributs à ajouter :
  private String sportif;
  private JTable tableauQn;
  private TableRowSorter<ModelTableauQaSp> sorter;
  private JButton bAnnulerQn;
  private JButton bDetailQna;
  private JButton bCreerQn;
  private JButton bSupprQn;
  private JLabel lIntro; 
  private JLabel lSportif; 
  private JLabel tricheQna;
  private JLabel tricheQnb;
  private JLabel tricheQnc;
  private JPanel pWestQst;
  private JPanel pEastQst;
  private JPanel pGlobBtn;
  private JPanel panelQnN;
  private JPanel panelQnN1;
  private JPanel panelQnN3;
  private JPanel pFinalQn;
  private Component wind;
  private ModelTableauQaSp modeleQaSp;
  
  
  // CONSTRUCTEUR :
  public WindowListeQuestionaireSp(Component compo, ModelTableauQaSp modeleQs, String PseudoSp){
  
    super((Frame) compo, "Questionnaires attribués", true);
    // Initilisation : 
    //this.selectedRowQa = selectedRowQa;
    this.sportif = PseudoSp;
    this.modeleQaSp = modeleQs;
    this.wind = compo;
    tableauQn = new JTable(modeleQaSp);
    bAnnulerQn = new JButton("Retour");
    bAnnulerQn.addActionListener(this);
    bDetailQna = new JButton("Détail des réponses");
    bDetailQna.addActionListener(this);
    bDetailQna.setEnabled(false);
    bCreerQn = new JButton("Ajouter réponses");
    bCreerQn.addActionListener(this);
    bSupprQn = new JButton("Supprimer réponses");
    bSupprQn.addActionListener(this);
    bSupprQn.setEnabled(false);
    lIntro = new JLabel("Liste des questionnaires pour le sportif : "); 
    lSportif = new JLabel(this.sportif); 
    tricheQna = new JLabel("     ");
    tricheQnb = new JLabel("     ");
    tricheQnc = new JLabel("     ");
    
    panelQnN = new JPanel(new FlowLayout());
    panelQnN.add(lIntro);
    panelQnN1 = new JPanel();
    panelQnN1.setLayout(new BoxLayout(panelQnN1, BoxLayout.Y_AXIS));
    panelQnN1.add(panelQnN);
    panelQnN1.add(Box.createRigidArea(new Dimension(0,5)));
    panelQnN3 = new JPanel();
    panelQnN3.add(lSportif);
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
    this.sorter = new TableRowSorter<ModelTableauQaSp>(modeleQaSp); 
    sorter.setSortsOnUpdates(true);
    tableauQn.setRowSorter(sorter);
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
    pEastQst.add(bDetailQna, BorderLayout.WEST);
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
      this.bDetailQna.setEnabled(true);
    } else {
      this.bSupprQn.setEnabled(false);
      this.bDetailQna.setEnabled(false);
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
    
    /**************************** DETAIL REPONSES DU QUESTIONNAIRE ********************************/
    
    else if(source == bDetailQna){
      int[] selections;
      int selection;
      selections = tableauQn.getSelectedRows();
      selection = tableauQn.convertRowIndexToModel(selections[0]);
      
      new WindowReponsesQuestionaireSp(this.wind, this.modeleQaSp, selection);
      this.bSupprQn.setEnabled(false);
      this.bDetailQna.setEnabled(false);
    }
    
    /**************************** SUPPRIMER REPONSES AU QUESTIONNAIRE ********************************/
    
    else if(source == bSupprQn){
      int[] selections;
      int selection;
      int replyR;
      String messageSupR;
      
      selections = tableauQn.getSelectedRows();
      selection = tableauQn.convertRowIndexToModel(selections[0]);
      
      messageSupR = "Etes-vous sur de vouloir supprimer les réponses de ce questionnaire pour cette "
          + "date, définitivement ?";
      replyR = JOptionPane.showConfirmDialog(null, messageSupR, "Confirmation de la suppression",
          JOptionPane.YES_NO_OPTION);
      
      if (replyR == JOptionPane.YES_OPTION) {
        modeleQaSp.removeReponses(selection);
      }
    }
    
    /**************************** AJOUTER REPONSES AU QUESTIONNAIRE ********************************/
    
    else if(source == bCreerQn){
      new WindowAjoutReponsesQuestionaireSp(this.wind, this.modeleQaSp);
    }
    
  }
}
