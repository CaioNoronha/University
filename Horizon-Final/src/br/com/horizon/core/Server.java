package br.com.horizon.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import br.com.horizon.model.User;

public class Server {
	
	public static ArrayList<User> users = new ArrayList<User>();


	public static void main(String[] args) throws IOException {

		ServerSocket ss = new ServerSocket(9806);
		

		while(true) {

			Socket soc = ss.accept();
			UserHandlerJob handler = new UserHandlerJob(soc);
			handler.start();
		}
	}

}


