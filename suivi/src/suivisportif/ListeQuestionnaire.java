package suivisportif;

import java.util.ArrayList;

public class ListeQuestionnaire {
  
  ArrayList<Questionnaire> listQ;

  public ListeQuestionnaire() {
    super();
    this.listQ = new ArrayList<Questionnaire>();
  }

  public ArrayList<Questionnaire> getListQ() {
    return listQ;
  }

  public void setListQ(ArrayList<Questionnaire> listQ) {
    this.listQ = listQ;
  }
}
