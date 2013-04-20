package de.jkdh.text2me_server.main;

import de.jkdh.text2me_server.main.connection.DB_Connect;
import java.sql.ResultSet;

public class Operations {

    public int addUser(DB_Connect dbc, String password, String number) {
        String sql = "INSERT INTO `user` (`Telefon`, `Password`) VALUES (\"" + number + "\", \"" + password + "\");";
        System.out.println(sql);
        return dbc.executeSQLInsert(sql);
    }

    public ResultSet getUser(DB_Connect dbc, String number) {
        String sql = "SELECT * FROM `messenger`.`user` WHERE Telefon = '" + number + "';";
        System.out.println(sql);
        return dbc.executeSQLQuery(sql);
    }
}
