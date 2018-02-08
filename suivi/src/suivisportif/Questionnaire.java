package suivisportif;

import java.sql.Date;
import java.util.ArrayList;

public class Questionnaire {
  String titre;
  String sstitre;
  Date dateD;
  Date dateF;
  String messageFin;
  ArrayList<Question> quListe;

  /**public Questionnaire(String titre, Date dateD, Date dateF).
   * Le constructeur de l'objet Questionnaire
   */
  public Questionnaire(Date dateD, Date dateF) {
    super();
    this.dateD = dateD;
    this.dateF = dateF;
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
}
