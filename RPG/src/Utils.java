
public class Utils {
	
	//CriarPersonagem
	public static Player createPlayer()  {
		
		String name;
		String nickName;
		int type;
		
		name = View.inputString("Creating Player", "Write your  real name:");
		
		while(name.length() <= 0)  {
			View.showErrorMessage("ERROR", "Can't create a empty name!");
			name = View.inputString("Creating Player", "Write your  real name:");

		}
		
		nickName = View.inputString("Creating Player", "Write your nickname:");
		
		while(name.length() <= 0)  {
			View.showErrorMessage("ERROR", "Can't create a empty name!");
			nickName = View.inputString("Creating Player", "Write your nickname:");
		}
		
		type = View.inputInt("Creating Player", "Which class do you want to play:\n"
				+ "1 - Archer \n"
				+ "2 - Wizard \n"
				+ "3 - Warrior \n"
				+  "4 - Rogue");
		while(type <= 0 || type > 4) {
			View.showErrorMessage("ERROR", "Choosen one of the options avaliable!");
			type = View.inputInt("Creating Player", "Which class do you want to play:\n"
					+ "1 - Archer \n"
					+ "2 - Wizard \n"
					+ "3 - Warrior \n"
					+  "4 - Rogue");
		}
		
		
		return new Player(name, nickName, 1);
	}
	
	//CriarBatalha
	
	//CriarItens

}
