package tests;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import cda.Reponse;
import cda.Sportif;


@SuppressWarnings("serial")
public class WindowAjoutReponsesQuestionaireSp extends JDialog implements ActionListener {
  
  // Attributs à ajouter :
  private JTable tableauQn;
  //private ModelTableauQa modeleQa;
  private JButton bAnnulerQn;
  private JButton bModifierQna;
  private JLabel lIntro; 
  private JLabel tricheQna;
  private JLabel tricheQnb;
  private JPanel pGlobBtn;
  private JPanel panelQnN;
  private JPanel panelQnN1;
  private JPanel panelQnN2;
  private JPanel pFinalQn;
  private ModelTableauQaSp modeleQaSp;
  private ModelTableauAjoutRpSp modeleAjRpSp;
  private Sportif sportif;
  private ArrayList<Boolean> reponse;
  @SuppressWarnings("rawtypes")
  private JComboBox jQuest;
  private String[] jElem;
  private int JComboSize;
  
  // CONSTRUCTEUR :
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public WindowAjoutReponsesQuestionaireSp(Component compo, ModelTableauQaSp modeleQs){
  
    super((Frame) compo, "Ajout de réponses", true);
    
    // Initilisation :
    modeleQaSp = modeleQs;
    modeleAjRpSp = new ModelTableauAjoutRpSp(modeleQaSp.getSportif(), 0);
    sportif = modeleQs.getSportif();
    tableauQn = new JTable(modeleAjRpSp);
    bAnnulerQn = new JButton("Annuler");
    bAnnulerQn.addActionListener(this);
    bModifierQna = new JButton("Ajouter");
    bModifierQna.addActionListener(this);
    
    jElem = new String[modeleAjRpSp.getListeQuestionnaires().getListQ().size()];
    for(JComboSize = 0; JComboSize < modeleAjRpSp.getListeQuestionnaires().getListQ().size(); JComboSize++){
      jElem[JComboSize] = modeleAjRpSp.getListeQuestionnaires().getListQ().get(JComboSize).getTitre();
    }
    this.jQuest = new JComboBox(this.jElem);
    this.jQuest.setPreferredSize(new Dimension(250, 25));
    this.jQuest.addActionListener(this);
    
    lIntro = new JLabel("Ajout de nouvelles réponses au questionnaire : ");
    tricheQna = new JLabel("     ");
    tricheQnb = new JLabel("     ");
    
    panelQnN = new JPanel(new FlowLayout());
    panelQnN.add(lIntro);
    panelQnN2 = new JPanel(new FlowLayout());
    panelQnN2.add(this.jQuest);
    panelQnN1 = new JPanel();
    panelQnN1.setLayout(new BoxLayout(panelQnN1, BoxLayout.Y_AXIS));
    panelQnN1.add(panelQnN);
    panelQnN1.add(Box.createRigidArea(new Dimension(0,5)));
    panelQnN1.add(panelQnN2);
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
    
    
    pGlobBtn = new JPanel();
    pGlobBtn.add(tricheQna);
    pGlobBtn.add(bModifierQna);
    pGlobBtn.add(tricheQnb);
    pGlobBtn.add(bAnnulerQn);
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


  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    
    /**************************** ANNULER ********************************/
    
    if(source == bAnnulerQn){
      this.setVisible(false);
      this.dispose();
    }
    
    /**************************** AJOUTER REPONSES DU QUESTIONNAIRE ********************************/
    
    else if(source == bModifierQna){
      
      this.reponse = new ArrayList<Boolean>();
      
      for(int i = 0; i < modeleAjRpSp.getRowCount(); i++){
        reponse.add((Boolean)modeleAjRpSp.getValueAt(i, 1));
      }
      
      // Ajout du questionnaire :
      this.modeleQaSp.addReponse(new Reponse(new Date(), this.reponse, this.sportif, 
          modeleAjRpSp.getQuestionnaire()));
      
      this.setVisible(false);
      this.dispose();
    }
    
    
    /**************************** JComboBox ********************************/
    else if(source == jQuest){
      modeleAjRpSp.setQuestionnaire(jQuest.getSelectedIndex());
    }
    
    
  }
}
