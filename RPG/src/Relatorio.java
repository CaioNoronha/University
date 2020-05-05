import java.util.ArrayList;

public class Relatorio {
	
	private ArrayList<Batalha> battles;
	
	
	public String showSummary() {
		String summary  = "";
		
			for(Batalha battle : battles) {
				summary += battle.getDate() + battle.getChampion().getNames() + battle.getChallengeChampion().getNames() + battle.getWinner().getNickName();
			}
			
			return summary;
	}
	
	public void addBattle(Batalha battle) {
		
		if(battles == null) {
			battles = new ArrayList<Batalha>();
		}
		battles.add(battle);
	}

}
