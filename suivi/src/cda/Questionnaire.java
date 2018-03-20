package cda;

import java.util.ArrayList;
import java.util.Date;


public class Questionnaire {
  private int id = 0;
  private String titre;
  private String sstitre;
  private Date dateD;
  private Date dateF;
  private String messageFin;
  private ArrayList<Question> quListe;

  
  public Questionnaire() {
    super();
  }

  public Questionnaire(int id, String titre, String sstitre, Date dateD, Date dateF, String messageFin,
      ArrayList<Question> quListe) {
    super();
    this.id = id;
    this.titre = titre;
    this.sstitre = sstitre;
    this.dateD = dateD;
    this.dateF = dateF;
    this.messageFin = messageFin;
    this.quListe = quListe;
  }

  /**public Questionnaire(String titre, String sstitre, Date dateD, Date dateF, String messageFin).
   * 
   * @param titre : titre du questionnaire
   * @param sstitre : sous titre du questionnaire
   * @param dateD : date de début du questionnaire
   * @param dateF : date de fin du questionnaire
   * @param messageFin : message de fin du questionnaire
   */
  public Questionnaire(String titre, String sstitre, Date dateD, Date dateF,
      String messageFin, ArrayList<Question> lq) {
    super();
    this.titre = titre;
    this.sstitre = sstitre;
    this.dateD = dateD;
    this.dateF = dateF;
    this.messageFin = messageFin;
    this.quListe = lq;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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
   * La fonction va ajouter la question e pass�e en argument a la liste question
   * du questionnaire en utilisant la m�thode add de l'objet ArrayList
   * </p>
   * @param e : La question a ajouter au questionnaire.
   * 
   * @return -1 si une erreur, 0 sinon.
   */
  public int addQuestion(Question e) {
    if (e == null) {
      return -1;
    }
    for (int i = 0; i < this.quListe.size(); i++) {
      if (this.quListe.get(i).equals(e)) {
        System.out.println("Erreur, question déjà existante dans le questionnaire.");
        return -1;
      }
    }
    if (!this.quListe.add(e)) {
      System.out.println("Erreur lors de l'ajout de la question");
      return -1;
    }
    return 0;
  }
  
  
  /**Supprime une question de la liste de questions du questionnaire.
   * 
   * @param index : l'indice de la question à supprimer
   * 
   * @return : -1 si une erreur, ou question inexistante, 0 sinon.
   */
  public int removeQuestion(int index) {
    if (index < 0 || index >= this.quListe.size()) {
      System.out.println("Erreur index invalide");
      return -1;
    }
    Question q = this.quListe.get(index);
    if (!this.quListe.remove(q)) {
      System.out.println("Erreur lors de la suppression de la question");
      return -1;
    }
    return 0;
  }
  
  
  /**Modifie la réponse par défaut d'une question.
   * 
   * @param index : indice de la question à modifier
   * @param rd : nouvelle réponse par défaut
   */
  public int modifQuestionRd(int index, String rd) {
    if (index < 0 || index >= this.quListe.size()) {
      return -1;
    }
    Question quest = this.quListe.get(index);
    quest.setChoixDeflt(rd);
    this.quListe.set(index, quest);
    return 0;
  }
  
  /**Modifie le titre d'une question.
   * 
   * @param index : indice de la question à modifier
   * @param t : nouveau titre de la question
   */
  public int modifQuestionT(int index, String t) {
    if (index < 0 || index >= this.quListe.size() || t == null) {
      return -1;
    }
    Question quest = this.quListe.get(index);
    quest.setQuestion(t);
    for (int i = 0; i < this.quListe.size(); i++) {
      if (this.quListe.get(i).equals(quest)) {
        System.out.println("Erreur, question déjà existante dans le questionnaire.");
        return -1;
      }
    }
    this.quListe.set(index, quest);
    return 0;
  }
  
  /**Déplace une question dans la liste de question (échange de place deux questions).
   * 
   * @param i1 : indice de la première question
   * @param i2 : indice de la deuxième question
   */
  public int switchQuestion(int i1, int i2) {
    if ((i1 < 0 || i1 >= this.quListe.size()) || (i2 < 0 || i2 >= this.quListe.size())) {
      System.out.println("Erreur index invalide");
      return -1;
    }
    Question q1 = this.quListe.get(i1);
    Question q2 = this.quListe.get(i2);
    this.quListe.set(i1, q2);
    this.quListe.set(i2, q1);
    return 0;
  }

  @Override
  public String toString() {
    return "Questionnaire [id=" + id + ", titre=" + titre + ", sstitre=" + sstitre + ", dateD=" + dateD + ", dateF="
        + dateF + ", messageFin=" + messageFin + ", quListe=" + quListe + "]";
  }

  
}