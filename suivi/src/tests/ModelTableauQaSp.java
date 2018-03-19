package tests;

import cda.ListeReponses;
import cda.Reponse;
import cda.Sportif;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.table.AbstractTableModel;


@SuppressWarnings("serial")
public class ModelTableauQaSp extends AbstractTableModel {
  
  private final Sportif sportif;
  private ListeReponses reponses;

  private final String[] entetes = {"Questionnaire", "Date"};

  private Calendar cal;
  private Date date1;
  private Date date2;  
  private Date date3;
  private Date date4;
  private ArrayList<Date> dates;
  
  private ArrayList<Boolean> reps1;
  private ArrayList<Boolean> reps2;
  private ArrayList<ArrayList<Boolean>> rep;
  
  public ModelTableauQaSp(Sportif ls) {
      super();
      
      this.sportif = ls;
      
      reps1 = new ArrayList<Boolean>();
      reps1.add(true);
      reps1.add(true);
      reps1.add(false);
      
      reps2 = new ArrayList<Boolean>();
      reps2.add(true);
      reps2.add(false);
      reps2.add(true);
      
      rep = new ArrayList<ArrayList<Boolean>>();
      rep.add(reps1);
      rep.add(reps2);
      rep.add(reps1);
      rep.add(reps2);
      rep.add(reps1);
      rep.add(reps2);
      rep.add(reps1);
      rep.add(reps2);
      rep.add(reps1);
      rep.add(reps2);
      
      
      dates = new ArrayList<Date>();
      this.cal = Calendar.getInstance();
      cal.set(Calendar.YEAR, 2018);
      cal.set(Calendar.MONTH, 4);
      cal.set(Calendar.DAY_OF_MONTH, 1);
      date1 = cal.getTime();
      dates.add(date1);
      
      
      cal.set(Calendar.YEAR, 2018);
      cal.set(Calendar.MONTH, 4);
      cal.set(Calendar.DAY_OF_MONTH, 1);
      date2 = cal.getTime();
      dates.add(date2);
      
      cal.set(Calendar.YEAR, 2018);
      cal.set(Calendar.MONTH, 4);
      cal.set(Calendar.DAY_OF_MONTH, 8);
      date3 = cal.getTime();
      dates.add(date3);
      
      cal.set(Calendar.YEAR, 2018);
      cal.set(Calendar.MONTH, 4);
      cal.set(Calendar.DAY_OF_MONTH, 8);
      date4 = cal.getTime();
      dates.add(date4);
      dates.add(date1);
      dates.add(date2);
      dates.add(date3);
      dates.add(date4);
      
      reponses = new ListeReponses();
      
      for(int i = 0; i < this.sportif.getquListe().size(); i++){
        reponses.ajouterReponse(new Reponse(dates.get(i), this.rep.get(i), this.sportif, 
            this.sportif.getquListe().get(i)));
      }
      
  }

  public int getRowCount() {
      return this.reponses.getSizeListR();
  }

  public int getColumnCount() {
      return this.entetes.length;
  }

  public String getColumnName(int columnIndex) {
      return this.entetes[columnIndex];
  }
  
  public ListeReponses getListR(){
    return this.reponses;
  }
  
  public Sportif getSportif(){
    return this.sportif;
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    switch(columnIndex){
        case 0:
            return this.reponses.getReponses().get(rowIndex).getQuestionnaire();
        case 1:
            return this.reponses.getReponses().get(rowIndex).getDate();
        default:
            return null; // Ne devrait jamais arriver
    }
  }
  
  public void addReponse(Reponse r) {
    this.reponses.ajouterReponse(r);
    
    fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
  }
  
  public void removeReponses(int rowIndex) {
    this.reponses.supprimerReponse(this.reponses.getReponses().get(rowIndex));
    
    fireTableRowsDeleted(rowIndex, rowIndex);
  }
  
  public void modifReponses(int rowIndex) {
    this.reponses.modifierReponse(this.reponses.getReponses().get(rowIndex), rowIndex);
    
    fireTableRowsUpdated(rowIndex, rowIndex);
  }
  
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public Class getColumnClass(int columnIndex){
    switch(columnIndex){
      case 1 :
        return Date.class;
      default:
        return Object.class;
    }
  }
  
  
}
