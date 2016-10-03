/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class Utils {
    
    
  /**
   * Transforme une date (format depracteted) en type Calendar 
   * @param date
   * @return une date sous format Calendar 
   */
  public static Calendar DateToCalendar(Date date){ 
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal;
  }
  
  /**
   * Transforme des millisecond en nombre de jour
   * @param milliseconds
   * @return nombre de jours
   */
  public static int milisecondToDays(int milliseconds) {
          int days = (int) (milliseconds / (1000*60*60*24));
          return days;
  }
  
  /**
   * Decoupe une string date  format DD/MM/YYYY en tableau de valeur de la date
   * 0 : annee
   * 1 : mois
   * 2 : jour
   * @param date
   * @return list
   */
  public static Integer[] decoupeDate(String date){
      Integer[] list = new Integer[3];
      
      String[] dateDecoupe= date.split("/");
      
      
      list[0] = Integer.parseInt(dateDecoupe[2]);
      list[1] = Integer.parseInt(dateDecoupe[1]);
      list[2] = Integer.parseInt(dateDecoupe[0]);
      return list;
  }
  
  /**
   * Transforme une date string en une valeur de la date 
   * format : "EEE MMM d HH:mm:ss ZZZ yyyy"
   *  0 : annee
   * 1 : mois
   * 2 : jour
   * @param date
   * @return 
   */
  public static Integer[] decoupeDateModeleDate(String date){
      Integer[] list = new Integer[3];
     
      
      SimpleDateFormat parserSDF = new SimpleDateFormat("EEE MMM d HH:mm:ss ZZZ yyyy", Locale.US);
      Date dateF = null;
      try {
         dateF = parserSDF.parse(date);
      } catch (ParseException ex) {
          Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
      }

      list[0] = Integer.valueOf(dateF.getYear()+1900);
      list[1] = Integer.valueOf(dateF.getMonth()+1);
      list[2] = Integer.valueOf(dateF.getDate());
      return list;
  }
}
