package cda;
import java.util.ArrayList;

public class ListeSportifs {
 
  private ArrayList<Sportif> listeDeSportifs = new ArrayList<Sportif>();
  
  
  
  public ListeSportifs() {
    super();
    this.listeDeSportifs = new ArrayList<Sportif>();
  }

  
  public ArrayList<Sportif> getListeS() {
    return listeDeSportifs;
  }

  public void setListeS(ArrayList<Sportif> listeDeSportifs) {
    this.listeDeSportifs = listeDeSportifs;
  }
  
  public int getSizeListS(){
    return this.listeDeSportifs.size();
  }

  /**
   * Méthode pour créer un sportif et l'ajouter à la liste des Sportifs. La méthode vérifie 
   * que le sportif n'existe pas déjà dans la Liste. 
   * @param nom Le nom du sportif
   * @param prenom Le prenom du sportif
   * @param pseudo Le pseudo du sportif
   * @param date La date de naissance du sportif
   * @param sport Le sport du sportif
   * @return
   */
  
  public int ajouterSportif(Sportif s) {
    int result = 0;
    
    for (Sportif s1: listeDeSportifs) {
      
      if (s1.getNom().equals(s.getNom()) && s1.getPrenom().equals(s.getPrenom()) 
          && s1.getNaissance().equals(s.getNaissance())) {
        
        result = 1;
      } else if (s1.getPseudo().equals(s.getPseudo())) {
        
        result = 2;
      }
    }
    
    if (result == 0) {
      if (s!=null) {
        listeDeSportifs.add(s);
      } else {
        result = 3;
      }
    }
    
    return result;
  }
  
  /**
   * Fonction de la classe ListeSportifs qui permet de modifier les informations 
   * d'un sportif qui existe déjà.
   * @param nom     le nouveau nom du sportif
   * @param prenom  le nouveau prenom du sportif
   * @param pseudo  le pseudo du sportif (permet de retrouver le sportif à modifier)
   * @param sport   le nouveau sport du sportif
   * @return
   */
  public int modifierSportif(Sportif sp) {
    int result = 0;
    Sportif sportif1 = null;
    
    for (Sportif s : listeDeSportifs) {
      if (s.getPseudo().equals(sp.getPseudo())) {
        sportif1 = s;
        result = 1;
      }
    }
    
    if (result == 1) {
      
      if (sp.getNom() != null) {
        sportif1.setNom(sp.getNom());
      }
      
      if (sp.getPrenom() != null) {
        sportif1.setPrenom(sp.getPrenom());
      }
      
      if (sp.getSport() != null) {
        sportif1.setSport(sp.getSport());
      }
      
      if (sp.getNaissance() != null) {
        sportif1.setNaissance(sp.getNaissance());
      }
      
    }
    
    
    return result;
  }
  
  
  public int supprimerSportif(String pseudo) {
    int result = 0;
    Sportif sportif1 = null;
    
    for (Sportif s : listeDeSportifs) {
      if (s.getPseudo().equals(pseudo)) {
        sportif1 = s;
      }
    }
    
    if (sportif1 != null) {
      listeDeSportifs.remove(sportif1);
      result = 1;
    }
    
    return result;
  }

  @Override
  public String toString() {
    return "ListeSportifs [listeDeSportifs=" + listeDeSportifs.toString() + "]";
  }


  public Sportif retourneSportif(String pseudo) {
    
    for(Sportif sp : listeDeSportifs) {
      if(pseudo.equals(sp.getPseudo()) ) {
        return sp;
      }
    }
    
    return null;
  }
  
  public void reinitialiser() {
    this.listeDeSportifs.clear();
  }
}