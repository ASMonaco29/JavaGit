package cda;

public class Sport {
  
  private String nom;
  private int id;

  public Sport(String nom, int id) {
    super();
    this.nom = nom;
    this.id = id;
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
