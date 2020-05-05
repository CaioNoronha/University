import java.util.Date;

public class Batalha {
	
	//Atributes
	private int date;
	private Player champion;
	private Player challengeChampion;
	private Player winner;
	
	//Constructor
	Batalha(int date, Player champion, Player challengeChampion) {
		battle();
	}
	
	//Methods
	private void battle() {
		
		if(champion.getType() < challengeChampion.getType()) {
			winner = challengeChampion;
		} else {
			winner = champion;
		}
	}
	
	
	
	
	
	//Getters and Setters

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public Player getChampion() {
		return champion;
	}

	private void setChampion(Player champion) {
		this.champion = champion;
	}

	public Player getChallengeChampion() {
		return challengeChampion;
	}

	private void setChallengeChampion(Player challengeChampion) {
		this.challengeChampion = challengeChampion;
	}

	public Player getWinner() {
		return winner;
	}

	private void setWinner(Player winner) {
		this.winner = winner;
	}
	

}
