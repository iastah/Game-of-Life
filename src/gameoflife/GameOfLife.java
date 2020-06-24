package gameoflife;

/**
 * The universe of the Game of Life is an infinite, two-dimensional orthogonal
 * grid of square cells, each of which is in one of two possible states, live or
 * dead, (or populated and unpopulated, respectively). Every cell interacts with
 * its eight neighbours, which are the cells that are horizontally, vertically,
 * or diagonally adjacent. At each step in time, the following transitions
 * occur:
 * 
 * Any live cell with fewer than two live neighbours dies, as if by
 * underpopulation. Any live cell with two or three live neighbours lives on to
 * the next generation. Any live cell with more than three live neighbours dies,
 * as if by overpopulation. Any dead cell with exactly three live neighbours
 * becomes a live cell, as if by reproduction. These rules, which compare the
 * behavior of the automaton to real life, can be condensed into the following:
 * 
 * Any live cell with two or three live neighbours survives. Any dead cell with
 * three live neighbours becomes a live cell. All other live cells die in the
 * next generation. Similarly, all other dead cells stay dead. The initial
 * pattern constitutes the seed of the system. The first generation is created
 * by applying the above rules simultaneously to every cell in the seed; births
 * and deaths occur simultaneously, and the discrete moment at which this
 * happens is sometimes called a tick. Each generation is a pure function of the
 * preceding one. The rules continue to be applied repeatedly to create further
 * generations.
 * 
 * Goal: Create a graphical UI to play Conway's Game of Life
 * 
 * Input parameters: User provides dimensions for 2-d array and number of
 * generations
 * 
 * @iastah
 *
 */
public class GameOfLife {

	class Neighbor {

		int[][] gs;
		int iMax;
		int jMax;

		public Neighbor(int[][] gs) {
			this.gs = gs;
			iMax = gs.length;
			jMax = gs[0].length;
		}

		public int checkNumNeighbors(int i, int j) {
			int count = 0;
			int ni = 0;
			int nj = 0;
			if ((nj = j - 1) >= 0 && (ni = i - 1) >= 0) { // NW
				if (gs[ni][nj] == 1) {
					count++;
				}
			}
			if ((ni = i - 1) >= 0) { // N
				if (gs[ni][j] == 1) {
					count++;
				}
			}
			if ((nj = j + 1) < jMax && (ni = i - 1) >= 0) { // NE
				if (gs[ni][nj] == 1) {
					count++;
				}
			}
			if ((nj = j + 1) < jMax) { // E
				if (gs[i][nj] == 1) {
					count++;
				}
			}
			if ((nj = j + 1) < jMax && (ni = i + 1) < iMax) { // SE
				if (gs[ni][nj] == 1) {
					count++;
				}
			}
			if ((ni = i + 1) < iMax) { // S
				if (gs[ni][j] == 1) {
					count++;
				}
			}
			if ((nj = j - 1) >= 0 && (ni = i + 1) < iMax) { // SW
				if (gs[ni][nj] == 1) {
					count++;
				}
			}
			if ((nj = j - 1) >= 0) { // W
				if (gs[i][nj] == 1) {
					count++;
				}
			}

			return count;
		}
	}

	public void play(int[][] in, long numGenerations) {

		int[][] x = in.clone();
		int iMax = x.length;
		int jMax = x[0].length;
		long generation = 0;

		while (generation++ < numGenerations) {
			// output current game state
			for (int i = 0; i < iMax; i++) {
				for (int j = 0; j < jMax; j++) {
					System.out.print(x[i][j]);
//					System.out.print("|"+i+","+j+"|"); //positions
				}
				System.out.println();
			}
			System.out.println();

			// update state
			int[][] y = new int[iMax][jMax];
			Neighbor n = new Neighbor(x);
			for (int i = 0; i < iMax; i++) {
				for (int j = 0; j < jMax; j++) {
					int num = n.checkNumNeighbors(i, j);
					if (x[i][j] == 1) {
						if (num < 2) {
							y[i][j] = 0;
						} else if (num < 4) {
							y[i][j] = 1;
						} else if (num > 3) {
							y[i][j] = 0;
						}
					} else {
						if (num == 3) {
							y[i][j] = 1;
						}
					}

				}
			}
			x = y;
		}

	}

}
