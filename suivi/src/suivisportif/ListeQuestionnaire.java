package suivisportif;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ListeQuestionnaire {
  
  private ArrayList<Questionnaire> listQ;

  public ListeQuestionnaire() {
    super();
    this.listQ = new ArrayList<Questionnaire>();
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
   * @param dateD : date de debut du questionnaire.
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
    }
    
    this.listQ.add(quest);
  }
  
  /**Modifie un Questionnaire.
   * 
   * @param quest : questionnaire a modifier
   */
  public void modifQuestionnaire(Questionnaire quest) {
    Date currD = new Date();
    Date d = quest.getDateD();
    Date f = quest.getDateF();
  }
}
