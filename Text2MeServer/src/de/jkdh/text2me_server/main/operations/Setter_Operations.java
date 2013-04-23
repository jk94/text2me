/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jkdh.text2me_server.main.operations;

import de.jkdh.text2me_server.main.connection.DB_Connect;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class Setter_Operations {
    
    
       public static int setMessageStatus(DB_Connect dbc, int id, int neuerStatus) {
        PreparedStatement stmt = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("UPDATE message SET Status = ? WHERE M_ID = ?");
            stmt.setInt(1, neuerStatus);
            stmt.setInt(2, id);
        } catch (SQLException ex) {
        }
        return dbc.executeSQLInsert(stmt);
    }

    public static int updateLastOnline(DB_Connect dbc, int userID) {
        PreparedStatement stmt = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("UPDATE user SET LastOnline = ? WHERE U_ID = ?");
            java.util.Date theDate = new java.util.Date();
            stmt.setTimestamp(1, new Timestamp(theDate.getTime()));
            stmt.setInt(2, userID);
        } catch (SQLException ex) {
        }
        return dbc.executeSQLInsert(stmt);
    }
    
    
    
}
