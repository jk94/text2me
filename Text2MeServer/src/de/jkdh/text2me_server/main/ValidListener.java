/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jkdh.text2me_server.main;

import de.jkdh.common.ProtocolPacket;
import de.jkdh.text2me_server.main.connection.DB_Connect;

/**
 *
 * @author Admin
 */
public class ValidListener extends Thread {

    private DB_Connect dbc;
    private ProtocolPacket pp;

    public ValidListener(DB_Connect dbc, ProtocolPacket pp) {
        this.dbc = dbc;
        this.pp = pp;
        run();
    }

    @Override
    public void run() {
        try {
            while (!this.pp.isValid()) {
                //Operations.runOperationSequenz(this.dbc, this.pp);
            }

        } catch (Exception ex) {
        }
    }
}
