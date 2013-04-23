package de.jkdh.text2me_server.main.operations;

import de.jkdh.text2me_server.main.connection.DB_Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Operations {

    // TODO SETTER/ADDER
    

 
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



    // TODO DELETER

}
