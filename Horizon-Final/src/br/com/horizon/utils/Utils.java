package br.com.horizon.utils;

import br.com.horizon.model.User;

public class Utils {
	
	public static int idCount = 0;
	
	public static int getId() {
		return idCount++;
	}
}
