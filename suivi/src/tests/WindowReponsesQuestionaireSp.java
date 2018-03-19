package tests;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
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

import cda.Reponse;

@SuppressWarnings("serial")
public class WindowReponsesQuestionaireSp extends JDialog implements ActionListener {
  
  // Attributs à ajouter :
  private JTable tableauQn;
  private JButton bAnnulerQn;
  private JButton bModifierQna;
  private JButton bSupprQn;
  private JLabel lIntro; 
  private JLabel tricheQna;
  private JLabel tricheQnb;
  private JPanel pGlobBtn;
  private JPanel panelQnN;
  private JPanel panelQnN1;
  private JPanel pFinalQn;
  private ModelTableauRpSp modeleRpSp;
  private ModelTableauQaSp modeleQaSp;
  private Reponse rep;
  private SimpleDateFormat format;
  private Date date;
  private int selectedRowQa;
  
  // CONSTRUCTEUR :
  public WindowReponsesQuestionaireSp(Component compo, ModelTableauQaSp modeleQs, int selectedRowQa){
  
    super((Frame) compo, "Liste de réponses", true);
    
    // Initilisation : 
    this.rep = modeleQs.getListR().getReponses().get(selectedRowQa);
    modeleRpSp = new ModelTableauRpSp(modeleQs.getListR(), selectedRowQa);
    modeleQaSp = modeleQs;
    tableauQn = new JTable(modeleRpSp);
    this.selectedRowQa = selectedRowQa;
    bAnnulerQn = new JButton("Retour");
    bAnnulerQn.addActionListener(this);
    bModifierQna = new JButton("Modifier");
    bModifierQna.addActionListener(this);
    bSupprQn = new JButton("Supprimer réponses");
    bSupprQn.addActionListener(this);
    
    format = new SimpleDateFormat("dd/MM/yyyy");
    date = this.rep.getDate();
    lIntro = new JLabel("Liste des réponses au questionnaire \""+ this.rep.getQuestionnaire().getTitre()
      +"\" à la date : " + format.format(this.date)); 
    tricheQna = new JLabel("     ");
    tricheQnb = new JLabel("     ");
    
    panelQnN = new JPanel(new FlowLayout());
    panelQnN.add(lIntro);
    panelQnN1 = new JPanel();
    panelQnN1.setLayout(new BoxLayout(panelQnN1, BoxLayout.Y_AXIS));
    panelQnN1.add(panelQnN);
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
    pGlobBtn.add(bSupprQn);
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
    
    /**************************** QUITTER ********************************/
    
    if(source == bAnnulerQn){
      this.setVisible(false);
      this.dispose();
    }
    
    /**************************** MODIFIER REPONSES DU QUESTIONNAIRE ********************************/
    
    else if(source == bModifierQna){
      modeleQaSp.modifReponses(this.selectedRowQa);
      this.setVisible(false);
      this.dispose();
    }
    
    /**************************** SUPPRIMER REPONSES AU QUESTIONNAIRE ********************************/
    
    else if(source == bSupprQn){

      int replyR;
      String messageSupR;

      messageSupR = "Etes-vous sur de vouloir supprimer les réponses de ce questionnaire pour cette "
          + "date, définitivement ?";
      replyR = JOptionPane.showConfirmDialog(null, messageSupR, "Confirmation de la suppression",
          JOptionPane.YES_NO_OPTION);
      
      if (replyR == JOptionPane.YES_OPTION) {
        modeleQaSp.removeReponses(this.selectedRowQa);
        this.setVisible(false);
        this.dispose();
      }
    }
    
    
  }
}
