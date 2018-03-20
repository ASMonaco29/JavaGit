package cda;
import java.util.ArrayList;
import java.util.Date;

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

  public Reponse retourneReponse(Sportif s, Questionnaire q, Date date) {
    Reponse r = null;

    for (Reponse rep : this.reponses ) {
      if (rep.getDate().equals(date) && rep.getSportif().equals(s) && rep.getQuestionnaire().equals(q)) {
        r = rep;
      }
    }

    return r;
  }

  @Override
  public String toString() {
    return "reponses=" + reponses + "";
  }

}