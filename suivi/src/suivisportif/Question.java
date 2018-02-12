package suivisportif;


public class Question {

  private String question;
  private boolean choixDeflt;

  public Question(String question, boolean cd) {
    this.question = question;
    this.choixDeflt = cd;
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

}
