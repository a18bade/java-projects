//written by bade0149
import java.util.Scanner;

public class BattleBoatsBoard{

	private final String[] directions = {"left", "up", "right", "down"};
	private String[][] board;
	private String[] boatNames = {"A","B","C","D","E","F","G","H","I","J"};
	private int[] hitMarkerboats = {5,5,4,4,3,3,3,3,2,2};
	private int totalTurns = 0;
	private int totalCannonShots = 0;
	private int numberOfDrones;
	private int numberOfMissles;
	private String gameSetting;


	// getters
	public int getTurns(){
		return totalTurns;
	}

	public int getCannonShots(){
		return totalCannonShots;
	}


	/* constructor that initializes the number of times powers (missle/drone) can be used based on game setting. Also initilizes the board so 
		that every spot is "empty". */
		
	public BattleBoatsBoard(){

		introduction();

		if (gameSetting == "standard"){
			board = new String[8][8];
			numberOfMissles = 1;
			numberOfDrones = 1;
		}
		if (gameSetting == "expert"){
			board = new String[12][12];
			numberOfDrones = 2;
			numberOfMissles = 2;
		}

		for (int a = 0; a < board.length; a++){
			for(int b = 0; b < board.length; b++){
				board[a][b] = "-";


			}
		}
		placeBoats();
	}

	//introduction to the game that prompts user to select a game mode.
	public void introduction(){

		System.out.println("\nWelcome to BattleBoats! \nPlease enter \"1\" to play in standard mode!");
		System.out.println("Please enter 2 to play in expert mode!");

		
		int choice = 0;
		String input;

		while(choice != 1 && choice != 2){
			Scanner myscanner = new Scanner(System.in);
			input = myscanner.nextLine();
			choice = Integer.parseInt(input);
			if (choice != 1 && choice != 2){
				System.out.println("Please choose a valid option (1 or 2)!");
			}
		}

		if (choice == 1){
			gameSetting = "standard";
		}
		else{
			gameSetting = "expert";
		}

	}
	
	// checks if a given [x,y] coordinate is actually on the board.
	public boolean isValidCoord(int[] coord){

		if (coord[0] <  board.length && coord[1] < board.length){
			if(coord[0] >= 0 && coord[1] >= 0){
				return true;
			}
		}
		return false;

	}

	// generates a valid random [x,y] coordinate on the board.
	public int[] randomCoordGenerator(){

		int[] coord = new int[2];
		coord[0] = (int)((this.board.length) * Math.random());
		coord[1] = (int)((this.board.length) * Math.random());

		return coord;

	}

	// returns the index of a letter in an array.
	public int indexOf(String[] array, String letter){

		int i = 0;

		while(array[i].equals(letter) == false){
			i+=1;
		}

		return i;

	}

	// returns true if all boats are sunk (hence the game is over) or false otherwise.
	public boolean boatsSunk(){
		for(int i = 0; i < hitMarkerboats.length; i++){

			if (hitMarkerboats[i]!= 0){
				return false;
			}

		}
		return true;
	}
	// this will be used to show a hidden version of the board to the player. A board that only shows where they have hit boats or missed boats.
	public String showBoard(){
		String string = "";
		String character = "";

		for(int a = 0; a < board.length; a++){
			for(int b = 0; b < board.length; b++){
				character = board[a][b];
				if (character.equals("-") || character.equals("0") || character.equals("X")){
					string += character + " ";
				}
				else{
					string+= "-" + " ";
				}
			}
			string+="\n";
		}
		return string;
	}

