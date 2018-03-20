package jdbc;

import cda.ListeQuestionnaires;
import cda.Question;
import cda.Questionnaire;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class JdbcListeQuestionnaire {

  private ListeQuestionnaires lqtnrs;
  private ArrayList<Question> lq;

  /** Constructeur.
   * 
   */
  public JdbcListeQuestionnaire() {
    super();
    this.lqtnrs = new ListeQuestionnaires();
    this.lq = new ArrayList<Question>();
  }

  public ListeQuestionnaires getLqtnrs() {
    return lqtnrs;
  }

  /** Initialise la liste de questionnaires avec les nouvelles données de la BDD.
   * 
   */
  public void initialiserListeQuestionnaireJdbc() {
    this.lqtnrs.reinitialiser();

    //Création d'un objet Statement
    try {
      ResultSet questionnaire;
      Questionnaire qtnr;
      Question qtn;
      Statement stmt = LaConnection.getInstance().createStatement();
      Statement stmt2 = LaConnection.getInstance().createStatement();
      Statement stmt3 = LaConnection.getInstance().createStatement();
      questionnaire = stmt.executeQuery("SELECT * FROM `t_questionnaire_que`");

      while (questionnaire.next()) {

        qtnr = new Questionnaire(null, null, null, null, null, new ArrayList<Question>());
        qtnr.setId(questionnaire.getInt("que_id"));
        qtnr.setTitre((String) questionnaire.getObject("que_titre"));
        qtnr.setSstitre((String)questionnaire.getObject("que_sstitre"));
        qtnr.setMessageFin((String)questionnaire.getObject("que_msgfin"));
        qtnr.setDateD((Date)questionnaire.getObject("que_dateD"));
        qtnr.setDateF((Date)questionnaire.getObject("que_dateF"));

        ResultSet question;
        question = stmt2.executeQuery("SELECT * FROM `t_question_qtn` WHERE que_id = "
            + questionnaire.getInt("que_id"));

        while (question.next()) {

          qtn = new Question(null, true);
          qtn.setId(question.getInt("qtn_id"));
          qtn.setQuestion((String) question.getString("qtn_intitule"));
          int r = question.getInt("qtn_repdef");

          ResultSet reponse;
          reponse = stmt3.executeQuery("SELECT * FROM t_listreponses_lrp WHERE qtn_id = "
              + question.getInt("qtn_id"));

          while (reponse.next()) {

            if (r == reponse.getInt("lrp_id")) {
              if (reponse.getString("lrp_intitule").equals("oui")) {
                qtn.setChoixDeflt(true);
              } else {
                qtn.setChoixDeflt(false);
              }
            }
          }
          qtnr.getquListe().add(qtn);

        }
        this.lqtnrs.addQuestionnaire(qtnr);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void initialiserListeQuestionJdbc() {
    this.lq.clear();
  }

  /** Ajoute/insert un questionnaire dans la BDD.
   * 
   * @param titre : titre du nouveau Questionnaire.
   * @param sstitre : sstitre du nouveau Questionnaire.
   * @param dateD : date de début du nouveau Questionnaire.
   * @param dateF : date de fin du nouveau Questionnaire.
   * @param messageF : message de fin du nouveau Questionnaire.
   * @param listq : questions du nouveau Questionnaire.
   * @return : objet Questionnaire résultant de la création à partir des paramètres.
   */
  public Questionnaire ajouterQuestionnaireJdbc(String titre, String sstitre, Date dateD,
      Date dateF, String messageF, ArrayList<Question> listq) {

    if (this.lqtnrs.retourneQuestionnaire(titre, sstitre) == null) {

      int questionnaire;
      int idQuestionnaire = -1;
      try {

        Questionnaire quest = new Questionnaire(titre,sstitre,dateD,dateF,messageF,listq);
        java.sql.Date dated = new java.sql.Date(dateD.getTime());
        java.sql.Date datef = new java.sql.Date(dateF.getTime());

        Statement stmt = LaConnection.getInstance().createStatement();
        questionnaire = stmt.executeUpdate("INSERT INTO `t_questionnaire_que`"
            + "(`que_id`, `que_titre`, `que_sstitre`, `que_msgfin`, `que_dateD`, `que_dateF`) "
            + "VALUES ("
            + "null"
            + ", '" + titre
            + "','" + sstitre
            + "','" + messageF
            + "','" + dated
            + "','" + datef + "');");

        if (questionnaire == 1) {

          idQuestionnaire = this.recupererIdQuestionnaireJdbc(quest);
          quest.setId(idQuestionnaire);

          if (this.ajouterListeQuestionJdbc(quest, listq) == true) {

            this.lqtnrs.addQuestionnaire(quest);
            return quest;
          }
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return "" + lqtnrs.toString() + "";
  }

  private boolean ajouterListeQuestionJdbc(Questionnaire q, ArrayList<Question> listq) {
    boolean reussie = false;

    for (Question quest : listq) {

      int question;
      String rd;
      Statement stmt;

      try {

        stmt = LaConnection.getInstance().createStatement();
        question = stmt.executeUpdate("INSERT INTO `t_question_qtn`"
            + "(`qtn_intitule`, `que_id`, `qtn_id`) VALUES ('" + quest.getQuestion()
            + "'," + q.getId() + "," + null + ");");

        if (question == 1) {
          Statement stmt2 = LaConnection.getInstance().createStatement();
          ResultSet rs2 = stmt2.executeQuery("SELECT * FROM `t_question_qtn` "
              + "WHERE `qtn_intitule` = '" + quest.getQuestion() + "';");

          while (rs2.next()) {
            // Création des réponses lrp
            int questid = rs2.getInt("qtn_id");

            for (boolean s : quest.getChoixRep()) {
              if (s) {
                rd = "oui";
              } else {
                rd = "non";
              }
              Statement stmt3 = LaConnection.getInstance().createStatement();
              int i3 = stmt3.executeUpdate("INSERT INTO `t_listreponses_lrp`"
                  + "(`lrp_intitule`, `qtn_id`) VALUES "
                  + "('" + rd
                  + "'," + questid + ")");

              if (i3 == 0) {
                return false;
              }
            }

            Statement stmt4 = LaConnection.getInstance().createStatement();
            int i4 = stmt4.executeUpdate("INSERT INTO `t_listreponses_lrp`"
                + "(`lrp_intitule`, `qtn_id`) VALUES "
                + "('" + quest.getChoixDeflt()
                + "'," + questid + ")");

            if (i4 == 1) {

              Statement stmt5 = LaConnection.getInstance().createStatement();
              ResultSet rs5 = stmt5.executeQuery("SELECT * FROM `t_listreponses_lrp` "
                  + "WHERE `lrp_intitule` = '" + quest.getChoixDeflt() 
                  + "' AND `qtn_id`= " + questid);

              if (rs5.first()) {
                int sss = rs5.getInt("lrp_id");
                Statement stmt6 = LaConnection.getInstance().createStatement();
                int i6 = stmt6.executeUpdate("UPDATE `t_question_qtn` "
                    + "SET `qtn_repdef`=" + sss
                    + " WHERE `qtn_id` = " + questid + ";");

                if (i6 == 1) {
                  reussie = true;
                }
              }

            }
          }
        }

      } catch (SQLException e) {
        e.printStackTrace();
      }

    }

    return reussie;
  }

  private int recupererIdQuestionnaireJdbc(Questionnaire q) {
    int idQuest = -1;

    try {
      Statement stmt = LaConnection.getInstance().createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM `t_questionnaire_que` "
          + "WHERE `que_titre` = '" + q.getTitre()
          + "' AND `que_sstitre`= '" + q.getSstitre()
          + "';");

      if (rs.first()) {
        idQuest = rs.getInt("que_id");
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return idQuest;
  }

  public int supprimerQuestionnaire(int id) {
    Questionnaire q = this.lqtnrs.recupererQuestionnaire(id);
    return this.supprimerQuestionnaire(q);  
  }

  /** Supprime un Questionnaire de la BDD.
   * 
   * @param q : Questionnaire à supprimer.
   * @return : 0 en cas de succès, -1 sinon.
   */
  public int supprimerQuestionnaire(Questionnaire q) {

    if (this.lqtnrs.supprQuestionnaire(q) == 0) {
      try {
        Statement stmt = LaConnection.getInstance().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM `t_question_qtn` "
            + "WHERE `que_id` = " + q.getId() + ";");

        while (rs.next()) {
          int idQuestion = rs.getInt("qtn_id");
          Statement stmt2 = LaConnection.getInstance().createStatement();
          stmt2.executeUpdate("DELETE FROM `t_listreponses_lrp` "
              + "WHERE `qtn_id`=" + idQuestion + ";");
        }

        Statement stmt3 = LaConnection.getInstance().createStatement();
        stmt3.executeUpdate("DELETE FROM `t_question_qtn` "
            + "WHERE `que_id`=" + q.getId() + ";");

        Statement stmt4 = LaConnection.getInstance().createStatement();
        stmt4.executeUpdate("DELETE FROM `t_questionnaire_que` "
            + "WHERE `que_id`=" + q.getId() + ";");

        return 0;
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return -1;
  }

  /** Modifie un Questionnaire dans la BDD.
   * 
   * @param q : objet Questionnaire contenant les nouvelles valeurs.
   */
  public void modifierQuestionnaire(Questionnaire q) {
    if (this.lqtnrs.modifQuestionnaire(q) == 0) {

      try {
        Statement stmt = LaConnection.getInstance().createStatement();
        stmt.executeUpdate("UPDATE `t_questionnaire_que` "
            + "SET `que_titre`='" + q.getTitre()
            + "' WHERE `que_id`=" + q.getId() + ";");

      } catch (SQLException e) {
        e.printStackTrace();
      }


      try {
        Statement stmt = LaConnection.getInstance().createStatement();
        stmt.executeUpdate("UPDATE `t_questionnaire_que` "
            + "SET `que_sstitre`='" + q.getSstitre()
            + "' WHERE `que_id`=" + q.getId() + ";");

      } catch (SQLException e) {
        e.printStackTrace();
      }

      java.sql.Date dated = new java.sql.Date(q.getDateD().getTime());
      try {
        Statement stmt = LaConnection.getInstance().createStatement();
        stmt.executeUpdate("UPDATE `t_questionnaire_que` "
            + "SET `que_dateD`= '" + dated
            + "' WHERE `que_id`=" + q.getId() + ";");

      } catch (SQLException e) {
        e.printStackTrace();
      }

      java.sql.Date datef = new java.sql.Date(q.getDateF().getTime());
      try {
        Statement stmt = LaConnection.getInstance().createStatement();
        stmt.executeUpdate("UPDATE `t_questionnaire_que` "
            + "SET `que_dateF`='" + datef
            + "' WHERE `que_id`=" + q.getId() + ";");

      } catch (SQLException e) {
        e.printStackTrace();
      }

      try {
        Statement stmt = LaConnection.getInstance().createStatement();
        stmt.executeUpdate("UPDATE `t_questionnaire_que` "
            + "SET `que_msgfin`='" + q.getMessageFin()
            + "' WHERE `que_id`=" + q.getId() + ";");

      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  /** Récupère toutes les questions dans la BDD et les stocke dans l'attribut lq.
   * 
   */
  public void recupererToutesQuestionsJdbc() {
    Question qtn;
    try {
      Statement stmt = LaConnection.getInstance().createStatement();
      ResultSet question;
      ResultSet reponse;
      question = stmt.executeQuery("SELECT * FROM `t_question_qtn`");

      while (question.next()) {

        qtn = new Question(null, true);
        qtn.setId(question.getInt("qtn_id"));
        qtn.setQuestion((String) question.getString("qtn_intitule"));
        int r = question.getInt("qtn_repdef");
        reponse = stmt.executeQuery("SELECT * FROM t_listreponses_lrp WHERE qtn_id = "
            + question.getInt("qtn_id"));

        while (reponse.next()) {

          if (r == reponse.getInt("lrp_id")) {
            if (reponse.getString("lrp_intitule").equals("oui")) {
              qtn.setChoixDeflt(true);
            } else {
              qtn.setChoixDeflt(false);
            }
          }
        }
        this.lq.add(qtn);
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  public Questionnaire recupererQuestionnaireJdbc(String titre, String sstitre) {
    return this.lqtnrs.retourneQuestionnaire(titre, sstitre);
  }
}
