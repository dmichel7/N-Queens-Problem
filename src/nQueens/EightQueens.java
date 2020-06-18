public class EightQueens 
{	
	void solveNQueens(int n)
	{
		boolean failed = false;
		int[][] board = {{0, 0, 0, 0, 0, 0, 0, 0},
				 {0, 0, 0, 0, 0, 0, 0, 0},
				 {0, 0, 0, 0, 0, 0, 0, 0},
				 {0, 0, 0, 0, 0, 0, 0, 0},
				 {0, 0, 0, 0, 0, 0, 0, 0},
				 {0, 0, 0, 0, 0, 0, 0, 0},
				 {0, 0, 0, 0, 0, 0, 0, 0},
				 {0, 0, 0, 0, 0, 0, 0, 0},};
		
		int queensToPlace = n;
		
		// Loops until all specified queens are placed.
		while(queensToPlace != 0)
		{
			boolean placed = false;
			int cycleCount = 0;

			// Generates random row and column positions for the queen to be placed and keeps looping until the queen is safe to place.
			// The number of cycles is to protect against an infinite loop when randomly placing a queen is impossible.
			while(!placed && cycleCount != 10000000) 
			{
				int row = (int) (Math.random() * 8);
				int col = (int) (Math.random() * 8);
				
				// Checks if the randomly placed queen is safe from other randomly placed queens
				if(isSafe(board, row, col))
				{
					// Sets the queen as 2 to denote that the queen is glued and among the queens randomly placed.
					board[row][col] = 2;
					queensToPlace--;
					
					placed = true;
				}
				cycleCount++;
			}

			if(cycleCount == 10000000)
			{
				System.out.println(" --------------------------------------------------------");
				System.out.println("| ** FAILED TO PLACE RANDOM QUEEN #: " + (n - queensToPlace) + " ** |");
				System.out.println(" --------------------------------------------------------");
				failed = true;
				break;
			}
			
		}
		
		if (!failed) 
		{
			System.out.println(" --------------------------------------------------------");
			System.out.println("| ** Board with " + n + " random queen(s) placed before solve ** |");
			System.out.println(" --------------------------------------------------------");
			printBoard(board);

			if (solve(board, 0) == false) 
			{
				System.out.println(" -------------------------------");
				System.out.println("| ** NO SOLUTIONS AVAILABLE! ** |");
				System.out.println(" -------------------------------\n");
			} 
			else 
			{
				System.out.println(" --------------------");
				System.out.println("| ** Solved Board ** |");
				System.out.println(" --------------------");
				printBoard(board);
			}
		}

		System.out.println("-------------------------------------------------------------\n");
	}
	
	/* Checks in all directions if the given queen is safe */
	boolean isSafe(int board[][], int row, int col)
	{
		boolean safe = true;
		
		/* Check column if upper column safe */
        	for(int p = 0; p < 8; p++)
        	{
        		if (board[p][col] == 1 || board[p][col] == 2) 
            		{
            			safe = false; 
            		}
        	}
        
        	/* Check row if safe */
        	for(int i = 0; i < 8; i++)
        	{
        		if (board[row][i] == 1 || board[row][i] == 2) 
            		{
            			safe = false; 
            		}
        	}
  
        	/* Check upper left diagonal if safe */
        	for(int r = row, c = col; c >= 0 && r >= 0; c--, r--) 
        	{
        		if (board[r][c] == 1 || board[r][c] == 2)
        		{
        			safe = false;
        		} 
        	}
  
        	/* Check lower left diagonal if safe */
        	for(int k = row, m = col; m >= 0 && k < 8; k++, m--) 
        	{
        		if (board[k][m] == 1 || board[k][m] == 2)
        		{
        			safe = false;
        		} 
        	}
        
        	/* Check upper right diagonal if safe */
        	for(int a = row, b = col; b < 8 && a >= 0; a--, b++)
        	{
        		if(board[a][b] == 1 || board[a][b] == 2)
        		{
        			safe = false;
        		}
        	}
        
        	/* Check lower right diagonal if safe */
        	for(int x = row, y = col; x < 8 && y < 8; x++, y++)
		{
        		if(board[x][y] == 1 || board[x][y] == 2)
        		{
        			safe = false;
        		}
        	}
  
        	return safe; 
	}
	
	boolean solve(int board[][], int col) 
	{
		/* Base case. If all columns are checked, return true. */
		if(col >= 8)
		{
			return true;
		}
		
		// Loops through every row checking if a queen is safe to place 
		for(int i = 0; i < 8; i++)
		{
			if(isSafe(board, i, col) || board[i][col] == 2)
			{
				// Establishes a previous value so that the glued queens do not get overwritten in the event of backtracking.
				int previousVal = board[i][col];
				
				// Checks if the queen is glued and if not, place the queen.
				if(board[i][col] != 2) 
				{
					board[i][col] = 1;
				}
				
				// Recursively calls the solve method while incrementing to the next column. If all queens get placed, the board is solved and will return true.
				if(solve(board, col +1)) 
				{
					return true;
				}
				
				// Backtracking by placing the previous value of the array back on the board.
				board[i][col] = previousVal;
			}
		}
		
		// No solution found.
		return false;
	}
	
	void printBoard(int board[][]) 
    	{ 
		System.out.println();
        	for(int i = 0; i < 8; i++)
        	{ 
            		for(int j = 0; j < 8; j++)
            		{
            			System.out.print(" " + board[i][j] + " "); 
            		}
            		System.out.println(); 
		} 
        	System.out.println("\n");
    	} 
	
	public static void main (String[] args)
	{
		EightQueens queen1 = new EightQueens();
		
		queen1.solveNQueens(1);
		queen1.solveNQueens(2);
		queen1.solveNQueens(3);
		queen1.solveNQueens(4);
		queen1.solveNQueens(5);
		queen1.solveNQueens(6);
		queen1.solveNQueens(7);
		
	}
}
