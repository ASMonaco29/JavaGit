package jdbc;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import cda.ListeReponses;
import cda.Questionnaire;
import cda.Reponse;
import cda.Sportif;

public class JdbcListeReponse {
  private ListeReponses lrps;
  private JdbcListeSportif lspts;
  private JdbcListeQuestionnaire lqtnrs;

  public JdbcListeReponse() {
    super();
    this.lrps = new ListeReponses();
    this.lspts = new JdbcListeSportif();
    this.lqtnrs = new JdbcListeQuestionnaire();
  }

  public ListeReponses getLrps() {
    return lrps;
  }
  
  public void initialiserListeReponsesJdbc() {
    this.lrps.reinitialiser();
    this.lspts.initialiserListeSportifsJdbc();
    this.lqtnrs.initialiserListeQuestionnaireJdbc();
    
  //Création d'un objet Statement
    try {
      ResultSet resultat;
      Reponse variable;
      Statement stmt = LaConnection.getInstance().createStatement();
      resultat = stmt.executeQuery("SELECT * FROM `t_reponses_rep` NATURAL JOIN `t_questionnaire_que`;");
        
      while (resultat.next()) {
          Sportif s = this.lspts.retourneSportifJdbc((String)resultat.getObject("spo_pseudo"));
          Questionnaire q = this.lqtnrs.retourneQuestionnaireJdbc((String)resultat.getObject("que_titre"));
          Date date = resultat.getDate("rep_daterep");
          
          ArrayList<Boolean> reponses = new ArrayList<Boolean>();
          ResultSet result;
          Statement stm = LaConnection.getInstance().createStatement();
          result = stm.executeQuery("SELECT * FROM `t_reponseschoisies_rpc` NATURAL JOIN "
              + "`t_listreponses_lrp` WHERE `rep_id` =" + resultat.getInt("rep_id") + ";");
          
          while (result.next()) {
            boolean rep = false;
            String str = result.getString("lrp_intitule");
            if( str.equals("oui")) {
              rep = true;
            }else {
              rep = false;
            }
            reponses.add(rep);
          }
          
          variable = new Reponse(date,reponses,s,q);
          this.lrps.ajouterReponse(variable);
          
      }
        
        
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public int ajouterReponseJdbc(String pseudo, String nom, String prenom, Date naissance, String nomSport) {
    return 0;
  }
}
