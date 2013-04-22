package de.jkdh.text2me_server.main;

import de.jkdh.text2me_server.main.connection.ConnectionServer;
import de.jkdh.text2me_server.main.connection.DB_Connect;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Control {

    private int port;
    private ConnectionServer connectionserver;
    private DB_Connect theDatabaseConnection;
    private Operations ops;

    public Control(int port) {
        this.port = port;
        this.ops = new Operations();
        // this.setDB_Connection(new DB_Connect("localhost", "messenger",
        // "root", ""));

        startServer();

        System.out.println(ops.addUser(theDatabaseConnection, "abc",
                "+491573478"));
        ResultSet rs = ops.getUser(theDatabaseConnection, "00491234");
        try {
            rs.first();
            System.out.println(rs.getString("Telefon"));
        } catch (SQLException ex) {
        }
    }

    public final void startServer() {
        this.setDB_Connection(new DB_Connect("localhost", "messenger", "root",
                ""));
        ConnectionServer cs = new ConnectionServer(this.port);
        cs.start();
        this.setConnectionServer(cs);
    }

    public ConnectionServer getConnectionServer() {
        return this.connectionserver;
    }

    public DB_Connect getDB_Connection() {
        return this.theDatabaseConnection;
    }

    private void setConnectionServer(ConnectionServer conserv) {
        this.connectionserver = conserv;
    }

    private void setDB_Connection(DB_Connect dbc) {
        this.theDatabaseConnection = dbc;
    }
}
