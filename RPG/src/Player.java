import java.util.ArrayList;

public class Player {
	
	//Atributes
	private String nickname;
	private String name;
	private int type;
	private ArrayList<Item> inventary;
	
	
	//Constructor
	Player(String name, String nickName, int type) {
		
		inventary = new ArrayList<Item>();
		
		if(name.length() <= 0)  {
			throw new IllegalArgumentException("Can't use a empty name!");
		}
		
		if(nickName.length() <= 0)  {
			throw new IllegalArgumentException("Can't use a empty name!");
		}
		
		setName(name);
		setNickname(nickName);
		setType(type);
	}
	
	//Methods
	public String getInventary() {
		
		String itens = "";
		
		for(Item item : inventary) {
			itens += item.getName() + "\n";
		}	
		return itens;
	}
	
	public String getNames() {
		
		String names = "";
		
		names += getName()  + " \n";
		names += getNickName();
		
		return names;
	}
	
	
	//Getters and Setters
	
	//NickName
	private void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNickName() {
		return this.nickname;
	}
	
	//Name
	private void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	//Type
	public int getType() {
		return type;
	}
	private void setType(int type) {
		this.type = type;
	}

}
