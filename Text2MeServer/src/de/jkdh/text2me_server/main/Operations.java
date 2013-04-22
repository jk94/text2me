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
            System.out.println(stmt.toString());
        } catch (SQLException ex) {
        }

        return dbc.executeSQLInsert(stmt);
    }

    public ResultSet getUser(DB_Connect dbc, String number) {
        PreparedStatement stmt=null;
        try{
        stmt = dbc.getTheConnection().prepareStatement("SELECT * FROM messenger.user WHERE Telefon = ?");
        stmt.setString(1, number);
        }catch (SQLException ex){
            
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
}
