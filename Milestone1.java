import java.util.Scanner;

public class Milestone1 {
	public static void main(String[] args){
		System.out.println("Welcome to Conway's Game Of Life");
		System.out.println("--------------------------------");
		boolean exit = true;
		Scanner scnr = new Scanner(System.in);
		while(exit){
			System.out.print("1)Glider 2)Beacon 3)Beehive 4)R-pentomino");
			System.out.println("\n5)Random 6)Custom or 7)Exit");
			
			System.out.print("Choose a pattern: ");
			boolean validInput = false;
			int menuOption = 0;
			do{ 
				if(scnr.hasNextInt()){ //checks to see if there is an integer value 
					int userChoice = scnr.nextInt(); //user input is assigned to variable height
					scnr.nextLine();
						if(userChoice<=7 && userChoice>=1){
							validInput = true; //exits loop if height is positive and an integer value	
							menuOption = userChoice;
					}
						else{
							System.out.print("Enter a number between 1 and 7: "); //asks user for new number
					}
				} 
				else{
					scnr.nextLine();
					System.out.print("Enter a number between 1 and 7: "); //asks user for new number
				}
			} while(!validInput);
			if(menuOption == 7){
				break;
			}
		
		boolean end = true;
		int genNum=0;
		while(end){
			
			
			System.out.println("\nGeneration: "+genNum);
			genNum++;
			char[][] world = new char[Config.WORLD_ROWS][Config.WORLD_COLUMNS];
			int aliveCount = 0;
			world[1][2] = Config.ALIVE;
			world[1][3] = Config.ALIVE;
			world[2][1] = Config.ALIVE;
			world[2][4] = Config.ALIVE;
			world[3][2] = Config.ALIVE;
			world[3][3] = Config.ALIVE;
			for(int i=0;i<Config.WORLD_ROWS;i++){
				for(int j=0;j<Config.WORLD_COLUMNS;j++){
						if(world[i][j]!=Config.ALIVE){
							world[i][j] = Config.DEAD;
						}
						else{
							aliveCount++;	
						}
					}
				}
			for(int i =0; i<world.length;i++){
				for(int j=0; j<world[i].length;j++){
					System.out.print(world[i][j]);
				}	
				System.out.println();
			}
			System.out.println(aliveCount + " cells are alive.");
			System.out.print("Press Enter for next generation, 'end' to stop: ");
		
			String endCommand = scnr.nextLine();
			if(endCommand.trim().equals("end")){
				end = false;
				
			}
			else if(endCommand.equals("\n")){
				continue;
			}
			else{
				continue;
			}	
		}
	}
	
		System.out.print("----------------------------\n");	
		System.out.println("End of Conway's Game Of Life");
		
	}
}
