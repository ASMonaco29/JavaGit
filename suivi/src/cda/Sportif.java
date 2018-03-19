package cda;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Sportif { 
  private String nom;
  private String prenom;
  private String pseudo;
  private Date naissance;
  private Sport sport;
  private ArrayList<Questionnaire> listQ;

  
  public Sportif() {
    super();
    this.nom = null;
    this.prenom = null;
    this.pseudo = null;
    this.naissance = null;
    this.listQ = new ArrayList<Questionnaire>();
  }

  public String getNom() {
    return nom;
  }
  
  /**
   *  Fonction permettent de modifier le nom du Sportif en verifiant que le nouveau nom
   *  est bien un nom.
   * @param nom le nouveau nom du sportif
   * @return
   */
  public boolean setNom(String nom) {
    boolean result = false;
    
    if (verifierString(nom)) {
      nom = nom.toUpperCase();
      this.nom = nom;
      result = true;
    } else if (nom.equals(null)) {
      this.nom = null;
      result = true;
    }
    return result;
  }
  
  public String getPrenom() {
    return prenom;
  }
  
  /**
   * Fonction permettent de modifier le prenom du Sportif.
   * @param prenom le nouveau prenom du sportif
   * @return
   */
  public boolean setPrenom(String prenom) {
    boolean result = false;
    
    if (verifierString(prenom)) {
      prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1).toLowerCase();
      this.prenom = prenom;
      result = true;
    } else if (prenom.equals(null)) {
      this.prenom = null;
    }
    return result;
  }

  public String getPseudo() {
    return pseudo;
  }
  
  public boolean setPseudo(String pseudo) {
    boolean result = false;
    
    if (verifierStringPseudo(pseudo)) {
      this.pseudo = pseudo;
      result = true;
    }else {
      result = false;
    }
    return result;
  }

  public Date getNaissance() {
    return naissance;
  }

  /**
   * Fonction de la classe sportifs permettent de modifier la date de naissance du sportif.
   * @param naissance la nouvelle date de naissance du sportif
   * @return 
   */
  public boolean setNaissance(Date naissance) {
    boolean verifdate = true;
    
    Date today;
    Date todayWithZeroTime;
    Date naissanceWithZeroTime;
    DateFormat formatter;
    String reportDate;
    String reportNaissance;
    int jourTdy = 0;
    int moisTdy = 0;
    int anTdy = 0;
    int jourNsc = 0;
    int moisNsc = 0;
    int anNsc = 0;
    
    
    formatter = new SimpleDateFormat("dd/MM/yyyy");
    today = new Date();
    try {
      todayWithZeroTime = formatter.parse(formatter.format(today));
      reportDate = formatter.format(todayWithZeroTime);
      jourTdy = Integer.parseInt(reportDate.substring(0, 2));
      moisTdy = Integer.parseInt(reportDate.substring(3, 5));
      anTdy = Integer.parseInt(reportDate.substring(6, 10));
      naissanceWithZeroTime = formatter.parse(formatter.format(naissance));
      reportNaissance = formatter.format(naissanceWithZeroTime);
      jourNsc = Integer.parseInt(reportNaissance.substring(0, 2));
      moisNsc = Integer.parseInt(reportNaissance.substring(3, 5));
      anNsc = Integer.parseInt(reportNaissance.substring(6, 10));
    
    } catch (ParseException e2) {
      e2.printStackTrace();
    }  
    
    
    
    
    if (anNsc < anTdy) {
      
      this.naissance = naissance;
      
    } else if (anNsc == anTdy) {
      
      if (moisNsc - 1 < moisTdy - 1) {
        
        this.naissance = naissance;
        
      } else if (moisNsc - 1 == moisTdy - 1) {
        if (jourNsc < jourTdy) {
          
          this.naissance = naissance;
          
        } else if (jourNsc == jourTdy) {
          this.naissance = naissance;
        }
      
      
      } else {
        verifdate = false;
      }
    }
    return verifdate;
  }

  public Sport getSport() {
    return sport;
  }

  public void setSport(Sport sport) {
    this.sport = sport;
  }

  @Override
  public String toString() {
    return "Sportifs [nom=" + nom + ", prenom=" + prenom + ", pseudo=" + pseudo + ", "
        + "naissance=" + naissance + ", sport=" + sport + "]";
  }

  @Override
  public boolean equals(Object obj) {
    
    if (this == obj) {
      return true;
    }
    
    if (obj == null) {
      return false;
    }
    
    if (getClass() != obj.getClass()) {
      return false;
    }
    
    Sportif other = (Sportif) obj;
      
    if (naissance == null) {
      if (other.naissance != null) {
        return false;
      }
    } else if (!naissance.equals(other.naissance)) {
      return false;
    }
       
    if (nom == null) {
      if (other.nom != null) {
        return false;
      }
    } else if (!nom.equals(other.nom)) {
      return false;
    }  
     
    if (prenom == null) {
      if (other.prenom != null) {
        return false;
      }
    } else if (!prenom.equals(other.prenom)) {
      return false;
    }
    
         
    if (pseudo == null) {
      if (other.pseudo != null) {
        return false;
      }
    } else if (!pseudo.equals(other.pseudo)) {
      return false;
    }
         
    if (sport != other.sport) {
      return false;
    }
    
    return true;
  }
  
  /**
   *  Fonction permettent de verifier qu'une chaine de caractère n'est pas composé
   *  de numerique ou de caractere spéciaux.
   * @param newString La chaine de caractère à verifier
   * @return
   */
  public boolean verifierString(String newString) {
    boolean result = true;
    for (int i = 0; i < newString.length();i++) {
      char chrNom = newString.charAt(i); //recup le cara
      if (Character.isLetter(chrNom) == true) { //test caractere
        result = true;
      }else if(Character.isSpaceChar(chrNom) == true) {
        result = true;
      }else {
        result = false;
      }
    }
      
    return result;
  }
  
  public boolean verifierStringPseudo(String newString) {
    boolean result = true;
    
    for (int i = 0; i < newString.length();i++) {
      char chrNom = newString.charAt(i); //recup le cara
      if(Character.isSpaceChar(chrNom) == true) {
        result = false;
      }
    }
    
    return result;
  }

  public ArrayList<Questionnaire> getquListe() {
    return listQ;
  }

  public void ajouterListQ(ArrayList<Questionnaire> listQu) {
    this.listQ = listQu;
  }
  
  public void ajouterQuestionnaire(Questionnaire q) {
    this.listQ.add(q);
  }
  
  public void supprimerQuestionnaire(Questionnaire q) {
    this.listQ.remove(q);
  }
  
}