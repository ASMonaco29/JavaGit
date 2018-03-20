package jdbc;

import cda.ListeSportifs;
import cda.Sport;
import cda.Sportif;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Date;

public class JdbcListeSportif {
  private ListeSportifs lsptfs;
  private JdbcListeSport lspt; 

  /** Constructeur.
   * 
   */
  public JdbcListeSportif() {
    super();
    this.lsptfs = new ListeSportifs();
    this.lspt = new JdbcListeSport();

  }

  /** Initialise la liste de sportif avec les données de la BDD.
   * 
   */
  public void initialiserListeSportifsJdbc() {

    this.lsptfs.reinitialiser();
    this.lspt.initialiserListeSportsJdbc();
    //Création d'un objet Statement
    try {
      ResultSet resultat;
      Sportif variable;
      Statement stmt = LaConnection.getInstance().createStatement();
      resultat = stmt.executeQuery("SELECT * FROM `t_sportif_spo` NATURAL JOIN `t_sports_spt`");

      while (resultat.next()) {
        variable = new Sportif();
        variable.setPseudo((String)resultat.getObject("spo_pseudo"));
        variable.setNom((String)resultat.getObject("spo_nom"));
        variable.setPrenom((String)resultat.getObject("spo_prenom"));
        variable.setNaissance((Date)resultat.getObject("spo_dateN"));
        String s = (String)resultat.getObject("spt_nom");
        variable.setSport(this.lspt.retourneSportJdbc(s));
        this.lsptfs.ajouterSportif(variable);
      }


    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public ListeSportifs getLsptfs() {
    return lsptfs;
  }

  public JdbcListeSport getLspt() {
    return lspt;
  }

  /** Ajoute un sportif dans la BDD à partir de ses attributs.
   * 
   * @param pseudo : pseudo du nouveau sportif.
   * @param nom : nom du nouveau sportif.
   * @param prenom : prenom du nouveau sportif.
   * @param naissance : date de naissance du nouveau sportif.
   * @param nomSport : nom du sport du nouveau sportif.
   * @return 0 en cas de succès, -1 sinon.
   */
  public int ajouterSportifJdbc(String pseudo, String nom, String prenom,
      Date naissance, String nomSport) {

    if (this.retourneSportifJdbc(pseudo) == null) {
      Sportif a = new Sportif();
      if (!a.setPseudo(pseudo)) {
        return 1;
      } else if (!a.setNom(nom)) {
        return 2;
      } else if (!a.setPrenom(prenom)) {
        return 3;
      } else if (!a.setNaissance(naissance)) {
        return 4;
      }

      Sport sp = null;
      sp = this.lspt.retourneSportJdbc(nomSport);
      if (sp == null) {
        boolean creer = this.lspt.ajouterSportJdbc(nomSport);
        if (creer == false) {
          return 5;
        } else {
          sp = this.lspt.retourneSportJdbc(nomSport);
        }
      }
      a.setSport(sp);

      try {
        int resultat;
        int sportid = this.lspt.retourneIdSport(nomSport);

        java.sql.Date d = new java.sql.Date(naissance.getTime());

        Statement stmt = LaConnection.getInstance().createStatement();
        resultat = stmt.executeUpdate("INSERT INTO `t_sportif_spo`"
            + "(`spo_pseudo`, `spo_nom`, `spo_prenom`, `spo_dateN`, `spt_id`) VALUES "
            + "('" + pseudo + "'"
            + ",'" + nom + "'"
            + ",'" + prenom + "'"
            + ",'" + d + "'"
            + ",'" + sportid + "');");

        if (resultat == 1) {
          this.lsptfs.ajouterSportif(a);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }

    } else {
      return 6;
    }

    return -1;
  }

  /** Ajoute un sportif dans la BDD à partir d'un objet Sportif.
   * 
   * @param s : objet sportif à ajouter dans la BDD.
   * @return 0 en cas de succès, -1 sinon.
   */
  public int ajouterSportifJdbc(Sportif s) {
    return this.ajouterSportifJdbc(s.getPseudo(), s.getNom(), s.getPrenom(),
        s.getNaissance(), s.getSport().getNom());
  }

  public Sportif retourneSportifJdbc(String pseudo) {
    return this.lsptfs.retourneSportif(pseudo);
  }

  /**
   * Fonction permettant de supprimer un sportif.
   * @param sp le sport à supprimer.
   * @return true si le sport est supprimer, false sinon.
   */
  public boolean supprimerSportifJdbc(Sportif sp) {
    boolean supprimersportif = false;

    if (sp != null) {
      //Création d'un objet Statement
      try {
        int resultat;
        Statement stmt = LaConnection.getInstance().createStatement();
        resultat = stmt.executeUpdate("DELETE FROM `t_sportif_spo` WHERE"
            + " `spo_pseudo` = '" + sp.getPseudo() + "';");

        if (resultat == 1) {
          supprimersportif = true;
          this.lsptfs.supprimerSportif(sp.getPseudo());
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return supprimersportif;
  }

  public boolean supprimerSportifJdbc(String pseudo) {
    Sportif a = this.retourneSportifJdbc(pseudo);
    return supprimerSportifJdbc(a);
  }

  /** Modifie les données d'un sportif dans la BDD.
   * 
   * @param sp : Objet Sportif contenant les nouvelles données du sportif
   * @return : true en cas de succes, false sinon.
   */
  public boolean modifierSportifJdbc(Sportif sp) {
    boolean modifiersportif = false;

    if (this.lsptfs.retourneSportif(sp.getPseudo()) != null) {
      try {
        int resultat;
        Statement stmt = LaConnection.getInstance().createStatement();
        resultat = stmt.executeUpdate("UPDATE `t_sportif_spo` "
            + "SET `spo_nom`=" + sp.getNom() + ","
            + "`spo_prenom`=" + sp.getPrenom() + ","
            + "`spo_dateN`=" + sp.getNaissance() + ","
            + "`spt_id`=" + sp.getSport()
            + "WHERE `spo_pseudo` = " + sp.getPseudo() + ";");

        if (resultat == 1) {
          modifiersportif = true;
          this.lsptfs.modifierSportif(sp);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return modifiersportif;
  }

  @Override
  public String toString() {
    return " lsptfs=" + lsptfs + ", lspt=" + lspt + "";
  }
}
