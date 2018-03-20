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
        Questionnaire q = this.lqtnrs.recupererQuestionnaireJdbc(
            (String)resultat.getObject("que_titre"),(String)resultat.getObject("que_sstitre"));
        Date date = resultat.getDate("rep_daterep");
        int i = resultat.getInt("rep_id");

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

        variable = new Reponse(i,date,reponses,s,q);
        this.lrps.ajouterReponse(variable);

      }


    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Fonction permettent d'ajouter une reponse
   * @return
   */
  public void ajouterReponseJdbc(Reponse r) {
    this.ajouterReponseJdbc(r.getSportif(), r.getQuestionnaire(), r.getDate(), r.getReponses());
  }

  /**
   * Fonction permettent d'ajouter une reponse
   * @return
   */
  public boolean ajouterReponseJdbc(Sportif s, Questionnaire q, java.util.Date date, ArrayList<Boolean> rep) {
    boolean ajouterreponse = false;

    if (this.retourneReponseJdbc(s,q,date) == null) {
      try {
        int resultat;
        Statement stmt = LaConnection.getInstance().createStatement();
        resultat = stmt.executeUpdate("INSERT INTO `t_reponses_rep`(`rep_id`, `rep_daterep`, `spo_pseudo`, `que_id`) VALUES ( null," + date + "," + s + "," + q + ")");

        if (resultat == 1) {
          ajouterreponse = true;
          int identifiant = this.retourneReponseJdbc(s,q,date).getId();
          //TODO Recuperer les id des reponsepossible et créer des t_rpc
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return ajouterreponse;
  }

  private Reponse retourneReponseJdbc(Sportif s, Questionnaire q, java.util.Date date) {
    return this.lrps.retourneReponse(s,q,date);
  }

  /**
   * Fonction permettent de supprimer un réponse à la bdd
   */
  public int supprimerReponseJdbc(Sportif s, Questionnaire q, java.util.Date date) {
    Reponse r = retourneReponseJdbc(s, q, date);
    this.lrps.supprimerReponse(r);
    try {
      Statement stmt = LaConnection.getInstance().createStatement();
      stmt.executeUpdate("DELETE FROM t_reponse_rep WHERE"
          + "rep_id = " + r.getId());
      return 0;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return -1;
  }

  /**
   * Fonction permettent de supprimer un réponse à la bdd
   */
  public int supprimerToutesReponsesJdbc(Questionnaire q) {
    for(int i = 0; i < this.lrps.getSizeListR(); i++) {
      if (this.lrps.getReponses().get(i).getQuestionnaire().getId() == q.getId()) {
        this.lrps.supprimerReponse(this.lrps.getReponses().get(i));
      }
    }
    try {
      Statement stmt = LaConnection.getInstance().createStatement();
      stmt.executeUpdate("DELETE FROM t_reponse_rep WHERE que_id = " + q.getId());
      return 0;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return -1;
  }

  @Override
  public String toString() {
    return "lrps=" + lrps + ", lspts=" + lspts + ", lqtnrs=" + lqtnrs + "";
  }


}
