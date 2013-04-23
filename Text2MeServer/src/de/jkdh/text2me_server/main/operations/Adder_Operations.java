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
 * @author Jan
 */
public class Adder_Operations {
    
    public static int addUser(DB_Connect dbc, String password, String number) {
        PreparedStatement stmt = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("INSERT INTO messenger.user (`Telefon`, `Password`) VALUES (?,?)");
            stmt.setString(1, number);
            stmt.setString(2, password);
        } catch (SQLException ex) {
        }
        return dbc.executeSQLInsert(stmt);
    }

    public static int addUser(DB_Connect dbc, int id, String password, String number) {
        PreparedStatement stmt = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("INSERT INTO messenger.user (U_ID, Telefon, Password) VALUES (?,?,?)");
            stmt.setInt(1, id);
            stmt.setString(2, number);
            stmt.setString(3, password);
        } catch (SQLException ex) {
        }
        return dbc.executeSQLInsert(stmt);
    }

    public static int addMessage(DB_Connect dbc, int senderID, int empfaengerID, String text) {
        PreparedStatement stmt = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("INSERT INTO messenger.message (Sender_ID, Empfaenger_ID, Text) VALUES (?,?,?)");
            stmt.setInt(1, senderID);
            stmt.setInt(2, empfaengerID);
            stmt.setString(3, text);

        } catch (SQLException ex) {
        }
        return dbc.executeSQLInsert(stmt);
    }
    
    
}
