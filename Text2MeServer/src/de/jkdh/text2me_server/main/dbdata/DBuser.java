/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jkdh.text2me_server.main.dbdata;

import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class DBuser {
    
    private int userID;
    private String telnr, password;
    private Timestamp time;

    public DBuser(int userID, String password, Timestamp time) {
        this.userID = userID;
        this.password = password;
        this.time = time;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTelnr() {
        return telnr;
    }

    public void setTelnr(String telnr) {
        this.telnr = telnr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    
    
    
}
