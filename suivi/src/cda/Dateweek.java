package cda;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

public class Dateweek {
  private int annee;
  private String numweek;
  
  public Dateweek() {
    super();
    // TODO Auto-generated constructor stub
  }

  public int getAnnee(Date date) {
    this.yearWeekYearFromDate(date);
    return annee;
  }

  public String getNumweek(Date date) {
    this.yearWeekYearFromDate(date);
    return numweek;
  }
  
  private void yearWeekYearFromDate(Date date) {
    Calendar cal = Calendar.getInstance();
    //La première semaine de l'année est celle contenant au moins 4 jours
    cal.setMinimalDaysInFirstWeek(4);
    cal.setTime(date);
    //On récupère le numéro de la semaine
    int week = cal.get(Calendar.WEEK_OF_YEAR);
    int annee;
    //On récupère l'année du premier jour de cette semaine
    if (week >= 52) {
      cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
      annee = cal.get(Calendar.YEAR);
    } else if (week == 1) {
      cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
      annee = cal.get(Calendar.YEAR);
    } else {
      annee = cal.get(Calendar.YEAR);
    }
    DecimalFormat df = new DecimalFormat("00");
    this.annee = annee;
    this.numweek = df.format(week);
  }
}