	// checks if space on the board is available for a board and places a boat in that spot
	// returns true if there was space and boat was placed successully and returns false otherwise.
	public boolean placeSingleBoat(int[] coord, String direction, String letter, int boatLength){

		boolean isEmpty = false;
		int temp = boatLength;

		switch (direction){

			case "left":
				{
					while((isValidCoord(coord) == true) && board[coord[0]][coord[1]].equals("-") && (boatLength>0)){
						boatLength-=1;
						coord[1]-= 1;

						if (boatLength == 0){
							isEmpty = true;
						}
					}
					if (isEmpty == true){
						boatLength+= temp;
						coord[1]+= temp;
						int x = 0;

						while(x < boatLength){
							board[coord[0]][coord[1]] = letter;
							coord[1]-= 1;
							x+=1;

						}
					}

				}

				break;

			case "up":
				{
					while((isValidCoord(coord) == true) && board[coord[0]][coord[1]].equals("-") && (boatLength>0)){
						boatLength-=1;
						coord[0]-= 1;

						if (boatLength == 0){
							isEmpty = true;
						}
					}
					if (isEmpty == true){
						boatLength+= temp;
						coord[0]+= temp;
						int x = 0;
					
						while(x < boatLength){
							board[coord[0]][coord[1]] = letter;
							coord[0]-= 1;
							x+=1;
						}
					}
				
				}
					break;				
			case "right":
				{	
					while((isValidCoord(coord) == true) && board[coord[0]][coord[1]].equals("-") && (boatLength>0)){
						boatLength-=1;
						coord[1]+= 1;

						if (boatLength == 0){
							isEmpty = true;
						}
					}

					if (isEmpty == true){
						boatLength+= temp;
						coord[1]-= temp;
						int x = 0;
					
						while(x < boatLength){
							board[coord[0]][coord[1]] = letter;
							coord[1]+= 1;
							x+=1;
						}
					}

				}
				break;
			case "down":
				{
					while((isValidCoord(coord) == true) && board[coord[0]][coord[1]].equals("-") && (boatLength>0)){
						boatLength-=1;
						coord[0]+= 1;

						if (boatLength == 0){
							isEmpty = true;
						}
					}

					if (isEmpty == true){
						boatLength+= temp;
						coord[0]-= temp;

						int x = 0;
					
						while(x < boatLength){
							board[coord[0]][coord[1]] = letter;
							coord[0]+= 1;
							x+=1;
						}
					}
				}
				break;
			}
		return isEmpty;

	}

	//uses placeSingleBoat in a for loop to place an entire fleet of boats.
	public void placeBoats(){

		boolean boatSuccessfullyPlaced;
		for(int x = 0; x < boatNames.length; x++){

			if (gameSetting == "standard"){
				hitMarkerboats[x] = 0;
				x++;
			}

			boatSuccessfullyPlaced = false;

			while(boatSuccessfullyPlaced == false){
				int[] coord = randomCoordGenerator();
				String random_direction = this.directions[(int)(4*Math.random())];
				boatSuccessfullyPlaced = placeSingleBoat(coord,random_direction,boatNames[x],hitMarkerboats[x]);
			}
		}
	}

	/*fires a regular cannon at a potential target, checks whether that target is inbounds or has already has been hit, if it hasn't it marks the (x,y)
 	coordinate with a "0" if the player didn't hit a boat or "X" if a player did hit a boat. it also checks if a boat has been sunk. */
	public void fire(){
		

		System.out.println("Where do you want to fire?");


		boolean onBoard = false;
		String character = "";

		int[] coord = new int[2];
		Scanner scanner = new Scanner(System.in);

		while(onBoard == false){

			System.out.println(showBoard()+"\n");

			System.out.print("Row:");
			coord[0] = scanner.nextInt();
			System.out.print("Column:");
			coord[1] = scanner.nextInt();

			onBoard = isValidCoord(coord);

			totalTurns+= 1;

			if (onBoard == false){
				System.out.println("Penalty: You fired off the board!");
				totalTurns+= 1;
				return;
			}

			else if (onBoard == true){

				character = board[coord[0]][coord[1]];

				if (character.equals("X") || character.equals("0")){
					System.out.println("Penalty: You already fired at this spot!");
					totalTurns+= 1;
				}

				else if (character.equals("-")){
					totalCannonShots+=1;
					board[coord[0]][coord[1]] = "0";
					System.out.println("Miss: You didn't hit a boat!");
				}
				else{
					totalCannonShots+=1;
					int index = indexOf(boatNames,character);
					hitMarkerboats[index]-=1;
					board[coord[0]][coord[1]] = "X";
					if (hitMarkerboats[index] == 0){
						System.out.println("Sunk: You sunk a boat!");
					}
				}
			}


		}
	}

