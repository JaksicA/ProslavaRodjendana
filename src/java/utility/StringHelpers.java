/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class StringHelpers {
    
    public static boolean isNumberc(String string){
        int intValue;
        
        if(string == null || string.equals("")) return false;
        
        try{
          intValue = Integer.parseInt(string);
          return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }
    
    public static Timestamp GetDateTimeFromString(String dateString) throws ParseException{
        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateString.replace("T", " ").concat(":00"));
        return new Timestamp(date.getTime());
    }
}
