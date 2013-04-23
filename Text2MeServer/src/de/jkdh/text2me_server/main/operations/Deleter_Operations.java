/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jkdh.text2me_server.main.operations;

import de.jkdh.text2me_server.main.connection.DB_Connect;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class Deleter_Operations {
    
        public static int removeUser(DB_Connect dbc, int id) {
        int erg = -1;
        PreparedStatement stmt = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("DELETE FROM messenger.user WHERE U_ID = ?");
            stmt.setInt(1, id);
        } catch (SQLException ex) {
        }
        dbc.executeSQLInsert(stmt);
        return erg;
    }
    
    
}
