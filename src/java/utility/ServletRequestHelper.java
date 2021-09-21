/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Lenovo
 */
public class ServletRequestHelper {
    public static HashMap<String, String> getParameters(HttpServletRequest request, String... parameterNames){
        
       HashMap<String, String> parameters = new HashMap<String, String>();
        
        for(String parameterName : parameterNames){
            String parameter = request.getParameter(parameterName).trim();
            parameters.put(parameterName, parameter);
        }
        
        return parameters;
    }
}
