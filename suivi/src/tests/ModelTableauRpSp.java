package tests;

import cda.ListeReponses;

import javax.swing.table.AbstractTableModel;


@SuppressWarnings("serial")
public class ModelTableauRpSp extends AbstractTableModel {
  
  private final int indx;
  private ListeReponses reponses;
  
  @SuppressWarnings("rawtypes")
  Class[] types = new Class[] { 
      String.class, Boolean.class 
  }; 
  
  private final String[] entetes = {"Question", "Réponse"};
  
  public ModelTableauRpSp(ListeReponses rp, int indx) {
      super();
      
      this.reponses = rp;
      this.indx = indx;
      
  }

  public int getRowCount() {
      return this.reponses.getReponses().get(this.indx).getReponses().size();
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

  public Object getValueAt(int rowIndex, int columnIndex) {
    switch(columnIndex){
        case 0:
            return this.reponses.getReponses().get(this.indx).getQuestionnaire().getquListe().get(rowIndex);
        case 1:
            return this.reponses.getReponses().get(this.indx).getReponses().get(rowIndex);
        default:
            return null; // Ne devrait jamais arriver
    }
  }
  
  
  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
      if(aValue != null){
        switch(columnIndex){
            case 1:
              this.reponses.getReponses().get(this.indx).getReponses().set(rowIndex, (Boolean)aValue);
                break;
        }
      }
  }
  
  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    if(columnIndex == 0){
      return false;
    } else {
      return true;
    }
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Class getColumnClass(int columnIndex) { 
      return types[columnIndex]; 
  } 
  
}
