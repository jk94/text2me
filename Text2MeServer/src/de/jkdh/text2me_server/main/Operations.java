package de.jkdh.text2me_server.main;

import de.jkdh.text2me_server.main.connection.DB_Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Operations {

    // TODO SETTER/ADDER
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

    public int addUser(DB_Connect dbc, int id, String password, String number) {
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

    public int addMessage(DB_Connect dbc, int senderID, int empfaengerID, String text) {
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

    public int setMessageStatus(DB_Connect dbc, int id, int neuerStatus) {
        PreparedStatement stmt = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("UPDATE message SET Status = ? WHERE M_ID = ?");
            stmt.setInt(1, neuerStatus);
            stmt.setInt(2, id);
        } catch (SQLException ex) {
        }
        return dbc.executeSQLInsert(stmt);
    }

    public int updateLastOnline(DB_Connect dbc, int userID) {
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
    //+++++++++++++++++++++++++++++++++++++++++++++

    // TODO Getter
    public ResultSet getUser(DB_Connect dbc, String number, String[] column, String[] columncontainment) {
        PreparedStatement stmt = null;
        try {
            String appending = "";
            if (column.length == columncontainment.length) {
                for (int i = 0; i < column.length; i++) {
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

    public int getMessageStatus(DB_Connect dbc, int messageID) {
        int erg = -1;
        ResultSet rs = getMessageByID(dbc, messageID);
        try {
            rs.first();
            erg = rs.getInt("Status");
        } catch (SQLException ex) {
        }
        return erg;
    }

    // TODO DELETER
    public int removeUser(DB_Connect dbc, int id) {
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
