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
  
  
  /**Ajoute/Crée un questionnaire.
   * 
   * @param titre : titre du question.
   * @param sstitre : sous titre du question.
   * @param dateD : date de début du questionnaire.
   * @param dateF : date de fin du questionnaire.
   * @return 
   */
  @SuppressWarnings("resource")
  public int addQuestionnaire(String titre, String sstitre, String msgFin,
      Date dateD, Date dateF, Question[] quliste) {
    Questionnaire quest = new Questionnaire(dateD, dateF);
    quest.setTitre(titre);
    quest.setSstitre(sstitre);
    quest.setMessageFin(msgFin);
    
    for (int i = 0; i < quliste.length; i++) {
      quest.addQuestion(quliste[i]);
    }
    listQ.add(quest);
    return 0;
  }
  
  public Object addQuestionnaire(String titre, String sstitre, Date dateD, Date dateF) {
    return addQuestionnaire(titre, sstitre, null, dateD, dateF, null);
  }
  
  
  /**Modifie un Questionnaire.
   * 
   * @param quest : questionnaire à modifier
   */
  @SuppressWarnings("resource")
  public int modifQuestionnaire(int index, String titre, String sstitre, Date dateD, Date dateF, String msgF, ArrayList<Question> lq) {
    if (index < 0 || index >= this.listQ.size()) {
      return -1;
    }
    int statut = testModifQuestionnaire(this.listQ.get(index));
    if (statut == -1) {
      return -1;
    }
    
    if (!this.listQ.get(index).getTitre().equals(titre)) {
      this.listQ.get(index).setTitre(titre);
    }
    if (!this.listQ.get(index).getSstitre().equals(sstitre)) {
      this.listQ.get(index).setSstitre(sstitre);
    }
    if (!this.listQ.get(index).getDateD().equals(dateD)) {
      this.listQ.get(index).setDateD(dateD);
    }
    if (!this.listQ.get(index).getDateF().equals(dateF)) {
      this.listQ.get(index).setDateF(dateF);
    }
    if (!this.listQ.get(index).getMessageFin().equals(msgF)) {
      this.listQ.get(index).setMessageFin(msgF);
    }
    
    for (int i = 0; i < this.listQ.get(index).getquListe().size(); i++) {
      if(!lq.get(i).equals(this.listQ.get(index).getquListe().get(i))) {
        this.listQ.get(index).getquListe().get(i).setChoixDeflt(lq.get(i).getChoixDeflt());
        this.listQ.get(index).getquListe().get(i).setQuestion(lq.get(i).getQuestion());
      }
    }
    return 0;
  }
  
  
  /** Test si on peu modifier un questionnaire par rapport a la date courante
   * et à ses dates de début et de fin.
   * 
   * @param quest : le questionnaire a modifier
   * @return : -2 questionnaire non commencé , -1 questionnaire commencé, 0 questionnaire est fermé.
   */
  public int testModifQuestionnaire(Questionnaire quest) {
    Date d = quest.getDateD();
    Date f = quest.getDateF();
    Date currD = new Date();
    if (d.after(currD)) {
      return -2;
    }
    if (d.after(currD) && f.before(currD)) {
      return -1;
    }
    return 0;
  }
  
  
  /** Supprime un questionnaire de la liste.
   * 
   * @param q : le questionnaire à supprimer.
   * @return -1 si l'argument n'est pas valide, le questionnaire n'est pas modifiable
   *         ou bien si la suppression ne s'est pas bien passée. Sinon renvoie 0.
   */
  public int supprQuestionnaire(Questionnaire q) {
    if (q == null || testModifQuestionnaire(q) == -1) {
      return -1;
    }
    if (!this.listQ.remove(q)) {
      return -1;
    }
    return 0;
  }
  
  
  /** Le main du programme.
   * 
   * @param args : argument.
   * 
   */
  @SuppressWarnings("deprecation")
  public static void main(String[] args) {
  }
}
