package de.jkdh.text2me_server.main;

import de.jkdh.text2me_server.main.operations.Operations;
import de.jkdh.text2me_server.main.connection.ConnectionServer;
import de.jkdh.text2me_server.main.connection.DB_Connect;
import de.jkdh.text2me_server.main.operations.Adder_Operations;
import de.jkdh.text2me_server.main.operations.Deleter_Operations;
import de.jkdh.text2me_server.main.operations.Getter_Operations;
import de.jkdh.text2me_server.main.operations.Setter_Operations;
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

        startServer();

        Adder_Operations.addUser(theDatabaseConnection, 7, "abc", "+491573488");

        Deleter_Operations.removeUser(theDatabaseConnection, 7);

        System.out.println("GetUser: " + Getter_Operations.getUser(theDatabaseConnection, "004954321").getTelnr());


        Getter_Operations.getMessageStatus(theDatabaseConnection, 1);
        Setter_Operations.setMessageStatus(theDatabaseConnection, 1, Konstanten.STATUS_GESENDET_ID);
        Adder_Operations.addMessage(theDatabaseConnection, 2, 1, "Hallihallo ^^ ich teste die SQLInjection'; DROP DATABASE messenger;");
        Setter_Operations.updateLastOnline(theDatabaseConnection, 2);
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
