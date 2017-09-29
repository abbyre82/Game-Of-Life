//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            Game of Life
// Files:            GameOfLife.java, Config.java, Milestone3Tests.java
// Semester:         CS 302 Spring 2017
//
// Author:           Abby Rechkin
// Email:            rechkin@wisc.edu
// CS Login:         rechkin
// Lecturer's Name:  Gary Dahl
// Lab Section:      306
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:     Holly Michalak
// Partner Email:    hmichalak@wisc.edu
// Partner CS Login: michalak
// Lecturer's Name:  Gary Dahl
// Lab Section:      306
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//    _X__ Write-up states that Pair Programming is allowed for this assignment.
//    _X__ We have both read the CS302 Pair Programming policy.
//    _X__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.
//
// Persons:          Chrissy R, friend, helped us debug 
//					isCellLivinginNextGeneration
// Online Sources: N/A
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


	import java.util.Random;
	import java.util.Scanner;

	/**
	 * The GameOfLife class contains code using variables from the Config.java
	 * files to run the Game of Life simulation.
	 * @author Holly
	 *
	 */
	public class GameOfLife {

	    /**
	     * The main method prompts the user to enter a world pattern, then runs 
	     * the simulation based on that input. The user can go through 
	     * generations and in each generation, the rules of the game are 
	     * applied and the world is printed out. The user also has the option 
	     * to end their round of the game and to exit the game entirely.
	     * @param args
	     */
	    public static void main(String[] args){
	        
	    	//introductory message
	    	System.out.println("Welcome to Conway's Game Of Life");
			System.out.println("--------------------------------");
			
			//scanner to get user input
			Scanner scnr = new Scanner(System.in);
			
			//used to run loop until user wants to leave game
			boolean exit = false;
			
			//provides user with different world options and continues 
			//game until user enters 7
			while(!exit){
				System.out.print("1)Glider 2)Beacon 3)Beehive 4)R-pentomino");
				System.out.println("\n5)Random 6)Custom or 7)Exit");
				
				System.out.print("Choose a pattern: ");
				
				//int choice indicates pattern, obtained by getIntChoice method
				int choice = getIntChoice(scnr,1,7);
				
				//leave loop when user enters 7
				if( choice == 7){
					System.out.print("----------------------------\n");	                        
					System.out.println("End of Conway's Game Of Life");
					break;
				}
				
				//initialize blank world
				boolean[][] emptyWorld = new boolean[Config.WORLD_ROWS]
						[Config.WORLD_COLUMNS];
				
				//run appropriate method based on user choice and run simulation
				if( choice == 6){
					System.out.print("Enter a pattern using " + Config.ALIVE + 
							" for alive and " + Config.DEAD + " as dead cells."+
					    	"\nTo end the pattern, type END on its own line.\n");
					initializeCustomWorld(scnr,emptyWorld);
					runSimulation(scnr,emptyWorld);
				}
			
				if( choice == 1){
					initializeGliderWorld(emptyWorld);
					runSimulation(scnr,emptyWorld);
				}
				
				if( choice == 2){
					initializeBeaconWorld(emptyWorld);
					runSimulation(scnr,emptyWorld);
				}
				
				if( choice == 3){
					initializeBeehiveWorld(emptyWorld);
					runSimulation(scnr,emptyWorld);
				}
				
				if( choice == 4){
					initializeRpentominoWorld(emptyWorld);
					runSimulation(scnr,emptyWorld);
				}
				
				if( choice == 5){
					initializeRandomWorld(emptyWorld);
					runSimulation(scnr,emptyWorld);
				}
			}
		}
	          
	    /**
	     * Prints out the world showing each cell as alive or dead.
	     * 
	     * Loops through every cell of the world. If a cell is alive, print out
	     * the Config.ALIVE character, otherwise print out the Config.DEAD 
	     * character. 
	     * 
	     * Counts how many cells are alive and prints out the number of cells 
	     * alive. For 2 or more cells alive, for 1 cell alive and 0 cells alive
	     * the following messages are printed:
	     *    5 cells are alive.
	     *    1 cell is alive.
	     *    No cells are alive.
	     * 
	     * @param world The array representation of the current world. 
	     */  
	    
	    public static void printWorld( boolean[][] world) {
	    	//tracks number of live cells, printed at end of method
	    	int aliveCount = 0;
	    	
	    	//loops through array and prints appropriate character for
	    	//dead and live cells
	    	for(int i =0; i<world.length;i++){
				for(int j=0; j<world[i].length;j++){
					
					if(world[i][j] == false){
					System.out.print(Config.DEAD);
					}
					else{
						System.out.print(Config.ALIVE);
						aliveCount++;
					}
					
				}	
				System.out.println();
			}
			System.out.println(aliveCount + " cells are alive.");

	    }    
	    
	    /**
	     * This method clears the world by setting all the cells in the 
	     * world to false (dead). This method uses array lengths, 
	     * not constants, to determine the size of the world.
	     * 
	     * @param world the world to clear
	     */
	    public static void clearWorld( boolean[][]world) {
	       
	    	//iterate through array setting all cells to dead
	       for(int i =0; i<world.length;i++){
	    	   for(int j=0;j<world[i].length;j++){
	    		   world[i][j] = false;  
	    	   }
	       }
	    }    
	    
	    /**
	     * This method expects an integer to be entered by the user
	     * between and including the values of min and max.  If the value 
	     * entered is not an integer or is an integer but less than
	     * min or greater than max the following message is displayed:
	     *         Enter a number between 1 and 5: 
	     * Assuming that min was 1 and max was 5 when this method was
	     * called.
	     * 
	     * @param input The Scanner instance for reading from System.in.
	     * @param min  The minimum acceptable integer.
	     * @param max  The maximum acceptable integer.
	     * @return  An integer between and including min and max.
	     */
	    public static int getIntChoice(Scanner input, int min, int max) {
	    	//used in loop to continue prompting for input until input is valid 
	    	//choice
	    	boolean validInput = false;
			
	    	//new variable that will be set equal to the user's int choice
	    	int menuOption = 0;
			
	    	do{ 
	    		//checks to see if there is an integer value 
				if(input.hasNextInt()){ 
					//user input is assigned to variable userChoice
					int userChoice = input.nextInt(); 
					input.nextLine();
						if(userChoice<=7 && userChoice>=1){
							//exits loop if input is and integer between 1 and 7	
							validInput = true;
							menuOption = userChoice;
					}
						else{
							//asks the user to input a new number if their 
							//previous input is out of bounds
							System.out.print("Enter a number between 1 and 7: ");
					}
				} 
				else{
					input.nextLine();
					//asks the user to input
					//a new number if their input is not an integer
					System.out.print("Enter a number between 1 and 7: "); 
				}
			} while(!validInput);
		
	    	//user's int choice is returned
			return menuOption;
	         
	    }

	    /**
	     * Initializes the world to the Glider pattern.
	     * <pre>
	     * ..........
	     * .@........
	     * ..@@......
	     * .@@.......
	     * ..........
	     * ..........
	     * ..........
	     * ..........
	     * </pre>
	     * 
	     * The world may have any pattern within it when passed into this
	     * method. After this method call, the only living cells in the
	     * world is the Glider pattern as shown.
	     * 
	     * @param world  the existing double dimension array that will be 
	     * reinitialized to the Glider pattern.
	     */
	   
	    public static void initializeGliderWorld(boolean[][]world) {
	        //set all array values as dead
	    	clearWorld(world);
	    	//assign appropriate cells as alive
	        world[1][1] = true;
	        world[2][2] = true;
	        world[2][3] = true;
	        world[3][1] = true;
	        world[3][2] = true;
	        
	    }

	    /**
	     * Initializes the world to the Beacon pattern.
	     * <pre>
	     * ..........
	     * .@@.......
	     * .@........
	     * ....@.....
	     * ...@@.....
	     * ..........
	     * ..........
	     * ..........
	     * </pre> 
	     * 
	     * The world may have any pattern within it when passed into this
	     * method. After this method call, the only living cells in the
	     * world is the Beacon pattern as shown.
	     *
	     * @param world the existing 2-dimension array that will be 
	     * reinitialized to the Beacon pattern.
	     */        
	    public static void initializeBeaconWorld(boolean[][] world) {
	       //set all array values as dead
	       clearWorld(world);
	       //assign appropriate cells as alive
	       world[1][1] = true;
	       world[1][2] = true;
	       world[2][1] = true;
	       world[3][4] = true;
	       world[4][3] = true;
	       world[4][4] = true;
	     
	    }

	    /**
	     * Initializes the world to the Beehive pattern.
	     * <pre>
	     * ..........
	     * ..@@......
	     * .@..@.....
	     * ..@@......
	     * ..........
	     * ..........
	     * ..........
	     * ..........
	     * </pre> 
	     * 
	     * The world may have any pattern within it when passed into this
	     * method. After this method call, the only living cells in the
	     * world is the Beehive pattern as shown.
	     *
	     * @param world the existing double dimension array that will be 
	     * reinitialized to the Beehive pattern.
	     */        
	    public static void initializeBeehiveWorld(boolean[][] world) {
	    	//set all array values as dead
			clearWorld(world);
			//assign appropriate cells as alive
			world[1][2] = true;
			world[1][3] = true;
			world[2][1] = true;
			world[2][4] = true;
			world[3][2] = true;
			world[3][3] = true;
				
	    }    
	    
	    /**
	     * Initializes the world to the R-pentomino pattern.
	     * <pre>
	     * ..........
	     * ..@@......
	     * .@@.......
	     * ..@.......
	     * ..........
	     * ..........
	     * ..........
	     * ..........
	     * </pre> 
	     * 
	     * The world may have any pattern within it when passed into this
	     * method. After this method call, the only living cells in the
	     * world is the R-pentomino pattern as shown.
	     *  
	     * @param world the existing double dimension array that will be 
	     * reinitialized to the R-pentomino pattern.
	     */        
	    public static void initializeRpentominoWorld(boolean[][] world) {
	    	//set all array values as dead
	    	clearWorld(world);
	    	//assign  appropriate cells as alive
	    	world[1][2] = true;
	        world[1][3] = true;
	        world[2][1] = true;
	        world[2][2] = true;
	        world[3][2] = true;
	        
	    }    
	    
	    /**
	     * Initialize the GameOfLife world with a random selection of 
	     * cells alive. 
	     * 
	     * For testing purposes, implementations should
	     * use the Config.CHANCE_ALIVE constant and Config.SEED. 
	     * Create an instance of the java.util.Random class, setting
	     * the seed to the SEED constant. For each cell, if the
	     * returned value of the nextDouble() method is less than 
	     * Config.CHANCE_ALIVE then the cell is alive.
	     * 
	     * @param world  the existing double dimension array that will be 
	     * reinitialized to a Random pattern.
	     */ 
	    public static void initializeRandomWorld(boolean[][] world){
	    	//set all array values as dead
	    	clearWorld(world);
	    	//initialize random number generator
	    	Random rand = new Random(Config.SEED);
	    	
	    	//loop through world and randomly set cells as alive
	    	for(int i =0;i<world.length;i++){
	    		for(int j =0; j<world[i].length;j++){
	    			//use random numbers and value from Config file to set 
	    			//cells as alive
	    			if(rand.nextDouble()<Config.CHANCE_ALIVE){
	    				world[i][j] = true;
	    			}
	    			else{
	    				world[i][j] = false;
	    			}
	    		}
	    	}	     
	    }

	    /**
	     * Prompt for a pattern of cells. Each line of input corresponds
	     * to one row in the world. Continue reading lines until
	     * 'END' is entered on a line of its own. Ignore case and 
	     * leading and trailing spaces when comparing to 'END'. (See String
	     * methods such as trim() method.)
	     * 
	     * The maximum size is the size of the world passed into this method. 
	     * Any additional characters typed by the user are ignored. 
	     * When interpreting the characters typed in, only the Config.ALIVE 
	     * character is used to determine which cells are alive. All other 
	     * characters are interpreted as dead cells.
	     * 
	     * @param input The Scanner instance that reads from System.in.
	     * @param world The world array that is filled with the pattern the
	     *          user enters.
	     */
	    public static void initializeCustomWorld(Scanner input, 
	            boolean [][]world) {
	    	//set all as dead
	    	clearWorld(world);
	    	
	    	//the user can enter a very large number of lines
	    	for(int i = 0; i<1000000;i++){   	
	    		//assign user input to variable to be used in nested loop
	    		String lineCustomWorld = input.nextLine(); 
	    		
	    		//leave loop if user enters any form of "end"
				if(lineCustomWorld.trim().toLowerCase().equals("end")){ 
	    			break;
	    		}
				
				//loops through each character of each line of input
	    		for(int j=0; j<lineCustomWorld.length();j++){
	    			//if the character is within the bounds of the array and is equal to 
	    			//alive character, the cell is set as alive
		    			if(lineCustomWorld.charAt(j) == Config.ALIVE && 
		    					i<Config.WORLD_ROWS && j<Config.WORLD_COLUMNS){
							
		    					world[i][j] = true;    					
		    			}
					}    			
	    		}	    				
	    	}
	        
	    
	    
	    
	    /**
	     * Checks whether a specific cell is alive or dead.
	     * 
	     * Note that cellRow and cellColumn may not be valid indexes into 
	     * the world array. Return false for any cell outside the 
	     * world array. Checks the values of cellRow and cellColumn to make sure
	     * they are valid prior to looking in the world array. Does not use 
	     * try-catch blocks or other exception handling mechanisms.
	     * 
	     * @param world The current world.
	     * @param cellRow The row of the cell which we are wanting to know
	     *  whether it is alive.
	     * @param cellColumn The column of the cell which we are wanting
	     *  to know whether it is alive.
	     * 
	     * @return Whether the specified cell is alive.
	     */    
	    public static boolean isCellAlive(boolean [][]world, int cellRow, 
	            int cellColumn) {
	    	
	    	//initialize the cell as dead
	    	boolean alive = false;
	    	
	    	//if coordinates of the cell are outside of array, set the cell 
	    	//as dead
	    	if ((cellRow > (world.length - 1))||(cellRow < 0)||
	    			(cellColumn > (world[0].length - 1))||(cellColumn < 0)){
	    			
	    		alive = false;
	    	}
	    	//the cell is within bounds
	    	else{
	    		//the cell is alive
	    		if(world[cellRow][cellColumn] == true){
	    			alive = true;
	    		}
	    		//the cell is dead
	    		else{
	    			alive = false;
	    		}
		}
	    	//returns whether the cell is alive or dead
	    	return alive;
	    
	    }
//	    	
	    /**
	     * Counts the number of neighbors that are currently living around the 
	     * specified cell.
	     *
	     * A cell has eight neighbors. The neighbors are the cells that are 
	     * horizontally, vertically and diagonally adjacent.
	     * 
	     * Calls the isCellAlive method to determine whether any specific cell
	     * is alive.
	     * 
	     * @param world The current world.
	     * @param row The row of the cell for which we are looking for living 
	     *             neighbors.
	     * @param column The column of the cell for which we are looking for 
	     *             living neighbors.
	     * 
	     * @return The number of neighbor cells that are currently living.
	     */
	    public static int numNeighborsAlive(boolean[][] world, int row, 
	            int column) {
	    		
	    		//initializes the number of neighbors alive
	    		int aliveCount = 0;
	    		//loops through row to left and right of given cell and 
	    		//column above and below
	    		for(int i = row - 1; i<= row + 1; i++){
	    			for(int j = column - 1; j<= column + 1; j++){
	    				//checks if coordinates are within bounds of world
	    				if((i >= 0 && i < world.length) && 
	    						(j>=0 && j<world[i].length)){
	    					//increments alive count if cell at given row and 
	    					//column is alive
	    					if((isCellAlive(world, i, j)) && 
	    							!(i == row && j == column)){
	    				
	    						aliveCount++;
	    						
	    					}
	    				}
	    			}
	    		}
	    		
	    		return aliveCount;
	    } 	

	    
	    
	    /** 
	     * Whether a cell is living in the next generation of the game.
	     * 
	     * The rules of the game are as follows:
	     * 1) Any live cell with fewer than two live neighbors dies, as if caused
	     *    by under-population.
	     * 2) Any live cell with two or three live neighbors lives on to the next 
	     *    generation.
	     * 3) Any live cell with more than three live neighbors dies, as if by 
	     *    overcrowding.
	     * 4) Any dead cell with exactly three live neighbors becomes a live cell, 
	     *    as if by reproduction.
	     * 
	     * @param numLivingNeighbors The number of neighbors that are currently
	     *        living.
	     * @param cellCurrentlyLiving Whether the cell is currently living.
	     * 
	     * @return true if this cell is living in the next generation, otherwise
	     *        false.   
	     */
	    public static boolean isCellLivingInNextGeneration( int numLivingNeighbors, 
	            boolean cellCurrentlyLiving) {
	    	    	//applies neighbor rules of game to live cells
	    			
	    			//checks to make sure cell is alive
	    	    	if (cellCurrentlyLiving){
	    	    	
	    	    		if (numLivingNeighbors < 2){
	    	    		return false;
	    	    		}
	    	    	
	    		    	if (numLivingNeighbors == 2){
	    		    		return true;
	    		    	}
	    		    	if (numLivingNeighbors == 3){
	    		    		return true;
	    		    	}
	    		    		
	    		    	if (numLivingNeighbors > 3){
	    		    		return false;
	    		    	}
	    	    	}
	   
	    	    	else{
	    		    	//applies neighbor rules of game to dead cells
	    		    	if (numLivingNeighbors == 3){
	    		    		return true;
	    		    	}
	    	    	}
					return false;
	    }
	    
	    /**
	     * Determines the cells living in the next generation of the world. 
	     * The next generation is created by applying the 4 rules simultaneously 
	     * to every cell in the previous generation. Births and deaths occur 
	     * simultaneously. In other words, look only at whether cells are living
	     * in the current generation to determine whether a cell lives in the new
	     * generation. Don't look at other cells in the new generation.
	     * 
	     * For each cell in the current generation, determine whether the cell
	     * at the same coordinates is living in the next generation using the 
	     * numNeighborsAlive and the isCellLivingInNextGeneration methods.
	     * 
	     * @param currentWorld The world currently shown. 
	     * @param newWorld The new world based on the rules of life.
	     */
	    public static void nextGeneration(boolean[][] currentWorld, 
	            boolean[][] newWorld) {
	    	
	    	//loops through the current world to determine the next generation
	    	for(int i = 0; i<currentWorld.length; i++){
	    		for(int j = 0; j<currentWorld[i].length; j++){
	    			//the number of living neighbors
	    			int neighbors = numNeighborsAlive(currentWorld,i,j);
	    			//based on the number of living neighbors and if the cell 
	    			//in the current world is alive, determines if the cell 
	    			//is living in next generation in the new world
	    			newWorld[i][j] = isCellLivingInNextGeneration(neighbors,
	    					isCellAlive(currentWorld, i, j));	
	    		}
	    	}
	    }
	    
	    /**
	     * This shows each generation of the simulation starting with 
	     * generation 0. The display of the world is by calling the 
	     * printWorld method and then prompting the user for whether to 
	     * calculate and show the next generation.
	     * Then, for any input other then 'end', this calculates the next 
	     * generation using the nextGeneration method and shows it. Any case
	     * of 'end' is acceptable, and also ignore leading and trailing 
	     * whitespace. (See String trim() method.)
	     * 
	     * Note that any number of generations are possible to implement 
	     * with only the world passed as the parameter and one other 
	     * 2-dimensional array the same size. Create the second world
	     * the same size as the world passed in, by using the length 
	     * attributes rather than using constant values. The world 
	     * passed in will be rectangular and not irregular.
	     * 
	     * @param input  The Scanner object used to read from System.in.
	     * @param world  The initialized world to show as generation 0.
	     */
	    public static void runSimulation(Scanner input, boolean[][] world) {
	    	//generation number
	    	int genNum = 0;
	    	//determines whether the user entered "end"
	    	boolean end = false;
	    	//creates new world of equal size to current world
	    	boolean[][] newWorld = new boolean[world.length][world[0].length];	    	
	    	
	    	//to continue adding generations until user wants to end 
	    	while(!end){
	    		System.out.println("\nGeneration: "+genNum);
				genNum++;
				//world is printed
				printWorld(world);
				//all world array values are set to false
				clearWorld(newWorld);
				//asks the user if they want to keep playing
				System.out.print("Press Enter for next generation, "
						+ "'end' to stop: ");
			
				String endCommand = input.nextLine();
				//leave loop if user enters "end"
				if(endCommand.trim().equals("end")){
					return;
				}
				//user wants to continue playing
				else{ 
					//next generation is created
					nextGeneration(world,newWorld);
					
					//sets current world cell values equal to the old world
					//cell values
					for(int i = 0; i<world.length; i++){
						for(int j = 0; j<world[i].length; j++){
							
							world[i][j] = newWorld[i][j];
						}
					}
				}	
			}
	    }
}
