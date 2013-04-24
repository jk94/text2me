/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jkdh.text2me_server.main.operations;

import de.jkdh.text2me_server.main.connection.DB_Connect;
import de.jkdh.text2me_server.main.dbdata.DBmessage;
import de.jkdh.text2me_server.main.dbdata.DBuser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jan
 */
public class Getter_Operations {

    public static DBuser getUser(DB_Connect dbc, String number) {
        PreparedStatement stmt = null;
        DBuser dbu = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("SELECT * FROM messenger.user WHERE Telefon = ?");
            stmt.setString(1, number);

            ResultSet erg = dbc.executeSQLQuery(stmt);

            if (erg != null) {
                erg.first();
                System.out.println(erg.getInt("U_ID"));
                System.out.println(erg.getString("Telefon"));
                System.out.println(erg.getString("Password"));
                dbu = new DBuser(erg.getInt("U_ID"), erg.getString("Telefon"), erg.getString("Password"), erg.getTimestamp("LastOnline"));
            }
        } catch (SQLException ex) {
        }
        return dbu;
    }

    public static ArrayList<DBuser> getAllUser(DB_Connect dbc, String number) {
        PreparedStatement stmt = null;
        ArrayList<DBuser> dbu = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("SELECT * FROM messenger.user WHERE Telefon = ?");
            stmt.setString(1, number);

            ResultSet erg = dbc.executeSQLQuery(stmt);

            if (erg != null) {
                while (erg.next()) {
                    dbu.add(new DBuser(erg.getInt("U_ID"), erg.getString("Telefon"), erg.getString("Password"), erg.getTimestamp("LastOnline")));
                }
            }
        } catch (SQLException ex) {
        }
        return dbu;
    }

    public static DBuser getUser(DB_Connect dbc, int id) {
        PreparedStatement stmt = null;
        DBuser dbu = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("SELECT * FROM messenger.user WHERE U_ID = ?");
            stmt.setString(1, String.valueOf(id));

            ResultSet erg = dbc.executeSQLQuery(stmt);

            if (erg != null) {
                erg.first();
                dbu = new DBuser(erg.getInt("U_ID"), erg.getString("Telefon"), erg.getString("Password"), erg.getTimestamp("LastOnline"));
            }

        } catch (SQLException ex) {
        }
        return dbu;
    }

    public static ArrayList<DBuser> getAllUser(DB_Connect dbc, int id) {
        PreparedStatement stmt = null;
        ArrayList<DBuser> dbu = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("SELECT * FROM messenger.user WHERE U_ID = ?");
            stmt.setString(1, String.valueOf(id));

            ResultSet erg = dbc.executeSQLQuery(stmt);

            if (erg != null) {
                while (erg.next()) {
                    dbu.add(new DBuser(erg.getInt("U_ID"), erg.getString("Telefon"), erg.getString("Password"), erg.getTimestamp("LastOnline")));
                }
            }

        } catch (SQLException ex) {
        }
        return dbu;
    }

    public static DBmessage getMessageByID(DB_Connect dbc, int id) {
        PreparedStatement stmt = null;
        DBmessage dbm = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("SELECT * FROM messenger.message WHERE M_ID = ?");
            stmt.setString(1, String.valueOf(id));

            ResultSet erg = dbc.executeSQLQuery(stmt);

            if (erg != null) {
                erg.first();
                dbm = new DBmessage(erg.getInt("M_ID"), erg.getInt("Sender_ID"), erg.getInt("Empfaenger_ID"), erg.getInt("Status"), erg.getString("Text"), erg.getTimestamp("time"));
            }

        } catch (SQLException ex) {
        }
        return dbm;
    }

    public static ArrayList<DBmessage> getMessageBySenderID(DB_Connect dbc, int senderID) {
        PreparedStatement stmt = null;
        ArrayList<DBmessage> dbm = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("SELECT * FROM messenger.message WHERE Sender_ID = ?");
            stmt.setString(1, String.valueOf(senderID));
            ResultSet erg = dbc.executeSQLQuery(stmt);
            if (erg != null) {
                while (erg.next()) {
                    dbm.add(new DBmessage(erg.getInt("M_ID"), erg.getInt("Sender_ID"), erg.getInt("Empfaenger_ID"), erg.getInt("Status"), erg.getString("Text"), erg.getTimestamp("time")));
                }
            }
        } catch (SQLException ex) {
        }
        return dbm;
    }

    public static ArrayList<DBmessage> getMessageByEmpfaengerID(DB_Connect dbc, int empfaengerID) {
        PreparedStatement stmt = null;
        ArrayList<DBmessage> dbm = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("SELECT * FROM messenger.message WHERE Empfaenger_ID = ?");
            stmt.setString(1, String.valueOf(empfaengerID));
            ResultSet erg = dbc.executeSQLQuery(stmt);
            if (erg != null) {
                while (erg.next()) {
                    dbm.add(new DBmessage(erg.getInt("M_ID"), erg.getInt("Sender_ID"), erg.getInt("Empfaenger_ID"), erg.getInt("Status"), erg.getString("Text"), erg.getTimestamp("time")));
                }
            }
        } catch (SQLException ex) {
        }
        return dbm;
    }

    public static int getMessageStatus(DB_Connect dbc, int messageID) {
        return getMessageByID(dbc, messageID).getStatus();
    }
}
