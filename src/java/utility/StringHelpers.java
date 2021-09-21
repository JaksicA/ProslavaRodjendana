/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

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
}
