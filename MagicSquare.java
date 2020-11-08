import java.util.Random;
import java.util.Scanner;

public class MagicSquare {

	public static void main(String[] args) {
		Scanner myMenu = new Scanner(System.in);
		int[][] matrix = null;

		while (true) {
			System.out.println(" Select one option");
			System.out.println("  Menu (1/2/3)  ");
			System.out.println("1. Create a Magic Sqaure by giving size ");
			System.out.println("2. Create a Random sqaure and solve it");
			System.out.println("3. Exit from the game");

			int Menu = myMenu.nextInt();

			if (Menu == 1) {
				// call the magic sqr creating function
				int size = GetSize();
				matrix = GenMgrSqr(size);
				PrintMatrix(matrix);
			} // end of 1st if
			else if (Menu == 2) {
				// call the Randomise func and let user play it
				if (matrix == null) {
					System.out.println("You haven't created a square yet. Do that first.");
					continue;
				}
				matrix = RandomizeMatrix(matrix);
				int count = 0;
				PrintMatrix(matrix);
				while (isMagicSqr(matrix) == false) {
					matrix = GameFunc(matrix);

					count = count + 1;
				}
				System.out.println("You made it. The game is Completed");
				System.out.print("The number of moves you took to complete game = " + count);
			}
			else if (Menu == 3) {
				System.exit(0);
			}
		}

	}// end of main

	public static int GetSize() {
		System.out.println("Enter the odd integer to create Magic Square of that size");
		Scanner scanner = new Scanner(System.in);
		int size = Integer.parseInt(scanner.next());
		if (size % 2 == 0) {
			System.out.println("Please enter odd integer");
		}
		return size;
	}

	public static int[][] GenMgrSqr(int size) {
		// Set x = 1 and y = n + 1 / 2
		int x = 0;
		int y = ((size + 1) / 2) - 1;

		int[][] MgSqr = new int[size][size];

		// Insert 1 at x and y
		MgSqr[x][y] = 1;

		// for i = 2 to n^2 do
		for (int i = 2; i <= size * size; i++) {
			
			// Wrapping
			int tx = 0;
			int ty = 0;
			if (y - 1 < 0)
				ty = size - 1;
			else
				ty = y - 1;
			
			if (x - 1 < 0)
				tx = size - 1;
			else
				tx = x - 1;

			// If element x -1, y - 1 is empty then
			if (MgSqr[tx][ty] == 0) {
				x = x - 1;
				y = y - 1;
			} else {
				x = x + 1;
			}

			// More wrapping
			if (x < 0)
				x = size - 1;
			if (x > size - 1)
				x = 0;
			if (y < 0)
				y = size - 1;
			if (y > size - 1)
				y = 0;

			// Insert i at x, y
			MgSqr[x][y] = i;
		}
		// End for
		return MgSqr;

	}

	public static int[][] RandomizeMatrix(int[][] ranMat) {// randomize the given matrix
		int i = 0;
		int j = 0;
		int size = ranMat.length;

		Random random = new Random();
		for (int a = 0; a <= size * size; a++) {

			i = random.nextInt(size);
			j = random.nextInt(size);
			int swap = ranMat[i][j];
			ranMat[i][j] = ranMat[i][j == size - 1 ? 0 : j == 0 ? size - 1 : j - 1];
			ranMat[i][j == size - 1 ? 0 : j == 0 ? size - 1 : j - 1] = swap;

		}
		return ranMat;

	}

	public static void PrintMatrix(int[][] mat) {
		for (int b = 0; b <= mat.length - 1; b++) {
			System.out.println();
			for (int c = 0; c <= mat.length - 1; c++) {
				System.out.print(mat[b][c] + " ");
			}
		}
		System.out.println();
	}

	public static int[][] GameFunc(int[][] mat) {
		System.out.println(
				"Now you are playing a game. Please enter input Row number, Coloumn number and direction like 1 2 U");
		Scanner scan = new Scanner(System.in);
		int i = scan.nextInt() - 1;
		int j = scan.nextInt() - 1;
		String direc = scan.next();

		int temp;

		switch (direc) {
		case "U":
			temp = mat[i][j];
			mat[i][j] = mat[i - 1][j];
			mat[i - 1][j] = temp;
			break;
		case "D":
			temp = mat[i][j];
			mat[i][j] = mat[i + 1][j];
			mat[i + 1][j] = temp;
			break;
		case "L":
			temp = mat[i][j];
			mat[i][j] = mat[i][j - 1];
			mat[i][j - 1] = temp;
			break;
		case "R":
			temp = mat[i][j];
			mat[i][j] = mat[i][j + 1];
			mat[i][j + 1] = temp;
			break;
		default:
			System.out.println("Invalid value!");
		}
		PrintMatrix(mat);
		return mat;

	}

	static boolean isMagicSqr(int mat[][]) {
		int N = mat.length;
		// calculate the sum of
		// the prime diagonal
		int sum = 0, sum2 = 0;
		for (int i = 0; i < N; i++)
			sum = sum + mat[i][i];

		// the secondary diagonal
		for (int i = 0; i < N; i++)
			sum2 = sum2 + mat[i][N - 1 - i];

		if (sum != sum2)
			return false;

		// For sums of Rows
		for (int i = 0; i < N; i++) {

			int rowSum = 0;
			for (int j = 0; j < N; j++)
				rowSum += mat[i][j];

			// check if every row sum is
			// equal to prime diagonal sum
			if (rowSum != sum)
				return false;
		}

		// For sums of Columns
		for (int i = 0; i < N; i++) {

			int colSum = 0;
			for (int j = 0; j < N; j++)
				colSum += mat[j][i];

			// check if every column sum is
			// equal to prime diagonal sum
			if (sum != colSum)
				return false;
		}

		return true;
	}

}
