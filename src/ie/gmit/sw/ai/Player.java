package ie.gmit.sw.ai;

public class Player {
	private int playerHealth = 20;
	private int playerPower = 20;
	
	public int getPlayerHealth() {
		return playerHealth;
	}
	public void setPlayerHealth(int playerHealth) {
		this.playerHealth += playerHealth;
	}
	public int getPlayerPower() {
		return playerPower;
	}
	public void setPlayerPower(int playerPower) {
		this.playerPower = playerPower;
	}
	
	
	
}
