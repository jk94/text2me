/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Listener;

import de.jkdh.common.ProtocolPacket;
import de.jkdh.text2me_server.main.connection.DB_Connect;
import de.jkdh.text2me_server.main.operations.Operations;

/**
 *
 * @author Admin
 */
public class ValidListener {

    public ValidListener(DB_Connect dbc, ProtocolPacket pp) {
        while (!pp.isValid()) {
            Operations.runOperationSequenz(dbc, pp);
        }
    }
}
