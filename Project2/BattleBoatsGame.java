//written by bade0149
import java.util.Scanner;

public class BattleBoatsGame{

	private static BattleBoatsBoard board = new BattleBoatsBoard();


	// prompts user for an option while all boats havents been sunk. If all boats have been sunk it prints out the final score.
	public static void playGame(){

		while(board.boatsSunk() == false){


			System.out.println("\n" + "Here is the current state of the board.");
			System.out.println(board.showBoard());

			System.out.println("\n" + "What is your next command?" + "\n" + 
								"Options:" + "\n" + 
								"fire: to fire a regular cannon" + "\n" +
								"drone: to scan a specific row or collumn" + "\n" + 
								"missle: to fire a missle at a coordinate" + "\n" +
								"print: to print the full revealed board" + "\n");

			Scanner scanner = new Scanner(System.in);

			String command = scanner.nextLine();

			if (command.equals("print")){
				System.out.println();
				System.out.println(board);
			}

			else if (command.equals("missle")){
				System.out.println();
				board.missle();

			}

			else if (command.equals("drone")){
				System.out.println();
				board.drone();

			}

			else if (command.equals("fire")){
				System.out.println();
				board.fire();
			}
			else{
				System.out.print("Please type a valid option!");
			}

	}
			System.out.println("\n" + "GAME OVER! ALL BOATS HAVE BEEN SUNK!");
			System.out.println("Number of cannon shots:" + board.getCannonShots());
			System.out.println("Number of turns:" + board.getTurns());

	}

	//main driver

	public static void main(String[] args){
		playGame();

}





}