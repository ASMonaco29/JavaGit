package cda;

import java.util.ArrayList;

public class ListeSports {

  private ArrayList<Sport> listeDeSports = new ArrayList<Sport>();

  
  public ListeSports() {
    super();
  }

  public ArrayList<Sport> getListeDeSports() {
    return listeDeSports;
  }

  public void setListeDeSports(ArrayList<Sport> listeDeSports) {
    this.listeDeSports = listeDeSports;
  }
  
  public void ajouterSports(Sport sp) {
    if( ! this.listeDeSports.contains(sp) ) {
      this.listeDeSports.add(sp);
    }
  }
  
  public void supprimerSports(Sport sp) {
    if( this.listeDeSports.contains(sp)) {
      this.listeDeSports.remove(sp);
    }
  }

  @Override
  public String toString() {
    return "ListeSports [listeDeSports=" + listeDeSports + "]";
  }
  
  public void reinitialiser() {
    this.listeDeSports.clear();
  }

  public boolean contient(Sport s) {
    boolean trouve = false;
    
    for(Sport sp : listeDeSports) {
      if(s.getId() == sp.getId()) {
        trouve = true;
      }
    }
    
    return trouve;
  }
  
  public Sport retourneSport(String nom) {
    
    for(Sport sp : listeDeSports) {
      if(sp.getNom().equals(nom) ) {
        return sp;
      }
    }
    
    return null;
  }
  
  public Sport retourneSport(int id) {
    
    for(Sport sp : listeDeSports) {
      if(sp.getId() == id ) {
        return sp;
      }
    }
    
    return null;
  }

  public void modifierSport(int id, String nom) {
    for(Sport sp : listeDeSports) {
      if(sp.getId() == id ) {
        sp.setNom(nom);
      }
    }
  }
  
}
