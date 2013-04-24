/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jkdh.text2me_server.main.dbdata;

import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class DBmessage {

    private int messageID, senderID, empfaengerID, status;
    private String content;
    private Timestamp time;

    public DBmessage(int messageID, int senderID, int empfaengerID, int status, String content, Timestamp time) {
        this.messageID = messageID;
        this.senderID = senderID;
        this.empfaengerID = empfaengerID;
        this.status = status;
        this.content = content;
        this.time = time;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getEmpfaengerID() {
        return empfaengerID;
    }

    public void setEmpfaengerID(int empfaengerID) {
        this.empfaengerID = empfaengerID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    
}
