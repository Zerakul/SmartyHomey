package com.homey.smarty.smartyhomey.Managers;



public class Controller {


    public static boolean VerifyUser(String name, String password){
         if(name.equals("a@a.com"))
             if(password.equals("a"))
                 return true;

        return false;




    }
}
