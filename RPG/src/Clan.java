import java.util.ArrayList;

public class Clan {
	
	//Atributes
	private String name;
	private ArrayList<Player> members;
	private Player lider;
	
	//Constructor
	Clan(String name, Player lider)  {
		setLider(lider);
		setName(name);
	}
	
	//Methods
	public void addMember(Player member) {
		
		if(members ==  null) {
			members = new ArrayList<Player>();
		}
		members.add(member);
	}
	
	public void banMember(Player member) {
		
	}
	
	public Player searchMember(String nickName)  {
		
		if(members ==  null) {
			return null;
		}
		
		for(Player member : members) {
			if(name.equals(member.getNickName())) {
				return member;
			}
		}
		
		return  null;
	}
	
	//Getters and Setters
	
	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public Player getLider() {
		return lider;
	}

	private void setLider(Player lider) {
		this.lider = lider;
	}
	
	

}
