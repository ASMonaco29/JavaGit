package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import cda.ListeQuestionnaires;
import cda.Question;
import cda.Questionnaire;
import cda.Sportif;

public class JdbcListeQuestionnaire {
  
  private ListeQuestionnaires lqtnrs;
  
  
  public JdbcListeQuestionnaire() {
    super();
    this.lqtnrs = new ListeQuestionnaires();
  }

  public ListeQuestionnaires getLqtnrs() {
    return lqtnrs;
  }

  public void initialiserListeQuestionnaireJdbc() {
    this.lqtnrs.reinitialiser();
    
    //Création d'un objet Statement
    try {
      ResultSet questionnaire;
      ResultSet question;
      ResultSet reponse;
      Questionnaire qtnr;
      Question qtn;
      Statement stmt = LaConnection.getInstance().createStatement();
      questionnaire = stmt.executeQuery("SELECT * FROM `t_questionnaire_que`");
      
      while (questionnaire.next()) {
        qtnr = new Questionnaire(null, null, null, null, null, null);
        qtnr.setId(questionnaire.getInt("que_id"));
        qtnr.setTitre((String) questionnaire.getObject("que_titre"));
        qtnr.setSstitre((String)questionnaire.getObject("que_sstitre"));
        qtnr.setMessageFin((String)questionnaire.getObject("que_msgfin"));
        qtnr.setDateD((Date)questionnaire.getObject("que_dateD"));
        qtnr.setDateF((Date)questionnaire.getObject("que_dateF"));
        question = stmt.executeQuery("SELECT * FROM `t_question_qtn` WHERE que_id = "
            + questionnaire.getInt("que_id"));
        while (question.next()) {
          reponse = stmt.executeQuery("SELECT * FROM t_listereponses_lrp WHERE qtn_id = "
              + question.getInt("qtn_id"));
          qtn = new Question(null, null);
          qtn.setId(question.getInt("qtn_id"));
          qtn.setQuestion((String) question.getString("qtn_intitule"));
          while (reponse.next()) {
            if(question.getInt("qtn_repdef") == reponse.getInt("lrp_id")) {
              qtn.setChoixDeflt(reponse.getString("lrp_intitule"));
            }
            qtn.getChoixRep().add(reponse.getString("lrp_intitule"));
          }
          qtnr.getquListe().add(qtn);
        }
        this.lqtnrs.addQuestionnaire(qtnr.getTitre(), qtnr.getSstitre(), qtnr.getDateD(),
            qtnr.getDateF(), qtnr.getMessageFin(), qtnr.getquListe());
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Questionnaire retourneQuestionnaireJdbc(String object) {
    return null;
  }

}
