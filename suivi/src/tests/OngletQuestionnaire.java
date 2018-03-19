package tests;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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



public class OngletQuestionnaire extends JFrame implements ActionListener {
  
  private static final long serialVersionUID = 1L;
  private ModelTableauQa modeleQ = new ModelTableauQa();
  private JTabbedPane tabbedPane;
 
  // ONGLET QUESTIONNAIRES
  private JComponent panelQ; 
  private JComponent panelQl; 
  private JComponent panelQg;
  private Border borderL;
  private Border borderG;
  private JPanel pTitre;
  private JPanel pStitre;
  private JPanel pDateDebut;
  private JPanel pDateFin;
  private JPanel pMessageFin;
  private JPanel pBoutons;
  private JTextField tTitre; 
  private JTextField tStitre;
  private JTextField tMessageFin;
  private JLabel lTitre;
  private JLabel lStitre;
  private JLabel lDateDebut;
  private JLabel lDateFin;
  private JLabel lMessageFin;
  private JLabel triche;
  private JButton bCreer;
  private JButton bModifier;
  private JButton bSupprimer;
  private JTable tableauQ;
  private TableRowSorter<ModelTableauQa> sorter;
  private Properties p;
  private UtilDateModel modelDebut;
  private UtilDateModel modelFin;
  private JDatePanelImpl datePanelDebut;
  private JDatePanelImpl datePanelFin;
  private JDatePickerImpl dateDebut;
  private JDatePickerImpl dateFin;
  private int selectedRowQa = -1;
 
  
  
  /** Constructeur.
  * @param nom Contient le nom de la fenêtre
  */
  public OngletQuestionnaire(JTabbedPane tabbedPane, JComponent panelQ) {
    this.tabbedPane = tabbedPane;
    this.panelQ = panelQ;
    construire();
  }
      
  
  
