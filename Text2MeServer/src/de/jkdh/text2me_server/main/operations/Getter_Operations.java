/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jkdh.text2me_server.main.operations;

import de.jkdh.text2me_server.main.connection.DB_Connect;
import de.jkdh.text2me_server.main.dbdata.DBuser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jan
 */
public class Getter_Operations {
    
    
        public static ResultSet getUser(DB_Connect dbc, String number) {
        PreparedStatement stmt = null;
        DBuser dbu;
        try {
            stmt = dbc.getTheConnection().prepareStatement("SELECT * FROM messenger.user WHERE Telefon = ?");
            stmt.setString(1, number);
            
            ResultSet erg = dbc.executeSQLQuery(stmt);
            
            if(erg!=null){
                erg.first();
                dbu = new DBuser(erg.getInt("U_ID"), erg.getString("Telefon") , erg.getTimestamp("LastOnline"));
            }
            
            
        } catch (SQLException ex) {
        }
        return dbc.executeSQLQuery(stmt);
    }

    public static ResultSet getUser(DB_Connect dbc, int id) {
        PreparedStatement stmt = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("SELECT * FROM messenger.user WHERE U_ID = ?");
            stmt.setString(1, String.valueOf(id));
        } catch (SQLException ex) {
        }
        return dbc.executeSQLQuery(stmt);
    }

    public static ResultSet getMessageByID(DB_Connect dbc, int id) {
        PreparedStatement stmt = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("SELECT * FROM messenger.message WHERE M_ID = ?");
            stmt.setString(1, String.valueOf(id));
        } catch (SQLException ex) {
        }
        return dbc.executeSQLQuery(stmt);
    }

    public static ResultSet getMessageBySenderID(DB_Connect dbc, int senderID) {
        PreparedStatement stmt = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("SELECT * FROM messenger.message WHERE Sender_ID = ?");
            stmt.setString(1, String.valueOf(senderID));
        } catch (SQLException ex) {
        }
        return dbc.executeSQLQuery(stmt);
    }

    public static ResultSet getMessageByEmpfaengerID(DB_Connect dbc, int empfaengerID) {
        PreparedStatement stmt = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("SELECT * FROM messenger.message WHERE Empfaenger_ID = ?");
            stmt.setString(1, String.valueOf(empfaengerID));
        } catch (SQLException ex) {
        }
        return dbc.executeSQLQuery(stmt);
    }

    public static int getMessageStatus(DB_Connect dbc, int messageID) {
        int erg = -1;
        ResultSet rs = getMessageByID(dbc, messageID);
        try {
            rs.first();
            erg = rs.getInt("Status");
        } catch (SQLException ex) {
        }
        return erg;
    }
    
    
    
}
