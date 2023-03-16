/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import javax.swing.JFrame;


/**
 *
 * @author momim
 */
public class Repository {
    public static String fullname;
    Database db = new Database();
    public Repository(){
    }
    public String getUser() {
        return fullname;
    }
    public void setUser(String username){
        this.fullname = username;
    }

    public void logoutFunction(JFrame fr){
        db.firstname = "";
        db.lastname="";
        fr.dispose();
        new Login().setVisible(true);
        
    }
}