	// checks to see how many targets are in a specific row or column and prints that info out to the user.
	public void drone(){

		Scanner scanner = new Scanner(System.in);
		String command = "";
		int counter = 0;
		int row;
		int collumn;

		totalTurns+= 1;

		if (numberOfDrones > 0){

			numberOfDrones-= 1;

			while(command.equals("r") == false && command.equals("c") == false){
				System.out.println("\n" + "Do you want to scan a row or column? (r for row, c for collumn)");
				command = scanner.nextLine();
				if (command.equals("r") == false && command.equals("c") == false){
					System.out.print("Please type \"r\" or \"c\"!");
				}
			}

			if(command.equals("r")){
				boolean validRow = true;

				do{
					System.out.println("Which row do you want to scan?");
					row = scanner.nextInt();

					while(row < 0 || row >= board.length){
						System.out.println("Invalid Input. Please enter a number within the boundaries of the board!");
						row = scanner.nextInt();
						validRow = false;
					}

					validRow = true;

				} while(validRow == false);

				for (int a = 0; a<board.length; a++){
					String character = board[row][a];
					if (character.equals("-") == false && character.equals("X") == false && character.equals("0") == false){
						counter+= 1;
					}
				}

			System.out.println("Drone has scanned " + counter + " targets.");


			}
			else if(command.equals("c")){

				boolean validCollumn = true;


				do{
					System.out.println("Which column do you want to scan?");
					collumn = scanner.nextInt();

					while(collumn < 0 || collumn >= board.length){
						System.out.println("Invalid Input. Please enter a number within the boundaries of the board!");
						collumn = scanner.nextInt();
						validCollumn = false;
					}

					validCollumn = true;

				} while(validCollumn == false);

				for (int a = 0; a <board.length; a++){
					String character = board[a][collumn];
					if (character.equals("-") == false && character.equals("X") == false && character.equals("0") == false){
						counter+= 1;
					}
				}

			System.out.println("Drone has scanned " + counter + " targets.");


			}

		}

		else{
			System.out.println("You are out of drones!");
		}



	}
	// fires a missle at a given x and y coordinate from the user. 
	public void missle(){

		totalTurns+= 1;

		Scanner scanner = new Scanner(System.in);
		int[] coord = new int[2];

		if (numberOfMissles > 0){

			numberOfMissles-= 1;

			do{
				System.out.println("Please type in a row and column you want to fire the missle at!");
				System.out.println("Row:");
				coord[0] = scanner.nextInt();
				System.out.println("Column:");
				coord[1] = scanner.nextInt();

				if (isValidCoord(coord) == false){
					System.out.println("Please pick a valid row and column!");
				}
			}while(isValidCoord(coord) == false);

			coord[0] -= 1;
			coord[1] -= 1;


			for(int a = 0; a < 3; a++){
				for(int b = 0; b < 3; b++){
					if (isValidCoord(coord) == true){
						if (board[coord[0]][coord[1]].equals("-") == true || board[coord[0]][coord[1]].equals("0") == true){
							board[coord[0]][coord[1]] = "0";
						}
						else if (board[coord[0]][coord[1]].equals("X") == false){
							String character = board[coord[0]][coord[1]];
							int index = indexOf(boatNames,character);
							hitMarkerboats[index]-= 1;;
							board[coord[0]][coord[1]] = "X";
						}
						
					}
					coord[1]+= 1;
				}
				coord[1]-=3;
				coord[0]+= 1;
			}

		}

		else{
			System.out.println("You are out of missles!");
		}
	}

	// this toString will be used for the fully revealed board.
	public String toString(){

		String string = "";

		int x;
		int y;

		for(x = 0; x < board.length; x++){
			for(y = 0; y < board[x].length; y++){

				string += board[x][y] + " ";

			}
			string+= "\n";

		}

		return string;
	}





}