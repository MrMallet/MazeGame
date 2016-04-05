package ie.gmit.sw.ai;

import net.sourceforge.jFuzzyLogic.*;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FuzzyFight {
	private float damage = 0;
	//private static int health = 90;
	//private static int power = 19;
	
	public float fuzzyFight(int health, int power){
	//public static void main(String[] args) throws Exception{
		String fileName = "fcl/fight.fcl";
        FIS fis = FIS.load(fileName,true);
		
		FunctionBlock functionBlock = fis.getFunctionBlock("Fight");
		fis.setVariable("health", health);
		fis.setVariable("power", power);
		
        fis.evaluate();
		return damage = (float) (fis.getVariable("damage").getValue());
        
		//return playerWin;
	}

}
