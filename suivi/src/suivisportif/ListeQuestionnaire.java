package suivisportif;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ListeQuestionnaire {
  
  private ArrayList<Questionnaire> listQ;

  public ListeQuestionnaire() {
    super();
    listQ = new ArrayList<Questionnaire>();
  }

  public ArrayList<Questionnaire> getListQ() {
    return listQ;
  }

  public void setListQ(ArrayList<Questionnaire> listQ) {
    this.listQ = listQ;
  }
  
  /**Ajoute un questionnaire.
   * 
   * @param titre : titre du question.
   * @param sstitre : sous titre du question.
   * @param dateD : date de début du questionnaire.
   * @param dateF : date de fin du questionnaire.
   */
  @SuppressWarnings("resource")
  public void addQuestionnaire(String titre, String sstitre, Date dateD, Date dateF) {
    Questionnaire quest = new Questionnaire(dateD, dateF);
    quest.setTitre(titre);
    quest.setSstitre(sstitre);
    
    Scanner sc = new Scanner(System.in);
    String scanne = "o";
    String rd;
    Question q;
    
    while (scanne.toUpperCase().equals("O") == true) {
      System.out.println("Saisissez l'intitulé de la question : ");
      scanne = sc.nextLine();
      System.out.println("Réponse par défaut [0 = oui / 1 = non] : ");
      rd = sc.nextLine();
      if (Integer.parseInt(rd) == 0) {
        q = new Question(scanne, true);
      } else {
        q = new Question(scanne, false);
      }
      quest.addQuestion(q);
      System.out.println("Voulez-vous continuer [o/n] :  ");
      scanne = sc.nextLine();
    }

    this.listQ.add(quest);
    return;
  }
  
  /**Modifie un Questionnaire.
   * 
   * @param quest : questionnaire a modifier
   */
  /*
  public void modifQuestionnaireDate(Questionnaire quest) {
    Date currD = new Date();
    Date d = quest.getDateD();
    Date f = quest.getDateF();
    Date tmp;
    Scanner sc = new Scanner(System.in);
    int j;
    int m;
    int a;
    
    if (currD.after(d) == true && currD.before(f) == true) {
      
    }
  }*/
  
  @SuppressWarnings("deprecation")
  public static void main(String[] args) {
    ListeQuestionnaire lQ = new ListeQuestionnaire();
    lQ.addQuestionnaire("titre","sous titre", new Date(), new Date(2020 - 1900, 3, 8));
    for(int i = 0; i < lQ.listQ.size(); i++) {
      System.out.println(lQ.listQ.get(i).toString());
    }
  }
}
