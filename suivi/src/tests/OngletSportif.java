package tests;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import cda.Sport;
import cda.Sportif;



public class OngletSportif extends JFrame implements ActionListener {
  
  private static final long serialVersionUID = 1L;
  private ModelTableauSp modeleS = new ModelTableauSp();
  private JTabbedPane tabbedPane;
 
  // ONGLET QUESTIONNAIRES
  private JComponent panelQ; 
  private JComponent panelQl; 
  private JComponent panelQg;
  private Border borderL;
  private Border borderG;
  private JPanel pPseudo;
  private JPanel pNom;
  private JPanel pPrenom;
  private JPanel pDateNaissance;
  private JPanel pSport;
  private JPanel pBoutons;
  private JPanel pQuestionnaire;
  private JTextField tPseudo; 
  private JTextField tNom;
  private JTextField tPrenom;
  @SuppressWarnings("rawtypes")
  private JComboBox jSport;
  private JLabel lPseudo;
  private JLabel lNom;
  private JLabel lPrenom;
  private JLabel lDateNaissance;
  private JLabel lSport;
  private JLabel triche;
  private JButton bQuestionnaire;
  private JButton bCreer;
  private JButton bModifier;
  private JButton bSupprimer;
  private JTable tableauS;
  private TableRowSorter<ModelTableauSp> sorter;
  private Properties p;
  private UtilDateModel modelDate;
  private JDatePanelImpl datePanel;
  private JDatePickerImpl dateNaissance;
  private int selectedRowQa = -1;
 
  
  
  /** Constructeur.
  * @param nom Contient le nom de la fenêtre
  */
  public OngletSportif(JTabbedPane tabbedPane, JComponent panelQ) {
    this.tabbedPane = tabbedPane;
    this.panelQ = panelQ;
    construire();
  }
      
  
  
