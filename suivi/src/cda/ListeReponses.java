package cda;
import java.util.ArrayList;

public class ListeReponses {

  private ArrayList<Reponse> reponses;

  public ListeReponses() {
    super();
    this.reponses = new ArrayList<Reponse>();
  }

  public int getSizeListR(){
    return this.reponses.size();
  }
  
  public ArrayList<Reponse> getReponses() {
    return reponses;
  }

  public void setReponses(ArrayList<Reponse> reponses) {
    this.reponses = reponses;
  }
  
  public void ajouterReponse(Reponse r) {
      this.reponses.add(r);
  }
  
  public void supprimerReponse(Reponse r) {
          this.reponses.remove(r);
  }
  
  public void modifierReponse(Reponse r, int indx){
    this.reponses.set(indx, r);
  }

  public void reinitialiser() {
    this.reponses.clear();
  }
  
}