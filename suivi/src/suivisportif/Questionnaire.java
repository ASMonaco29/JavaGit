package suivisportif;

import java.util.ArrayList;
import java.util.Date;


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
  public Questionnaire(java.util.Date dateD2, java.util.Date dateF2) {
    super();
    this.dateD = dateD2;
    this.dateF = dateF2;
    this.quListe = new ArrayList<Question>();
    this.titre = new String();
    this.sstitre = new String();
    this.messageFin = new String();
  }

  /**public Questionnaire(String titre, String sstitre, Date dateD, Date dateF, String messageFin).
   * 
   * @param titre : titre du questionnaire
   * @param sstitre : sous titre du questionnaire
   * @param dateD : date de début du questionnaire
   * @param dateF : date de fin du questionnaire
   * @param messageFin : message de fin du questionnaire
   */
  public Questionnaire(String titre, String sstitre, Date dateD, Date dateF, String messageFin) {
    super();
    this.titre = titre;
    this.sstitre = sstitre;
    this.dateD = dateD;
    this.dateF = dateF;
    this.messageFin = messageFin;
    this.quListe = new ArrayList<Question>();
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
   */
  public void addQuestion(Question e) {
    for (int i = 0; i < this.quListe.size(); i++) {
      if (!this.quListe.get(i).equals(e)) {
        System.out.println("Erreur, question déjà existante dans le questionnaire.");
        return;
      }
    }
    if (!this.quListe.add(e)) {
      System.out.println("Erreur lors de l'ajout de la question");
    }
  }
  
  /**Supprime une question de la liste de questions du questionnaire.
   * 
   * @param index : l'indice de la question � supprimer
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
  
  /**Modifie la r�ponse par defaut d'une question.
   * 
   * @param index : indice de la question � modifier
   * @param rd : nouvelle r�ponse par d�faut
   */
  public void modifQuestionRd(int index, boolean rd) {
    Question quest = this.quListe.get(index);
    quest.setChoixDeflt(rd);
    this.quListe.set(index, quest);
  }
  
  /**Modifie le titre d'une question.
   * 
   * @param index : indice de la question � modifier
   * @param t : nouveau titre de la question
   */
  public void modifQuestionT(int index, String t) {
    Question quest = this.quListe.get(index);
    quest.setQuestion(t);
    this.quListe.set(index, quest);
  }
  
  /**Déplace une question dans la liste de question (échange de place deux questions).
   * 
   * @param i1 : indice de la premi�re question
   * @param i2 : indice de la deuxi�me question
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

  @Override
  public String toString() {
    String res = "Questionnaire [titre=" + titre + ", sstitre=" + sstitre + ","
        + " dateD=" + dateD + ", dateF=" + dateF
        + ", messageFin=" + messageFin + ", quListe=";
    for (int i = 0; i < quListe.size(); i++) {
      res += quListe.get(i).toString();
    }
    return res + "]";
  }
}
