package MyProject;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class ChooseMapType {
	private static Scanner inputs;

	public static ArrayList<String> readFile(String fileName) {
		File file = new File(fileName);
		try {
			inputs = new Scanner(file);
			ArrayList<String> list = new ArrayList<String>();
			while (inputs.hasNextLine())
				list.add(inputs.nextLine());
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

	public abstract int[][] getInput();
	
	public final int[][] read() {
		return getInput();
	}
	
	
}
