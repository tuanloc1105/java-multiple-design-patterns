package MyProject;

import java.util.ArrayList;

public class xeMay extends ChooseMapType{

	@Override
	public int[][] getInput() {
		String fileName = "datag.txt";
		ArrayList<String> lines = readFile(fileName);
		int[][] am = new int[lines.size()][lines.size()];
		for (int i = 0; i < lines.size(); i++) {
			String[] tokens = lines.get(i).split(" ");
			for (int j = 0; j < tokens.length; j++) {
				am[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		return am;
	}
	
	
	
}
