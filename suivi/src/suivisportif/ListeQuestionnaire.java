package suivisportif;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class ListeQuestionnaire {
  
  private ArrayList<Questionnaire> listQ;

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
  
  public void addQuestionnaire(String titre, String sstitre, Date dateD, Date dateF) {
    Questionnaire Q = new Questionnaire(dateD, dateF);
    Q.setTitre(titre);
    Q.setSstitre(sstitre);
    
    Scanner sc = new Scanner(System.in);
    String scanne = "o";
    String rd;
    Question q;
    
    while(scanne.toUpperCase().equals("O") == true) {
      System.out.println("Saisissez l'intitulé de la question : ");
      scanne = sc.nextLine();
      System.out.println("Réponse par défaut [0 = oui / 1 = non] : ");
      rd = sc.nextLine();
      if (Integer.parseInt(rd) == 0) {
        q = new Question(scanne, true);
      }
      else {
        q = new Question(scanne, false);
      }
      Q.addQuestion(q);
    }
    
    this.listQ.add(Q);
  }
}