  /** Construire.
  * Permet de construire la fenêtre
  */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void construire() { 
       
    this.panelQ = new JPanel();
    this.panelQ.setLayout(new BoxLayout(this.panelQ, BoxLayout.PAGE_AXIS));
    this.panelQl = new JPanel();
    this.panelQl.setLayout(new BorderLayout());
    this.panelQl.setMaximumSize(new Dimension(1700, 250));
    this.panelQl.setPreferredSize(new Dimension(this.getWidth(), 250));
    this.panelQg = new JPanel();
   
    // Attributs
    this.borderL = BorderFactory.createTitledBorder("Liste Sportifs");
    this.borderG = BorderFactory.createTitledBorder("Gérer Sportifs");
    
    this.bQuestionnaire = new JButton("Détails questionnaires");
    this.bQuestionnaire.addActionListener(this);
    this.bQuestionnaire.setEnabled(false);
    this.bCreer = new JButton("Créer");
    this.bCreer.addActionListener(this);
    this.bModifier = new JButton("Modifier");
    this.bModifier.addActionListener(this);
    this.bModifier.setEnabled(false);
    this.bSupprimer = new JButton("Supprimer");
    this.bSupprimer.addActionListener(this);
    this.bSupprimer.setEnabled(false);
            
    this.tPseudo = new JTextField(); 
    this.tPseudo.setPreferredSize(new Dimension(250, 25));
    
    this.tNom = new JTextField();
    this.tNom.setPreferredSize(new Dimension(250, 25));
    
    this.tPrenom = new JTextField();
    this.tPrenom.setPreferredSize(new Dimension(250, 25));
       
    this.p = new Properties();
    this.p.put("text.today", "Today");
    this.p.put("text.month", "Month");
    this.p.put("text.year", "Year");
    
    this.modelDate = new UtilDateModel();
    this.datePanel = new JDatePanelImpl(modelDate, p);
    this.dateNaissance = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    this.dateNaissance.setPreferredSize(new Dimension(120, 25));
    
    this.jSport = new JComboBox(Sport.values());
    this.jSport.setPreferredSize(new Dimension(250, 25));
    this.jSport.setSelectedIndex(-1);
    
    this.lPseudo = new JLabel("Pseudo : ");
    this.lNom = new JLabel("Nom : ");
    this.lPrenom = new JLabel("Prénom : ");
    this.lDateNaissance = new JLabel("Date de naissance : ");
    this.lSport = new JLabel("Sport : ");
    this.triche = new JLabel("                                                               "
        + "                                                                                   "
        + "                                                                                   "
        + "                                                                                  ");
    
    this.pPseudo = new JPanel();
    this.pPseudo.setLayout(new BorderLayout());
    this.pNom = new JPanel();
    this.pNom.setLayout(new BorderLayout());
    this.pPrenom = new JPanel();
    this.pPrenom.setLayout(new BorderLayout());
    this.pDateNaissance = new JPanel();
    this.pSport = new JPanel();
    this.pQuestionnaire = new JPanel();
    this.pBoutons = new JPanel();
    this.pBoutons.setLayout(new BoxLayout(this.pBoutons, BoxLayout.LINE_AXIS));
    
    this.pPseudo.add(this.lPseudo, BorderLayout.WEST);
    this.pPseudo.add(this.tPseudo, BorderLayout.EAST);
    this.pNom.add(this.lNom, BorderLayout.WEST);
    this.pNom.add(this.tNom, BorderLayout.EAST);
    this.pPrenom.add(this.lPrenom, BorderLayout.WEST);
    this.pPrenom.add(this.tPrenom, BorderLayout.EAST);
    this.pDateNaissance.add(this.lDateNaissance, BorderLayout.WEST);
    this.pDateNaissance.add(this.dateNaissance, BorderLayout.EAST);
    this.pSport.add(this.lSport, BorderLayout.WEST);
    this.pSport.add(this.jSport, BorderLayout.EAST);
    this.pQuestionnaire.add(this.bQuestionnaire);
    this.triche.setPreferredSize(new Dimension(0, 50));
    
    this.pBoutons.add(this.bCreer); 
    this.pBoutons.add(Box.createRigidArea(new Dimension(200,0)));
    this.pBoutons.add(this.bModifier);
    this.pBoutons.add(Box.createRigidArea(new Dimension(200,0)));
    this.pBoutons.add(this.bSupprimer);
    
    this.tableauS = new JTable(modeleS);
    this.sorter = new TableRowSorter<ModelTableauSp>(modeleS); 
    tableauS.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    tableauS.getColumn("Questionnaire(s) attribué(s)").setCellRenderer(new JListRenderer());
    tableauS.getColumn("Questionnaire(s) attribué(s)").setCellEditor(new JListEditor());
    tableauS.setRowHeight(70);
    sorter.setSortable(5, false);
    sorter.setSortsOnUpdates(true);
    tableauS.setRowSorter(sorter);
    tableauS.getTableHeader().setReorderingAllowed(false);
    tableauS.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
          jTable1MouseClicked(evt);
      }
    });
       
    // Paramètres de l'onglet Questionnaires
    
    tabbedPane.addTab("Sportifs", panelQ);
    tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);  
    
    // Ajout des attributs au contenu de l'onglet Questionnaires    
    panelQl.setBorder(borderL);
    panelQl.add(new JScrollPane(tableauS), BorderLayout.CENTER); 
    
    panelQg.setBorder(borderG);
    panelQg.add(pPseudo);
    panelQg.add(pNom);
    panelQg.add(pPrenom);
    panelQg.add(pDateNaissance);
    panelQg.add(pSport);
    panelQg.add(pQuestionnaire);
    panelQg.add(triche);
    panelQg.add(pBoutons);
    
    panelQ.add(panelQl);
    panelQ.add(Box.createRigidArea(new Dimension(0,5)));
    panelQ.add(panelQg);   
    
    
  }
  
  
  
  
  /*
   * Actions réalisées sur clic (quitter, créer, modifier, supprimer)
   */
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    
    /**************************** GERER QUESTIONNAIRE ********************************/
    if(source == this.bQuestionnaire){
      int[] selections;
      int selection;
      selections = tableauS.getSelectedRows();
      selection = tableauS.convertRowIndexToModel(selections[0]);
      
      Sportif s = null;
      for(int i = 0; i < this.modeleS.getListeSportifs().getSizeListS(); i++){
        if((String)modeleS.getValueAt(selection, 0) ==  
            this.modeleS.getListeSportifs().getListeS().get(i).getPseudo()){
          s = this.modeleS.getListeSportifs().getListeS().get(i);
        }
      }
      
      new WindowListeQuestionaireSp(this, new ModelTableauQaSp(s), 
      (String)modeleS.getValueAt(selection, 0));
      
    }
    
    /**************************** CREER SPORTIF ********************************/
    if(source == this.bCreer){
      modeleS.creerSportif(this.tNom.getText(), this.tPrenom.getText(), this.tPseudo.getText(),
          (Date)dateNaissance.getModel().getValue(), (Sport)this.jSport.getSelectedItem());
    }
    
    /**************************** MODIFIER SPORTIF ********************************/
    if(source == this.bModifier){
      modeleS.modifSportif(this.tNom.getText(), this.tPrenom.getText(), this.tPseudo.getText(),
          (Date)dateNaissance.getModel().getValue(), (Sport)this.jSport.getSelectedItem(), this.selectedRowQa);
    }
    
    /**************************** SUPPRIMER SPORTIF ********************************/
    if(source == this.bSupprimer){
      
      int[] selections;
      int selection;
      int modelRow;
      int replyQa;
      String messageSupQ; 
      Date today;
      Date todayWithZeroTime;
      DateFormat formatter;
      String reportDate;
      int jourTdy = 0;
      int moisTdy = 0;
      int anTdy = 0;
      
      selections = tableauS.getSelectedRows();
      selection = selections[0];
      
      messageSupQ = "Etes-vous sur de vouloir supprimer ce sportif définitivement ?";
      replyQa = JOptionPane.showConfirmDialog(null, messageSupQ, "Confirmation de la suppression",
          JOptionPane.YES_NO_OPTION);
      
      if (replyQa == JOptionPane.YES_OPTION) {
        modelRow = tableauS.convertRowIndexToModel(selection);
        modeleS.supprimerSportif(modelRow);
        
        
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        today = new Date();
        try {
          todayWithZeroTime = formatter.parse(formatter.format(today));
          reportDate = formatter.format(todayWithZeroTime);
          jourTdy = Integer.parseInt(reportDate.substring(0, 2));
          moisTdy = Integer.parseInt(reportDate.substring(3, 5));
          anTdy = Integer.parseInt(reportDate.substring(6, 10));
        
        } catch (ParseException e2) {
          e2.printStackTrace();
        }  
        
        this.tPseudo.setText(null);
        this.tNom.setText(null);
        this.tPrenom.setText(null);
        this.jSport.setSelectedIndex(-1);
        this.dateNaissance.getModel().setSelected(false);
        this.dateNaissance.getModel().setDate(anTdy, moisTdy-1, jourTdy);
        this.bSupprimer.setEnabled(false);
        this.bModifier.setEnabled(false);
        this.bQuestionnaire.setEnabled(false);
        this.bCreer.setEnabled(true);
      } // Sinon, la fenêtre se ferme.
      
    }
    
    
    
  }
  
  private void jTable1MouseClicked(MouseEvent evt) {                                     
    
   // get the model from the jtable
   ModelTableauSp model = (ModelTableauSp)tableauS.getModel();
   int selectedRowIndex;
   Date dt;
   Date today;
   Date todayWithZeroTime;
   DateFormat formatter;
   String reportDate;
   String combo;
   int jourDate2;
   int moisDate2;
   int anDate2;
   int jourTdy = 0;
   int moisTdy = 0;
   int anTdy = 0;
   
   // Gestion des dates
   formatter = new SimpleDateFormat("dd/MM/yyyy");
   today = new Date();
   try {
     todayWithZeroTime = formatter.parse(formatter.format(today));
     reportDate = formatter.format(todayWithZeroTime);
     jourTdy = Integer.parseInt(reportDate.substring(0, 2));
     moisTdy = Integer.parseInt(reportDate.substring(3, 5));
     anTdy = Integer.parseInt(reportDate.substring(6, 10));
   
   } catch (ParseException e) {
     e.printStackTrace();
   }  
   
   // get the selected row index
   int modelRow = tableauS.getSelectedRow();
   
   if(modelRow != -1){
     selectedRowIndex = tableauS.convertRowIndexToModel(modelRow);
     this.selectedRowQa = selectedRowIndex;
     
     dt = (Date)model.getValueAt(selectedRowIndex, 3);
     
     reportDate = formatter.format(dt);
     jourDate2 = Integer.parseInt(reportDate.substring(0, 2));
     moisDate2 = Integer.parseInt(reportDate.substring(3, 5));
     anDate2 = Integer.parseInt(reportDate.substring(6, 10));
     
     combo = model.getValueAt(selectedRowIndex, 4).toString();
     int i = 0;
     for (Sport sp : Sport.values()) {
       if(combo.equalsIgnoreCase(sp.name())){
         break;
       }
       i++;
     }
     
     // set the selected row data into jtextfields
     this.tPseudo.setText(model.getValueAt(selectedRowIndex, 0).toString());
     this.tPseudo.setEditable(false);
     this.tNom.setText(model.getValueAt(selectedRowIndex, 1).toString());
     this.tPrenom.setText(model.getValueAt(selectedRowIndex, 2).toString());
     this.jSport.setSelectedIndex(i);
     this.dateNaissance.getModel().setDate(anDate2, moisDate2-1, jourDate2);
     this.dateNaissance.getModel().setSelected(true);
     this.bSupprimer.setEnabled(true);
     this.bModifier.setEnabled(true);
     this.bQuestionnaire.setEnabled(true);
     this.bCreer.setEnabled(false);
   } else {
     this.tPseudo.setText(null);
     this.tPseudo.setEditable(true);
     this.tNom.setText(null);
     this.tPrenom.setText(null);
     this.jSport.setSelectedIndex(-1);
     this.dateNaissance.getModel().setSelected(false);
     this.dateNaissance.getModel().setDate(anTdy, moisTdy-1, jourTdy);
     this.bSupprimer.setEnabled(false);
     this.bModifier.setEnabled(false);
     this.bQuestionnaire.setEnabled(false);
     this.bCreer.setEnabled(true);
   }
}               

  
  public void setUpCheckBoxColumn(JTable table, TableColumn boolCol) {

    JCheckBox checkBox = new JCheckBox();
    boolCol.setCellEditor(new DefaultCellEditor(checkBox));
    
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setToolTipText("Click to check");
    boolCol.setCellRenderer(renderer);
  }

  
  
}