package suivisportif;

import java.sql.Date;
import java.util.ArrayList;


public class Questionnaire {
  private String titre;
  private String sstitre;
  private Date dateD;
  private Date dateF;
  private String messageFin;
  private ArrayList<Question> quListe;

  /**public Questionnaire(String titre, Date dateD, Date dateF).
   * Le constructeur de l'objet Questionnaire
   */
  public Questionnaire(Date dateD, Date dateF) {
    super();
    this.dateD = dateD;
    this.dateF = dateF;
    this.quListe = new ArrayList<Question>();
    this.titre = new String();
    this.sstitre = new String();
    this.messageFin = new String();
  }

  public String getTitre() {
    return this.titre;
  }

  public void setTitre(String s) {
    this.titre = s;
  }

  public String getSstitre() {
    return this.sstitre;
  }

  public void setSstitre(String s) {
    this.sstitre = s;
  }

  public Date getDateD() {
    return dateD;
  }

  public void setDateD(Date dateD) {
    this.dateD = dateD;
  }

  public Date getDateF() {
    return dateF;
  }

  public void setDateF(Date dateF) {
    this.dateF = dateF;
  }

  public String getMessageFin() {
    return messageFin;
  }

  public void setMessageFin(String messageFin) {
    this.messageFin = messageFin;
  }

  public ArrayList<Question> getquListe() {
    return quListe;
  }

  public void setqListe(ArrayList<Question> quListe) {
    this.quListe = quListe;
  }

  /** public void addQuestion(Question e).
   * <b>Ajoute une question a la liste de questions du questionnaire</b>
   * <p>
   * La fonction va ajouter la question e passée en argument a la liste question
   * du questionnaire en utilisant la méthode add de l'objet ArrayList
   * </p>
   * @param e : La question a ajouter au questionnaire.
   */
  public void addQuestion(Question e) {
    if (!this.quListe.add(e)) {
      System.out.println("Erreur lors de l'ajout de la question");
    }
  }
  
  /**Supprime une question de la liste de questions du questionnaire.
   * 
   * @param index : l'indice de la question à supprimer
   */
  public void removeQuestion(int index) {
    if (index < 0 || index >= this.quListe.size()) {
      System.out.println("Erreur index invalide");
      return;
    }
    Question q = this.quListe.get(index);
    if (!this.quListe.remove(q)) {
      System.out.println("Erreur lors de la suppression de la question");
    }
  }
  
  /**Modifie la réponse par defaut d'une question
   * 
   * @param index : indice de la question à modifier
   * @param rd : nouvelle réponse par défaut
   */
  public void modifQuestionRD(int index, boolean rd) {
    Question quest = this.quListe.get(index);
    quest.setChoixDeflt(rd);
    this.quListe.set(index, quest);
  }
  
  /**Modifie le titre d'une question
   * 
   * @param index : indice de la question à modifier
   * @param t : nouveau titre de la question
   */
  public void modifQuestionT(int index, String t) {
    Question quest = this.quListe.get(index);
    quest.setQuestion(t);
    this.quListe.set(index, quest);
  }
  
  /**Déplace une question dans la liste de question (echange de place deux questions)
   * 
   * @param i1 : indice de la première question
   * @param i2 : indice de la deuxième question
   */
  public void switchQuestion(int i1, int i2) {
    if ((i1 < 0 || i1 >= this.quListe.size()) || (i2 < 0 || i2 >= this.quListe.size())) {
      System.out.println("Erreur index invalide");
      return;
    }
    Question q1 = this.quListe.get(i1);
    Question q2 = this.quListe.get(i2);
    this.quListe.set(i1, q2);
    this.quListe.set(i2, q1);
  }
}
