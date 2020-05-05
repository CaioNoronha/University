import java.util.ArrayList;

public class Executora {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Variables
		int option = 0;
		String name;
		String name2;
		
		//Initialized Classes
		Player masterRPG = new Player("Caio", "MasterSupreme", 1);
		Clan clan = new Clan("AcademyBattle", masterRPG);
		Relatorio summary = new Relatorio();
		
		//Generic Class
		Player player;
		Player player2;
		Batalha battle;
		
		//Instructions
		
		
		
		do {
			option = menu();
			
			switch(option) {
			
				//Create a Player
				case 1:
					player = Utils.createPlayer();
					clan.addMember(player);
				
				//Show a Player
				case 2:
					name = View.inputString("Search Player", "Write the nickName of your player");
					player = clan.searchMember(name);
					View.showStringMessage(player.getNickName(), player.getNames() + " \n"
							+ player.getInventary());
					
				
				//Battle
				case 3:
					name = View.inputString("Battle", "Write the nickName of your player");
					name2 = View.inputString("Battle", "Write the nickName of the other player");
					player = clan.searchMember(name);
					player2 = clan.searchMember(name2);
					
					if(player != null &&  player2 != null) {
						battle = new Batalha(1, player, player2);
						player = battle.getWinner();
						View.showStringMessage("Winner", "The winner was the" + player.getNickName());
					} else {
						View.showErrorMessage("ERROR", "Can't Battle!");
					}
					
				//Exit
				case 4:
					option = 0;
					break;
			}
			
			
		}while(option != 0);

	}
	
	public static int menu() {
		int option;
		
		option =  View.inputInt("RPGamer", "What do you want to do? \n 1 - Create a Player \n"
				+ "Show a Player \n"
				+ "Battle\n"
				+ "Exit");
		
		return option;
	}

}
