package jdbc;

import cda.ListeSports;
import cda.Sport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcListeSport {
  
  private ListeSports lspts;

  public JdbcListeSport() {
    super();
    this.lspts = new ListeSports();
  }

  public ListeSports getLspts() {
    return lspts;
  }
  
  /**
   * Fonction permettant de récuperer tout les sports de la BDD.
   */
  public void initialiserListeSportsJdbc() {
    this.lspts.reinitialiser();
    
    //Création d'un objet Statement
    try {
      ResultSet resultat;
      Sport variable;
      Statement stmt = LaConnection.getInstance().createStatement();
      resultat = stmt.executeQuery("SELECT * FROM `t_sports_spt`");
        
      while (resultat.next()) {
        variable = new Sport((String)resultat.getObject("spt_nom"));
        this.lspts.ajouterSports(variable);
      }
        
        
    } catch (SQLException e) {
      e.printStackTrace();
    }
      
  }
  
  /**
   * Fonction permettant de savoir si un Sport existe dans la liste.
   * @param s le sport à verifier
   * @return retourne true si le sport existe dans la liste, false sinon.
   */
  public boolean contientJdbc(Sport s) {
    return this.lspts.contient(s);
  }
  
  /**
   * Fonction permettant de recuperer un Sport qui existe dans la liste.
   * @param nom Le nom du sport à récuperer
   * @return Le sport si trouvé, null sinon.
   */
  public Sport retourneSportJdbc(String nom) {
    return this.lspts.retourneSport(nom);
  }

  @Override
  public String toString() {
    return "JDBCListeSport [lspts=" + lspts + "]";
  }
  
  /**
   * Fonction permettant d'ajouter un Sport dans la BDD(et la liste).
   * @param nom Le nom du sport à ajouter
   * @return true si ajouté, false sinon.
   */
  public boolean ajouterSportJdbc(String nom) {
    boolean ajoutersport = false;
    
    if (this.retourneSportJdbc(nom) == null) {
      //Création d'un objet Statement
      try {
        int resultat;
        Statement stmt = LaConnection.getInstance().createStatement();
        resultat = stmt.executeUpdate("INSERT INTO `t_sports_spt`(`spt_id`, `spt_nom`) "
            + "VALUES (null,'" + nom + "');");
       
        if (resultat == 1) {
          ajoutersport = true;
          Sport a = new Sport(nom);
          this.lspts.ajouterSports(a);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    
    return ajoutersport;
  }
  
  /**
   * Fonction permettant de supprimer un sport.
   * @param sp le sport à supprimer.
   * @return true si le sport est supprimer, false sinon.
   */
  public boolean supprimerSportJdbc(Sport sp) {
    boolean supprimersport = false;
    
    if (sp != null) {
      //Création d'un objet Statement
      try {
        int resultat;
        Statement stmt = LaConnection.getInstance().createStatement();
        resultat = stmt.executeUpdate("DELETE FROM `t_sports_spt` "
            + "WHERE `spt_nom` = '" + sp.getNom() + "';");
        
        if (resultat == 1) {
          supprimersport = true;
          this.lspts.supprimerSports(sp);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return supprimersport;
  }
  
  public boolean supprimerSport(String nom) {
    Sport a = this.retourneSportJdbc(nom);
    return supprimerSportJdbc(a);
  }
  
  public int retourneIdSport(String nom) {
    int variable = -1;
    
    //Création d'un objet Statement
    try {
      ResultSet resultat;
      Statement stmt = LaConnection.getInstance().createStatement();
      resultat = stmt.executeQuery("SELECT * FROM `t_sports_spt` WHERE `spt_nom` = '" + nom + "' ;");
        
      while (resultat.next()) {
        variable = (int)resultat.getObject("spt_id");
      }
        
        
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return variable;
  }

}
