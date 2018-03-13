package suivisportif;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

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
  public int addQuestionnaire(String titre, String sstitre,
      Date dateD, Date dateF, String msgFin, ArrayList<Question> quliste) {
    Questionnaire quest = new Questionnaire(titre, sstitre, dateD, dateF, msgFin);
    
    for (int i = 0; i < quliste.size(); i++) {
      quest.addQuestion(quliste.get(i));
    }
    listQ.add(quest);
    return 0;
  }
  
  public Object addQuestionnaire(String titre, String sstitre, Date dateD, Date dateF) {
    return addQuestionnaire(titre, sstitre, dateD, dateF, null, null);
  }
  
  
  /** Modifie un questionnaire de la liste de questionnaires.
   * 
   * @param nq : Questionnaire rassemblant les modifications à apporter.
   * @param index : indice du questionnaire à modifier.
   * @return
   */
  public int modifQuestionnaire(Questionnaire nq, int index) {
    if (index < 0 || index >= this.listQ.size()) {
      return -1;
    }
    int statut = testModifQuestionnaire(this.listQ.get(index));
    if (statut == -1) {
      return -1;
    }
    
    if (!this.listQ.get(index).getTitre().equals(nq.getTitre())) {
      this.listQ.get(index).setTitre(nq.getTitre());
    }
    if (!this.listQ.get(index).getSstitre().equals(nq.getSstitre())) {
      this.listQ.get(index).setSstitre(nq.getSstitre());
    }
    if (!this.listQ.get(index).getDateD().equals(nq.getDateD())) {
      this.listQ.get(index).setDateD(nq.getDateD());
    }
    if (!this.listQ.get(index).getDateF().equals(nq.getDateF())) {
      this.listQ.get(index).setDateF(nq.getDateF());
    }
    if (!this.listQ.get(index).getMessageFin().equals(nq.getMessageFin())) {
      this.listQ.get(index).setMessageFin(nq.getMessageFin());
    }
    this.listQ.get(index).setqListe(nq.getquListe());
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
  
  
  /** Donne le nombre de questionnaires.
   * 
   * @return : la taille de la liste de questionnaires.
   */
  public int getSizeListQ() {
    return this.listQ.size();
  }
  
  
  /** Le main du programme.
   * 
   * @param args : argument.
   * 
   */
  public static void main(String[] args) {
    try {
      Class.forName("org.mariadb.jdbc.Driver");
      
      String url = "jdbc:mariadb://localhost/suivi_sportif";
      String user = "root";
      String passwd = "";
      
      Connection conn = DriverManager.getConnection(url, user, passwd);
      System.out.println("Connection établie!");
      
      //Création d'un objet Statement
      Statement state = conn.createStatement();
      //L'objet ResultSet contient le résultat de la requête SQL
      ResultSet result = state.executeQuery("SELECT * FROM v_peutrepondrea WHERE 1");
      //On récupère les MetaData
      ResultSetMetaData resultMeta = result.getMetaData();
         
      System.out.println("\n******************************************************************************************************");
      //On affiche le nom des colonnes
      for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
        System.out.print("\t" + resultMeta.getColumnName(i) + "\t *");
      }
         
      System.out.println("\n******************************************************************************************************");
         
      while (result.next()) {         
        for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
          System.out.print("\t" + result.getObject(i).toString() + "\t |");
        }
            
        System.out.println("\n---------------------------------------------------------------------------------------------------");

      }

      result.close();
      state.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
