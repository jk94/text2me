package de.jkdh.text2me_server.main;

import javax.management.timer.Timer;

import de.jkdh.text2me_server.main.connection.ConnectionServer;
import de.jkdh.text2me_server.main.connection.DB_Connect;

class main {

	private Timer ttimer = new Timer();
	private static Control theControl;	
	public static void main(String[] args) {
		System.out.println("Starte Server");
		theControl = new Control(2729);
		
	}

	public void startTimer() {

	}

	public void timertick() {

	}

}
