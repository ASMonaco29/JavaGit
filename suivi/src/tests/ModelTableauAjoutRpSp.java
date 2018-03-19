package tests;

import cda.Question;
import cda.ListeQuestionnaires;
import cda.Questionnaire;
import cda.Reponse;
import cda.Sportif;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.table.AbstractTableModel;


@SuppressWarnings("serial")
public class ModelTableauAjoutRpSp extends AbstractTableModel {
  
  @SuppressWarnings("rawtypes")
  Class[] types = new Class[] { 
      String.class, Boolean.class 
  }; 
  
  private Reponse reponse;
  private Questionnaire questionnaire;
  private ListeQuestionnaires questionnaires;
  
  private ArrayList<Question> questions;
  private ArrayList<Question> questions1;

  private Calendar cal;
  private Date dated1;
  private Date dated2;  
  private Date datef1;
  private Date datef2;
  

  
  private final String[] entetes = {"Question", "Réponse"};
  
  public ModelTableauAjoutRpSp(Sportif sp, int indxListeQa) {
      super();
      
      this.questionnaires = new ListeQuestionnaires();
      this.questions = new ArrayList<Question>();
      this.questions1 = new ArrayList<Question>();
      
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
      
      this.questionnaire = this.questionnaires.getListQ().get(indxListeQa);
      
  }

  public int getRowCount() {
      return this.questionnaire.getquListe().size();
  }

  public int getColumnCount() {
      return this.entetes.length;
  }

  public String getColumnName(int columnIndex) {
      return this.entetes[columnIndex];
  }
  
  public Reponse getListR(){
    return this.reponse;
  }
  
  public ListeQuestionnaires getListeQuestionnaires(){
    return this.questionnaires;
  }
  
  public Questionnaire getQuestionnaire(){
    return this.questionnaire;
  }

  public void setQuestionnaire(int indx){
    this.questionnaire = this.questionnaires.getListQ().get(indx);
    fireTableDataChanged();
  }
  
  public Object getValueAt(int rowIndex, int columnIndex) {
    switch(columnIndex){
        case 0:
            return this.questionnaire.getquListe().get(rowIndex).getQuestion();
        case 1:
            return this.questionnaire.getquListe().get(rowIndex).getChoixDeflt();
        default:
            return null; // Ne devrait jamais arriver
    }
  }
  
  
  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
      if(aValue != null){
        switch(columnIndex){
            case 1:
              this.questionnaire.getquListe().get(rowIndex).setChoixDeflt((Boolean)aValue);
                break;
        }
      }
  }
  
  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    if(columnIndex == 0){
      return false;
    } else {
      return true;
    }
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Class getColumnClass(int columnIndex) { 
      return types[columnIndex]; 
  } 
  
}
