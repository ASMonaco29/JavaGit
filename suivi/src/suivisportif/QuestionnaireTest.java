package suivisportif;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class QuestionnaireTest {

  @Test
  public void testQuestionnaire() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetTitre() {
    @SuppressWarnings("deprecation")
    Date d = new Date(2000, 01, 21);
    
    @SuppressWarnings("deprecation")
    Date f = new Date(2001, 01, 21);
    
    Questionnaire q = new Questionnaire(d, f);
    
    String t = new String();
    
    assertTrue("Titre non trouvé", t.equals(q.getTitre()) == true);
    
    q.setTitre("Coucou");
    t = q.getTitre();
    assertEquals("Titre trouvé", true, t.equals("Coucou"));
  }

  @Test
  public void testSetTitre() {
    @SuppressWarnings("deprecation")
    Date d = new Date(2000, 01, 21);
    
    @SuppressWarnings("deprecation")
    Date f = new Date(2001, 01, 21);
    
    Questionnaire q = new Questionnaire(d, f);
    
    q.setTitre("Coucou");
    String tres = q.getTitre();
    assertEquals("Titre trouvé", true, tres.equals("Coucou"));
  }

  @Test
  public void testGetSstitre() {
    @SuppressWarnings("deprecation")
    Date d = new Date(2000, 01, 21);
    
    @SuppressWarnings("deprecation")
    Date f = new Date(2001, 01, 21);
    
    Questionnaire q = new Questionnaire(d, f);
    
    String t = new String();
    
    assertTrue("Titre non trouvé", t.equals(q.getSstitre()) == true);
    
    q.setSstitre("Coucou");
    t = q.getSstitre();
    assertEquals("Titre trouvé", true, t.equals("Coucou"));
  }

  @Test
  public void testSetSstitre() {
    @SuppressWarnings("deprecation")
    Date d = new Date(2000, 01, 21);
    
    @SuppressWarnings("deprecation")
    Date f = new Date(2001, 01, 21);
    
    Questionnaire q = new Questionnaire(d, f);
    
    q.setSstitre("Coucou");
    String tres = q.getSstitre();
    assertEquals("Titre trouvé", true, tres.equals("Coucou"));
  }

  @Test
  public void testGetDateD() {
    @SuppressWarnings("deprecation")
    Date d = new Date(2001 - 1900, 1, 21);
    
    @SuppressWarnings("deprecation")
    Date f = new Date(2001 - 1900, 1, 21);
    
    Questionnaire q = new Questionnaire(d, f);
    
    SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
    String strDate = formatter.format(q.getDateD());  
    System.out.println("Date Format with dd MMMM yyyy : " + strDate);
    
    assertFalse("Date non conforme.", d.equals(q.getDateD()) == false);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testSetDateD() {
    Date d = new Date(2000-1900, 01, 21);
    
    Date f = new Date(2001-1900, 01, 21);
    
    Questionnaire q = new Questionnaire(d, f);
    
    SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
    String strDate = formatter.format(q.getDateD());  
    System.out.println("DateD (init) : " + strDate);
    
    d.setDate(15);
    q.setDateD(d);
    
    formatter = new SimpleDateFormat("dd MMMM yyyy");  
    strDate = formatter.format(q.getDateD());  
    System.out.println("DateD (end) : " + strDate);


    assertEquals("Date changée", true, d.equals(q.getDateD()));
  }

  @Test
  public void testGetDateF() {
    @SuppressWarnings("deprecation")
    Date d = new Date(2001 - 1900, 1, 21);
    
    @SuppressWarnings("deprecation")
    Date f = new Date(2002 - 1900, 1, 21);
    
    Questionnaire q = new Questionnaire(d, f);
    
    SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
    String strDate = formatter.format(q.getDateF());  
    System.out.println("Date Format with dd MMMM yyyy : " + strDate);
    
    assertFalse("Date non conforme.", f.equals(q.getDateF()) == false);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testSetDateF() {
    Date d = new Date(2000-1900, 01, 21);
    
    Date f = new Date(2001-1900, 01, 21);
    
    Questionnaire q = new Questionnaire(d, f);
    
    SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
    String strDate = formatter.format(q.getDateF());  
    System.out.println("DateF (init) : " + strDate);
    
    f.setMonth(8);
    q.setDateD(d);
    
    formatter = new SimpleDateFormat("dd MMMM yyyy");  
    strDate = formatter.format(q.getDateF());  
    System.out.println("DateF (end) : " + strDate);


    assertEquals("Date changée", true, f.equals(q.getDateF()));
  }

  @Test
  public void testGetMessageFin() {
    fail("Not yet implemented");
  }

  @Test
  public void testSetMessageFin() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetquListe() {
    fail("Not yet implemented");
  }

  @Test
  public void testSetqListe() {
    fail("Not yet implemented");
  }

  @Test
  public void testAddQuestion() {
    fail("Not yet implemented");
  }

}
