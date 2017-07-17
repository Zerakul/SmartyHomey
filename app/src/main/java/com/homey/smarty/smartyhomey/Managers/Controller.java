package com.homey.smarty.smartyhomey.Managers;

/**
 * Created by Seiran on 17/07/2017.
 */

public class Controller {


    public static boolean VerifyUser(String name, String password){
         if(name.equals("a@a.com"))
             if(password.equals("a"))
                 return true;

        return false;




    }
}
