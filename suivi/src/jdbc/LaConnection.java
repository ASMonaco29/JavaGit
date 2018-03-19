package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;


public class LaConnection {
  private static String url = "jdbc:mysql://localhost/suivi-sportif";
  //Nom du user
  private static String user = "root";
  //Mot de passe de l'utilisateur
  private static String passwd = "";
  //Objet Connection
  private static Connection connect;
   
  //Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
  public static Connection getInstance(){
    if(connect == null){
      try {
        connect = DriverManager.getConnection(url, user, passwd);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }      
    return connect;
  }   
}
