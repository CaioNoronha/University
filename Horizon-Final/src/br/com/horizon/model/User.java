package br.com.horizon.model;

import java.util.ArrayList;

public class User {
	
	//Attributes
	private int id;
	private String name;
	private String password;
	private ArrayList<Product> registeredProducts;
	
	//Constructor
	public User(int id, String name, String password) {
		setId(id);
		setName(name);
		setPassword(password);
		setRegisteredProducts(new ArrayList<Product>());
	}

	@Override
	public String toString() {
		return  getId() + ":" + getName() + ":" + getPassword() + ":" + getRegisteredProducts().toString();
	}

	//Getters and Setters
	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	private void setName(String userName) {
		this.name = userName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Product> getRegisteredProducts() {
		return registeredProducts;
	}

	public void setRegisteredProducts(ArrayList<Product> registeredProducts) {
		this.registeredProducts = registeredProducts;
	}
}


/*
MENU

Logar/Criar Login
Cadastrar Produto
Listar os Produtos
Remover Produto
Chat

*/