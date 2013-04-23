package de.jkdh.text2me_server.main.operations;

import de.jkdh.common.ProtocolHelper;
import de.jkdh.common.ProtocolPacket;
import de.jkdh.text2me_server.main.Konstanten;
import de.jkdh.text2me_server.main.connection.DB_Connect;

public class Operations {

    public static void runOperationSequenz(DB_Connect dbc, ProtocolPacket pp){
        switch(pp.getContentType()){
            case ProtocolHelper.CONTENT_TYPE_SEND:
                
                
                
               break;
            case ProtocolHelper.CONTENT_TYPE_GET:
                break;
            case ProtocolHelper.CONTENT_TYPE_READ:
                Setter_Operations.setMessageStatus(dbc, 1, Konstanten.STATUS_GELESEN_ID);
                
                break;
            case ProtocolHelper.CONTENT_TYPE_DELIVER:
                break;
            case ProtocolHelper.CONTENT_TYPE_OK:
                break;
        }
    }
    
 
}
