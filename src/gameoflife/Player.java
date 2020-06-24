package gameoflife;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {
	String dimensions;
	long generations;
	int[][] gs;

	public void getInput() {
		Scanner s = new Scanner(System.in);

		boolean b = true;
		do {
			System.out.println("Enter game of life dimensions(nxn): ");
			dimensions = s.nextLine();
			Pattern p = Pattern.compile("\\d+x\\d+");
			Matcher m = p.matcher(dimensions);
			b = !m.matches();
		} while (b);

		do {
			System.out.println("Enter game of life number of generations: ");
			String numGenerations = s.nextLine();
			generations = Long.parseLong(numGenerations);
		} while (!(generations > 0 && generations < 100));

		//randomly generate values
		String[] dimensions = this.dimensions.split("x");
		int x = Integer.parseInt(dimensions[0]);
		int y = Integer.parseInt(dimensions[1]);
		gs = new int[x][y];
		for (int i = 0; i < gs.length; i++) {
			for (int j = 0; j < gs[0].length; j++) {
				gs[i][j] = (int) (Math.random() * 2);
			}
		}

		GameOfLife gol = new GameOfLife();
		gol.play(gs, generations);
	}

	public static void main(String[] args) {
		Player p = new Player();
		p.getInput();
	}

}
