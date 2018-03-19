package jdbc;

import cda.ListeQuestionnaires;
import cda.Questionnaire;

public class JdbcListeQuestionnaire {
  
  private ListeQuestionnaires lqtnrs;
  private JdbcListeQuestionnaire lqtrs;
  
  
  public JdbcListeQuestionnaire() {
    super();
    this.lqtnrs = new ListeQuestionnaires();
    this.lqtrs = new JdbcListeQuestionnaire();
  }

  public ListeQuestionnaires getLqtnrs() {
    return lqtnrs;
  }

  public void initialiserListeQuestionnaireJdbc() {
    this.lqtnrs.reinitialiser();
  }

  public Questionnaire retourneQuestionnaireJdbc(String object) {
    return null;
  }

}