  /** Construire.
  * Permet de construire la fenêtre
  */
  public void construire() { 
       
    this.panelQ = new JPanel();
    this.panelQ.setLayout(new BoxLayout(this.panelQ, BoxLayout.PAGE_AXIS));
    this.panelQl = new JPanel();
    this.panelQl.setLayout(new BorderLayout());
    this.panelQl.setMaximumSize(new Dimension(1700, 250));
    this.panelQl.setPreferredSize(new Dimension(this.getWidth(), 250));
    this.panelQg = new JPanel();
   
    // Attributs
    this.borderL = BorderFactory.createTitledBorder("Liste Questionnaires");
    this.borderG = BorderFactory.createTitledBorder("Gérer Questionnaires");
    
    this.bCreer = new JButton("Créer");
    this.bCreer.addActionListener(this);
    this.bModifier = new JButton("Modifier");
    this.bModifier.addActionListener(this);
    this.bModifier.setEnabled(false);
    this.bSupprimer = new JButton("Supprimer");
    this.bSupprimer.addActionListener(this);
    this.bSupprimer.setEnabled(false);
            
    this.tTitre = new JTextField(); 
    this.tTitre.setPreferredSize(new Dimension(250, 25));
    
    this.tStitre = new JTextField();
    this.tStitre.setPreferredSize(new Dimension(250, 25));
       
    this.p = new Properties();
    this.p.put("text.today", "Today");
    this.p.put("text.month", "Month");
    this.p.put("text.year", "Year");
    
    this.modelDebut = new UtilDateModel();
    this.datePanelDebut = new JDatePanelImpl(modelDebut, p);
    this.dateDebut = new JDatePickerImpl(datePanelDebut, new DateLabelFormatter());
    this.dateDebut.setPreferredSize(new Dimension(120, 25));
    
    this.modelFin = new UtilDateModel();
    this.datePanelFin = new JDatePanelImpl(modelFin, p);
    this.dateFin = new JDatePickerImpl(datePanelFin, new DateLabelFormatter());
    this.dateFin.setPreferredSize(new Dimension(120, 25));
    
    this.tMessageFin = new JTextField();
    this.tMessageFin.setPreferredSize(new Dimension(250, 25));
    
    this.lTitre = new JLabel("Titre : ");
    this.lStitre = new JLabel("Sous-titre : ");
    this.lDateDebut = new JLabel("Date début : ");
    this.lDateFin = new JLabel("Date fin : ");
    this.lMessageFin = new JLabel("Message de Fin : ");
    this.triche = new JLabel("                                                               "
        + "                                                                                   "
        + "                                                                                   "
        + "                                                                                  ");
    
    this.pTitre = new JPanel();
    this.pTitre.setLayout(new BorderLayout());
    this.pStitre = new JPanel();
    this.pStitre.setLayout(new BorderLayout());
    this.pDateDebut = new JPanel();
    this.pDateFin = new JPanel();
    this.pMessageFin = new JPanel();
    this.pBoutons = new JPanel();
    this.pBoutons.setLayout(new BoxLayout(this.pBoutons, BoxLayout.LINE_AXIS));
    
    this.pTitre.add(this.lTitre, BorderLayout.WEST);
    this.pTitre.add(this.tTitre, BorderLayout.EAST);
    this.pStitre.add(this.lStitre, BorderLayout.WEST);
    this.pStitre.add(this.tStitre, BorderLayout.EAST);
    this.pDateDebut.add(this.lDateDebut, BorderLayout.WEST);
    this.pDateDebut.add(this.dateDebut, BorderLayout.EAST);
    this.pDateFin.add(this.lDateFin, BorderLayout.WEST);
    this.pDateFin.add(this.dateFin, BorderLayout.EAST);
    this.pMessageFin.add(this.lMessageFin, BorderLayout.WEST);
    this.pMessageFin.add(this.tMessageFin, BorderLayout.EAST);
    this.triche.setPreferredSize(new Dimension(0, 50));
    
    this.pBoutons.add(this.bCreer); 
    this.pBoutons.add(Box.createRigidArea(new Dimension(200,0)));
    this.pBoutons.add(this.bModifier);
    this.pBoutons.add(Box.createRigidArea(new Dimension(200,0)));
    this.pBoutons.add(this.bSupprimer);
    
    this.tableauQ = new JTable(modeleQ);
    this.sorter = new TableRowSorter<ModelTableauQa>(modeleQ); 
    tableauQ.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    tableauQ.getColumn("Question(s)").setCellRenderer(new JListRenderer());
    tableauQ.getColumn("Question(s)").setCellEditor(new JListEditor());
    tableauQ.setRowHeight(70);
    sorter.setSortable(5, false);
    sorter.setSortsOnUpdates(true);
    tableauQ.setRowSorter(sorter);
    tableauQ.getTableHeader().setReorderingAllowed(false);
    tableauQ.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
          jTable1MouseClicked(evt);
      }
    });
       
    // Paramètres de l'onglet Questionnaires
    
    tabbedPane.addTab("Questionnaires", panelQ);
    tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);  
    
    // Ajout des attributs au contenu de l'onglet Questionnaires    
    panelQl.setBorder(borderL);
    panelQl.add(new JScrollPane(tableauQ), BorderLayout.CENTER); 
    
    panelQg.setBorder(borderG);
    panelQg.add(pTitre);
    panelQg.add(pStitre);
    panelQg.add(pDateDebut);
    panelQg.add(pDateFin);
    panelQg.add(pMessageFin);
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
    
    
    /**************************** CREER QUESTIONNAIRE ********************************/
    if(source == this.bCreer){

      new WindowCreerQuestionaire(this, modeleQ, this.tTitre.getText(), this.tStitre.getText(), this.tMessageFin.getText(),
          (Date)dateDebut.getModel().getValue(), (Date)dateFin.getModel().getValue());
    }
    
    /**************************** MODIFIER QUESTIONNAIRE ********************************/
    if(source == this.bModifier){

      new WindowModifierQuestionaire(this, modeleQ, this.selectedRowQa, this.tTitre.getText(), this.tStitre.getText(), this.tMessageFin.getText(),
          (Date)dateDebut.getModel().getValue(), (Date)dateFin.getModel().getValue());
    }
    
    /**************************** SUPPRIMER QUESTIONNAIRE ********************************/
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
      
      selections = tableauQ.getSelectedRows();
      selection = selections[0];
      
      messageSupQ = "Etes-vous sur de vouloir supprimer ce questionnaire définitivement ?";
      replyQa = JOptionPane.showConfirmDialog(null, messageSupQ, "Confirmation de la suppression",
          JOptionPane.YES_NO_OPTION);
      
      if (replyQa == JOptionPane.YES_OPTION) {
        modelRow = tableauQ.convertRowIndexToModel(selection);
        modeleQ.removeQuestionnaire(modelRow);
        
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
        
        this.tTitre.setText(null);
        this.tStitre.setText(null);
        this.tMessageFin.setText(null);
        this.dateDebut.getModel().setSelected(false);
        this.dateDebut.getModel().setDate(anTdy, moisTdy-1, jourTdy);
        this.dateFin.getModel().setSelected(false);
        this.dateFin.getModel().setDate(anTdy, moisTdy-1, jourTdy);
        this.bSupprimer.setEnabled(false);
        this.bModifier.setEnabled(false);
        this.bCreer.setEnabled(true);
      } // Sinon, la fenêtre se ferme.
      
    }
    
    
    
  }
  
  private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {                                     
    
   // get the model from the jtable
   ModelTableauQa model = (ModelTableauQa)tableauQ.getModel();
   int selectedRowIndex;
   Date dt1;
   Date dt2;
   Date today;
   Date todayWithZeroTime;
   DateFormat formatter;
   String reportDate;
   int jourDate1;
   int moisDate1;
   int anDate1;
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
   int modelRow = tableauQ.getSelectedRow();
   
   if(modelRow != -1){
     selectedRowIndex = tableauQ.convertRowIndexToModel(modelRow);
     this.selectedRowQa = selectedRowIndex;
     
     dt1 = (Date)model.getValueAt(selectedRowIndex, 2);
     dt2 = (Date)model.getValueAt(selectedRowIndex, 3);
     
     reportDate = formatter.format(dt1);
     jourDate1 = Integer.parseInt(reportDate.substring(0, 2));
     moisDate1 = Integer.parseInt(reportDate.substring(3, 5));
     anDate1 = Integer.parseInt(reportDate.substring(6, 10));
     
     reportDate = formatter.format(dt2);
     jourDate2 = Integer.parseInt(reportDate.substring(0, 2));
     moisDate2 = Integer.parseInt(reportDate.substring(3, 5));
     anDate2 = Integer.parseInt(reportDate.substring(6, 10));
     
     // set the selected row data into jtextfields
     this.tTitre.setText(model.getValueAt(selectedRowIndex, 0).toString());
     this.tStitre.setText(model.getValueAt(selectedRowIndex, 1).toString());
     this.tMessageFin.setText(model.getValueAt(selectedRowIndex, 4).toString());
     this.dateDebut.getModel().setDate(anDate1, moisDate1-1, jourDate1);
     this.dateDebut.getModel().setSelected(true);
     this.dateFin.getModel().setDate(anDate2, moisDate2-1, jourDate2);
     this.dateFin.getModel().setSelected(true);
     this.bSupprimer.setEnabled(true);
     this.bModifier.setEnabled(true);
     this.bCreer.setEnabled(false);
   } else {
     this.tTitre.setText(null);
     this.tStitre.setText(null);
     this.tMessageFin.setText(null);
     this.dateDebut.getModel().setSelected(false);
     this.dateDebut.getModel().setDate(anTdy, moisTdy-1, jourTdy);
     this.dateFin.getModel().setSelected(false);
     this.dateFin.getModel().setDate(anTdy, moisTdy-1, jourTdy);
     this.bSupprimer.setEnabled(false);
     this.bModifier.setEnabled(false);
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
