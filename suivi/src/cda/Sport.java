package cda;

public class Sport {
  
  private String nom;
  private int id = 0;

  public Sport(String nom) {
    super();
    this.nom = nom;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return nom;
  }
}
