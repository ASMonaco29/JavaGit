package cda;
import java.util.Date;
import java.util.ArrayList;

public class Reponse {
  private Date date;
  private ArrayList<Boolean> reponses;
  private Sportif sportif;
  private Questionnaire questionnaire;
  
  public Reponse(Date date, ArrayList<Boolean> reponses, Sportif sportif, Questionnaire questionnaire) {
    super();
    this.date = date;
    this.reponses = reponses;
    this.sportif = sportif;
    this.questionnaire = questionnaire;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public ArrayList<Boolean> getReponses() {
    return reponses;
  }

  public void setReponses(ArrayList<Boolean> reponses) {
    this.reponses = reponses;
  }

  public Sportif getSportif() {
    return sportif;
  }

  public void setSportif(Sportif sportif) {
    this.sportif = sportif;
  }

  public Questionnaire getQuestionnaire() {
    return questionnaire;
  }

  public void setQuestionnaire(Questionnaire questionnaire) {
    this.questionnaire = questionnaire;
  }
}