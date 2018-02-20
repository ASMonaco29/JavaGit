package suivisportif;

import java.util.Arrays;

public class Question {

  private String question;
  private boolean choixDeflt;
  private boolean[] choixRep = new boolean[2];

  /**Constructeur de l'objet question.
   * 
   * @param question : chaine de caractere constituant la question
   * @param cd : la r�ponse par defaut
   */
  public Question(String question, boolean cd) {
    this.question = question;
    this.choixDeflt = cd;
    this.choixRep[0] = true;
    this.choixRep[1] = false;
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

  @Override
  public String toString() {
    return "Question [question=" + question + ", choixDeflt=" + choixDeflt
        + ", choixRep=" + Arrays.toString(choixRep) + "]";
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
