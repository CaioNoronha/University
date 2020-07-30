package br.com.horizon.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import br.com.horizon.model.Product;
import br.com.horizon.model.User;
import br.com.horizon.utils.Utils;
import br.com.horizon.utils.View;

public class Client {

	private static Socket socket;
	private static BufferedReader input;
	private static PrintWriter output;

	public static void main(String[] args) {

		startClient();			

		//Menu
		int option;

		do {
			option = initialMenu();

			switch(option) {

			//Login
			case 1:
				if(loginView()) {
					User user;
					try {
						String dataUser = input.readLine();
						user = Client.convertUser(dataUser);
						View.showText("Login Horizon", "Logado com sucesso! :D");
						mainMenu(user);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					View.showWarning("Login Horizon", "Login ou senha inválidos!");
				}
				break;

			//Registration
			case 2:
				registrationView();
				break;

				//Exit
			case 3:
				output.println(option + ":");
				option = 0;
				break;

			}

		} while(option != 0);

		finishClient();
	}

	private static User convertUser(String stringUser) {
		String[] convert = stringUser.split(":");

		User user = new User(Integer.parseInt(convert[0]), convert[1], convert[2]);
		user.setRegisteredProducts(Client.convertProducts(convert[3]));

		return user;
	}

	private static ArrayList<Product> convertProducts(String stringProducts) {
		stringProducts = stringProducts.replaceAll("\\[|\\]", "");
		String[] convert = stringProducts.split(",");
		ArrayList<Product> products = new ArrayList<Product>();

		if(stringProducts.isEmpty()) {
			return products;
		}

		for (String s: convert) {
			String[] stringProduct = s.split("/");
			products.add(new Product(Integer.parseInt(stringProduct[0]), stringProduct[1], Integer.parseInt(stringProduct[2])));
		}

		return products;
	}


	//TODO Client Setups
	public static void startClient() {
		try {
			socket = new Socket("localhost", 9806);
			setInput(new BufferedReader(new InputStreamReader(socket.getInputStream())));
			setOutput(new PrintWriter(socket.getOutputStream(), true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void finishClient() {
		try {
			socket.close();
			input.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int initialMenu(){

		int userInput = View.inputInt("Horizon Company", "1 - Login\n2 - Cadastrar\n3 - Sair");

		while(userInput < 1 || userInput > 3) {
			View.showWarning("Horizon", "Digite algo válido!(1, 2 ou 3)");
			userInput = View.inputInt("Horizon Company", "1 - Login\n2 - Cadastrar\n3 - Sair");
		}

		return userInput;
	}


	private static void mainMenu(User user) throws IOException {
		int option;

		do {
			//String[] userData = input.readLine().split(":");

			option = View.inputInt("Horizon Products", "O que deseja fazer?\n" +
					"1 - Cadastrar Produto\n" +
					"2 - Listar Produtos\n" +
					"3 - Editar Produto\n" +
					"4 - Deletar Produto\n" +
					"5 - Abrir Chat Online\n" +
					"6 - Sair");

			while(option < 1 || option > 6) {
				View.showWarning("Horizon Produtos", "Digite algo válido(1 a 6)!");
				option = View.inputInt("Horizon Products", "O que deseja fazer?\n" +
						"1 - Cadastrar Produto\n" +
						"2 - Listar Produtos\n" +
						"3 - Editar Produto\n" +
						"4 - Deletar Produto\n" +
						"5 - Abrir Chat Online\n" +
						"6 - Sair");
			}

			switch (option) {

			//Create
			case 1:
				String productName = View.inputString("Horizon Cadastro Produtos", "Digite o nome de seu produto:");
				int productQtd = View.inputInt("Horizon Cadastro Produtos", "Digite a quantidade disponível em estoque:");
				int productId = View.inputInt("Horizon Cadastro Produtos", "Digite o código do produto(único)");
				boolean check = true;

				//Confere o nome
				if (productName.isEmpty()) {
					View.showWarning("Horizon Cadastro Produtos", "Digite algo diferente de vazio!");
					check = false;
				}

				//Confere a quantidade
				if (productQtd <= 0) {
					View.showWarning("Horizon Cadastro Produtos", "Digite algo acima de 0!");
					check = false;
				}

				//Confere o id

				for(Product product: user.getRegisteredProducts()) {
					if (productId == product.getId()) {
						View.showWarning("Horizon Cadastro Produtos", "ID de produto já utilizado no:\n\n" +
								"Nome:" + product.getName() + "\n" +
								"Id:" + product.getId() + "\n" +
								"Quantidade:" + product.getQtd());
						check = false;
					}
				}


				if(check) {
					user.getRegisteredProducts().add(new Product(productId, productName, productQtd));
					output.println(option + ":" + productId + ":" + productName + ":" + productQtd);
				}
				break;

			//Read
			case 2:
				//output.println(option + ":");

				String products = "";
				for(Product product: user.getRegisteredProducts()) {
					products +=
						"ID: " + product.getId() + "\n" +
						"Nome: " + product.getName() + "\n" +
						"Quantidade: " + product.getQtd() + "\n\n";
				}
				View.showText("Horizon Listagem Produtos", "Produtos:\n" + products);
				break;

			//Update
			case 3:

				if(user.getRegisteredProducts().size() <= 0) {
					View.showText("Horizon Editar Produtos", "Não existem produtos cadastrados!");
					break;
				}

				int id = View.inputInt("Horizon Editar Produtos", "Qual o código do produto?");
				for (Product product: user.getRegisteredProducts()) {

					if(product.getId() == id) {
						String newName = View.inputString("Horizon Editar Produtos", "Digite o novo nome de seu produto:");
						int newQtd = View.inputInt("Horizon Editar Produtos", "Digite a nova quantidade disponível em estoque:");
						boolean newCheck = true;

						//Confere o nome
						if (newName.isEmpty()) {
							View.showWarning("Horizon Editar Produtos", "Digite algo diferente de vazio!");
							newCheck = false;
						}

						//Confere a quantidade
						if (newQtd <= 0) {
							View.showWarning("Horizon Editar Produtos", "Digite algo acima de 0!");
							newCheck = false;
						}

						if(newCheck) {
							output.println(option + ":" + id + ":" + newName + ":" + newQtd);
							product.setName(newName);
							product.setQtd(newQtd);
							View.showText("Horizon Editar Produtos", "Produto atulizado com sucesso!");
						}
						break;
					}
				}
				//View.showText("Horizon Editar Produtos", "Não existe um produto com esse código!");
				break;

			//Delete
			case 4:
				if(user.getRegisteredProducts().size() <= 0) {
					View.showText("Horizon Editar Produtos", "Não existem produtos cadastrados!");
					break;
				}

				int deleteId = View.inputInt("Horizon Deletar Produtos", "Qual o código do produto?");
				for (Product product: user.getRegisteredProducts()) {
					if(product.getId() == deleteId) {
						int deleteOption = View.inputInt("Horizon Deletar Produtos", "Deseja deletar o produto:\n" +
								product.getName() + "? (1 - Sim  2 - Não)");

						while(deleteOption < 1  || deleteOption > 2) {
							View.showText("Horizon Deletar Produtos", "Digite algo válido!(1/2)");
							deleteOption = View.inputInt("Horizon Deletar Produtos", "Deseja deletar o produto:\n" +
									product.getName() + "? (1 - Sim  2 - Não)");
						}

						if (deleteOption == 1) {
							output.println(option + ":" + deleteId);
							user.getRegisteredProducts().remove(product);
							View.showText("Horizon Deletar Produtos", "Produto deletado com sucesso!");
						} else {
							View.showText("Horizon Deletar Produtos", "Produto não deletado");
						}
						break;
					}
				}
				break;

			case 5:
				ClientChat client = new ClientChat();
				try {
					client.startChat();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 6:
				output.println(option + ":");
				option = 0;
				break;
			}
			//Mandar Dados
		} while(option != 0);


	}

	private static void registrationView() {

		String name;
		String password;
		int id;
		int option;

		do {
			name = View.inputString("Cadastro", "Digite seu nome de usuário\n");
			password = View.inputString("Cadastro", "Digite a sua senha\n");
			option = View.inputInt("Cadastro", "Deseja confirmar?\n(1 - Sim/2 - Não/3 - Sair)\nnome:" + name + "\nsenha:" + password);

			if(name.isEmpty() || password.isEmpty()) {
				View.showText("Cadastro Horizon", "Nome ou Senha Inválidos!");
			}
			while(option < 1 || option > 3) {
				View.showWarning("Cadastro Horizon", "Digite algo válido\n(1 - Sim/2 - Não/3 - Sair)");
				option = View.inputInt("Cadastro", "Deseja confirmar?(1 - Sim/2 - Não/3 - Sair)\nnome:" + name + "\nsenha:" + password);
			}
		} while(option == 2 || (name.isEmpty()|| password.isEmpty()));
		id = Utils.getId();
		output.println(2 + ":" + id + ":"  + name + ":" + password);
	}

	/** Open loginView and returning if the user is  */
	private static Boolean loginView() {
		
		boolean check = false;

		try {
			String name = View.inputString("Cadastro", "Digite seu nome de usuário\n");
			String password = View.inputString("Cadastro", "Digite a sua senha\n");

			output.println(1 + ":" + name + ":" + password);
			System.out.println(name + password);
			String loginCheck = input.readLine();
			check = Boolean.parseBoolean(loginCheck);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return check;
	}



	//Getters and Setters
	public static BufferedReader getInput() {
		return input;
	}

	public static void setInput(BufferedReader input) {
		Client.input = input;
	}

	public static PrintWriter getOutput() {
		return output;
	}

	public static void setOutput(PrintWriter output) {
		Client.output = output;
	}

}
