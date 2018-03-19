package cda;

import java.util.ArrayList;

public class Question {

  private int id = 0;
  private String question;
  private String choixDeflt;
  private ArrayList<String> choixRep;

  /**Constructeur de l'objet question.
   * 
   * @param question : chaine de caractere constituant la question
   * @param cd : la r�ponse par defaut
   */
  public Question(String question, String cd) {
    this.question = question;
    this.choixDeflt = cd;
    this.choixRep = new ArrayList<String>();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getChoixDeflt() {
    return choixDeflt;
  }

  public void setChoixDeflt(String choixDeflt) {
    this.choixDeflt = choixDeflt;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public ArrayList<String> getChoixRep() {
    return choixRep;
  }

  public void setChoixRep(ArrayList<String> choixRep) {
    this.choixRep = choixRep;
  }

  @Override
  public String toString() {
    return this.question + this.choixDeflt;
  }

  /** Méthode equals pour les questions.
   * 
   * @param q : question a tester
   * @return : true si égal, false sinon
   */
  public boolean equals(Question q) {
    if (!this.question.equals(q.question)) {
      return false;
    }
    if (this.choixDeflt != q.choixDeflt) {
      return false;
    }
    if (this.choixRep.size() != q.choixRep.size()) {
      return false;
    }
    for (int i = 0; i < this.choixRep.size(); i++) {
      if (this.choixRep.get(i) != q.choixRep.get(i)) {
        return false;
      }
    }
    
    return true;
  }

}