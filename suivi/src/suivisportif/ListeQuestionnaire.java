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
  
  public int testModifQuestionnaire(Questionnaire quest) {
    Date d = quest.getDateD();
    Date f = quest.getDateF();
    Date currD = new Date();
    
    if (d.before(currD)) {
      return -2;
    }
    if (d.after(currD) && f.before(currD)) {
      return -1;
    }
    return 0;
  }
  
  /**Ajoute/Crée un questionnaire.
   * 
   * @param titre : titre du question.
   * @param sstitre : sous titre du question.
   * @param dateD : date de début du questionnaire.
   * @param dateF : date de fin du questionnaire.
   * @return 
   */
  @SuppressWarnings("resource")
  public int addQuestionnaire(String titre, String sstitre, String msgFin, Date dateD, Date dateF, Question... quliste) {
    Questionnaire quest = new Questionnaire(dateD, dateF);
    quest.setTitre(titre);
    quest.setSstitre(sstitre);
    quest.setMessageFin(msgFin);
    
    for(int i = 0; i < quliste.length; i++) {
      quest.addQuestion(quliste[i]);
    }
    listQ.add(quest);
    return 0;
  }
  
  public Object addQuestionnaire(String titre, String sstitre, Date dateD, Date dateF) {
    return addQuestionnaire( titre, sstitre, null, dateD, dateF);
  }
  
  /**Modifie un Questionnaire.
   * 
   * @param quest : questionnaire a modifier
   */
  @SuppressWarnings({ "deprecation", "unused", "resource" })
  public void modifQuestionnaireDate(Questionnaire quest) {
    Date currD = new Date();
    Date d = quest.getDateD();
    Date f = quest.getDateF();
    Date tmp;
    Scanner sc = new Scanner(System.in);
    String scanne = new String();
    int j;
    int m;
    int a;
    
    if (currD.after(d) == true && currD.before(f) == true) {
      System.out.println("Vous ne pouvez modifier que la date de fin!");
      tmp = f;
      System.out.println("Le jour de fin :  ");
      j = sc.nextInt();
      System.out.println("Le mois de fin :  ");
      m = sc.nextInt();
      System.out.println("L'année de fin :  ");
      a = sc.nextInt() - 1900;
      f = new Date(a,m,j);
    }
    else if (currD.after(d) == true && currD.before(f) == true) {
      System.out.println("Quelle date voulez-vou modifiez ? [d = debut / f = fin]  ");
      scanne = sc.nextLine();
    }
  }
  
  /** Le main du programme.
   * 
   * @param args : argument.
   */
  @SuppressWarnings("deprecation")
  public static void main(String[] args) {
    ListeQuestionnaire lq = new ListeQuestionnaire();
    lq.addQuestionnaire("titre","sous titre", new Date(), new Date(2020 - 1900, 3, 8));
    for (int i = 0; i < lq.listQ.size(); i++) {
      System.out.println(lq.listQ.get(i).toString());
    }
  }
}
