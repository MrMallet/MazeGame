package ie.gmit.sw.ai;

import net.sourceforge.jFuzzyLogic.*;

public class FuzzyFight {
	private boolean playerWin = true;
	
	public boolean fuzzyFight(int health, int playerPower, int enemyPower){
		
		String fileName = "fcl/fight.fcl";
        FIS fis = FIS.load(fileName,true);
		
		//FunctionBlock functionBlock = fis.getFunctionBlock("fight");
		fis.setVariable("health", health);
		fis.setVariable("playerPower", playerPower);
        fis.setVariable("enemyPower", enemyPower);
		
        fis.evaluate();
		
		return playerWin;
	}

}
