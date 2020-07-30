package br.com.horizon.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import br.com.horizon.model.Product;
import br.com.horizon.model.User;
import br.com.horizon.utils.View;

public class UserHandlerJob extends Thread {

	//Attributes
	private static Socket socket;
	private static BufferedReader input;
	private static PrintWriter output;

	//Initializer
	public UserHandlerJob(Socket socket) throws IOException {
		setSocket(socket);
	}

	@Override
	public void run() {
		super.run();
		try {
			startServer();
			//Menu
			int option;
			do {

				String[] registration = input.readLine().split(":");
				option = Integer.parseInt(registration[0]);
				System.out.println(option);

				switch(option) {

				//Login
				case 1:
					boolean check = checkLogin(registration[1], registration[2]);
					output.println(check);
					if (check) {
						User user = sendLogin(registration[1], registration[2]);
						mainMenu(user);
					}
					break;

				//Registration
				case 2:
					createLogin(Integer.parseInt(registration[1]), registration[2], registration[3]);
					break;

				//Exit
				case 3:
					option = 0;
					break;
				} 
			}while(option != 0);
			
			finishServer();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//Methods

	//TODO: Setups Server
	private static void startServer() throws Exception {
		setInput(new BufferedReader(new InputStreamReader(getSocket().getInputStream())));
		setOutput(new PrintWriter(getSocket().getOutputStream(), true));
	}

	private static void finishServer() {
		try {
			input.close();
			output.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//TODO Login Setups

	private static boolean checkLogin(String name, String pw) {
		String username = name;
		String password = pw;

		for (User user: Server.users) {

			if(user.getName().equals(username) && user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	private static User sendLogin(String name, String pw) {
		int id;
		String username = name;
		String password = pw;

		for (User user: Server.users) {
			if(user.getName().equals(username) && user.getPassword().equals(password)) {
				id = user.getId();
				output.println(user.toString());
				return user;
			}
		}
		
		return null;
	}


	private static void createLogin(int id, String username, String password) {
		User user = new User(id, username, password);
		Server.users.add(user);
		//View.showText("Horizon", "Cadastro Feito com sucesso! :D");
	}

	//TODO: MainMenu
	private static void mainMenu(User user) {
		output.println(user.getId() + ":" + user.getName() + ":" + user.getPassword());
		try {
			int option;
			do {

				String userInputs[] = input.readLine().split(":");
				option = Integer.parseInt(userInputs[0]);
				System.out.println(option);

				//Product
				switch (option) {

				//Create
				case 1:
					Product product = new Product(Integer.parseInt(userInputs[1]), userInputs[2], Integer.parseInt(userInputs[3]));
					user.getRegisteredProducts().add(product);
					//output.println("");
					break;

					//Read

				case 2:
					break;

					//Update
				case 3:
					if (user.getRegisteredProducts() != null) {
						String newName = userInputs[2];
						int newQtd = Integer.parseInt(userInputs[3]);

						for(Product newProduct: user.getRegisteredProducts()) {
							if(newProduct.getId() == Integer.parseInt(userInputs[1])) {
								newProduct.setName(newName);
								newProduct.setQtd(newQtd);
								//View.showText("Update", "Atualizado com sucesso");
							}
						}
					}
					break;

					//Delete
				case 4:
					if(!userInputs[1].isEmpty()) {
						for(Product deleteProduct: user.getRegisteredProducts()) {
							if(deleteProduct.getId() == Integer.parseInt(userInputs[1])) {
								user.getRegisteredProducts().remove(deleteProduct);
							}
						}
 					}
					break;
				case 5:
					//Exit
				case 6:
					option = 0;
				}

			}while (option != 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Getters and Setters
	private static Socket getSocket() {
		return socket;
	}

	public static void setSocket(Socket soc) {
		socket = soc;
	}

	public BufferedReader getInput() {
		return input;
	}

	public static void setInput(BufferedReader in) {
		input = in;
	}

	public PrintWriter getOutput() {
		return output;
	}

	public static void setOutput(PrintWriter out) {
		output = out;
	}

}
