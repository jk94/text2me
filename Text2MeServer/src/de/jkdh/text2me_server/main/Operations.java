package de.jkdh.text2me_server.main;

import de.jkdh.text2me_server.main.connection.DB_Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Operations {

    public int addUser(DB_Connect dbc, String password, String number) {
        PreparedStatement stmt = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("INSERT INTO messenger.user (`Telefon`, `Password`) VALUES (?,?)");
            stmt.setString(1, number);
            stmt.setString(2, password);
        } catch (SQLException ex) {
        }
        return dbc.executeSQLInsert(stmt);
    }

    //+++++++++++++++++++++++++++++++++++++++++++++
    public ResultSet getUser(DB_Connect dbc, String number, String[] column, String[] columncontainment) {
        PreparedStatement stmt = null;
        try {
            String appending = "";
            if(column.length == columncontainment.length){
                for(int i=0;i<column.length;i++){
                //String srtg = columncontainment[i].replaceAll(";", "");
                appending = appending + " AND ";
                appending = appending + column[i] + " = \'" + columncontainment[i] + "\'";
                }
            }
            
            stmt = dbc.getTheConnection().prepareStatement("SELECT * FROM messenger.user WHERE Telefon = ?");
            stmt.setString(1, number);
        } catch (SQLException ex) {
        }
        return dbc.executeSQLQuery(stmt);
    }
    //+++++++++++++++++++++++++++++++++++++++++++++

    public ResultSet getUser(DB_Connect dbc, String number) {
        PreparedStatement stmt = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("SELECT * FROM messenger.user WHERE Telefon = ?");
            stmt.setString(1, number);
        } catch (SQLException ex) {
        }
        return dbc.executeSQLQuery(stmt);
    }

    public ResultSet getUser(DB_Connect dbc, int id) {
        PreparedStatement stmt = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("SELECT * FROM messenger.user WHERE U_ID = ?");
            stmt.setString(1, String.valueOf(id));
        } catch (SQLException ex) {
        }
        return dbc.executeSQLQuery(stmt);
    }

    public ResultSet getMessageByID(DB_Connect dbc, int id) {
        PreparedStatement stmt = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("SELECT * FROM messenger.message WHERE M_ID = ?");
            stmt.setString(1, String.valueOf(id));
        } catch (SQLException ex) {
        }
        return dbc.executeSQLQuery(stmt);
    }

    public ResultSet getMessageBySenderID(DB_Connect dbc, int senderID) {
        PreparedStatement stmt = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("SELECT * FROM messenger.message WHERE Sender_ID = ?");
            stmt.setString(1, String.valueOf(senderID));
        } catch (SQLException ex) {
        }
        return dbc.executeSQLQuery(stmt);
    }

    public ResultSet getMessageByEmpfaengerID(DB_Connect dbc, int empfaengerID) {
        PreparedStatement stmt = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("SELECT * FROM messenger.message WHERE Empfaenger_ID = ?");
            stmt.setString(1, String.valueOf(empfaengerID));
        } catch (SQLException ex) {
        }
        return dbc.executeSQLQuery(stmt);
    }
}
