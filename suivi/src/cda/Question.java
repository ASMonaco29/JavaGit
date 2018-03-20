package cda;

import java.util.ArrayList;

public class Question {

  private int id = 0;
  private String question;
  private boolean choixDeflt;
  private boolean[] choixRep;

  /**Constructeur de l'objet question.
   * 
   * @param question : chaine de caractere constituant la question
   * @param cd : la r�ponse par defaut
   */
  public Question(String question, boolean cd) {
    this.question = question;
    this.choixDeflt = cd;
    this.choixRep = new boolean[2];
    this.choixRep[0] = true;
    this.choixRep[1] = false;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean getChoixDeflt() {
    return choixDeflt;
  }

  public void setChoixDeflt(boolean choixDeflt) {
    this.choixDeflt = choixDeflt;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public boolean[] getChoixRep() {
    return choixRep;
  }

  public void setChoixRep(boolean[] choixRep) {
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
    if (this.choixRep.length != q.choixRep.length) {
      return false;
    }
    for (int i = 0; i < this.choixRep.length; i++) {
      if (this.choixRep[i] != q.choixRep[i]) {
        return false;
      }
    }
    
    return true;
  }

}