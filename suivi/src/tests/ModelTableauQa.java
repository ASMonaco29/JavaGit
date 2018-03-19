package tests;

import cda.ListeQuestionnaires;
import cda.Question;
import cda.Questionnaire;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.table.AbstractTableModel;


@SuppressWarnings("serial")
public class ModelTableauQa extends AbstractTableModel {
  
  private final ListeQuestionnaires questionnaires;

  private final String[] entetes = {"Titre", "Sous-titre", "Date debut", "Date fin", 
      "Message fin", "Question(s)"};
  
  @SuppressWarnings("rawtypes")
  private ArrayList<DefaultListModel> modl;
  private ArrayList<Question> questions;
  private ArrayList<Question> questions1;

  private Calendar cal;
  private Date dated1;
  private Date dated2;  
  private Date datef1;
  private Date datef2;
  
  @SuppressWarnings("rawtypes")
  public ModelTableauQa() {
      super();
      
      this.questionnaires = new ListeQuestionnaires();
      this.questions = new ArrayList<Question>();
      this.questions1 = new ArrayList<Question>();
      this.modl = new ArrayList<DefaultListModel>();
      
      questions.add(new Question("Bien ?", false));
      questions.add(new Question("Reveillé ?", true));
      questions.add(new Question("Debout ?", true));
      questions1.add(new Question("Good ?", false));
      questions1.add(new Question("Awake ?", true));
      questions1.add(new Question("Wakdsse up ?", true));
      questions1.add(new Question("Wakedffffffffffffffffffffffffffffffffffffds up ?", false));
      questions1.add(new Question("Waksdsde up ?", true));
      questions1.add(new Question("Wakdsdse up ?", true));
      
      this.cal = Calendar.getInstance();
      cal.set(Calendar.YEAR, 2018);
      cal.set(Calendar.MONTH, 02);
      cal.set(Calendar.DAY_OF_MONTH, 28);
      dated1 = cal.getTime();
      
      cal.set(Calendar.YEAR, 2018);
      cal.set(Calendar.MONTH, 03);
      cal.set(Calendar.DAY_OF_MONTH, 01);
      dated2 = cal.getTime();
      
      cal.set(Calendar.YEAR, 2018);
      cal.set(Calendar.MONTH, 03);
      cal.set(Calendar.DAY_OF_MONTH, 28);
      datef1 = cal.getTime();
      
      cal.set(Calendar.YEAR, 2018);
      cal.set(Calendar.MONTH, 04);
      cal.set(Calendar.DAY_OF_MONTH, 01);
      datef2 = cal.getTime();
          
      questionnaires.addQuestionnaire("Bien-être", "Soin", dated1, datef1, 
          "Merci d'avoir répondu à ce questionnaire.", questions);
      questionnaires.addQuestionnaire("Santé", "Pour vous", dated1, datef1, 
          "Merci d'avoir répondu à ce questionnaire.", questions1);
      questionnaires.addQuestionnaire("Entrainement", "Quotidien", dated1, datef1, 
          "Merci d'avoir répondu à ce questionnaire.", questions1);
      questionnaires.addQuestionnaire("Conditions", "Tenace ?", dated2, datef2, 
          "Merci d'avoir répondu à ce questionnaire.", questions);
      questionnaires.addQuestionnaire("Sélections", "Travail", dated2, datef2, 
          "Merci d'avoir répondu à ce questionnaire.", questions1);
      questionnaires.addQuestionnaire("Bien-être", "Soin", dated1, datef1, 
          "Merci d'avoir répondu à ce questionnaire.", questions);
      questionnaires.addQuestionnaire("Santé", "Pour vous", dated1, datef1, 
          "Merci d'avoir répondu à ce questionnaire.", questions);
      questionnaires.addQuestionnaire("Entrainement", "Quotidien", dated1, datef1, 
          "Merci d'avoir répondu à ce questionnaire.", questions);
      questionnaires.addQuestionnaire("Conditions", "Tenace ?", dated2, datef2, 
          "Merci d'avoir répondu à ce questionnaire.", questions);
      questionnaires.addQuestionnaire("Sélections", "Travail", dated2, datef2, 
          "Merci d'avoir répondu à ce questionnaire.", questions);
      questionnaires.addQuestionnaire("Bien-être", "Soin", dated1, datef1, 
          "Merci d'avoir répondu à ce questionnaire.", questions);
      questionnaires.addQuestionnaire("Santé", "Pour vous", dated1, datef1, 
          "Merci d'avoir répondu à ce questionnaire.", questions);
      questionnaires.addQuestionnaire("Entrainement", "Quotidien", dated1, datef1, 
          "Merci d'avoir répondu à ce questionnaire.", questions);
      questionnaires.addQuestionnaire("Conditions", "Tenace ?", dated2, datef2, 
          "Merci d'avoir répondu à ce questionnaire.", questions);
      questionnaires.addQuestionnaire("Sélections", "Travail", dated2, datef2, 
          "Merci d'avoir répondu à ce questionnaire.", questions);

      // Présentation sous forme de liste des questions de chaque questionnaire
      actualiserListeQuestions();
  }

  public int getRowCount() {
      return this.questionnaires.getSizeListQ();
  }

  public int getColumnCount() {
      return this.entetes.length;
  }

  public String getColumnName(int columnIndex) {
      return this.entetes[columnIndex];
  }
  
  public ListeQuestionnaires getListeQuestionnaires(){
    return this.questionnaires;
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    switch(columnIndex){
        case 0:
            return this.questionnaires.getListQ().get(rowIndex).getTitre();
        case 1:
            return this.questionnaires.getListQ().get(rowIndex).getSstitre();
        case 2:
            return this.questionnaires.getListQ().get(rowIndex).getDateD();
        case 3:
            return this.questionnaires.getListQ().get(rowIndex).getDateF();
        case 4:
            return this.questionnaires.getListQ().get(rowIndex).getMessageFin();
        case 5:
            return this.modl.get(rowIndex);
        default:
            return null; // Ne devrait jamais arriver
    }
  }
    
  public void addQuestionnaire(Questionnaire quest) {
    this.questionnaires.addQuestionnaire(quest.getTitre(), quest.getSstitre(), quest.getDateD(), 
        quest.getDateF(), quest.getMessageFin(), quest.getquListe());
    actualiserListeQuestions();

    fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
  }
  
  public void removeQuestionnaire(int rowIndex) {
    this.questionnaires.supprQuestionnaire(this.questionnaires.getListQ().get(rowIndex));
    this.modl.remove(rowIndex);
    
    fireTableRowsDeleted(rowIndex, rowIndex);
  }
  
  public void modifQuestionnaire(Questionnaire quest, int indx){
    this.questionnaires.modifQuestionnaire(quest, indx);
    
    fireTableRowsUpdated(indx, indx);
  }
  
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public Class getColumnClass(int columnIndex){
    switch(columnIndex){
        case 2:
            return Date.class;
        case 3:
            return Date.class;
        default:
            return Object.class;
    }
  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void actualiserListeQuestions(){
    this.modl.clear();
    for(int i = 0; i < getRowCount(); i++){
      DefaultListModel dlm = new DefaultListModel();
      for(int j = 0; j < questionnaires.getListQ().get(i).getquListe().size(); j++){
        dlm.addElement(questionnaires.getListQ().get(i).getquListe().get(j));
      }
      this.modl.add(dlm);
    }
  }
  
  
}